package controller.marketing;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import entity.Collection;
import model.CollectionDAO;
import model.UploadImageToFile;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
@MultipartConfig

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
            Collection collection = col.getCollectionsByDate();
            req.setAttribute("c", collection);
            req.getRequestDispatcher("seasonCollectionEdit.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String collectionName = req.getParameter("collectionName");
        String collectionDescription = req.getParameter("collectionDescription");
        String collectionImage = null;
        String collectionID = req.getParameter("collectionID");
        String createDate = req.getParameter("createDate");
        CollectionDAO col = new CollectionDAO();
        if (collectionName.equals("") || collectionDescription.equals("")) {
            Collection collection = col.getCollections(collectionID);
            req.setAttribute("c", collection);
            req.setAttribute("message", "Must Enter All Field");
            req.getRequestDispatcher("seasonCollectionEdit.jsp").forward(req, resp);
        } else {
            UploadImageToFile uploadImageToFile = new UploadImageToFile();
            Part file = req.getPart("collectionImg");
            String uCollectionImage = uploadImageToFile.uploadPath(file, collectionImage, "collectionImg");

            col.updateCollection(collectionName,uCollectionImage,collectionDescription, Timestamp.valueOf(createDate),Integer.parseInt(collectionID));
            Collection collection = col.getCollections(collectionID);
            req.setAttribute("c", collection);
            req.setAttribute("message", "Update successfully");
            try {
                Thread.sleep(2000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            req.getRequestDispatcher("seasonCollectionEdit.jsp").forward(req, resp);
        }

    }
}
