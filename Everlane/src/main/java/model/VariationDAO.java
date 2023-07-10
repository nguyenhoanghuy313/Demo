package model;

public class VariationDAO extends myDAO{
    public void createNewVariation( int xProductID, int xColor_ID, int xSize_ID, int xQty_in_stock, int xProduct_img_ID){
        try{
            xSql = "INSERT INTO variation (ProductID, color_ID, size_ID, qty_in_stock, product_img_ID) value (?,?,?,?,?)";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, xProductID);
            ps.setInt(2, xColor_ID);
            ps.setInt(3, xSize_ID);
            ps.setInt(4, xQty_in_stock);
            ps.setInt(5, xProduct_img_ID);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println("Create new Story: " + e.getMessage());
        }
    }

}
