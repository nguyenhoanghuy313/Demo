package entity;

public class ShopOrder {
    int shop_orderID;
    int UserID;
    int AddressID;
    int Order_total;
    String Order_status;
    String recipient;
    String recipent_phone;

    public ShopOrder(int userID, int order_total, String recipient, String recipent_phone) {
        UserID = userID;
        Order_total = order_total;
        this.recipient = recipient;
        this.recipent_phone = recipent_phone;
    }

    public ShopOrder(int shop_orderID, int userID, int addressID, int order_total, String order_status, String recipient, String recipent_phone) {
        this.shop_orderID = shop_orderID;
        UserID = userID;
        AddressID = addressID;
        Order_total = order_total;
        Order_status = order_status;
        this.recipient = recipient;
        this.recipent_phone = recipent_phone;
    }

    public ShopOrder(int shop_orderID, int userID, int addressID, int order_total, String order_status) {
        this.shop_orderID = shop_orderID;
        UserID = userID;
        AddressID = addressID;
        Order_total = order_total;
        Order_status = order_status;
    }

    public ShopOrder(int userID, int order_total) {
        UserID = userID;
        Order_total = order_total;
    }

    public ShopOrder(int userID, String order_status, String recipient, String recipent_phone) {
        UserID = userID;
        Order_status = order_status;
        this.recipient = recipient;
        this.recipent_phone = recipent_phone;
    }

    public int getShop_orderID() {
        return shop_orderID;
    }

    public void setShop_orderID(int shop_orderID) {
        this.shop_orderID = shop_orderID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getAddressID() {
        return AddressID;
    }

    public void setAddressID(int addressID) {
        AddressID = addressID;
    }

    public int getOrder_total() {
        return Order_total;
    }

    public void setOrder_total(int order_total) {
        Order_total = order_total;
    }

    public String getOrder_status() {
        return Order_status;
    }

    public void setOrder_status(String order_status) {
        Order_status = order_status;
    }

    public String getRecipent_phone() {
        return recipent_phone;
    }

    public void setRecipent_phone(String recipent_phone) {
        this.recipent_phone = recipent_phone;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }
}
