package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Category;
import model.CategoryDAO;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "categoryEditServlet", urlPatterns = {"/categoryEditServlet"})
public class CategoryEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CategoryDAO c = new CategoryDAO();
        List<Category> cateList = c.getAllCategory();
        req.setAttribute("cateList", cateList);
        req.getRequestDispatcher("categoryEdit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cateID = req.getParameter("cateImg");
    }
}
