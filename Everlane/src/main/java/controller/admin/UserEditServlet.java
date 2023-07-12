package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.User;
import model.UserDAO;

import java.sql.Date;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserEditServlet", value = "/UserEditServlet")
public class UserEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int xUserID = Integer.parseInt(request.getParameter("UserID").trim());
        if (xUserID == 0){
            User u = null;
            request.setAttribute("userNeedEdit", u);
            request.getRequestDispatcher("editUser.jsp").forward(request, response);
        }else{
            UserDAO userDAO = new UserDAO();
            User u = userDAO.getUserById(xUserID);
            request.setAttribute("userNeedEdit", u);
            request.getRequestDispatcher("editUser.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        String email = req.getParameter("email").trim();
        String firstname = req.getParameter("firstName").trim();
        String lastname = req.getParameter("lastName").trim();
        Date date=Date.valueOf(req.getParameter("dob").trim());
        int sex = Integer.parseInt(req.getParameter("gender").trim());
        int role = Integer.parseInt(req.getParameter("role").trim());
        String phone = req.getParameter("phoneNumber").trim();

        UserDAO u = new UserDAO();

        List<User> userList = u.getAllUser();
        req.setAttribute("userList", userList);

        int xUserID = Integer.parseInt(req.getParameter("UserID").trim());
        if (xUserID == 0){
            u.createNewUser(username, password, email, firstname, lastname, date, sex, role, phone);
        }else{
            u.UpdateAccount(username, password, email, firstname, lastname, date, sex, role, phone, xUserID);
        }
        req.getRequestDispatcher("userListManager.jsp").forward(req, resp);
    }
}
