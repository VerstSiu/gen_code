package com.ijoic.gen_code;

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
   * Release all memory resources.
   */
  static void releaseAll() {
    clazzMap.clear();
  }

  private RulesFactory() {}
}
