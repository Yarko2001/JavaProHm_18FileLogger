package ua.dehtiar.exceptions;


/**
 * @author Yaroslav Dehtiar on 23.11.2022
 */
public class FileMaxSizeReachedException extends Exception {

  public FileMaxSizeReachedException(String message) {
    super(message);
  }
}
