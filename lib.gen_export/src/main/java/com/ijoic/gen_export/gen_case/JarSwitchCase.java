package com.ijoic.gen_export.gen_case;

import com.ijoic.gen_export.annotations.ParamPath;
import com.ijoic.gen_export.annotations.TemplatePath;
import com.ijoic.gen_export.utils.ExportUtils;

/**
 * Activity state methods.
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
@TemplatePath("templates/jar_switch_case")
@ParamPath("params/interface_keys")
public class JarSwitchCase {

  public static void main(String[] args) {
    ExportUtils.execSimple(JarSwitchCase.class);
  }
}
