package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDAO extends myDAO {

    public List<User> getAllUser() {
        List<User> t = new ArrayList<>();
        xSql = "select * from user";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xUserID, xSex, xPhone;
            String xUserName, xPassword, xEmail, xFirstName, xLastName, xDob, xRole;
            User x;
            while (rs.next()) {
                xUserID = rs.getInt("UserID");
                xUserName = rs.getString("UserName");
                xPassword = rs.getString("Password");
                xEmail = rs.getString("Email");
                xFirstName = rs.getString("FirstName");
                xLastName = rs.getString("LastName");
                xDob = rs.getString("Dob");
                xSex = rs.getInt("Sex");
                xPhone = rs.getInt("Phone");
                xRole = rs.getString("Role");
                x = new User(xUserID, xUserName, xPassword, xEmail, xFirstName, xLastName, xDob, xSex, xPhone, xRole);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }
//Nguyễn Đắc Hoàng Đạt - HE70720
    public User checkUser(String xemail, String xpassword) {
        try {
            xSql = "select *\n" +
                    "from user\n" +
                    "where Email=? and Password=?";
            ps = con.prepareStatement(xSql);
            ps.setString(1, xemail);//dau hoi so 1
            ps.setString(2, xpassword);//dau hoi so 2
            rs = ps.executeQuery();
            while (rs.next()) {
                return new User(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getInt(8),
                        rs.getInt(9),
                        rs.getString(10));
            }
        } catch (Exception e) {
            System.out.println("CheckUser: " + e.getMessage());
        }
        return null;
    }

    public User getRoleByEmail(String xemail) {
        User u = null;
        xSql = "select Role from user where Email=? ";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, xemail);
            rs = ps.executeQuery();
            while (rs.next()) {
//                String xEmail = rs.getString(1);
//                String xPassword = rs.getString(2);
                String xRole = rs.getString(1);
                u = new User(xRole);
            }
        } catch (Exception e) {
            System.out.println("getRoleByUser" + e);
        }
        return u;
    }



    public boolean checkAccountExist(String xusername,String xemail) {
        try {
            xSql = "select *\n" +
                    "from user\n" +
                    "where UserName=? and Email=?";
            ps = con.prepareStatement(xSql);
            ps.setString(1, xusername);//dau hoi so 1
            ps.setString(2, xemail);//dau hoi so 2
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("CheckAccountExist: " + e.getMessage());
        }
        return false;
    }

    public void addUser(String xusername,String xpassword, String xemail) {
        String xRole = "Customer";
        try {
            xSql = "INSERT INTO user (UserID,UserName,Password,Email,FirstName,LastName,Dob,Sex,Phone,Role)\n" +
                    "SELECT IFNULL(MAX(UserID), 0) + 1, ?,?,?,null,null,null,null,null,?\n" +
                    "FROM user";
            ps = con.prepareStatement(xSql);
            ps.setString(1, xusername);
            ps.setString(2, xpassword);
            ps.setString(3, xemail);
            ps.setString(4, xRole);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("addUser: " + e.getMessage());
        }
    }

    //Nguyễn Đắc Hoàng Đạt - HE70720

//    public static void main(String[] args) {
//        UserDAO test = new UserDAO();
//        String xemail = "hoangdatsup2003@gmail.com";
//        String xusername = "Ducdz";
////        String xpassword = "duc123";
//        boolean check = test.checkAccountExist(xusername, xemail);
//        System.out.printf(check + "");
//        }
}

//