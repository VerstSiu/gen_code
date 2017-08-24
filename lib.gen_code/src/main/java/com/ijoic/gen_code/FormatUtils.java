package com.ijoic.gen_code;

import com.ijoic.gen_code.annotation.NonNull;

import java.util.List;

/**
 * Format utils.
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
public class FormatUtils {

  /**
   * Returns new value if new value is not a empty text, old value otherwise.
   *
   * @param oldValue old value.
   * @param newValue new value.
   */
  public static String replaceIfNotEmpty(String oldValue, String newValue) {
    if (newValue != null && !newValue.isEmpty()) {
      return newValue;
    }
    return oldValue;
  }

  /**
   * Returns list item value at specific index.
   *
   * @param listItems list items.
   *
   * @param <T> param type.
   */
  public static<T> T valueAtList(List<T> listItems, int index) {
    if (listItems == null || index < 0 || index >= listItems.size()) {
      return null;
    }
    return listItems.get(index);
  }

  /**
   * Returns sub text contains status.
   *
   * @param src source.
   * @param text sub text.
   */
  public static boolean containsText(String src, CharSequence text) {
    return src != null && src.contains(text);
  }

  /**
   * Returns indent text.
   *
   * @param indent indent.
   */
  @NonNull
  public static String genIndentText(int indent) {
    if (indent <= 0) {
      return "";
    }
    StringBuilder sb = new StringBuilder();

    while (indent > 0) {
      sb.append(" ");
      --indent;
    }
    return sb.toString();
  }

  private FormatUtils() {}
}
