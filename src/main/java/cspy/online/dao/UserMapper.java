package cspy.online.dao;

import cspy.online.bean.User;

import java.util.List;

public interface UserMapper {
    User selectOne(String username);
    List<User> selectAll();
}
