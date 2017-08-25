package com.ijoic.gen_code;

import java.util.HashMap;
import java.util.Map;

/**
 * Template.
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
final class Template {

  private int indent;
  private final Map<String, GenRules> rulesMap = new HashMap<>();
  private String templateContent;

  /**
   * Returns indent.
   */
  final int getIndent() {
    return indent;
  }

  /**
   * Set indent.
   *
   * @param indent indent.
   */
  final void setIndent(int indent) {
    this.indent = indent;
  }

  /**
   * Add gen rules.
   *
   * @param rulesName rules name.
   * @param rules rules.
   */
  final void addRules(String rulesName, GenRules rules) {
    if (rulesName == null) {
      return;
    }
    this.rulesMap.put(rulesName, rules);
  }

  /**
   * Returns gen rules.
   *
   * @param rulesName rules name.
   */
  final GenRules getRules(String rulesName) {
    if (rulesName == null) {
      return null;
    }
    return this.rulesMap.get(rulesName);
  }

  /**
   * Returns template content.
   */
  final String getTemplateContent() {
    return this.templateContent;
  }

  /**
   * Set template content.
   *
   * @param content content.
   */
  final void setTemplateContent(String content) {
    this.templateContent = content;
  }

}
