package model;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends myDAO {
    public List<Category> getCategory() {
        List<Category> t = new ArrayList<>();
        xSql = "select * from Category";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xId;
            String xName, xImg;
            Category c;
            while (rs.next()) {
                xId = rs.getInt("CategoryID");
                xName = rs.getString("CategoryName");
                xImg = rs.getString("Category_img");
                c = new Category(xId, xName, xImg);
                t.add(c);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public Category getCollectionsByID(String xId){
        Category x = null;
        int i = Integer.parseInt(xId);
        xSql = "select * from category where CategoryID = ?";
        try{
            ps = con.prepareStatement(xSql);
            ps.setInt(1, i);
            rs = ps.executeQuery();
            String xcateName;
            String xcateImg;
            while(rs.next()){
                i = rs.getInt("CategoryID");
                xcateName = rs.getString("CategoryName");
                xcateImg= rs.getString("Category_img");
                x = new Category(i, xcateName, xcateImg);
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return x;
    }

    public void updateCategory(String xcateImg, int xcateId) {
        try {
            xSql = "update category set  cImg =? where CategoryID =?";
            ps = con.prepareStatement(xSql);
            ps.setString(1, xcateImg);
            ps.setString(2, String.valueOf(xcateId));
//            ps.setString(9, xRole);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("updateCategory: " + e.getMessage());
        }
    }

//    public static void main(String[] args) {
//        CategoryDAO categoryDAO = new CategoryDAO();
//        List<Category> categoryList = categoryDAO.getCategory();
//        for (Category category : categoryList) {
//            System.out.println(category.toString());
//        }
//    }
}
