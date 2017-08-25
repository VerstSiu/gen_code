package com.ijoic.gen_export;

import com.ijoic.gen_code.GenCode;
import com.ijoic.gen_code.printer.SystemPrinter;

/**
 * Gen Export.
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
public class GenExport {

  public static void main(String[] args) {
    final String FILE_ROOT = "lib.gen_export/src/main/";

    GenCode.loadTemplate(FILE_ROOT + "templates/getter_and_setter");
    GenCode.execTemplate(FILE_ROOT + "params/getter_and_setter_params", new SystemPrinter());
  }

}
