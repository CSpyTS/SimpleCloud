package cspy.online;

import cspy.online.util.SystemInitializer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author CSpy
 * @date 2019/3/18 23:24
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class TestInitializer {

    @Autowired
    SystemInitializer initializer;

    @Test
    public void testInit() {
        initializer.initSystem();
    }
}
