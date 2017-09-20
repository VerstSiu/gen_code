package com.ijoic.gen_code.io.printer;

import com.ijoic.gen_code.io.GenPrinter;

/**
 * System printer.
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
public final class SystemPrinter implements GenPrinter {

  @Override
  public void init() {
    // do nothing.
  }

  @Override
  public final void printMessage(String message) {
    System.out.print(message);
  }

  @Override
  public void destroy() {
    // do nothing.
  }
}
