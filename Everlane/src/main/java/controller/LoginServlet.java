//Nguyễn Đắc Hoàng Đạt - HE170720
package controller;

//import controller.LoginGoogle.UserGoogleDto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "login-servlet", urlPatterns = {"/login-servlet"})
public class
LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter out = resp.getWriter()) {
            String email = req.getParameter("email").trim();
            String password = req.getParameter("password").trim();
            UserDAO u = new UserDAO();
//            ProductsDAO p = new ProductsDAO();
            CategoryDAO c = new CategoryDAO();
            CollectionDAO col = new CollectionDAO();

//            List<Product> data = p.getAllProducts();
            List<Category> cateList = c.getAllCategory();
            Collection collection = col.getCollections("1");

            User checkUser = u.checkUser(email, password);
            User Role = u.getRoleByEmail(email);
            if (checkUser == null) {
                if (email.isEmpty()) {
                    req.setAttribute("EmailErr", "Email not allow empty!!!");
                }
                if (password.isEmpty()) {
                    req.setAttribute("PassErr", "Password not allow  empty!!!");
                }
                if(!email.isEmpty() && !password.isEmpty()){
                    req.setAttribute("Message", "Email or Password is incorrect or not exist!!!");
                }
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            } else {
                if (Role.getRole() == 4) {
                    HttpSession session = req.getSession();
                    session.setAttribute("acc", checkUser);
//                    req.setAttribute("data", data);
                    req.setAttribute("cateList", cateList);
                    req.setAttribute("collection", collection);
                    req.getRequestDispatcher("home.jsp").forward(req, resp);
//                    return;
//                } else if (Role.getRole() == 1) {
//                    HttpSession session = req.getSession();
//                    session.setAttribute("acc", checkUser);
//                    UserDAO ud = new UserDAO();
//                    List<User> userList = ud.getAllUser();
//                    req.setAttribute("userList", userList);
//                    req.getRequestDispatcher("userListManager.jsp").forward(req, resp);
////                    return;
                } else {
                    req.setAttribute("Message", "Email or Password is incorrect or not exist!!!");
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
//                    return;
                }
            }
//            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
//Nguyễn Đắc Hoàng Đạt - HE170720