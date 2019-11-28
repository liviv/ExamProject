package royalCruises;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    Logger logger = Logger.getLogger("AppTest");
//logger.info(" ")//todo почему здесь не работает логгер?

    /**
     * Rigorous Test! :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
        logger.info("test");
        logger.error("jj");
    }

}
