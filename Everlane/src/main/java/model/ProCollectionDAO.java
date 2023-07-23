package model;

import entity.ProCollection;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ProCollectionDAO extends myDAO{
    public List<ProCollection> getAllCollections(){
        List<ProCollection> t = new ArrayList<>();
        xSql = "select * from collection";
        try{
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xCollectionID;
            String xCollectionName;
            int xPromotionID;
            String xGetCollectionImg;
            String xCollection_description;
            Timestamp xDate;
            ProCollection pc;
            while(rs.next()){
                xCollectionID = rs.getInt("CollectionID");
                xCollectionName = rs.getString("CollectionName");
                xPromotionID = rs.getInt("PromotionID");
                xGetCollectionImg = rs.getString("collectionImg");
                xCollection_description = rs.getString("collection_description");
                xDate = rs.getTimestamp("create_date");
                pc = new ProCollection(xCollectionID, xCollectionName, xPromotionID , xGetCollectionImg, xCollection_description, xDate);
                t.add(pc);
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return (t);
    }

}
