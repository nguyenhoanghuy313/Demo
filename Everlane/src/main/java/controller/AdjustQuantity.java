package controller;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.CartItemDAO;
@WebServlet(name = "adjustQuantity", urlPatterns = {"/adjustQuantity"})

public class AdjustQuantity extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        CartItemDAO cid = new CartItemDAO();
        String xProductID = request.getParameter("ProductID");
        String xVariationID = request.getParameter("VariationID");
        String choice = request.getParameter("choice");
        if(choice.equals("plus")){
            cid.plusQuantity(xProductID,xVariationID);
            response.sendRedirect(request.getHeader("referer"));
        }else {
            cid.minusQuantity(xProductID,xVariationID);
            response.sendRedirect(request.getHeader("referer"));

        }
    }

}
