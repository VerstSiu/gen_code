package com.ijoic.gen_code.printer;

import com.ijoic.gen_code.ExportPrinter;

/**
 * System export printer.
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
public final class SystemPrinter implements ExportPrinter {
  @Override
  public final void printMessage(String message) {
    System.out.print(message);
  }
}
