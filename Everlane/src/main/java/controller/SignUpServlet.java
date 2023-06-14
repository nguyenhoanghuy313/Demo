package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import model.UserDAO;

import java.io.IOException;
@WebServlet(name = "register-servlet", urlPatterns = {"/register-servlet"})
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username").trim();
        String email = req.getParameter("email").trim();
        String password = req.getParameter("password").trim();
        String repass = req.getParameter("repass").trim();
        UserDAO u = new UserDAO();
        User x = new User();
        if (email.isEmpty() || password.isEmpty() || repass.isEmpty()) {
            req.setAttribute("error", "Please fill all the fields");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        } else if (!password.equals(repass)) {
            req.setAttribute("error", "Password and repassword are not the same");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
        } else {
            boolean checkAccountExist = u.checkAccountExist(username, email);
            if (checkAccountExist == true) {
                req.setAttribute("error", "Account already exists");
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            } else {
//                User user = new User(username, email, password);
                u.addUser(username, password, email);
                req.setAttribute("newemail", email);
                req.setAttribute("success", "Register successfully please sign in");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        }
    }
}
