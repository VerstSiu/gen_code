package com.ijoic.gen_code;

import com.ijoic.gen_code.annotation.NonNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
   * Load template.
   *
   * @param templatePath template path.
   */
  public static void loadTemplate(String templatePath) {
    if (templatePath == null) {
      return;
    }
    Template template = TemplateFactory.getTemplate(templatePath);

    if (template != null) {
      templateList.add(template);
      return;
    }
    InputStream is = null;

    try {
      is = new FileInputStream(templatePath);
      template = ParseEngine.loadTemplate(is);

      if (template != null) {
        templateList.add(template);
        TemplateFactory.addTemplate(templatePath, template);
      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    if (is != null) {
      try {
        is.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Exec template.
   *
   * @param paramPath param path.
   */
  public static void execTemplate(String paramPath, ExportPrinter printer) {
    GenParams genParams;

    if (printer == null || (genParams = loadGenParams(paramPath)) == null) {
      return;
    }
    for (Template template : templateList) {
      if (template == null) {
        continue;
      }
      execTemplate(template, genParams, printer);
    }
  }

  private static void execTemplate(@NonNull Template template, @NonNull GenParams params, @NonNull ExportPrinter printer) {
    String templateContent = template.getTemplateContent();

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

    while (valueIndex < valueSize) {
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
      printer.printMessage(blankLine);

      // update value index.
      ++valueIndex;
    }
  }

  private static GenParams loadGenParams(String paramPath) {
    if (paramPath == null) {
      return null;
    }
    InputStream is = null;
    GenParams genParams = null;

    try {
      is = new FileInputStream(paramPath);
      genParams = ParseEngine.loadParams(is);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    if (is != null) {
      try {
        is.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return genParams;
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
