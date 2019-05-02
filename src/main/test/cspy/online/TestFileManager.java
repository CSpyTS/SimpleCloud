package cspy.online;

import cspy.online.controller.FileManageController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author CSpy
 * @date 2019/4/24 15:30
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:application-context.xml", "classpath:springmvc-config.xml"})
public class TestFileManager {
    MockMvc mock;


    @Autowired
    WebApplicationContext context;

    @Before
    public void setUp() {
        this.mock = MockMvcBuilders.webAppContextSetup(context).build();
    }



    @Test
    public void testDelete() {
        String path = "D:/SimpleCloud/users/CSpy/资源";
        System.out.println("待删目录为：" + path);
        MvcResult result = null;
        try {
            result = mock.perform(MockMvcRequestBuilders.get("/removeDirectory").param("path", path)
            ).andReturn();
            String resultString = result.getResponse().getContentAsString();
            System.out.println("resultString = " + resultString);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
