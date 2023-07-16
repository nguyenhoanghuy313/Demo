package entity;

public class Address {
    int addressID;
    String addressLine;
    String city;
    String region;

    public Address(int addressID, String addressLine, String city, String region) {
        this.addressID = addressID;
        this.addressLine = addressLine;
        this.city = city;
        this.region = region;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
