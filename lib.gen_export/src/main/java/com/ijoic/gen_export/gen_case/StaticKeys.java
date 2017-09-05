package com.ijoic.gen_export.gen_case;

import com.ijoic.gen_code.GenCode;
import com.ijoic.gen_code.printer.SystemPrinter;

/**
 * Static keys.
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
public class StaticKeys {

  public static void main(String[] args) {
    final String FILE_ROOT = "lib.gen_export/src/main/";

    GenCode.loadTemplate(FILE_ROOT + "templates/static_keys");
    GenCode.execTemplate(FILE_ROOT + "params/static_keys", new SystemPrinter());
  }
}
