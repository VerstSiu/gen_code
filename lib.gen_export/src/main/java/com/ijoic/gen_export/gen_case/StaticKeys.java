package com.ijoic.gen_export.gen_case;

import com.ijoic.gen_export.annotations.ParamPath;
import com.ijoic.gen_export.annotations.TemplatePath;
import com.ijoic.gen_export.utils.ExportUtils;

/**
 * Static keys.
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
@TemplatePath("templates/static_keys")
@ParamPath("params/static_keys")
public class StaticKeys {

  public static void main(String[] args) {
    ExportUtils.execSimple(StaticKeys.class);
  }
}
