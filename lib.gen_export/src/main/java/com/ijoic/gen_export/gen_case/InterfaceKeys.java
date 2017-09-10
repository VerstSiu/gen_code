package com.ijoic.gen_export.gen_case;

import com.ijoic.gen_export.annotations.ParamPath;
import com.ijoic.gen_export.annotations.TemplatePath;
import com.ijoic.gen_export.utils.ExportUtils;

/**
 * Interface keys.
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
@TemplatePath("templates/interface_keys")
@ParamPath("params/interface_keys")
public class InterfaceKeys {

  public static void main(String[] args) {
    ExportUtils.execSimple(InterfaceKeys.class);
  }
}
