package com.bluemsun.servletdemo;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author deepwind
 */
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        resp.setContentType("text/html;");

        boolean flag = true;
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String pwd = req.getParameter("password");
        String confirmPwd = req.getParameter("confirm-password");

        try {
            if (username == null || checkLength(username, 3, 10)) {
                resp.getWriter().println("用户名格式错误");
                flag = false;
            }
            if (email == null || !checkEmail(email)) {
                resp.getWriter().println("邮箱格式错误");
                flag = false;
            }
            if (pwd == null || !pwd.equals(confirmPwd) || checkLength(pwd, 8, 20)) {
                resp.getWriter().println("密码或确认密码填写有误");
                flag = false;
            }
            if (flag) {
                /*
                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                session.setAttribute("password", pwd);
                 */
                ServletContext servletContext = getServletContext();
                servletContext.setAttribute("username", username);
                servletContext.setAttribute("password", pwd);
                resp.getWriter().println("注册成功");
            }
        } catch (Exception e) {
            resp.setStatus(500);
        }
    }

    public boolean checkEmail(String email) {
        String[] endWith = {"163.com", "qq.com", "gmail.com"};
        String[] splits = email.split("@");
        String stringTail = splits[splits.length - 1];
        for (var i : endWith) {
            if (i.equals(stringTail)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkLength(String input, int min, int max) {
        return input.length() < min || input.length() > max;
    }
}
