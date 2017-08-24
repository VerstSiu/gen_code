package com.ijoic.gen_code;

import java.util.ArrayList;
import java.util.List;

/**
 * Gen Params.
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
public class GenParams {

  private List<String> keyList = new ArrayList<>();
  private String wordSeparator;
  private List<String[]> valuesList = new ArrayList<>();
  private List<boolean[]> multiFlagList = new ArrayList<>();

  /**
   * Returns param key at specific index.
   *
   * @param index index.
   */
  public String getParamKey(int index) {
    if (index < 0 || index >= keyList.size()) {
      return null;
    }
    return keyList.get(index);
  }

  /**
   * Set param key at specific index.
   *
   * @param index index.
   * @param key param key.
   */
  public void setParamKey(int index, String key) {
    keyList.set(index, key);
  }

  /**
   * Returns word separator.
   */
  public String getWordSeparator() {
    return wordSeparator;
  }

  /**
   * Set word separator.
   *
   * @param wordSeparator word separator.
   */
  public void setWordSeparator(String wordSeparator) {
    this.wordSeparator = wordSeparator;
  }

  /**
   * Add param values.
   *
   * @param values values.
   * @param multiFlags multi flags.
   */
  public void addParamValues(String[] values, boolean[] multiFlags) {
    valuesList.add(values);
    multiFlagList.add(multiFlags);
  }

  /**
   * Returns param values at specific index.
   *
   * @param index index.
   */
  public String[] getParamValues(int index) {
    return FormatUtils.valueAtList(valuesList, index);
  }

  /**
   * Returns multi flags at specific index.
   *
   * @param index index.
   */
  public boolean[] getMultiFlags(int index) {
    return FormatUtils.valueAtList(multiFlagList, index);
  }

  /**
   * Returns values size.
   */
  public int size() {
    return valuesList.size();
  }

}
