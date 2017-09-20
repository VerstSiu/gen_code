package com.ijoic.gen_code.io.printer;

import com.ijoic.gen_code.annotation.NonNull;
import com.ijoic.gen_code.io.GenPrinter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * File printer.
 *
 * @author VerstSiu verstsiu@126.com
 * @date 2017/09/20 17:55
 * @version 1.0
 */
public final class FilePrinter implements GenPrinter {

  private final String filePath;

  private OutputStream output;
  private boolean writeError;

  /**
   * Constructor.
   *
   * @param filePath file path.
   */
  public FilePrinter(@NonNull String filePath) {
    this.filePath = filePath;
  }

  @Override
  public void init() {
    // If last time write error
    // close current output and recreate one.
    if (writeError) {
      closeOutput();
      writeError = false;
      this.output = null;
    }

    // generate output
    OutputStream output = this.output;

    if (output == null) {
      try {
        output = new FileOutputStream(filePath);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }

      this.output = output;
    }
  }

  @Override
  public void printMessage(String message) {
    OutputStream output = this.output;

    if (output != null && !writeError) {
      try {
        output.write(message.getBytes());
      } catch (IOException e) {
        e.printStackTrace();
        writeError = true;
      }
    }
  }

  @Override
  public void destroy() {
    closeOutput();
  }

  private void closeOutput() {
    OutputStream output = this.output;
    this.output = null;

    if (output != null) {
      try {
        output.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
