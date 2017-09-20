package com.ijoic.gen_code.io.scanner;

import com.ijoic.gen_code.annotation.NonNull;
import com.ijoic.gen_code.io.GenScanner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * File scanner.
 *
 * @author VerstSiu verstsiu@126.com
 * @date 2017/09/20 17:55
 * @version 1.0
 */
public final class FileScanner implements GenScanner {

  private final String filePath;

  private Scanner scanner;

  /**
   * Constructor.
   *
   * @param filePath file path.
   */
  public FileScanner(@NonNull String filePath) {
    this.filePath = filePath;
  }

  @Override
  public void init() {
    if (scanner != null) {
      return;
    }

    try {
      InputStream input = new FileInputStream(filePath);
      scanner = new Scanner(input);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean hasNext() {
    Scanner scanner = this.scanner;
    return scanner != null && scanner.hasNext();
  }

  @Override
  public String nextLine() {
    Scanner scanner = this.scanner;
    return scanner == null ? null : scanner.nextLine();
  }

  @Override
  public void destroy() {
    Scanner scanner = this.scanner;
    this.scanner = null;

    if (scanner != null) {
      scanner.close();
    }
  }
}
