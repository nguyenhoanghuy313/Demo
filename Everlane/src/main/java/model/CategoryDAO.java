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

//    public static void main(String[] args) {
//        CategoryDAO categoryDAO = new CategoryDAO();
//        List<Category> categoryList = categoryDAO.getCategory();
//        for (Category category : categoryList) {
//            System.out.println(category.toString());
//        }
//    }
}
