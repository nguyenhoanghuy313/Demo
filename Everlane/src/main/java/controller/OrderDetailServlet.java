package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.OrderDetailDAO;
import model.Product;
import model.User;

import java.io.IOException;
import java.util.List;
@WebServlet(name = "OrderDetail", value = "/OrderDetail")

public class OrderDetailServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        User currUser = (User) request.getSession().getAttribute("currUser");
        String buyerID = String.valueOf(currUser.getUserID());
        String xOrderID = request.getParameter("OrderID");
        OrderDetailDAO odd = new OrderDetailDAO();
        List<Product> userOrder =  odd.getUserOrder(buyerID,xOrderID);
        request.setAttribute("userOrder", userOrder);
        request.getRequestDispatcher("orderDetailUser.jsp").forward(request,response);
    }
}
