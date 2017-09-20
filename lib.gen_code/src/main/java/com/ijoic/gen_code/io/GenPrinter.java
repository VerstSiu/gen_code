package com.ijoic.gen_code.io;

import com.ijoic.gen_code.annotation.Nullable;

/**
 * Generate printer.
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
public interface GenPrinter extends StateCompat {
  /**
   * Print message.
   *
   * @param message message.
   */
  @Nullable
  void printMessage(String message);
}
