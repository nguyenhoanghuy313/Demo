package model;

public class AddressDAO extends myDAO{
    public void insertAddress(String street, String addressLine, String city, String region, String postalCode){
        xSql = "insert into address (Street, address_line1, city, region, postalcode) values (?, ?, ?, ?, ?);";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, street);
            ps.setString(2, addressLine);
            ps.setString(3, city);
            ps.setString(4, region);
            ps.setString(5, postalCode);
            ps.executeUpdate();
            ps.close();
        }catch (Exception e){
            System.out.println("insertAddress: " + e.getMessage());
        }
    }
}
