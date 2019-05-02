package cspy.online;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * @author CSpy
 * @date 2019/4/24 9:46
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class TestUUID {

    @Test
    public void testUUIDString() {
        System.out.println(UUID.randomUUID().toString().replace("-",""));
    }
}
