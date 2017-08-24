package com.ijoic.gen_code;

import com.ijoic.gen_code.annotation.NonNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Gen Code.
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
public class GenCode {

  private static List<Template> templateList = new ArrayList<>();

  /**
   * Load template.
   *
   * @param templatePath template path.
   */
  public static void loadTemplate(String templatePath) {
    if (templatePath == null) {
      return;
    }
    InputStream is = null;

    try {
      is = new FileInputStream(templatePath);
      Template template = ParseEngine.loadTemplate(is);

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
    Scanner sc = new Scanner(templateContent);
    String lineContent;
    String indent = FormatUtils.genIndentText(template.getIndent());

    Pattern pattern = Pattern.compile(".*\\{$([a-zA-Z]+)(\\.[a-zA-Z]+)+\\}.*");
    Matcher matcher;

    while (sc.hasNext()) {
      lineContent = sc.nextLine();

      if (lineContent == null || lineContent.isEmpty()) {
        continue;
      }
      // indent.
      printer.printMessage(indent);

      matcher = pattern.matcher(lineContent);
      if (matcher.find()) {
        // find for replacements.

      } else {
        printer.printMessage(lineContent);
      }
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
