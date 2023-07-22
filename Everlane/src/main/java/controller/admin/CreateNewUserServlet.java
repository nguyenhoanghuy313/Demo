package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import entity.Encryptor;
import entity.User;
import model.UserDAO;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@WebServlet(name = "CreateNewUserServlet", value = "/CreateNewUserServlet")
public class CreateNewUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int xUserID = Integer.parseInt(request.getParameter("UserID").trim());
        if (xUserID == 0){
            User u = null;
            request.setAttribute("userNeedEdit", u);
            request.getRequestDispatcher("createNewUser.jsp").forward(request, response);
        }else{
            UserDAO userDAO = new UserDAO();
            User u = userDAO.getUserById(xUserID);
            request.setAttribute("userNeedEdit", u);
            request.getRequestDispatcher("createNewUser.jsp").forward(request, response);
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
        User user;
        Encryptor encryptor = new Encryptor();

        if (u.checkUsername(username)) {
            req.setAttribute("error", "Username is existed");
            req.getRequestDispatcher("createNewUser.jsp").forward(req, resp);
        } else if(!username.matches("^(?=[a-zA-Z0-9._]{5,20}$)(?!.*[_.]{2})[^_.].*[^_.]$")){
            req.setAttribute("error", "Username must be 5-20 letters, no _ or . ,");
            req.getRequestDispatcher("createNewUser.jsp").forward(req, resp);
        }else if (password.length() < 3 ||password.length() > 20) {
                req.setAttribute("error", "Password must be between 3 and 20 characters");
                req.getRequestDispatcher("createNewUser.jsp").forward(req, resp);
        }else if (u.checkEmail(email)) {
                    req.setAttribute("error", "Email is existed");
                    req.getRequestDispatcher("createNewUser.jsp").forward(req, resp);
        }else {
            Calendar calendar = GregorianCalendar.getInstance();
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 18);
            if (!calendar.getTime().after(date)) {
                req.setAttribute("error", "User must be 18 years old or older");
                req.getRequestDispatcher("createNewUser.jsp").forward(req, resp);
            }else{
                List<User> userList = u.getAllUser();
                req.setAttribute("userList", userList);
                int xUserID = Integer.parseInt(req.getParameter("UserID").trim());
                if (xUserID == 0){
                    req.setAttribute("done", "Create done");
                    try {
                        u.createNewUser(username, encryptor.encryptString(password), email, firstname, lastname, date, sex, role, phone);
                    } catch (NoSuchAlgorithmException e) {
                        throw new RuntimeException(e);
                    }
                    req.getRequestDispatcher("createNewUser.jsp").forward(req, resp);
                }else{
                        u.UpdateAccount(username, password, email, firstname, lastname, date, sex, role, phone, xUserID);
                    req.getRequestDispatcher("createNewUser.jsp").forward(req, resp);
                }
            }
        }
    }
}
