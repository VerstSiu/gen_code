package com.ijoic.gen_code;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Gen Code.
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
public class ParseEngine {

  private static final String COMMAND_RULE = "R";
  private static final String COMMAND_INDENT = "I";

  private static final String COMMAND_TEMPLATE_START = "//";

  private static final String COMMAND_PARAM_WORD_SEPARATOR = "S";
  private static final String COMMAND_PARAM_PLACE_HOLDER = "E";

  private static final String COMMAND_PARAM_START = "//";

  private static final String PARAM_SEGMENT_SEPARATOR = " ";
  private static final String DEFAULT_WORD_SEPARATOR = "_";
  private static final String DEFAULT_PLACE_HOLDER = "#";

  /**
   * Returns loaded template.
   *
   * @param is input stream.
   */
  public static Template loadTemplate(InputStream is) {
    if (is == null) {
      return null;
    }
    Template template = new Template();

    Scanner sc = new Scanner(is);
    String lineContent;
    StringBuilder templateBuilder = new StringBuilder();
    int lineCount = 0;

    boolean templateStart = false;
    String[] runParams;

    while (sc.hasNext()) {
      lineContent = sc.nextLine();

      if (!templateStart) {
        if (lineContent == null || lineContent.isEmpty()) {
          continue;
        }
        runParams = lineContent.split(" ");

        if (runParams.length == 0) {
          continue;
        }
        if (COMMAND_RULE.equals(runParams[0])) {
          // load rules.
          if (runParams.length >= 3) {
            loadRules(template, runParams[1], runParams[2]);
          } else if (runParams.length >= 2) {
            loadRules(template, runParams[1], null);
          }

        } else if (COMMAND_INDENT.equals(runParams[0])) {
          // load indent.
          if (runParams.length >= 2) {
            try {
              template.setIndent(Integer.parseInt(runParams[1]));
            } catch (NumberFormatException e) {
              e.printStackTrace();
            }
          }

        } else if (COMMAND_TEMPLATE_START.equals(runParams[0])) {
          templateStart = true;
        }

      } else {
        // append template content.
        if (lineCount > 0) {
          templateBuilder.append("\n");
        }

        templateBuilder.append(lineContent);
        ++lineCount;
      }
    }

    if (lineCount > 0) {
      template.setTemplateContent(templateBuilder.toString());
      return template;
    }
    return null;
  }

  /**
   * Load rules.
   *
   * @param template template.
   * @param ruleName rules name.
   * @param ruleClazz rules class.
   */
  private static void loadRules(Template template, String ruleName, String ruleClazz) {
    if (template == null) {
      return;
    }
    template.addRules(ruleName, RulesFactory.getGenRulesInstance(ruleName, ruleClazz));
  }

  /**
   * Returns loaded params.
   *
   * @param is input stream.
   */
  public static GenParams loadParams(InputStream is) {
    if (is == null) {
      return null;
    }
    GenParams params = new GenParams();

    Scanner sc = new Scanner(is);
    String lineContent;

    boolean paramStart = false;
    String[] runParams;
    String paramValues[];
    boolean multiFlags[];
    int segmentSize;

    String wordSeparator = DEFAULT_WORD_SEPARATOR;
    String placeHolder = DEFAULT_PLACE_HOLDER;

    while (sc.hasNext()) {
      lineContent = sc.nextLine();

      if (!paramStart) {
        if (lineContent == null || lineContent.isEmpty()) {
          continue;
        }
        runParams = lineContent.split(" ");

        if (runParams.length == 0) {
          continue;
        }
        if (COMMAND_PARAM_WORD_SEPARATOR.equals(runParams[0])) {
          // load rules.
          if (runParams.length >= 2) {
            wordSeparator = FormatUtils.replaceIfNotEmpty(wordSeparator, runParams[1]);
          }

        } else if (COMMAND_PARAM_PLACE_HOLDER.equals(runParams[0])) {
          // load indent.
          if (runParams.length >= 2) {
            placeHolder = FormatUtils.replaceIfNotEmpty(placeHolder, runParams[1]);
          }

        } else if (COMMAND_PARAM_START.equals(runParams[0])) {
          // param start.
          paramStart = true;

        } else {
          // key map.
          if (runParams.length >= 2) {
            try {
              int paramIndex = Integer.valueOf(runParams[0]);
              params.setParamKey(paramIndex, runParams[1]);

            } catch (NumberFormatException e) {
              e.printStackTrace();
            }
          }
        }

      } else {
        // append template content.
        paramValues = lineContent.split(PARAM_SEGMENT_SEPARATOR);
        segmentSize = paramValues.length;

        if (segmentSize > 0) {
          multiFlags = new boolean[segmentSize];

          for (int i = 0; i < segmentSize; ++i) {
            multiFlags[i] = FormatUtils.containsText(paramValues[i], wordSeparator);
          }
          params.addParamValues(paramValues, multiFlags);
        }
      }
    }

    if (params.size() > 0) {
      params.setWordSeparator(wordSeparator);
      return params;
    }
    return null;
  }

  private ParseEngine() {}

}
