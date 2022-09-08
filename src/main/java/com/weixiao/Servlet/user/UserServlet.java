package com.weixiao.Servlet.user;


import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.core.util.StringUtils;
import com.weixiao.Pojo.User;
import com.weixiao.Service.user.Impl.UserServiceImpl;
import com.weixiao.Service.user.UserService;
import com.weixiao.Util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author weixiao
 * @date 2022/9/7 19:47
 */
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method.equals("savepwd")) {
            updatePassword(req, resp);
        } else if (method.equals("pwdmodify")) {
            checkPassword(req, resp);
        }
    }

    private void checkPassword(HttpServletRequest req, HttpServletResponse resp) {
        String oldPassword = req.getParameter("oldpassword");
        Object attribute = req.getSession().getAttribute(Constants.USER_SESSION);
        Map<String, String> resultMap = new HashMap<>(1);
        if (attribute != null) {
            if (StringUtils.isNullOrEmpty(oldPassword)) {
                resultMap.put("result", "error");
            }
            String userPassword = ((User) attribute).getUserPassword();
            if (userPassword.equals(oldPassword)) {
                resultMap.put("result", "true");
            } else {
                resultMap.put("result", "false");
            }
        } else {
            resultMap.put("result", "sessionerror");
        }
        try {
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            writer.write(JSONArray.toJSONString(resultMap));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void updatePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        req.getRequestDispatcher("/jsp/pwdmodify.jsp").forward(req, resp);
    }
}
