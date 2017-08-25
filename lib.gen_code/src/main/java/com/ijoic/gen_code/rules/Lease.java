package com.ijoic.gen_code.rules;

import com.ijoic.gen_code.GenRules;

/**
 * Lease rules.
 *
 * <p>Append extra space after latter words.</p>
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
public final class Lease implements GenRules {
  @Override
  public final String[] formatTextContent(String[] params) {
    if (params != null) {
      String param;

      for (int i = 1, size = params.length; i < size; ++i) {
        param = params[i];

        if (param != null && !param.isEmpty()) {
          params[i] = " " + param;
        }
      }
    }
    return params;
  }
}
