package controller.sale;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;

@WebServlet(name = "deleteProduct", value = "/DeleteProduct")
public class DeleteProduct extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String delProductID = request.getParameter("ProductID").trim();
        String delColName = request.getParameter("color_Name").trim();
        String delSizeName = request.getParameter("size_Name").trim();

        ProductDAO pd = new ProductDAO();
        ProductForEditDAO pfed = new ProductForEditDAO();

        int productCount = pd.count(delProductID);
        if (productCount > 1){
            pd.delete(delProductID, delColName, delSizeName);
            System.out.println(pd.count(delProductID));
        }else{
            pd.delete(delProductID, delColName, delSizeName);
            pfed.delete(delProductID);
        }
        response.sendRedirect("ProductListManagerServlet?input=all");
    }
}
