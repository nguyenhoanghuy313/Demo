package controller;

import java.io.IOException;
import java.io.PrintWriter;

import entity.CartItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

import model.*;

@WebServlet(name = "ListCart", urlPatterns = {"/ListCart"})
public class CartItemList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    User u = (User)  request.getSession().getAttribute("currUser");
    CartDAO cd = new CartDAO();
    CartItemDAO cid = new CartItemDAO();
    int   xProductID = Integer.parseInt(request.getParameter("ProductID"));
    List<CartItem> cartItemList = cid.getCartItem(u.getUserID(), xProductID);
    request.setAttribute("cartItemList", cartItemList);
//    request.getRequestDispatcher("home.jsp").forward(request,response);

    }
}
