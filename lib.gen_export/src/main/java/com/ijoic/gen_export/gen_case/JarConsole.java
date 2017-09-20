package com.ijoic.gen_export.gen_case;

import com.ijoic.gen_export.annotations.ParamPath;
import com.ijoic.gen_export.annotations.TemplatePath;
import com.ijoic.gen_export.utils.ExportUtils;

/**
 * Jar console.
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
@TemplatePath("templates/jar_console")
@ParamPath("params/jar_console")
public class JarConsole {

  public static void main(String[] args) {
    ExportUtils.execSimple(JarConsole.class);
  }
}
