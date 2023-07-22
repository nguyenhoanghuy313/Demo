//Nguyễn Đắc Hoàng Đạt - HE170720
package controller.user;

//import controller.LoginGoogle.UserGoogleDto;
import entity.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@WebServlet(name = "login-servlet", urlPatterns = {"/login-servlet"})
public class
LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie arr[] = req.getCookies();
        if (arr != null) {
            for (Cookie o : arr) {
                if (o.getName().equals("emailUser")) {
                    req.setAttribute("emailUser", o.getValue());
                }
                if (o.getName().equals("passwordUser")) {
                    req.setAttribute("passwordUser", o.getValue());
                }
            }
        }
            req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (PrintWriter out = resp.getWriter()) {
            String email = req.getParameter("email").trim();
            String password = req.getParameter("password").trim();
            UserDAO u = new UserDAO();
            CategoryDAO c = new CategoryDAO();
            CollectionDAO col = new CollectionDAO();
            PromotionDAO promotionDAO = new PromotionDAO();
            StoryDAO storyDAO = new StoryDAO();

            List<Story> storyList = storyDAO.getAllStory("all");
            List<Category> cateList = c.getAllCategory();
            Collection collection = col.getCollectionsByDate();
            Promotion promotion = promotionDAO.getLastestPromotion();

            Encryptor encryptor = new Encryptor();
            User checkUser = u.checkUser(email, encryptor.encryptString(password));
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
                if(password.length() < 3 || password.length() > 20){
                    req.setAttribute("PassErr", "Password must be from 3 to 20 characters!!!");
                }
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            } else {
                if (Role.getRole() == 4) {
                    HttpSession session = req.getSession();
                    session.setAttribute("acc", checkUser);

                    Cookie cookieAcc = new Cookie("emailUser", email);
                    Cookie cookiePass = new Cookie("passwordUser", password);
                    cookieAcc.setMaxAge(60 * 60 * 24 * 365);
                    if(req.getParameter("remember me") != null){
                        cookiePass.setMaxAge(60 * 60 * 24 * 365);
                    } else {
                        cookiePass.setMaxAge(0);
                    }
                    resp.addCookie(cookieAcc); // lưu cookie lên client
                    resp.addCookie(cookiePass); // lưu cookie lên client
//                    req.setAttribute("data", data);
                    req.setAttribute("storyList", storyList);
                    req.setAttribute("promotion", promotion);
                    req.setAttribute("cateList", cateList);
                    req.setAttribute("collection", collection);
                    req.getRequestDispatcher("home.jsp").forward(req, resp);
                } else {
                    req.setAttribute("Message", "Email or Password is incorrect or not exist!!!");
                    req.getRequestDispatcher("login.jsp").forward(req, resp);
                }
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
