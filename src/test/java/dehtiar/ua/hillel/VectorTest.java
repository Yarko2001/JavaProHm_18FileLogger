package dehtiar.ua.hillel;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import org.springframework.core.io.ClassPathResource;
import ua.dehtiar.exceptions.FileMaxSizeReachedException;
import ua.dehtiar.model.Vector;
import ua.dehtiar.model.logger.FileLogger;
import ua.dehtiar.model.logger.FileLoggerConfigurationLoader;

/**
 * @author Yaroslav Dehtiar on 11.12.2022
 */
public class VectorTest {

  FileLoggerConfigurationLoader loader = new FileLoggerConfigurationLoader();
  FileLogger fileLogger = new FileLogger(
      loader.load(new ClassPathResource(
          "src\\main\\resources\\my_custom_configuration.properties").getPath()));

  @org.junit.Test
  public void startVectorMethods() throws FileMaxSizeReachedException {

    Vector[] vectors = {new Vector(3.0, 5.0, 7.0), new Vector(2.0, 4.0, 9.0)};
    fileLogger.info("Vectors: " + Arrays.toString(vectors));
    assertEquals(new Vector(5.0, 9.0, 16.0), vectors[0].add(vectors[1]));
    fileLogger.info("Result add: " + vectors[0].add(vectors[1]));
    assertEquals(new Vector(1.0, 1.0, -2.0), vectors[0].subtract(vectors[1]));
    fileLogger.info("Result subtract: " + vectors[0].subtract(vectors[1]));
    assertEquals(89.0, vectors[0].scalarProduct(vectors[1]), 0.001);
    fileLogger.info("Result scalarProduct: " + vectors[0].scalarProduct(vectors[1]));
    assertEquals(new Vector(17.0, -13.0, 2.0), vectors[0].crossProduct(vectors[1]));
    fileLogger.info("Result crossProduct: " + vectors[0].crossProduct(vectors[1]));

  }

}
