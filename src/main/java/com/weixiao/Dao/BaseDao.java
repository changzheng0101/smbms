package com.weixiao.Dao;

import com.weixiao.Util.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * 对和数据库的交互进行封装
 *
 * @author weixiao
 * @date 2022/9/7 14:53
 */
public class BaseDao {
    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    static {
        Properties properties = null;
        InputStream inputStream = BaseDao.class.getClassLoader().getResourceAsStream(Constants.DB_CONFIG);
        try {
            properties.load(inputStream);
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            user = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static ResultSet executeQuery(Connection connection, PreparedStatement preparedStatement,
                                         String sql, Object[] params) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet;
    }

    public static int executeUpdate(Connection connection, PreparedStatement preparedStatement, String sql,
                                    Object[] params) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setObject(i + 1, params[i]);
        }
        int result = preparedStatement.executeUpdate();
        return result;
    }

    public static boolean closeResource(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        boolean flag = true;
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }

        return flag;
    }
}
