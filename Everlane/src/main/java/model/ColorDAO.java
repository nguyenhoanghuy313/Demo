package model;

import java.util.ArrayList;
import java.util.List;

public class ColorDAO extends myDAO{

    public List<Color> getAllColors() {
        List<Color> t = new ArrayList<>();
        xSql = "select * from color;";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xColor_ID;
            String xColor_Name;
            Color x;
            while (rs.next()) {
                xColor_ID = rs.getInt("color_ID");
                xColor_Name = rs.getString("color_Name");
                x = new Color(xColor_ID, xColor_Name);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }




    public Color getColorsByID(String id) {
        Color t = null;
        xSql = "select * from color where color_ID = '"+id+"'";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xColor_ID;
            String xColor_Name;
            Color x;
            while (rs.next()) {
                xColor_ID = rs.getInt("color_ID");
                xColor_Name = rs.getString("color_Name");
                t = new Color(xColor_ID, xColor_Name);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public Color getColorsByName(String name) {
        Color t = null;
        xSql = "select * from color where color_Name like '"+name+"'";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xColor_ID;
            String xColor_Name;
            Color x;
            while (rs.next()) {
                xColor_ID = rs.getInt("color_ID");
                xColor_Name = rs.getString("color_Name");
                t = new Color(xColor_ID, xColor_Name);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }
}
