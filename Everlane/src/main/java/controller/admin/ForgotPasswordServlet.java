package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;
import model.UserDAO;

import java.io.IOException;
@WebServlet(name = "ForgotPasswordServlet", urlPatterns = {"/ForgotPasswordServlet"})
public class ForgotPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.getRequestDispatcher("forgotPasswordHighUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        UserDAO u = new UserDAO();
        User checkEmail = u.getUserByEmail(email);
        if(checkEmail == null)
        {
            req.setAttribute("message", "Email not exist");
            req.getRequestDispatcher("forgotPasswordHighUser.jsp").forward(req, resp);
        }
        else
        {
            req.setAttribute("message", "Please check your email to reset password");
            req.getRequestDispatcher("forgotPasswordHighUser.jsp").forward(req, resp);
        }
    }
}
