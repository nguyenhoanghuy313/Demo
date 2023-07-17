package model;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends myDAO {



    public void updateCategory(String xcateImg, int xcateId) {
        try {
            xSql = "update category set category_img =? where CategoryID =?;";
            ps = con.prepareStatement(xSql);
            ps.setString(1, xcateImg);
            ps.setInt(2, xcateId);
//            ps.setString(9, xRole);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("updateCategory: " + e.getMessage());
        }
    }

//    public static void main(String[] args) {
//        CategoryDAO categoryDAO = new CategoryDAO();
//        categoryDAO.updateCategory("imge", 1);
//        }

}
