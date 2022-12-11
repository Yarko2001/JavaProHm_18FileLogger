package ua.dehtiar.model.logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import ua.dehtiar.exceptions.FileMaxSizeReachedException;

/**
 * @author Yaroslav Dehtiar on 22.11.2022
 */
public class FileLogger {

  public FileLogger(FileLoggerConfiguration config) {
    this.config = config;
  }

  private FileLoggerConfiguration config;
  private int countLog;
  long currentSize;


  public void debug(String message) throws FileMaxSizeReachedException, IOException {
    if (configLog()) {
      File file = new File(
          config.getDestinationFile() + "." + config.getFormat());
      try (BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(file, true))) {
        String text =
            "[" + LocalDateTime.now() + "][DEBUG] Message : [" + message + "]\n";
        byte[] textSize = text.getBytes("UTF-8");
        checkFileSize(currentSize, textSize.length);
        bufferWriter.write(text);
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
    }
  }

  public void info(String message) throws FileMaxSizeReachedException {
    File file = new File(config.getDestinationFile() + "." + config.getFormat());
    try (BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(file, true))) {
      String text =
          "[" + LocalDateTime.now() + "][INFO] Message : [" + message + "]\n";
      byte[] textSize = text.getBytes("UTF-8");
      checkFileSize(currentSize, textSize.length);
      bufferWriter.write(text);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  private boolean configLog() throws IOException {
    Path path = Paths.get(config.getDestinationFile() + "." + config.getFormat());

    try {
      currentSize = Files.size(path);

    } catch (NoSuchFileException e) {
      File file = new File(
          config.getDestinationFile() + "." + config.getFormat());
      file.createNewFile();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    switch (config.getCurrentLoggingLevel()) {
      case DEBUG:
      case INFO:
        return true;
      default:
        return false;
    }
  }

  private void checkFileSize(long currentSize, long textSize) throws FileMaxSizeReachedException {
    if (currentSize + textSize > config.getMaxSizeFile()) {
      countLog++;
      createLogFile();
      currentSize = 0;

      throw new FileMaxSizeReachedException(
          "Over max size " + config.getMaxSizeFile() + " A size of the file must be " + (
              currentSize + textSize));
    } else {
      System.out.println("Log was added to file.");
    }
  }

  private String getFileName() {

    String fileName = countLog + "_" + currentDate("d.M.y-HH:mm");

    return config.getDestinationFile().replace(".", fileName + ".");
  }

  private void createLogFile() {

    try {

      File log = new File(currentDate("d.M.y-HH:mm") + "," + getFileName());
      if (log.createNewFile()) {
        System.out.println("Log File created");
      }

    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  private String currentDate(String pattern) {

    DateFormat dateFormat = new SimpleDateFormat(pattern);
    Date date = new Date();

    return dateFormat.format(date);
  }
}
