package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import entity.User;
import model.UserDAO;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

@WebServlet(name = "UserEditServlet", value = "/UserEditServlet")
public class UserEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int xUserID = Integer.parseInt(request.getParameter("UserID").trim());
        if (xUserID == 0) {
            User u = null;
            request.setAttribute("userNeedEdit", u);
            request.getRequestDispatcher("editUser.jsp").forward(request, response);
        } else {
            UserDAO userDAO = new UserDAO();
            User u = userDAO.getUserById(xUserID);
            request.setAttribute("userNeedEdit", u);
            request.getRequestDispatcher("editUser.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int xUserID = Integer.parseInt(req.getParameter("userID").trim());
        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        String email = req.getParameter("email").trim();
        String firstname = req.getParameter("firstName").trim();
        String lastname = req.getParameter("lastName").trim();
        Date date = Date.valueOf(req.getParameter("dob").trim());
        int sex = Integer.parseInt(req.getParameter("gender").trim());
        int role = Integer.parseInt(req.getParameter("role").trim());
        String phone = req.getParameter("phoneNumber").trim();

        UserDAO u = new UserDAO();

        String input = req.getParameter("input").trim();
        switch (input) {

            case "username":
                if (u.checkUsername(username)) {
                    req.setAttribute("error1", "Username is existed");
                } else if (!username.matches("^(?=[a-zA-Z0-9._]{5,20}$)(?!.*[_.]{2})[^_.].*[^_.]$")) {
                    req.setAttribute("error1", "Username must be 5-20 letters, no _ or . ,");
                } else {

                    u.UpdateAccount(username, password, email, firstname, lastname, date, sex, role, phone, xUserID);

                    req.setAttribute("success1", "Saved");
                }
                User userNeedEdit = u.getUserById(xUserID);
                req.setAttribute("userNeedEdit", userNeedEdit);
                req.getRequestDispatcher("editUser.jsp").forward(req, resp);
                break;
            case "email":
                if (u.checkEmail(email)) {
                    req.setAttribute("error2", "Email is existed");
                } else {

                    u.UpdateAccount(username, password, email, firstname, lastname, date, sex, role, phone, xUserID);

                    req.setAttribute("success2", "Saved");
                }
                userNeedEdit = u.getUserById(xUserID);
                req.setAttribute("userNeedEdit", userNeedEdit);
                req.getRequestDispatcher("editUser.jsp").forward(req, resp);
                break;
            case "detail":
                Calendar calendar = GregorianCalendar.getInstance();
                calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 18);
                System.out.printf("Date %s is older than 18? %s", date, calendar.getTime().after(date));
                if (calendar.getTime().after(date)) {

                    u.UpdateAccount(username, password, email, firstname, lastname, date, sex, role, phone, xUserID);

                    userNeedEdit = u.getUserById(xUserID);
                    req.setAttribute("userNeedEdit", userNeedEdit);
                    req.setAttribute("success3", "Saved");
                    req.getRequestDispatcher("editUser.jsp").forward(req, resp);
                } else {
                    userNeedEdit = u.getUserById(xUserID);
                    req.setAttribute("userNeedEdit", userNeedEdit);
                    req.setAttribute("error3", "User must be 18 years old or older");
                    req.getRequestDispatcher("editUser.jsp").forward(req, resp);
                }
                break;
            default:
                userNeedEdit = u.getUserById(xUserID);
                req.setAttribute("userNeedEdit", userNeedEdit);
                req.getRequestDispatcher("editUser.jsp").forward(req, resp);
                break;
        }
    }
}