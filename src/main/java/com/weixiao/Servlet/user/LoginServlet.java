package com.weixiao.Servlet.user;

import com.weixiao.Pojo.User;
import com.weixiao.Service.user.Impl.UserServiceImpl;
import com.weixiao.Service.user.UserService;
import com.weixiao.Util.Constants;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author weixiao
 * @date 2022/9/7 20:15
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");
        UserService userService = new UserServiceImpl();
        try {
            User user = userService.login(userCode, userPassword);
            if (user == null) {
                // login failed
                req.setAttribute("error", "用户名或密码错误");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            } else {
                // login success
                req.getSession().setAttribute(Constants.USER_SESSION, user);
                resp.sendRedirect("/smbms/jsp/frame.jsp");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
