package cspy.online.dao;

import cspy.online.bean.SCUser;

import java.util.List;

public interface UserMapper {
    SCUser selectOneByUserName(String username);
    SCUser selectOneByPhone(String phone);
    SCUser selectOneByEmail(String email);
    SCUser selectOneByUid(int uid);

    List<SCUser> selectAll();

    List<String> getAllUser();

    int insertUser(SCUser scUser);
    int insertUsers(List<SCUser> scUsers);
    String getPasswordByUserName(String username);
    String getPasswordByPhone(String phone);
    String getPasswordByEmail(String email);

}
