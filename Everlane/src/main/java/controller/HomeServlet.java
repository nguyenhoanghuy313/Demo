package controller;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.*;
import model.Collection;


import java.util.*;
@WebServlet(name = "category-servlet", value = "/category-servlet")
public class HomeServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
//        ProductsDAO p = new ProductsDAO();
        CategoryDAO c = new CategoryDAO();
        CollectionDAO col = new CollectionDAO();
        PromotionDAO promotionDAO = new PromotionDAO();

//        List<Product> data = p.getAllProducts();
        List<Category> cateList = c.getAllCategory();
        Collection collection = col.getCollectionsByDate();
        request.getSession().setAttribute("collection", collection);

        Promotion promotion = promotionDAO.getLastestPromotion();
        request.setAttribute("promotion", promotion);

//        request.setAttribute("data", data);
        request.setAttribute("cateList", cateList);
        request.setAttribute("collection", collection);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
