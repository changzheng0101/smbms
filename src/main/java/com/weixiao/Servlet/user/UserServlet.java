package com.weixiao.Servlet.user;


import com.weixiao.Dao.BaseDao;
import com.weixiao.Dao.user.Impl.UserDaoImpl;
import com.weixiao.Dao.user.UserDao;
import com.weixiao.Pojo.User;
import com.weixiao.Service.user.Impl.UserServiceImpl;
import com.weixiao.Service.user.UserService;
import com.weixiao.Util.Constants;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author weixiao
 * @date 2022/9/7 19:47
 */
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object attribute = req.getSession().getAttribute(Constants.USER_SESSION);
        String newPassword = req.getParameter("newpassword");
        boolean result = false;
        if (attribute != null && newPassword != null && newPassword.length() > 0) {
            UserService userService = new UserServiceImpl();
            try {
                result = userService.updatePassword(((User) attribute).getId(), newPassword);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (result) {
            req.getSession().removeAttribute(Constants.USER_SESSION);
            req.setAttribute(Constants.MESSAGE, "修改密码成功");
        } else {
            req.setAttribute(Constants.MESSAGE, "修改密码失败");
        }

        req.getRequestDispatcher("/jsp/pwdmodify.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
