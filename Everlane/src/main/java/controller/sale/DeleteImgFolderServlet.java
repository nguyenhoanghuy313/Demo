package controller.sale;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.ProductImgDAO;
import model.VariationDAO;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "DeleteImgFolderServlet", value = "/DeleteImgFolderServlet")
public class DeleteImgFolderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String ImageID = request.getParameter("ImageID").trim();
        ProductImgDAO productImgDAO = new ProductImgDAO();
        VariationDAO variationDAO = new VariationDAO();
        if(variationDAO.getVariationByImgID(ImageID) == null){
            productImgDAO.delete(ImageID);
            response.sendRedirect("ProductListManagerServlet?input=all");
        }else{
            request.setAttribute("error", "Img folder is in use, can't delete");
            request.getRequestDispatcher("ProductListManagerServlet?input=all").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
