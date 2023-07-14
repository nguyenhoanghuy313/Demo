package entity;

public class Payment {
    int paymentID;
    int userID;
    int orderDetailID;
    int type;
    int status;

    public Payment(int paymentID, int userID, int orderDetailID, int type, int status) {
        this.paymentID = paymentID;
        this.userID = userID;
        this.orderDetailID = orderDetailID;
        this.type = type;
        this.status = status;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
