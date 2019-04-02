package cspy.online.dao;

import cspy.online.bean.User;

import java.util.List;

public interface UserMapper {
    User selectOneByUserName(String username);
    User selectOneByPhone(String phone);
    User selectOneByEmail(String email);
    List<User> selectAll();

    List<String> getAllUser();

    int insertUser(User user);
    int insertUsers(List<User> users);
    String getPasswordByUserName(String username);
    String getPasswordByPhone(String phone);
    String getPasswordByEmail(String email);

}
