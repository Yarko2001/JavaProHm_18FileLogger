package ua.dehtiar.api;

import java.io.IOException;
import ua.dehtiar.exceptions.FileMaxSizeReachedException;

/**
 * @author Yaroslav Dehtiar on 10.12.2022
 */
public interface Loggable {

  public void debug(String message) throws FileMaxSizeReachedException, IOException;

  public void info(String message) throws FileMaxSizeReachedException, IOException;

}
