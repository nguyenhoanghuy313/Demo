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
//        String cateID[] = req.getParameter("cateID");
//        String cateImg = req.getParameter("cateImg");
        String cateID[] = req.getParameterValues("cateID");
        String cateImg[] = req.getParameterValues("cateImg");
        CategoryDAO c = new CategoryDAO();
        Category category = new Category();

        for (int i = 0; i < cateID.length; i++) {
            if (cateImg[i].equals("")) {
                List<Category> cateList = c.getAllCategory();
                req.setAttribute("cateList", cateList);
                req.setAttribute("message", "Must Enter All Field");
                req.getRequestDispatcher("categoryEdit.jsp").forward(req, resp);
            } else if (!cateImg[i].endsWith(".jpg") && !cateImg[i].endsWith(".png")) {
                List<Category> cateList = c.getAllCategory();
                req.setAttribute("cateList", cateList);
                req.setAttribute("message", "Must Enter Image Link End With .jpg or .png");
                req.getRequestDispatcher("categoryEdit.jsp").forward(req, resp);
            } else {
                c.updateCategory(cateImg[i],Integer.parseInt(cateID[i]));
                req.setAttribute("message", "Update successfully");
            }
        }
//        for(int i = 0; i < cateID.length; i++) {
//            c.updateCategory(cateImg[i],Integer.parseInt(cateID[i]));
//        }
            List<Category> cateList = c.getAllCategory();
            req.setAttribute("cateList", cateList);
//            req.setAttribute("message", "Update successfully");
            req.getRequestDispatcher("categoryEdit.jsp").forward(req, resp);

    }
}
