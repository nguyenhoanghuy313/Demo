package controller;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.*;
import java.util.*;
@WebServlet(name = "productList-servlet", value = "/productList-servlet")
public class ProductServlet extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ProductDAO pd = new ProductDAO();
        CategoryDAO cd = new CategoryDAO();
        ColorDAO col = new ColorDAO();
        SizeDAO si = new SizeDAO();

        String cateID = request.getParameter("categoryID").trim();
        String colID = request.getParameter("color_ID").trim();

        List<Category> cateList = cd.getAllCategory();
        Category category = cd.getCategory(cateID);
        request.setAttribute("cateList", cateList);
        request.setAttribute("category", category);

        if(colID.equals("all")){
            List<Product> productList = pd.getProductsByCID(cateID);
            request.setAttribute("productList", productList);
        }else{
            List<Product> productList = pd.getProductsByColIDProID(cateID, colID);
            request.setAttribute("productList", productList);
        }

        List<Color> colorList = col.getAllColors();
        request.setAttribute("colorList", colorList);

        List<Size> sizeList = si.getAllSizes();
        request.setAttribute("sizeList", sizeList);

        request.getRequestDispatcher("productList.jsp").forward(request, response);

    }
}
