package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.User;
import model.UserDAO;

import java.io.IOException;
import java.util.List;
@WebServlet(name = "loginHighUserServlet", urlPatterns = {"/loginHighUserServlet"})
public class LoginHighUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("loginHighUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email").trim();
        String password = req.getParameter("password").trim();
        UserDAO u = new UserDAO();
        User checkUser = u.checkUser(email, password);
        User Role = u.getRoleByEmail(email);
        if (checkUser == null) {
            if(!email.isEmpty() || !password.isEmpty()) {
                req.setAttribute("error", "Email or Password cannot be empty!!!");
            }
            if(!email.isEmpty() && !password.isEmpty()){
                req.setAttribute("error", "Email or Password is incorrect or not exist!!!");
            }
            req.getRequestDispatcher("loginHighUser.jsp").forward(req, resp);
        } else {
            if (Role.getRole().equals("Admin")) {
                HttpSession session = req.getSession();
                session.setAttribute("acc", checkUser);

                // lưu account lên cookie
                Cookie cookieAcc = new Cookie("email", email);
                Cookie cookiePass = new Cookie("password", password);
                cookieAcc.setMaxAge(60 * 60 * 24 * 30);
                cookiePass.setMaxAge(60 * 60 * 24 * 30);
                resp.addCookie(cookieAcc); // lưu cookie lên client
                resp.addCookie(cookiePass); // lưu cookie lên client
                UserDAO ud = new UserDAO();
                List<User> userList = ud.getAllUser();
                req.setAttribute("userList", userList);
                req.getRequestDispatcher("dashboardManager.jsp").forward(req, resp);
            }
//            else if (Role.getRole().equals("Customer")) {
//                req.getRequestDispatcher("home.jsp").forward(req, resp);
//            }
        }
    }
}
