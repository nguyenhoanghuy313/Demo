package controller;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.*;
import java.util.*;
import entity.*;
@WebServlet(name = "DeleteFromCart", urlPatterns = {"/DeleteFromCart"})
public class DeleteFromCart extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        CartItemDAO cid = new CartItemDAO();
        String xCartItemID = request.getParameter("CartItemID");
        String xProductID = request.getParameter("ProductID");
        String xVariationID = request.getParameter("variationID");
//        cid.deleteCartItemByProdID(xProductID);
        cid.deleteCartItem(xProductID,xVariationID);
        response.sendRedirect(request.getHeader("referer"));
    }
}
