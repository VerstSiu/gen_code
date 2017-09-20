package com.ijoic.gen_code.rules;

import com.ijoic.gen_code.GenRules;

/**
 * Upper Case.
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
public final class FirstLetterUpperCase implements GenRules {
  @Override
  public final String[] formatTextContent(String[] params) {
    if (params != null && params.length > 0) {
      String firstWord = params[0];

      if (firstWord != null && firstWord.length() > 0) {
        String firstLetter = firstWord.substring(0, 1).toUpperCase();

        if (firstWord.length() == 1) {
          params[0] = firstLetter;
        } else {
          params[0] = firstLetter + firstWord.substring(1);
        }
      }
    }
    return params;
  }
}
