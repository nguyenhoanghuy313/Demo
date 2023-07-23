package controller.sale;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import entity.ProductForEdit;
import model.ProductForEditDAO;

import java.io.IOException;

@WebServlet(name = "CreateNewProductServlet", value = "/CreateNewProductServlet")
public class CreateNewProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("newProduct");
        session.removeAttribute("colorName");
        session.removeAttribute("newProductImg");
        request.getRequestDispatcher("addNewProduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productName = request.getParameter("productName").trim();

        ProductForEditDAO pfed = new ProductForEditDAO();
        ProductForEdit pfe = pfed.getProduct(productName);
        if(pfe == null){
            HttpSession session = request.getSession();
            String categoryID = request.getParameter("categoryID").trim();
            String price = request.getParameter("price").trim();
            String collectionID = request.getParameter("collectionID").trim();
            String description = request.getParameter("description").trim();
            pfed.createNewProduct(Integer.parseInt(categoryID), productName,  Integer.parseInt(price), Integer.parseInt(collectionID), description);
            ProductForEdit product = pfed.getProduct(productName);
            session.setAttribute("newProduct", product);
            request.setAttribute("alert", "Create done");
            request.getRequestDispatcher("addNewProduct.jsp").forward(request, response);
        }else{
            request.setAttribute("pfe", pfe);
            request.getRequestDispatcher("addNewProduct.jsp").forward(request, response);
        }

    }
}
