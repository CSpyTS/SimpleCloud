package cspy.online.service;

import cspy.online.bean.SCUser;
import cspy.online.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author CSpy
 * @date 2019/4/1 9:26
 */
@Service
public class LoginRegisterService {

    @Autowired
    UserMapper userMapper;


    public boolean checkUserValidByUserName(String username, String password) {
        String validPassword = userMapper.getPasswordByUserName(username);
        if (Objects.equals(validPassword, password)) {
            return true;
        } else {
            return false;
        }
    }

    public Map<String, Boolean> checkUserValidByPhoneOrEmail(String phoneOrEmail, String password) {
        Map<String, Boolean> loginResult = new HashMap<>();
        String validPassword = null;
        if (phoneOrEmail.contains("@")) {
            validPassword = userMapper.getPasswordByEmail(phoneOrEmail);
            loginResult.put("email", Objects.equals(validPassword, password));
        } else {
            validPassword= userMapper.getPasswordByPhone(phoneOrEmail);
            loginResult.put("phone", Objects.equals(validPassword, password));
        }
        return loginResult;
    }

    public boolean registerUser(String username, String password) {
        SCUser SCUser = new SCUser();
        SCUser.setUsername(username);
        SCUser.setPassword(password);
        return userMapper.insertUser(SCUser) == 1;
    }

    public boolean isUserByUserName(String username) {
        return userMapper.selectOneByUserName(username) != null;
    }

    public boolean isUserByPhone(String phone) {
        return userMapper.selectOneByPhone(phone) != null;
    }

    public boolean isUserByEmail(String email) {
        return userMapper.selectOneByEmail(email) != null;
    }

}
