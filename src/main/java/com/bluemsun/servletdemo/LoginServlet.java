package com.bluemsun.servletdemo;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private ServletConfig config;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.config = this.getServletConfig();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        // 获取初始参数设置的用户名、密码
        String innerUsername = this.config.getInitParameter("username");
        String innerPwd = this.config.getInitParameter("pwd");
        // 获取请求中的用户名、密码
        String username = req.getParameter("username");
        String pwd = req.getParameter("password");
        // 获取 session 中的用户名密码
        HttpSession session = req.getSession();
        String tmpUsername = (String) session.getAttribute("username");
        tmpUsername = tmpUsername==null ? "" : tmpUsername;
        String tmpPwd = (String) session.getAttribute("password");
        tmpPwd = tmpPwd == null ? "" : tmpPwd;

        if (innerUsername.equals(username) || tmpUsername.equals(username)){
            if (innerPwd.equals(pwd) || tmpPwd.equals(pwd)) {
                resp.getWriter().println("登录成功");
            }
        }
        else {
            resp.getWriter().println("用户名或密码错误");
        }
    }
}
