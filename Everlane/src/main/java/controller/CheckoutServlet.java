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
        UserDAO ud = new UserDAO();
        AddressDAO ad = new AddressDAO();
        User currUser = (User) request.getSession().getAttribute("currUser");
        if(currUser == null){
            request.setAttribute("Message", "Please Login to perform this action!");
            request.getRequestDispatcher("login-servlet").forward(request,response);
            return;
        }
        String buyerID = String.valueOf(currUser.getUserID());
        String xFirstName = request.getParameter("FirstName").trim();
        String xPhone = request.getParameter("phone").trim();
        String xCity = request.getParameter("city").trim();
        String xAddressLine = request.getParameter("address_line1").trim();
        String xStreet = request.getParameter("Street").trim();
        String xRegion = request.getParameter("region").trim();
        String xPostalCode = request.getParameter("postalcode").trim();
        if(xFirstName.isEmpty() || xPhone.isEmpty() || xCity.isEmpty() || xAddressLine.isEmpty() ||
                xStreet.isEmpty() || xRegion.isEmpty() || xPostalCode.isEmpty()){
            request.setAttribute("ErrMessage", "Please fill all the fields");
            request.getRequestDispatcher("checkout.jsp").forward(request,response);
        } else {
            ud.insertDetailed(xFirstName,xPhone,buyerID);
            ad.insertAddress(xStreet,xAddressLine,xCity,xRegion,xPostalCode);
            request.setAttribute("ErrMessage", "Address has been added Successfully");
            request.getRequestDispatcher("checkout.jsp").forward(request,response);
        }

    }
}
