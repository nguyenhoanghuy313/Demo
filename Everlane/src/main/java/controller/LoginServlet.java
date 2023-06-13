package controller;

import com.mysql.cj.protocol.x.XMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "login-servlet", urlPatterns = {"/login-servlet"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter out = resp.getWriter()) {
            String email = req.getParameter("email");
            String password =  req.getParameter("password");
            UserDAO u = new UserDAO();
            ProductsDAO p = new ProductsDAO();
            CategoryDAO c = new CategoryDAO();
            String cateID = req.getParameter("categoryID");
            List<Product> productListOnClick = p.getProductsByCateID(cateID);
            List<Product> data = p.getAllProducts();
            List<Category> cateList = c.getCategory();
            boolean checkUser = u.checkUser(email, password);
            User Role = u.getRoleByEmail(email);
            boolean ok = true;
            if(email.isEmpty() || password.isEmpty()) {
                ok = false;
                req.setAttribute("Message", "DO NOT EMPTY");
                if(email.isEmpty()) {
                    req.setAttribute("EmailErr","Email not allow empty!!!");
                }
                if (password.isEmpty()) {
                    req.setAttribute("PassErr","Password not allow  empty!!!");
                }
            }
            if (ok) {
                if (checkUser) {
                    if(Role.getRole().equals("Customer")) {
                        req.setAttribute("productListOnClick", productListOnClick);
                        req.setAttribute("data", data);
                        req.setAttribute("cateList", cateList);
                        req.getRequestDispatcher("home.jsp").forward(req, resp);
                    }
                    if(Role.getRole().equals("Admin")) {
                        req.getRequestDispatcher("admin.jsp").forward(req, resp);
                    }
                }
            } else {
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }

//                System.out.printf(String.valueOf(checkAdmin));
        }
    }
}
