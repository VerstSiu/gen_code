package com.ijoic.gen_code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Gen Params.
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
final class GenParams {

  private static final int DEFAULT_BLANK_LINE = 1;

  private final List<String[][]> valuesList = new ArrayList<>();
  private final Map<String, Integer> keyIndexMap = new HashMap<>();

  private int blankLine = DEFAULT_BLANK_LINE;

  /**
   * Returns key index, -1 if not exist.
   *
   * @param key key.
   */
  final int getKeyIndex(String key) {
    if (key == null) {
      return -1;
    }
    Integer index = keyIndexMap.get(key);

    return index == null ? -1 : index;
  }

  /**
   * Set param key at specific index.
   *
   * @param index index.
   * @param key param key.
   */
  final void setParamKey(int index, String key) {
    if (key != null) {
      keyIndexMap.put(key, index);
    }
  }

  /**
   * Add param values.
   *
   * @param values values.
   */
  final void addParamValues(String[][] values) {
    valuesList.add(values);
  }

  /**
   * Returns param values at specific index.
   *
   * @param index index.
   */
  private String[][] getParamValues(int index) {
    return FormatUtils.valueAtList(valuesList, index);
  }

  /**
   * Returns param value at specific value index & key index.
   *
   * @param index value index.
   * @param keyIndex key index.
   */
  final String[] getParamValue(int index, int keyIndex) {
    return FormatUtils.valueAtArray(getParamValues(index), keyIndex);
  }

  /**
   * Returns values size.
   */
  public final int size() {
    return valuesList.size();
  }

  /**
   * Returns blank line.
   */
  public int getBlankLine() {
    return blankLine;
  }

  /**
   * Set blank line.
   *
   * @param blankLine blank line.
   */
  public void setBlankLine(int blankLine) {
    this.blankLine = blankLine;
  }

}
