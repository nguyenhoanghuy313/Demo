package controller;
import jakarta.servlet.annotation.WebServlet;
//import model.ProductsDAO;
import model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name = "filter", value = "/filter")


public class SearchFilter extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        String[] type = request.getParameterValues("type");
        int priceRange = Integer.parseInt(request.getParameter("priceRange"));
        String color = request.getParameter("color");
        String size = request.getParameter("size");
        request.setAttribute("offSet", 0);
        request.getRequestDispatcher("productList.jsp").forward(request,response);
    }

    public void destroy() {
    }
}