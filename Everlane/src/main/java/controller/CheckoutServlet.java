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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        request.getRequestDispatcher("checkout.jsp").forward(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        UserDAO ud = new UserDAO();
        AddressDAO ad = new AddressDAO();
        User currUser = (User) request.getSession().getAttribute("currUser");
        if(currUser == null){
            request.getRequestDispatcher("login-servlet").forward(request,response);
            return;
        }
        String buyerID = String.valueOf(currUser.getUserID());
        String xFirstName = request.getParameter("FirstName");
        String xPhone = request.getParameter("phone");
        String xCity = request.getParameter("city");
        String xAddressLine = request.getParameter("address_line1");
        String xStreet = request.getParameter("Street");
        String xRegion = request.getParameter("region");
        String xPostalCode = request.getParameter("postalcode");
        ud.insertDetailed(xFirstName,xPhone,buyerID);
        ad.insertAddress(xStreet,xAddressLine,xCity,xRegion,xPostalCode);
        request.getRequestDispatcher("checkout.jsp").forward(request,response);
    }
}
