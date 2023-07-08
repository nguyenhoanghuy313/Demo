package model;

import java.util.ArrayList;
import java.util.List;

public class StoryDAO extends myDAO{
    public List<Story> getAllStory(String xName) {
        List<Story> t = new ArrayList<>();
        if(xName.equals("all")){
            xSql = "select * from story";
        }else{
            xSql = "select * from story where title like '%"+ xName + "%'";
        }
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery(); 
            int xStory_ID;
            String xThumbnail, xTitle, xDesciption;
            Story s;
            while (rs.next()) {
                xStory_ID = rs.getInt("story_id");
                xThumbnail = rs.getString("thumbnail");
                xTitle = rs.getString("title");
                xDesciption = rs.getString("description");
                s = new Story(xStory_ID, xThumbnail, xTitle, xDesciption);
                t.add(s);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }
}
