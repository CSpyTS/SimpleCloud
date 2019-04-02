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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class TestConnection {

    @Autowired
    ApplicationContext context;

    @Test
    public void testHello() {
        UserMapper mapper = (UserMapper) context.getBean("userMapper");
        User cspy = mapper.selectOneByUserName("CSpy");
        System.out.println(cspy);

        System.out.println("=========");
        List<User> users = mapper.selectAll();
        for (User user: users) {
            System.out.println(user);
        }

    }

    @Test
    public void insertUser() {
        UserMapper mapper = (UserMapper) context.getBean("userMapper");
        User user = getRandomUser();
        System.out.println("第一个用户:" + user);
        mapper.insertUser(user);

        List<User> users = new ArrayList<>();
        int randomNumber = new Random().nextInt(20);
        for (int i = 0; i < randomNumber; i++) {
            users.add(getRandomUser());
        }
        System.out.println("一批用户:" + users.size());
        mapper.insertUsers(users);


    }

    public User getRandomUser() {
        User user = new User();
        user.setUsername(UUID.randomUUID().toString().substring(0, 8));
        user.setPassword("randomUser");
        return user;
    }
}
