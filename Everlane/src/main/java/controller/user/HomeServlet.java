package controller.user;
import java.io.*;

import entity.Category;
import entity.Promotion;
import entity.Story;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.*;
import entity.Collection;


import java.util.*;

@MultipartConfig
@WebServlet(name = "category-servlet", value = "/home-servlet")
public class HomeServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        CategoryDAO c = new CategoryDAO();
        CollectionDAO col = new CollectionDAO();
        PromotionDAO promotionDAO = new PromotionDAO();
        StoryDAO storyDAO = new StoryDAO();

        List<Story> storyList = storyDAO.getAllStory("all");
        List<Category> cateList = c.getAllCategory();
        Collection collection = col.getCollectionsByDate();
        request.getSession().setAttribute("collection", collection);
        Promotion promotion = promotionDAO.getLastestPromotion();

        request.setAttribute("storyList", storyList);
        request.setAttribute("promotion", promotion);
        request.setAttribute("cateList", cateList);
        request.setAttribute("collection", collection);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }
}
