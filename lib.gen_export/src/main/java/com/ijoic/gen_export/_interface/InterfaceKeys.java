package com.ijoic.gen_export._interface;

import com.ijoic.gen_code.GenCode;
import com.ijoic.gen_code.printer.SystemPrinter;

/**
 * Static keys.
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
public class InterfaceKeys {

  public static void main(String[] args) {
    final String FILE_ROOT = "lib.gen_export/src/main/";

    GenCode.loadTemplate(FILE_ROOT + "templates/interface_keys");
    GenCode.execTemplate(FILE_ROOT + "params/interface_keys", new SystemPrinter());
  }
}
