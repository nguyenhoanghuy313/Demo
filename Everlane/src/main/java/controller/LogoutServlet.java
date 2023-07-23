package controller;
import entity.Category;
import entity.Collection;
import entity.Promotion;
import entity.Story;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "logout-servlet", urlPatterns = {"/logout-servlet"})
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("mod") != null && request.getParameter("mod").equals("1")) {
            HttpSession session = request.getSession();
            session.removeAttribute("accHU");
            Cookie arr[] = request.getCookies();
            if (arr != null) {
                for (Cookie o : arr) {
                    if (o.getName().equals("email")) {
                        request.setAttribute("email", o.getValue());
                    }
                    if (o.getName().equals("password")) {
                        request.setAttribute("password", o.getValue());
                    }
                }
            }
            request.getRequestDispatcher("loginHighUser.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.removeAttribute("acc");
            session.removeAttribute("currUser");
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
