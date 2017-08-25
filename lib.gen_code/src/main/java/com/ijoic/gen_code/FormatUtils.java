package com.ijoic.gen_code;

import com.ijoic.gen_code.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

/**
 * Format utils.
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
public final class FormatUtils {

  /**
   * Returns new value if new value is not a empty text, old value otherwise.
   *
   * @param oldValue old value.
   * @param newValue new value.
   */
  static String replaceIfNotEmpty(String oldValue, String newValue) {
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
  static<T> T valueAtList(List<T> listItems, int index) {
    if (listItems == null || index < 0 || index >= listItems.size()) {
      return null;
    }
    return listItems.get(index);
  }

  /**
   * Returns value at specific array index.
   *
   * @param array array.
   * @param index index.
   */
  static<T> T valueAtArray(T[] array, int index) {
    if (array == null || index < 0 || index >= array.length) {
      return null;
    }
    return array[index];
  }

  /**
   * Returns array copy.
   *
   * @param src source array.
   *
   * @param <T> item type.
   */
  static<T> T[] copyArray(T[] src) {
    if (src == null) {
      return null;
    }
    return Arrays.copyOf(src, src.length);
  }

  /**
   * Returns indent text.
   *
   * @param indent indent.
   */
  @NonNull
  static String genIndentText(int indent) {
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

  /**
   * Returns text format by [first letter upper].
   *
   * @param text text.
   */
  public static String upperFirstLetter(String text) {
    if (text != null && !text.isEmpty() && text.length() > 0) {
      if (text.length() == 1) {
        return text.substring(0, 1).toUpperCase();
      }
      return text.substring(0, 1).toUpperCase() + text.substring(1);
    }
    return text;
  }

  private FormatUtils() {}
}
