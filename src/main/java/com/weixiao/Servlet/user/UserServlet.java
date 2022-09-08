package com.weixiao.Servlet.user;


import org.junit.Test;

import java.lang.annotation.Retention;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author weixiao
 * @date 2022/9/7 19:47
 */
public class UserServlet {

    @Test
    public void test() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smbms?useSSL=false&useUnicode=true&characterEncoding=utf-8","root","123456");
            System.out.println(connection);
        } catch (Exception e) {
            System.out.println("not found");
            e.printStackTrace();
        }
    }

}
