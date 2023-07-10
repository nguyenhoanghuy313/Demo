//Nguyễn Đắc Hoàng Đạt - HE170720
package controller;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "logout-servlet", urlPatterns = {"/logout-servlet"})
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("acc");
//        ProductsDAO p = new ProductsDAO();
        CategoryDAO c = new CategoryDAO();
        ProCollectionDAO col = new ProCollectionDAO();
        PromotionDAO promotionDAO = new PromotionDAO();


//        List<Product> data = p.getAllProducts();
        List<Category> cateList = c.getAllCategory();
        ProCollection proCollection = col.getCollections("1");

        Promotion promotion = promotionDAO.getLastestPromotion();
        request.setAttribute("promotion", promotion);


//        request.setAttribute("data", data);\
        request.setAttribute("cateList", cateList);
        request.setAttribute("collection", proCollection);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
//Nguyễn Đắc Hoàng Đạt - HE170720