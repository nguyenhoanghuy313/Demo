package controller;

import java.io.*;
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
        ProductsDAO pd = new ProductsDAO();
        CategoryDAO cd = new CategoryDAO();
        String cateID = request.getParameter("categoryID");
        List<Category> cateList = cd.getCategory();
        request.setAttribute("cateList", cateList);
        List<Product> productList = pd.searchByName(xName);
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("productList.jsp").forward(request, response);
    }
}
