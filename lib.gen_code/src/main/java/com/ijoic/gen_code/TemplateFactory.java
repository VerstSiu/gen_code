package com.ijoic.gen_code;

import java.util.HashMap;
import java.util.Map;

/**
 * Template factory.
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
final class TemplateFactory {

  private static final Map<String, Template> templateMap = new HashMap<>();

  /**
   * Returns template for specific path.
   *
   * @param path path.
   */
  static Template getTemplate(String path) {
    if (path == null) {
      return null;
    }
    return templateMap.get(path);
  }

  /**
   * Add template.
   *
   * @param path path.
   * @param template template.
   */
  static void addTemplate(String path, Template template) {
    if (path == null) {
      return;
    }
    templateMap.put(path, template);
  }

  /**
   * Release all memory resources.
   */
  static void releaseAll() {
    templateMap.clear();
  }

  private TemplateFactory() {}
}
