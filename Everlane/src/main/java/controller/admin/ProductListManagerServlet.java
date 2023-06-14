package controller.admin;

import java.io.*;
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
        ProductsDAO pd = new ProductsDAO();
        CategoryDAO cd = new CategoryDAO();
        String cateID = request.getParameter("categoryID");
        List<Category> cateList = cd.getCategory();
        request.setAttribute("cateList", cateList);
        List<Product> productList = pd.getAllProducts();
        request.setAttribute("productList", productList);
        request.getRequestDispatcher("productListManager.jsp").forward(request, response);

    }
}
