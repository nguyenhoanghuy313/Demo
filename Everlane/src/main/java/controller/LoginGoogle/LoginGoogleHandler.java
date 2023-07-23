package controller.LoginGoogle;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.List;

import entity.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Form;

/**
 * @author heaty566
 */
@WebServlet(name = "LoginGoogleHandler",urlPatterns = { "/LoginGoogleHandler","/LoginGoogle/*" })
public class LoginGoogleHandler extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        String accessToken = getToken(code);
        UserDAO u = new UserDAO();
        CollectionDAO col = new CollectionDAO();
        CategoryDAO c = new CategoryDAO();
        StoryDAO storyDAO = new StoryDAO();

        List<Story> storyList = storyDAO.getAllStory("all");
//        ProductsDAO p = new ProductsDAO();
//        List<Product> data = p.getAllProducts();
        List<Category> cateList = c.getAllCategory();
        PromotionDAO promotionDAO = new PromotionDAO();
        UserGoogleDto user = getUserInfo(accessToken);
        Collection collection = col.getCollectionsByDate();
        Promotion promotion = promotionDAO.getPromotionByID("1");
        User ugoogle = u.getUserByEmail(user.getEmail());
        request.getSession().setAttribute("acc", ugoogle);
//        response.sendRedirect(request.getContextPath()+"/home.jsp");
//        request.getRequestDispatcher("home.jsp").forward(request,response);
//        System.out.println(user.getEmail());
//        request.getSession().setAttribute("acc", ugoogle);
        if(ugoogle != null) {
//            request.setAttribute("data", data);
//            request.getSession().setAttribute("data", data);
            request.setAttribute("promotion", promotion);
            request.setAttribute("cateList", cateList);
            request.setAttribute("collection", collection);
            request.setAttribute("storyList", storyList);
            request.getSession().setAttribute("storyList", storyList);
            request.getSession().setAttribute("promotion", promotion);
            request.getSession().setAttribute("collection", collection);
            request.getSession().setAttribute("cateList", cateList);
            response.sendRedirect(request.getContextPath()+"/home.jsp");
        } else {
            String MessageGG = "Email is incorrect or not exist please register";
            request.getSession().setAttribute("MessageGG", MessageGG);
            response.sendRedirect(request.getContextPath()+"/login.jsp");        }
    }

    public static String getToken(String code) throws ClientProtocolException, IOException {
        // call api to get token
        String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static UserGoogleDto getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();
        UserGoogleDto googlePojo = new Gson().fromJson(response, UserGoogleDto.class);
        return googlePojo;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the +
    // sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}