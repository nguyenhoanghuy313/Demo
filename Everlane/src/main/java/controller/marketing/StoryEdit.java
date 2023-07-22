package controller.marketing;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import entity.Story;
import model.StoryDAO;
import model.UploadImageToFile;

import java.io.IOException;

@MultipartConfig
@WebServlet(name = "StoryEdit", value = "/StoryEdit")
public class StoryEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String xStoryID = request.getParameter("StoryID").trim();
        StoryDAO sd = new StoryDAO();
        Story getStory = sd.getStoryByID(xStoryID);
        request.setAttribute("story", getStory);
        request.getRequestDispatcher("editStory.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String xStoryID = request.getParameter("StoryID").trim();
        String xStoryTitle = request.getParameter("storytitle").trim();
        String xStoryThumbnail = null;
        UploadImageToFile uploadImageToFile = new UploadImageToFile();

        Part file = request.getPart("storythumbnail");
        String uThumbnail = uploadImageToFile.uploadPath(file, xStoryThumbnail, "storyImg");

        String xStoryContent = request.getParameter("storycontent").trim();
        StoryDAO sd = new StoryDAO();
        sd.updateStory(xStoryID, uThumbnail, xStoryTitle, xStoryContent);
        response.sendRedirect("StoryServlet?input=all");
    }
}
