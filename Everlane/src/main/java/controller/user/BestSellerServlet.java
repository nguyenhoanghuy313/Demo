package controller.user;

import entity.BestSeller;
import entity.Category;
import entity.Color;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import model.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BestSellerServlet", value = "/BestSellerServlet")
public class BestSellerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BestSellerDAO bestseller = new BestSellerDAO();
        List<BestSeller> bestSellers = bestseller.getBestSeller();

        ProductDAO pd = new ProductDAO();
        CategoryDAO cd = new CategoryDAO();
        ColorDAO col = new ColorDAO();
        VariationDAO vr = new VariationDAO();

        List<Category> cateList = cd.getAllCategory();
        Category category = cd.getCategory("9");
        request.setAttribute("cateList", cateList);
        request.setAttribute("category", category);

        List<Color> colorList = col.getAllColors();
        request.setAttribute("colorList", colorList);

        List<Product> productList = new ArrayList<>();
        for (BestSeller bestSeller: bestSellers){
            Product product = pd.getProductByProIDColName(String.valueOf(bestSeller.getProductID()), bestSeller.getColorName());
            productList.add(product);
        }

        request.setAttribute("productList", productList);
        request.getRequestDispatcher("productList.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
