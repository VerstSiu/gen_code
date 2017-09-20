package com.ijoic.gen_code.io;

/**
 * Generate scanner.
 *
 * @author VerstSiu verstsiu@126.com
 * @date 2017/09/20 18:07
 * @version 1.0
 */
public interface GenScanner extends StateCompat {
  /**
   * Returns content empty status.
   */
  boolean hasNext();

  /**
   * Returns next line content.
   */
  String nextLine();
}
