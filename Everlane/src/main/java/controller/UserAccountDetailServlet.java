package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "user-account-detail-servlet", urlPatterns = {"/user-account-detail-servlet"})
public class UserAccountDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String mod = req.getParameter("mod");
        String accountActivation = req.getParameter("accountActivation");
        UserDAO u = new UserDAO();
//        ProductsDAO p = new ProductsDAO();
        CategoryDAO c = new CategoryDAO();
//        List<Product> data = p.getAllProducts();
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
//                req.setAttribute("data", data);
                req.setAttribute("cateList", cateList);
                req .getRequestDispatcher("home.jsp").forward(req, resp);
            }
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

        int xUserID = Integer.parseInt(req.getParameter("UserID").trim());

        HttpSession session = req.getSession();
        UserDAO u = new UserDAO();
        User user = (User) session.getAttribute("acc");
        if ( username.isEmpty() || email.isEmpty()) {
            user = u.getUserById(user.getUserID());
            req.setAttribute("u", user);
            req.setAttribute("error", "Email or UserName cannot be empty");
            req.getRequestDispatcher("userAccount.jsp").forward(req, resp);
        } else if (!u.isValidDate(String.valueOf(date))) {
            user = u.getUserById(user.getUserID());
            req.setAttribute("u", user);
            req.setAttribute("error", "Invalid date of birth");
            req.getRequestDispatcher("userAccount.jsp").forward(req, resp);
        } else {
            boolean checkAccountExist = u.checkAccountExistUserDetail(username, email, xUserID);
            if (checkAccountExist) {
                user = u.getUserById(user.getUserID());
                req.setAttribute("u", user);
                req.setAttribute("error", "Email or UserName already exists");
                req.getRequestDispatcher("userAccount.jsp").forward(req, resp);
            } else {
                u.UpdateAccount(username, password, email, firstname, lastname, date, sex, role, phone, xUserID);
                user = u.getUserById(user.getUserID());
                req.setAttribute("u", user);
                req.setAttribute("success", "Changing Account successfully");
                req.getRequestDispatcher("userAccount.jsp").forward(req, resp);

            }
        }
    }
}
