package controller.admin;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Collection;
import model.CollectionDAO;

import java.io.IOException;
@WebServlet(name = "seasonCollectionEditServlet", urlPatterns = {"/seasonCollectionEditServlet"})
public class SeasonCollectionEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            CollectionDAO col = new CollectionDAO();
            Collection collection = col.getCollections("1");
            req.setAttribute("c", collection);
            req.getRequestDispatcher("seasonCollectionEdit.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String collectionName = req.getParameter("collectionName");
        String collectionDescription = req.getParameter("collectionDescription");
        String collectionImage = req.getParameter("collectionImg");
        String collectionID = req.getParameter("collectionID");
        CollectionDAO col = new CollectionDAO();
        if(collectionID.equals("1")) {
            req.setAttribute("c", col.getCollections("1"));
            req.getSession().setAttribute("collectionS1", col.getCollections("1"));
            req.getRequestDispatcher("seasonCollectionEdit.jsp").forward(req, resp);
        } else if(collectionID.equals("2")) {
            req.setAttribute("c", col.getCollections("2"));
            req.getSession().setAttribute("collectionS2", col.getCollections("2"));
            req.getRequestDispatcher("seasonCollectionEdit.jsp").forward(req, resp);
        } else {
            req.setAttribute("c", col.getCollections("3"));
            req.getSession().setAttribute("collectionS3", col.getCollections("3"));
            req.getRequestDispatcher("seasonCollectionEdit.jsp").forward(req, resp);
        }
        col.updateCollection(collectionName,collectionImage,collectionDescription,Integer.parseInt(collectionID));
        req.setAttribute("message", "Update successfully");
        req.getRequestDispatcher("seasonCollectionEdit.jsp").forward(req, resp);
    }
}
