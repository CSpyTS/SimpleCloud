package cspy.online;

import cspy.online.bean.SCFile;
import cspy.online.bean.SCUser;
import cspy.online.dao.FileMapper;
import cspy.online.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.nio.file.Path;
import java.nio.file.Paths;
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
        SCUser cspy = mapper.selectOneByUserName("CSpy");
        System.out.println(cspy);

        System.out.println("=========");
        List<SCUser> SCUsers = mapper.selectAll();
        for (SCUser SCUser : SCUsers) {
            System.out.println(SCUser);
        }

    }

    @Test
    public void insertUser() {
        UserMapper mapper = (UserMapper) context.getBean("userMapper");
        SCUser SCUser = getRandomUser();
        System.out.println("第一个用户:" + SCUser);
        mapper.insertUser(SCUser);

        List<SCUser> SCUsers = new ArrayList<>();
        int randomNumber = new Random().nextInt(40);
        for (int i = 0; i < randomNumber; i++) {
            SCUsers.add(getRandomUser());
        }
        System.out.println("一批用户:" + SCUsers.size());
        mapper.insertUsers(SCUsers);


    }

    public SCUser getRandomUser() {
        SCUser SCUser = new SCUser();
        SCUser.setUsername(UUID.randomUUID().toString().substring(0, 8));
        SCUser.setPassword("randomUser");
        return SCUser;
    }


    @Test
    public void testFileSearch() {
        Path path = Paths.get("D:/SimpleCloud/users/CSpy");
        System.out.println(path);
        FileMapper fileMapper = (FileMapper) context.getBean("fileMapper");
        List<SCFile> fileList = fileMapper.getFileList(path.toString());
        for (SCFile file : fileList) {
            System.out.println(file.getPath() + "----" + file.getFilename() + "----" + file.getType());
        }
    }

    @Test
    public void testSelectDirectory() {
        String path = "CSpy";
        String dirName = "44444";
        FileMapper fileMapper = (FileMapper) context.getBean("fileMapper");
        int count = fileMapper.selectDirectory(path, dirName);
        System.out.println(path + "/" + dirName + " 文件夹存在" + count + "个");


    }
}
