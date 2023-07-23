package controller.marketing;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import entity.Category;
import model.CategoryDAO;
import model.UploadImageToFile;

import java.io.IOException;
import java.util.List;
@MultipartConfig
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
        String cateID = req.getParameter("cateID");
        String cateImg = null;
        CategoryDAO c = new CategoryDAO();
        Category category = new Category();
        UploadImageToFile uploadImageToFile = new UploadImageToFile();

        Part file = req.getPart("cateImg");
        String uCateImg = uploadImageToFile.uploadPath(file, cateImg, "categoryImg");

        c.updateCategory(uCateImg, Integer.parseInt(cateID));
        req.setAttribute("message", "Update successfully");

        List<Category> cateList = c.getAllCategory();
        req.setAttribute("cateList", cateList);
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        req.getRequestDispatcher("categoryEdit.jsp").forward(req, resp);
    }
}
