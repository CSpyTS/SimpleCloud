package cspy.online;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

/**
 * @author CSpy
 * @date 2019/3/17 22:49
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class TestLog {

    @Test
    public void testLog() {
        Logger logger = LoggerFactory.getLogger("com.foo.Bar");
        logger.info("show something " + LocalDateTime.now());
    }
}
