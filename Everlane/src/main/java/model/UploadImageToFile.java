package model;

import jakarta.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.InputStream;

public class UploadImageToFile extends myDAO{
    public String uploadPath(Part x, String y){
        String originalFileName = x.getSubmittedFileName();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        y = System.currentTimeMillis() + fileExtension;

        String uploadPath = "C:/Users/minileisduk/IdeaProjects/SWP391_G5/Everlane/src/main/webapp/webImage/" + y;
        try {
            FileOutputStream fos = new FileOutputStream(uploadPath);
            InputStream is = x.getInputStream();
            byte[] data = new byte[is.available()];
            is.read(data);
            fos.write(data);
            fos.close();
            return y;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
