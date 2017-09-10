package com.ijoic.gen_export.gen_case;

import com.ijoic.gen_export.annotations.ParamPath;
import com.ijoic.gen_export.annotations.TemplatePath;
import com.ijoic.gen_export.utils.ExportUtils;

/**
 * Getter and Setter.
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
@TemplatePath("templates/getter_and_setter")
@ParamPath("params/getter_and_setter_params")
public class GetterAndSetter {

  public static void main(String[] args) {
    ExportUtils.execSimple(GetterAndSetter.class);
  }
}
