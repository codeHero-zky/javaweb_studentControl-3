package test.controller;

import test.pojo.user;
import test.service.serviceUser;
import test.service.serviceUserimp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user/*")
public class UserController extends HttpServlet implements serviceUser {
    serviceUserimp service = new serviceUserimp();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] split = req.getRequestURI().split("/");
        String op=split[split.length-1];
        if(op.equals("regist"))
        {
            user u=new user(req.getParameter("userId"),req.getParameter("userPwd"));
            int regist = 0;
            try {
                regist = regist(u);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if(regist>0)
            {
                resp.sendRedirect("/project/registSuccess.html");
            }
            else
            {
                resp.sendRedirect("/project/registFail.html");
            }
        }
        else if(op.equals("login"))
        {
            HttpSession session = req.getSession();
            session.setAttribute("isLogin","true");
            String userId = req.getParameter("username");
            String userPwd = req.getParameter("userPwd");
            user u;
            try
            {
                u=findByuserId(userId);
            }
            catch (Exception e)
            {
                throw new RuntimeException(e);
            }

            if(u==null)
            {
                resp.sendRedirect("/project/loginUsernameError.html");
            }
            else if(!u.getUserPwd().equals(userPwd))
            {
                resp.sendRedirect("/project/loginPasswordError.html");
            }
            else
            {
                resp.sendRedirect("/project/show.html");
            }
        }
        else
        {
            System.out.println("NULL");
        }

    }

    @Override
    public int regist(user u) throws SQLException {
        return service.regist(u);
    }

    @Override
    public user findByuserId(String userId) throws SQLException, NoSuchFieldException, InstantiationException, IllegalAccessException {
        return service.findByuserId(userId);
    }

}
