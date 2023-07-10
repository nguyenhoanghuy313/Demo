package controller.admin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.Variation;
import model.VariationDAO;

import java.io.IOException;

@WebServlet(name = "CreateVariationServlet", value = "/CreateVariationServlet")
public class CreateVariationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productID = request.getParameter("productID").trim();
        String colorID = request.getParameter("colorID").trim();
        String sizeID = request.getParameter("sizeID").trim();
        String qty_in_stock = request.getParameter("qty_in_stock").trim();
        String productimgID = request.getParameter("productimgID").trim();

        VariationDAO variationDAO = new VariationDAO();
        variationDAO.createNewVariation(Integer.parseInt(productID),Integer.parseInt(colorID) ,Integer.parseInt(sizeID) ,Integer.parseInt(qty_in_stock) ,Integer.parseInt(productimgID) );
        response.sendRedirect("addNewProduct.jsp");
    }
}
