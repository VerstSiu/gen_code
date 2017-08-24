package com.ijoic.gen_code;

import java.util.HashMap;
import java.util.Map;

/**
 * Template factory.
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
public class TemplateFactory {

  private static Map<String, Template> templateMap = new HashMap<>();

  /**
   * Add template.
   *
   * @param path path.
   * @param template template.
   */
  public static void addTemplate(String path, Template template) {
    if (path == null) {
      return;
    }
    templateMap.put(path, template);
  }

  /**
   * Release all memory resources.
   */
  public static void releaseAll() {
    templateMap.clear();
  }

  private TemplateFactory() {}
}
