package com.weixiao.Dao.user.Impl;

import com.weixiao.Dao.BaseDao;
import com.weixiao.Dao.user.UserDao;
import com.weixiao.Pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author weixiao
 * @date 2022/9/7 16:02
 */
public class UserDaoImpl implements UserDao {


    @Override
    public User getUserByUserCode(Connection connection, String UserCode) throws SQLException {
        User user = null;
        PreparedStatement preparedStatement = null;
        String sql = "select * from smbms_user where userCode = ?";
        Object[] params = {UserCode};
        if (connection != null) {
            ResultSet rs = BaseDao.executeQuery(connection, preparedStatement, sql, params);
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUserRole(rs.getInt("userRole"));
                user.setCreatedBy(rs.getInt("createdBy"));
                user.setCreationDate(rs.getTimestamp("creationDate"));
                user.setModifyBy(rs.getInt("modifyBy"));
                user.setModifyDate(rs.getTimestamp("modifyDate"));
            }
            BaseDao.closeResource(null, preparedStatement, rs);
        }
        return user;
    }

    @Override
    public boolean updatePasswordById(Connection connection, int id, String password) throws SQLException {
        String sql = "update smbms_user set userPassword = ? where id = ?";
        Object[] params = {password, id};
        PreparedStatement preparedStatement = null;
        int i = BaseDao.executeUpdate(connection, preparedStatement, sql, params);
        BaseDao.closeResource(null, preparedStatement, null);
        if (i > 0) {
            return true;
        }
        return false;
    }
}
