package com.ijoic.gen_code;

import com.ijoic.gen_code.annotation.NonNull;
import com.ijoic.gen_code.rules.UpperCamelCase;
import com.ijoic.gen_code.rules.FirstLetterUpperCase;
import com.ijoic.gen_code.rules.Lease;
import com.ijoic.gen_code.rules.LowerCamelCase;
import com.ijoic.gen_code.rules.LowerCase;
import com.ijoic.gen_code.rules.Underline;
import com.ijoic.gen_code.rules.UpperCase;

import java.util.HashMap;
import java.util.Map;

/**
 * Rules factory.
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
final class RulesFactory {

  private static final Map<String, GenRules> nameMap = new HashMap<>();
  private static final Map<String, GenRules> clazzMap = new HashMap<>();

  static {
    addDefaultGenRules("upper_camel_case", UpperCamelCase.class);
    addDefaultGenRules("lower_camel_case", LowerCamelCase.class);
    addDefaultGenRules("upper_case", UpperCase.class);
    addDefaultGenRules("lower_case", LowerCase.class);
    addDefaultGenRules("underline", Underline.class);
    addDefaultGenRules("lease", Lease.class);
    addDefaultGenRules("first_letter_upper_case", FirstLetterUpperCase.class);
  }

  /**
   * Returns rules instance.
   *
   * <p>Returns rule instance for specified class name, if class name exist. Default rule by specified rules name as other.</p>
   *
   * @param rulesName rules name.
   * @param className rules class.
   */
  static GenRules getGenRulesInstance(String rulesName, String className) {
    GenRules ruleInstance;

    if (className != null) {
      // case for class name specified.
      ruleInstance = clazzMap.get(className);

      if (ruleInstance == null) {
        try {
          ruleInstance = (GenRules) Class.forName(className).newInstance();
          clazzMap.put(className, ruleInstance);

        } catch (Exception e) {
          e.printStackTrace();
        }
      }

    } else {
      ruleInstance = getDefaultGenRules(rulesName);
    }
    return ruleInstance;
  }

  /**
   * Returns default gen rules.
   *
   * @param rulesName rules name.
   */
  private static GenRules getDefaultGenRules(String rulesName) {
    return nameMap.get(rulesName);
  }

  /**
   * Add default gen rules.
   *
   * @param rulesName rules name.
   * @param rulesClazz rules clazz.
   */
  private static void addDefaultGenRules(@NonNull String rulesName, @NonNull Class<? extends GenRules> rulesClazz) {
    GenRules ruleInstance;

    try {
      ruleInstance = rulesClazz.newInstance();
      clazzMap.put(rulesClazz.getCanonicalName(), ruleInstance);
      nameMap.put(rulesName, ruleInstance);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Release all memory resources.
   */
  static void releaseAll() {
    clazzMap.clear();
  }

  private RulesFactory() {}
}
