package model;

import javax.management.relation.Role;
import java.io.PrintStream;
import java.text.SimpleDateFormat;

public class UserDAO extends myDAO {

    public boolean checkUser(String xemail, String xpassword) {
        try {
            xSql = "select *\n" +
                    "from user\n" +
                    "where Email=? and Password=?";
            ps = con.prepareStatement(xSql);
            ps.setString(1, xemail);//dau hoi so 1
            ps.setString(2, xpassword);//dau hoi so 2
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("CheckUser: " + e.getMessage());
        }
        return false;
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

    public static void main(String[] args) {
        UserDAO test = new UserDAO();
        String xemail = "hoangdatsup2003@gmail.com";
//        String xpassword = "duc123";
        User check = test.getRoleByEmail(xemail);
        System.out.printf(String.valueOf(check.getRole()));
//        if (check.equals("Customer")) {
//            System.out.printf("đúng vl");
//        } else {
//            System.out.printf("Sai vl");
//        }
    }
}
//