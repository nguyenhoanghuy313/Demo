package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Collection;
import model.CollectionDAO;

import java.io.IOException;
import java.sql.Date;

@WebServlet(name = "seasonCollectionEditServlet", urlPatterns = {"/seasonCollectionEditServlet"})
public class SeasonCollectionEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("collectionIDGet")!=null)
        {
            CollectionDAO col = new CollectionDAO();
            Collection collection = col.getCollections(req.getParameter("collectionIDGet"));
            req.setAttribute("c", collection);
            req.getRequestDispatcher("seasonCollectionEdit.jsp").forward(req, resp);

        }
        else
        {
            CollectionDAO col = new CollectionDAO();
            Collection collection = col.getCollections("1");
            req.setAttribute("c", collection);
            req.getRequestDispatcher("seasonCollectionEdit.jsp").forward(req, resp);

        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String collectionName = req.getParameter("collectionName");
        String collectionDescription = req.getParameter("collectionDescription");
        String collectionImage = req.getParameter("collectionImg");
        String collectionID = req.getParameter("collectionID");
        String createDate = req.getParameter("createDate");
        CollectionDAO col = new CollectionDAO();
//        java.sql.Date create_date =  hcurrentDate;
//        Date now = LocalDateTime.now();
        col.updateCollection(collectionName,collectionImage,collectionDescription, Date.valueOf(createDate),Integer.parseInt(collectionID));
        Collection collection = col.getCollections(collectionID);
        req.setAttribute("c", collection);
        req.setAttribute("message", "Update successfully");
        req.getRequestDispatcher("seasonCollectionEdit.jsp").forward(req, resp);
    }
}
