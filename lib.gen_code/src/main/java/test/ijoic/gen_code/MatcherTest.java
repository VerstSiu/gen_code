package test.ijoic.gen_code;

import com.ijoic.gen_code.annotation.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Mather test.
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
public class MatcherTest {
  /**
   * Main.
   *
   * @param args arguments.
   */
  public static void main(String[] args) {
//    testMatcher("**");
    testMatcher("** * {$name.lower.upper} * *");
//    testMatcher("** * {$name.lower.upper} * ** {$age.atLarge.atLower");
  }

  private static void testMatcher(@NonNull String input) {
    Pattern pattern = Pattern.compile("\\{\\$([a-zA-Z]+)(\\.[a-zA-Z]+)*\\}");
    Matcher matcher;

    matcher = pattern.matcher(input);
    if (matcher.find()) {
      // find for replacements.

    }
  }
}
