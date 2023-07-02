package model;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

public class CollectionDAO extends myDAO{
    public Collection getAllCollections(){
        Collection x = null;
        xSql = "select * from collection";
        try{
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xCollectionID;
            String xCollectionName;
            int xPromotionID;
            String xGetCollectionImg;
            String xCollection_description;
            while(rs.next()){
                xCollectionID = rs.getInt("CollectionID");
                xCollectionName = rs.getString("CollectionName");
                xPromotionID = rs.getInt("PromotionID");
                xGetCollectionImg = rs.getString("collectionImg");
                xCollection_description = rs.getString("collection_description");
                x = new Collection(xCollectionID, xCollectionName, xPromotionID , xGetCollectionImg, xCollection_description);
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return x;
    }

    public Collection getCollections(String xId){
        Collection x = null;
        int i = Integer.parseInt(xId);
        xSql = "select * from collection where CollectionID = ?";
        try{
            ps = con.prepareStatement(xSql);
            ps.setInt(1, i);
            rs = ps.executeQuery();
            String xCollectionName;
            int xPromotionID;
            String xGetCollectionImg;
            String xCollection_description;
            while(rs.next()){
                i = rs.getInt("CollectionID");
                xCollectionName = rs.getString("CollectionName");
                xPromotionID = rs.getInt("PromotionID");
                xGetCollectionImg = rs.getString("collectionImg");
                xCollection_description = rs.getString("collection_description");
                x = new Collection(i, xCollectionName, xPromotionID , xGetCollectionImg, xCollection_description);
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return x;
    }

    public void updateCollection(String xcolname, String xcolImg, String xcoldescrip, int xcolId) {
        try {
            xSql = "update collection set  CollectionName=?, collectionImg =?,collection_description=? where collectionID =?";
            ps = con.prepareStatement(xSql);
            ps.setString(1, xcolname);
            ps.setString(2, xcolImg);
            ps.setString(3, xcoldescrip);
            ps.setInt(4, xcolId);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("updateCollection: " + e.getMessage());
        }
    }
}
