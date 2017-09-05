package com.ijoic.gen_code.rules;

import com.ijoic.gen_code.FormatUtils;
import com.ijoic.gen_code.GenRules;

/**
 * Upper Case.
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
public final class UpperCase implements GenRules {
  @Override
  public final String[] formatTextContent(String[] params) {
    if (params != null) {
      for (int i = 0, size = params.length; i < size; ++i) {
        params[i] = FormatUtils.upperCase(params[i]);
      }
    }
    return params;
  }
}
