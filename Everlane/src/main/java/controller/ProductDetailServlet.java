package controller;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.*;
import java.util.*;
@WebServlet(name = "productDetail-servlet", urlPatterns = {"/productDetail-servlet"})

public class ProductDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String xId = request.getParameter("ProductID");
        Product x;
        ProductsDAO pd = new ProductsDAO();
        x = pd.getProduct(xId);
        request.setAttribute("pi", x);
        request.getRequestDispatcher("productDetail.jsp").forward(request,response);
    }

}
