package model;
import entity.Country;

import java.util.ArrayList;
import java.util.List;
public class CountryDAO extends myDAO{
    public List<Country> getAllCountry(){
        List<Country> c = new ArrayList<>();
        xSql = "SELECT * from country";

        try {
            ps = con.prepareStatement(xSql);
            ps.executeQuery();
            int xCountryID;
            String xCountryName;
            while (rs.next()){
                xCountryID = rs.getInt("CountryID");
                xCountryName = rs.getString("CountryName");
                c.add(new Country(xCountryID,xCountryName));
            }
        }catch (Exception e){
            System.out.println("getAllCountry: " + e.getMessage());
        }
        return c;
    }
}
