package com.weixiao.Service.user.Impl;

import com.weixiao.Dao.BaseDao;
import com.weixiao.Dao.user.Impl.UserDaoImpl;
import com.weixiao.Dao.user.UserDao;
import com.weixiao.Pojo.User;
import com.weixiao.Service.user.UserService;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author weixiao
 * @date 2022/9/7 16:12
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl() {
        this.userDao = new UserDaoImpl();
    }


    @Override
    public User login(String userName, String password) throws SQLException {
        Connection connection = BaseDao.getConnection();
        User userByUserCode = userDao.getUserByUserCode(connection, userName);
        BaseDao.closeResource(connection, null, null);
        if (userByUserCode != null && !userByUserCode.getUserPassword().equals(password)) {
            userByUserCode = null;
        }
        return userByUserCode;
    }

    @Override
    public boolean updatePassword(int id, String password) throws SQLException {
        Connection connection = BaseDao.getConnection();
        boolean result =  userDao.updatePasswordById(connection, id, password);
        BaseDao.closeResource(connection, null, null);
        return result;
    }
}
