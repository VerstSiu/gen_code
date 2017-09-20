package com.ijoic.gen_code;

import com.ijoic.gen_code.annotation.NonNull;
import com.ijoic.gen_code.annotation.Nullable;
import com.ijoic.gen_code.io.GenPrinter;
import com.ijoic.gen_code.io.GenScanner;
import com.ijoic.gen_code.io.printer.FilePrinter;
import com.ijoic.gen_code.io.printer.SystemPrinter;
import com.ijoic.gen_code.io.scanner.FileScanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Gen Code.
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
public final class GenCode {

  private static final List<Template> templateList = new ArrayList<>();

  /**
   * Main entrance.
   *
   * <p>Argument list; template_path, param_path, output_path</p>
   *
   * @param args arguments.
   */
  public static void main(String[] args) {
    int argumentSize = args.length;

    if (argumentSize >= 2) {
      String templatePath = args[0];
      String paramPath = args[1];

      if (argumentSize == 2) {
        loadTemplate(templatePath);
        execTemplate(paramPath, new SystemPrinter());

      } else {
        String outputPath = args[2];

        loadTemplate(templatePath);
        execTemplate(paramPath, new FilePrinter(outputPath));
      }
    }
  }

  /**
   * Load template.
   *
   * @param templatePath template path.
   */
  public static void loadTemplate(String templatePath) {
    if (templatePath == null) {
      return;
    }

    // load template from cache.
    Template template = TemplateFactory.getTemplate(templatePath);

    if (template != null) {
      templateList.add(template);
      return;
    }

    // load template from file.
    template = ParseEngine.loadTemplate(new FileScanner(templatePath));

    if (template != null) {
      templateList.add(template);
      TemplateFactory.addTemplate(templatePath, template);
    }
  }

  /**
   * Exec template.
   *
   * @param paramPath param path.
   */
  public static void execTemplate(String paramPath, GenPrinter printer) {
    GenParams genParams;

    if (printer == null || (genParams = loadGenParams(paramPath)) == null) {
      return;
    }
    printer.init();
    int i = 0;
    int templateSize = templateList.size();

    for (Template template : templateList) {
      if (template == null) {
        continue;
      }
      execTemplate(template, genParams, printer, i, templateSize);
      ++i;
    }
    printer.destroy();
  }

  private static void execTemplate(
      @NonNull Template template,
      @NonNull GenParams params,
      @NonNull GenPrinter printer,
      int templateIndex,
      int templateSize) {

    String templateContent = template.getTemplateContent();
    boolean isFirstTemplate = templateIndex == 0;
    boolean isLastTemplate = templateIndex == templateSize - 1;

    if (templateContent == null) {
      return;
    }
    Scanner sc;
    String lineContent;
    String indent = FormatUtils.repeatText(" ", template.getIndent());
    String blankLine = FormatUtils.repeatText("\n", params.getBlankLine());

    Pattern pattern = Pattern.compile("\\{\\$(([a-zA-Z0-9_]+)(\\.[a-zA-Z0-9_]+)*)\\}");
    Matcher matcher;
    int textStart;
    int textEnd;

    String segmentsText;
    String replaceText;
    String[] segments;
    int segmentSize;

    Map<String, String> replaceMap = new HashMap<>();
    String paramKey;
    String ruleText;
    GenRules genRules;

    String[] paramValue;
    int valueIndex = 0;
    int valueSize = params.size();
    StringBuilder sb = new StringBuilder();

    // !!print extra blank line for first template.
    if (isFirstTemplate) {
      printer.printMessage("\n\n");
    }

    boolean isLastValue;

    while (valueIndex < valueSize) {
      isLastValue = valueIndex == valueSize - 1;
      sc = new Scanner(templateContent);
      replaceMap.clear();

      while (sc.hasNext()) {
        lineContent = sc.nextLine();

        if (lineContent == null) {
          continue;
        }
        // append indent.
        printer.printMessage(indent);
        matcher = pattern.matcher(lineContent);
        textStart = 0;

        while (matcher.find()) {
          // append text start.
          textEnd = matcher.start();

          if (textStart < textEnd) {
            printer.printMessage(lineContent.substring(textStart, textEnd));
          }

          // append replace text.
          segmentsText = matcher.group(1);

          if (segmentsText != null && !segmentsText.isEmpty()) {
            replaceText = replaceMap.get(segmentsText);

            if (replaceText == null) {
              segments = segmentsText.split("\\.");
              segmentSize = segments.length;
              replaceText = "";

              if (segmentSize >= 1 && (paramKey = segments[0]) != null && !paramKey.isEmpty()) {
                paramValue = FormatUtils.copyArray(params.getParamValue(valueIndex, params.getKeyIndex(paramKey)));

                // perform rules replace.
                for (int i = 0; i < segmentSize; ++i) {
                  ruleText = segments[i];

                  if (ruleText == null || ruleText.isEmpty()) {
                    continue;
                  }
                  genRules = template.getRules(ruleText);

                  if (genRules != null) {
                    paramValue = genRules.formatTextContent(paramValue);
                  }
                }

                // generate replace text.
                if (paramValue != null && paramValue.length > 0) {
                  sb.delete(0, sb.length());

                  for (String valueItem : paramValue) {
                    sb.append(valueItem);
                  }
                  replaceText = sb.toString();
                }
              }
              replaceMap.put(segmentsText, replaceText);
            }

            // print replace text.
            if (!replaceText.isEmpty()) {
              printer.printMessage(replaceText);
            }
          }

          // update text position.
          textStart = matcher.end();
        }

        // print last text content.
        if (textStart < lineContent.length()) {
          printer.printMessage(lineContent.substring(textStart, lineContent.length()));
        }

        // append line end(new line).
        printer.printMessage("\n");
      }

      // append block end(blank line).
      // !!skip extra blank line for last template.
      if (!isLastTemplate || !isLastValue) {
        printer.printMessage(blankLine);
      }

      // update value index.
      ++valueIndex;
    }
  }

  @Nullable
  private static GenParams loadGenParams(String paramPath) {
    if (paramPath == null) {
      return null;
    }
    GenScanner scanner = new FileScanner(paramPath);
    return ParseEngine.loadParams(scanner);
  }

  /**
   * Reset template list.
   */
  public static void resetTemplateList() {
    templateList.clear();
  }

  /**
   * Release all memory resources.
   */
  public static void releaseAll() {
    RulesFactory.releaseAll();;
    TemplateFactory.releaseAll();
  }

}
