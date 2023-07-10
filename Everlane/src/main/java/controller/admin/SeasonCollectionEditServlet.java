package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProCollection;
import model.ProCollectionDAO;

import java.io.IOException;
import java.sql.Timestamp;

@WebServlet(name = "seasonCollectionEditServlet", urlPatterns = {"/seasonCollectionEditServlet"})
public class SeasonCollectionEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("collectionIDGet")!=null)
        {
            ProCollectionDAO col = new ProCollectionDAO();
            ProCollection collection = col.getCollections(req.getParameter("collectionIDGet"));
            req.setAttribute("c", collection);
            req.getRequestDispatcher("seasonCollectionEdit.jsp").forward(req, resp);

        }
        else
        {
            ProCollectionDAO col = new ProCollectionDAO();
            ProCollection collection = col.getCollections("1");
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
        ProCollectionDAO col = new ProCollectionDAO();
        col.updateCollection(collectionName,collectionImage,collectionDescription, Timestamp.valueOf(createDate),Integer.parseInt(collectionID));
        ProCollection collection = col.getCollections(collectionID);
        req.setAttribute("c", collection);
        req.setAttribute("message", "Update successfully");
        req.getRequestDispatcher("seasonCollectionEdit.jsp").forward(req, resp);
    }
}
