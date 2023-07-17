package model;

import java.util.ArrayList;
import java.util.List;

public class SizeDAO extends  myDAO{


    public Size getSizebyId(String id) {
        Size t = null;
        xSql = "select * from size where size_ID = '"+id+"';";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xSize_ID;
            String xSize_Name;
            Size x;
            while (rs.next()) {
                xSize_ID = rs.getInt("size_ID");
                xSize_Name = rs.getString("size_Name");
                t = new Size(xSize_ID, xSize_Name);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }

    public Size getSizebyName(String name) {
        Size t = null;
        xSql = "select * from size where size_Name like '"+name+"';";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xSize_ID;
            String xSize_Name;
            Size x;
            while (rs.next()) {
                xSize_ID = rs.getInt("size_ID");
                xSize_Name = rs.getString("size_Name");
                t = new Size(xSize_ID, xSize_Name);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (t);
    }



}
