package com.ijoic.gen_code.io;

/**
 * State compat.
 *
 * <p>Offers init and destroy support.</p>
 *
 * @author VerstSiu verstsiu@126.com
 * @date 2017/09/20 18:07
 * @version 1.0
 */
public interface StateCompat {
  /**
   * Prepare input.
   */
  void init();

  /**
   * Prepare input.
   */
  void destroy();
}
