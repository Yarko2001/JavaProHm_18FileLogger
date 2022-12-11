package ua.dehtiar.model.logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import ua.dehtiar.enums.LoggingLevel;

/**
 * @author Yaroslav Dehtiar on 22.11.2022
 */
public class FileLoggerConfigurationLoader {

  public FileLoggerConfiguration load(String path) {
    FileLoggerConfiguration flc = null;
    try (InputStream input = new FileInputStream(path)) {

      Properties prop = new Properties();

      prop.load(input);

      flc = new FileLoggerConfiguration(prop.getProperty("FILE"),
          LoggingLevel.valueOf(prop.getProperty("LEVEL")),
          Integer.parseInt(prop.getProperty("MAX-SIZE")),
          prop.getProperty("FORMAT"));
    } catch (IOException io) {
      io.printStackTrace();
    }
    return flc;
  }

}
