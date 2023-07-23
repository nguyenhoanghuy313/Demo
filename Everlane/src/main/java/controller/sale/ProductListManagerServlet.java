package controller.sale;

import java.io.*;

import entity.Category;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.*;
import java.util.*;

@WebServlet(name = "ProductListManagerServlet", value = "/ProductListManagerServlet")
public class ProductListManagerServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ProductDAO pd = new ProductDAO();
        CategoryDAO cd = new CategoryDAO();

        String input = request.getParameter("input");

        List<Category> cateList = cd.getAllCategory();
        request.setAttribute("cateList", cateList);

            List<Product> productList = pd.getAllProducts(input, 1);
            request.setAttribute("productList", productList);

        request.getRequestDispatcher("productListManager.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String xName = request.getParameter("productName").trim();
        ProductDAO pd = new ProductDAO();
        CategoryDAO cd = new CategoryDAO();

        String productName = request.getParameter("productName");

        List<Category> cateList = cd.getAllCategory();
        request.setAttribute("cateList", cateList);

        List<Product> productList = pd.getAllProducts(productName, 2);
        request.setAttribute("productList", productList);

        request.getRequestDispatcher("productListManager.jsp").forward(request, response);
    }
}
