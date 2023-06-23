package controller.admin;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

@WebServlet(name = "deleteProduct", value = "/DeleteProduct")
public class DeleteProduct extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String delProductID = request.getParameter("ProductID");
        ProductsDAO pd = new ProductsDAO();
        pd.delete(delProductID);
        response.sendRedirect("ProductListManagerServlet?categoryID=all");
    }
}
