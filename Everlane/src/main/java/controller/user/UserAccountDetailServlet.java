package controller.user;

import entity.Category;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@WebServlet(name = "user-account-detail-servlet", urlPatterns = {"/user-account-detail-servlet"})
public class UserAccountDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mod = req.getParameter("mod");
        String accountActivation = req.getParameter("accountActivation");
        UserDAO u = new UserDAO();
        CategoryDAO c = new CategoryDAO();
        List<Category> cateList = c.getAllCategory();
        if (mod == null) {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("acc");
            user = u.getUserById(user.getUserID());
            req.setAttribute("u", user);
            req.getRequestDispatcher("userAccount.jsp").forward(req, resp);
        } else {
            if (mod.equals("1")) {
                HttpSession session = req.getSession();
                session.getAttribute("acc");
                req.setAttribute("cateList", cateList);
                req.getRequestDispatcher("home.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String input = req.getParameter("input").trim();

        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        String email = req.getParameter("email").trim();
        String firstname = req.getParameter("firstName").trim();
        String lastname = req.getParameter("lastName").trim();
        Date date = Date.valueOf(req.getParameter("dob").trim());
        int sex = Integer.parseInt(req.getParameter("gender").trim());
        int role = Integer.parseInt(req.getParameter("role").trim());
        String phone = req.getParameter("phoneNumber").trim();

        int xUserID = Integer.parseInt(req.getParameter("UserID").trim());

        HttpSession session = req.getSession();
        UserDAO u = new UserDAO();
        User user = (User) session.getAttribute("acc");

        switch (input) {
            case "username":
                if (u.checkUsername(username)) {
                    user = u.getUserById(user.getUserID());
                    req.setAttribute("u", user);
                    req.setAttribute("error1", "Username is existed");
                } else if (!username.matches("^(?=[a-zA-Z0-9._]{5,20}$)(?!.*[_.]{2})[^_.].*[^_.]$")) {
                    user = u.getUserById(user.getUserID());
                    req.setAttribute("u", user);
                    req.setAttribute("error1", "Username must be 5-20 letters, no _ or . ,");
                } else {

                    u.UpdateAccount(username, password, email, firstname, lastname, date, sex, role, phone, xUserID);

                    user = u.getUserById(user.getUserID());
                    req.setAttribute("u", user);
                    req.setAttribute("success1", "Saved");
                }
                req.getRequestDispatcher("userAccount.jsp").forward(req, resp);
                break;
            case "email":
                if (u.checkEmail(email)) {
                    user = u.getUserById(user.getUserID());
                    req.setAttribute("u", user);
                    req.setAttribute("error2", "Email is existed");
                } else {

                    u.UpdateAccount(username, password, email, firstname, lastname, date, sex, role, phone, xUserID);

                    user = u.getUserById(user.getUserID());
                    req.setAttribute("u", user);
                    req.setAttribute("success2", "Saved");
                }
                req.getRequestDispatcher("userAccount.jsp").forward(req, resp);
                break;
            case "detail":
                Calendar calendar = GregorianCalendar.getInstance();
                calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - 18);
                System.out.printf("Date %s is older than 18? %s", date, calendar.getTime().after(date));
                if (calendar.getTime().after(date)) {
                    u.UpdateAccount(username, password, email, firstname, lastname, date, sex, role, phone, xUserID);

                    user = u.getUserById(user.getUserID());
                    req.setAttribute("u", user);
                    req.setAttribute("success3", "Saved");
                    req.getRequestDispatcher("userAccount.jsp").forward(req, resp);
                } else {
                    user = u.getUserById(user.getUserID());
                    req.setAttribute("u", user);
                    req.setAttribute("error3", "User must be 18 years old or older");
                    req.getRequestDispatcher("userAccount.jsp").forward(req, resp);
                }

                break;
            default:
                user = u.getUserById(user.getUserID());
                req.setAttribute("u", user);
                req.getRequestDispatcher("userAccount.jsp").forward(req, resp);
                break;
        }

    }
}
