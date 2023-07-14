package controller;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderList", urlPatterns = {"/OrderList"})

public class OrderList extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        String xUserID = request.getParameter("UserId");
        OrderDetailDAO odd = new OrderDetailDAO();
        List<Product> odList = odd.getUserOrder(xUserID);
        request.setAttribute("odList", odList);
        request.getRequestDispatcher("orderListUser.jsp").forward(request,response);
    }
}
