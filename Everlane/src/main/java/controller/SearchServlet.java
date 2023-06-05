package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import model.ProductsDAO;
import model.Product;

@WebServlet(name = "search-servlet", value = "/search-servlet")
public class SearchServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String name = request.getParameter("name").trim();
        ProductsDAO cd = new ProductsDAO();
        List<Product> foundList = cd.searchByName(name);
        request.setAttribute("offSet", 0);
        request.setAttribute("list", foundList);
        request.getRequestDispatcher("productlist.jsp").forward(request,response);
    }
}