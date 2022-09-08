package com.weixiao.Service.user;

import com.weixiao.Pojo.User;

import java.sql.SQLException;


/**
 * @author weixiao
 * @date 2022/9/7 16:10
 */
public interface UserService {
    /**
     * 登录接口
     *
     * @param userName
     * @param password
     * @exception SQLException
     * @return
     */
    User login(String userName, String password) throws SQLException;

    boolean updatePassword(int id, String password) throws SQLException;
}
