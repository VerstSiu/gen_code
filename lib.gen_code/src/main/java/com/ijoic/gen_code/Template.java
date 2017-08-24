package com.ijoic.gen_code;

import java.util.HashMap;
import java.util.Map;

/**
 * Template.
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
public class Template {

  private int indent;
  private Map<String, GenRules> rulesMap = new HashMap<>();
  private String templateContent;

  /**
   * Returns indent.
   */
  public int getIndent() {
    return indent;
  }

  /**
   * Set indent.
   *
   * @param indent indent.
   */
  public void setIndent(int indent) {
    this.indent = indent;
  }

  /**
   * Add gen rules.
   *
   * @param rulesName rules name.
   * @param rules rules.
   */
  public void addRules(String rulesName, GenRules rules) {
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
  public GenRules getRules(String rulesName) {
    if (rulesName == null) {
      return null;
    }
    return this.rulesMap.get(rulesName);
  }

  /**
   * Returns template content.
   */
  public String getTemplateContent() {
    return this.templateContent;
  }

  /**
   * Set template content.
   *
   * @param content content.
   */
  public void setTemplateContent(String content) {
    this.templateContent = content;
  }

}
