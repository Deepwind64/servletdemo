package com.bluemsun.servletdemo;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;");

        boolean flag = true;
        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String pwd = req.getParameter("password");
        String confirmPwd = req.getParameter("confirm-password");

        try {
            if(username==null || username.length()<3 || username.length()>10){
                resp.getWriter().println("用户名格式错误");
                flag=false;
            }
            if (email==null || !checkEmail(email)){
                resp.getWriter().println("邮箱格式错误");
                flag=false;
            }
            if (pwd == null || !pwd.equals(confirmPwd)){
                resp.getWriter().println("密码或确认密码填写有误");
                flag=false;
            }
            if (flag) {
                HttpSession session = req.getSession();
                session.setAttribute("username",username);
                session.setAttribute("password",pwd);
                resp.getWriter().println("注册成功");
            }
        } catch (Exception e) {
            resp.setStatus(500);
        }
    }

    public boolean checkEmail(String email){
        String[] endWith = {"163.com","qq.com","gmail.com"};
        String[] splits = email.split("@");
        String stringTail = splits[splits.length-1];
        for (var i: endWith){
            if (i.equals(stringTail)){
                return true;
            }
        }
        return false;
    }
}
