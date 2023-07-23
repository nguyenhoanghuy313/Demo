package controller.sale;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import entity.Collection;
import model.CollectionDAO;

import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(name = "CollectionUpdatePromotion", urlPatterns = {"/CollectionUpdatePromotion"})
public class CollectionUpdatePromotion extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("collectionIDGet")!=null)
        {
            CollectionDAO col = new CollectionDAO();
            Collection collection = col.getCollections(req.getParameter("collectionIDGet"));
            req.setAttribute("c", collection);
            req.getRequestDispatcher("seasonCollectionUpdatePromotion.jsp").forward(req, resp);
        }
        else
        {
            CollectionDAO col = new CollectionDAO();
            Collection collection = col.getCollectionsByDate();
            req.setAttribute("c", collection);
            req.getRequestDispatcher("seasonCollectionUpdatePromotion.jsp").forward(req, resp);
        }    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String collectionName = req.getParameter("collectionName");
        String collectionDescription = req.getParameter("collectionDescription");
        String collectionImage = req.getParameter("collectionImg");
        String collectionID = req.getParameter("collectionID");
        String createDate = req.getParameter("createDate");
        String promotionID = req.getParameter("promotionID");
        CollectionDAO col = new CollectionDAO();
        col.updateCollectionPromoID(collectionName, Integer.parseInt(promotionID), collectionImage,collectionDescription, Timestamp.valueOf(createDate),Integer.parseInt(collectionID));
        Collection collection = col.getCollections(collectionID);
        req.setAttribute("c", collection);
        req.setAttribute("message", "Update successfully");
        req.getRequestDispatcher("seasonCollectionUpdatePromotion.jsp").forward(req, resp);

    }
}
