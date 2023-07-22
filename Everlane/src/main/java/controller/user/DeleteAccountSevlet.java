package controller.user;

import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

import java.io.IOException;
@WebServlet(name = "deleteAccountSevlet", urlPatterns = {"/deleteAccountSevlet"})
public class DeleteAccountSevlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String accountActivation = req.getParameter("accountActivation");
        UserDAO u = new UserDAO();
        if (accountActivation != null) {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("acc");
            u.deleteUser(String.valueOf(user.getUserID()));
            req.setAttribute("success", "Your account has been deleted");
            session.removeAttribute("acc");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("acc");
            user = u.getUserById(user.getUserID());
            req.setAttribute("u", user);
            req.setAttribute("errorDelete", "Please check the box to confirm");
            req.getRequestDispatcher("userAccount.jsp").forward(req, resp);

        }
    }
}
