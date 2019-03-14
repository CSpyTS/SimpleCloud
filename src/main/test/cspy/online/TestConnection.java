package cspy.online;

import cspy.online.bean.User;
import cspy.online.dao.UserMapper;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class TestConnection {

    @Autowired
    ApplicationContext context;

    @Test
    public void testHello() {
        UserMapper mapper = (UserMapper) context.getBean("userMapper");
        User cspy = mapper.selectOne("CSpy");
        System.out.println(cspy);

        System.out.println("=========");
        List<User> users = mapper.selectAll();
        for (User user: users) {
            System.out.println(user);
        }

    }
}
