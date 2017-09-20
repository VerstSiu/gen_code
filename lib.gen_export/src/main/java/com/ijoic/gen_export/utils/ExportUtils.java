package com.ijoic.gen_export.utils;

import com.ijoic.gen_code.GenCode;
import com.ijoic.gen_code.annotation.NonNull;
import com.ijoic.gen_code.annotation.Nullable;
import com.ijoic.gen_code.io.printer.SystemPrinter;
import com.ijoic.gen_export.annotations.ParamPath;
import com.ijoic.gen_export.annotations.TemplatePath;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Export utils.
 *
 * @author VerstSiu verstsiu@126.com
 * @version 1.0
 */
public class ExportUtils {

  /**
   * Execute gen export simple.
   *
   * <p>Read from template file, param file and export directly to system console.</p>
   * <p>Use {@link TemplatePath} and {@link ParamPath} to config execute params.</p>
   *
   * @param execClazz execute class.
   *
   * @see TemplatePath
   * @see ParamPath
   */
  public static void execSimple(@NonNull Class<?> execClazz) {
    String templatePath = readStringValue(execClazz, TemplatePath.class);
    String paramPath = readStringValue(execClazz, ParamPath.class);

    if (templatePath != null && paramPath != null) {
      execSimple(templatePath, paramPath);
    }
  }

  private static void execSimple(@NonNull String templatePath, @NonNull String paramPath) {
    final String FILE_ROOT = "lib.gen_export/src/main/";

    GenCode.loadTemplate(FILE_ROOT + templatePath);
    GenCode.execTemplate(FILE_ROOT + paramPath, new SystemPrinter());
  }

  @Nullable
  private static<T extends Annotation> String readStringValue(@NonNull Class<?> execClazz, @NonNull Class<T> annClazz) {
    T annotation = execClazz.getAnnotation(annClazz);

    if (annotation != null) {
      return readStringValue(annotation);
    }
    return null;
  }

  @Nullable
  private static<T extends Annotation> String readStringValue(@NonNull T annotation) {
    try {
      Method valueMethod = annotation.getClass().getMethod("value");
      Object value = valueMethod.invoke(annotation);

      if (String.class.isInstance(value)) {
        return (String) value;
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private ExportUtils() {}
}
