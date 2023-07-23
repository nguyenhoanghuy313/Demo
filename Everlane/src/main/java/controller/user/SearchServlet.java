package controller.user;

import java.io.*;

import entity.Category;
import entity.Color;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.*;
import java.util.*;

@WebServlet(name = "SearchServlet", value = "/SearchServlet")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String xName = request.getParameter("productName").trim();
        String cateID = request.getParameter("categoryID");

        ProductDAO pd = new ProductDAO();
        CategoryDAO cd = new CategoryDAO();
        ColorDAO col = new ColorDAO();

        List<Category> cateList = cd.getAllCategory();
        Category category = cd.getCategory("9");
        request.setAttribute("cateList", cateList);
        request.setAttribute("category", category);

        List<Color> colorList = col.getAllColors();
        request.setAttribute("colorList", colorList);

        List<Product> productList = pd.searchByName(xName);
        request.setAttribute("productList", productList);

        request.getRequestDispatcher("productList.jsp").forward(request, response);
    }
}
