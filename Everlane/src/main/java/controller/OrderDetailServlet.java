package controller;
import entity.OrderDetail;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServlet;
import model.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "OrderDetailServlet", urlPatterns = {"/OrderDetailServlet"})

public class OrderDetailServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        String xOrderID = request.getParameter("OrderID");
        OrderDetailDAO odd = new OrderDetailDAO();
        List<OrderDetail> od = odd.getOrderItems(Integer.parseInt(xOrderID));
    }
}
