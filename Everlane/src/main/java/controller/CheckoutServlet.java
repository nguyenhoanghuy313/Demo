package controller;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.*;
import java.util.*;
import entity.*;
@WebServlet(name = "Checkout", value = "/Checkout")
public class CheckoutServlet extends HttpServlet{
    //    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException{
//        response.sendRedirect(request.getHeader("referer"));
//    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        ShopOrderDAO sod = new ShopOrderDAO();
        AddressDAO ad = new AddressDAO();
        User currUser = (User) request.getSession().getAttribute("currUser");
        assert currUser != null;
        String buyerID = String.valueOf(currUser.getUserID());
        String xRecipentName = request.getParameter("recipient").trim();
        String xPhone = request.getParameter("recipent_phone").trim();
        String xCity = request.getParameter("city").trim();
        String xAddressLine = request.getParameter("address_line").trim();
        String xPostalCode = request.getParameter("postalcode").trim();
        ShopOrder shopOrder = new ShopOrder(Integer.parseInt(buyerID), 0,xRecipentName,xPhone);
        sod.insertOrder(shopOrder);
        ad.insertAddress(xAddressLine,xCity,xPostalCode);
        ad.setAddressIDtoShopOrder();
        request.setAttribute("ErrMessage", "Address has been added Successfully");
        request.getRequestDispatcher("checkout.jsp").forward(request,response);
//        response.sendRedirect("checkout.jsp");
    }
}
