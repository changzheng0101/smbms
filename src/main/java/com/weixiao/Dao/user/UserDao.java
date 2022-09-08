package com.weixiao.Dao.user;

import com.weixiao.Pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author weixiao
 * @date 2022/9/7 16:01
 */
public interface UserDao {
    /**
     * 根据UserCode获取用户
     *
     * @param connection
     * @param UserCode
     * @return
     */
    User getUserByUserCode(Connection connection, String UserCode) throws SQLException;
}
