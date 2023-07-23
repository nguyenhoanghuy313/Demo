package model;

import jakarta.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.InputStream;

public class UploadImageToFile extends myDAO{
    public String uploadPath(Part x, String y, String type){
        String originalFileName = x.getSubmittedFileName();
        String fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        y = System.currentTimeMillis() + fileExtension;

        if(type.equals("productImg")){
//            String uploadPath = "Everlane_war/webImage/productImg/" + y;
            String uploadPath = "C:/Users/minileisduk/IdeaProjects/SWP391_G5/Everlane/src/main/webapp/webImage/productImg/" + y;
//            String uploadPath = "C:/Users/admin/Documents/GitHub/SWP391_G5/Everlane/src/main/webapp/webImage/productImg" + y;
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
        }else if(type.equals("collectionImg")){
//            String uploadPath = "Everlane_war/webImage/collection/" + y;
            String uploadPath = "C:/Users/minileisduk/IdeaProjects/SWP391_G5/Everlane/src/main/webapp/webImage/collection/" + y;
//            String uploadPath = "C:/Users/admin/Documents/GitHub/SWP391_G5/Everlane/src/main/webapp/webImage/collection" + y;

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
        } else if (type.equals("categoryImg")) {
            String uploadPath = "C:/Users/minileisduk/IdeaProjects/SWP391_G5/Everlane/src/main/webapp/webImage/category/" + y;
//            String uploadPath = "Everlane_war/webImage/category/" + y;
//            String uploadPath = "C:/Users/admin/Documents/GitHub/SWP391_G5/Everlane/src/main/webapp/webImage/category" + y;

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
        } else if (type.equals("storyImg")) {
            String uploadPath = "C:/Users/minileisduk/IdeaProjects/SWP391_G5/Everlane/src/main/webapp/webImage/story/" + y;
//            String uploadPath = "Everlane_war/webImage/story/" + y;
//            String uploadPath = "C:/Users/admin/Documents/GitHub/SWP391_G5/Everlane/src/main/webapp/webImage/story" + y;
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
        }
        return null;
    }
}
