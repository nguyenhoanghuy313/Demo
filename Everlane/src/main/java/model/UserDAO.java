package model;

import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends myDAO {

    public List<User> getAllUser() {
        List<User> t = new ArrayList<>();
        xSql = "select * from user";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xUserID;
            String xUserName, xPassword, xEmail, xFirstName, xLastName;
            Date xDob;
            int xSex, xRole;
            String xPhone, xUserImg;
            User x;
            while (rs.next()) {
                xUserID = rs.getInt("UserID");
                xUserName = rs.getString("UserName");
                xPassword = rs.getString("Password");
                xEmail = rs.getString("Email");
                xFirstName = rs.getString("FirstName");
                xLastName = rs.getString("LastName");
                xDob = rs.getDate("Dob");
                xSex = rs.getInt("Sex");
                xRole = rs.getInt("Role");
                xPhone = rs.getString("Phone");
                xUserImg = rs.getString("UserImg");
                x = new User(xUserID, xUserName, xPassword, xEmail, xFirstName, xLastName, xDob, xSex, xRole, xPhone, xUserImg);
                t.add(x);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

//    public User checkUser(String xemail, String xpassword) {
//        try {
//            xSql = "select *\n" +
//                    "from user\n" +
//                    "where Email=? and Password=?";
//            ps = con.prepareStatement(xSql);
//            ps.setString(1, xemail);//dau hoi so 1
//            ps.setString(2, xpassword);//dau hoi so 2
//            rs = ps.executeQuery();
//            while (rs.next()) {
//                return new User(rs.getInt(1),
//                        rs.getString(2),
//                        rs.getString(3),
//                        rs.getString(4),
//                        rs.getString(5),
//                        rs.getString(6),
//                        rs.getDate(7),
//                        rs.getInt(8),
//                        rs.getInt(9),
//                        rs.getString(10));
//            }
//        } catch (Exception e) {
//            System.out.println("CheckUser: " + e.getMessage());
//        }
//        return null;
//    }

    public User checkUser(String xEmail, String xPassword) {
        try {
            xSql = "select *\n" +
                    "from user\n" +
                    "where Email=? and Password=?";
            ps = con.prepareStatement(xSql);
            ps.setString(1, xEmail);//dau hoi so 1
            ps.setString(2, xPassword);//dau hoi so 2
            int xUserID;
            String xUserName, xFirstName, xLastName;
            Date xDob;
            int xSex, xRole;
            String xPhone, xUserImg;
            rs = ps.executeQuery();
            while (rs.next()) {
                xUserID = rs.getInt("UserID");
                xUserName = rs.getString("UserName");
                xPassword = rs.getString("Password");
                xEmail = rs.getString("Email");
                xFirstName = rs.getString("FirstName");
                xLastName = rs.getString("LastName");
                xDob = rs.getDate("Dob");
                xSex = rs.getInt("Sex");
                xRole = rs.getInt("Role");
                xPhone = rs.getString("Phone");
                xUserImg = rs.getString("UserImg");
                return new User(xUserID, xUserName, xPassword, xEmail, xFirstName, xLastName, xDob, xSex, xRole, xPhone, xUserImg);

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
                int xRole = rs.getInt(1);
                u = new User(xRole);
            }
        } catch (Exception e) {
            System.out.println("getRoleByUser" + e);
        }
        return u;
    }


    public boolean checkAccountExist(String xusername, String xemail) {
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

    public boolean checkUsername(String xusername) {
        try {
            xSql = "select *\n" +
                    "from user\n" +
                    "where UserName like ?";
            ps = con.prepareStatement(xSql);
            ps.setString(1, xusername);//dau hoi so 1
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("CheckAccountExist: " + e.getMessage());
        }
        return false;
    }

    public boolean checkEmail(String xemail) {
        try {
            xSql = "select *\n" +
                    "from user\n" +
                    "where Email like ?";
            ps = con.prepareStatement(xSql);
            ps.setString(1, xemail);//dau hoi so 1
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("CheckAccountExist: " + e.getMessage());
        }
        return false;
    }

    public boolean checkPasswordExist(String xpassword) {
        try {
            xSql = "select *\n" +
                    "from user\n" +
                    "where Password=?";
            ps = con.prepareStatement(xSql);
            ps.setString(1, xpassword);//dau hoi so 1
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("CheckPasswordExist: " + e.getMessage());
        }
        return false;
    }

    //add User for register Customer
    public void addUser(String xUsername, String xPassword, String xEmail, int xRole, Date dob) {
        try {
            xSql = "INSERT INTO user (UserName,Password,Email,FirstName,LastName,Dob,Sex,Role, Phone, UserImg) values (?, ?, ?, null, null, ?, null, ?, null, null)";
            ps = con.prepareStatement(xSql);
            ps.setString(1, xUsername);
            ps.setString(2, xPassword);
            ps.setString(3, xEmail);
            ps.setDate(4, dob);
            ps.setInt(5, xRole);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("addUser: " + e.getMessage());
        }
    }

    //create new User for highUser
    public void createNewUser(String xUsername, String xPassword, String xEmail, String xFirstName, String xLastName, Date xDob, int xSex, int xRole, String xPhone) {
        try {
            xSql = "INSERT INTO user (UserName,Password,Email,FirstName,LastName,Dob,Sex, Role, Phone, UserImg) values (?, ?, ?, ?, ?, ?, ?, ?, ?, null)";
            ps = con.prepareStatement(xSql);
            ps.setString(1, xUsername);
            ps.setString(2, xPassword);
            ps.setString(3, xEmail);
            ps.setString(4, xFirstName);
            ps.setString(5, xLastName);
            ps.setDate(6, xDob);
            ps.setInt(7, xSex);
            ps.setInt(8, xRole);
            ps.setString(9, xPhone);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Create new User: " + e.getMessage());
        }
    }


    public List<User> searchByName(String sName, String role) {
        if(role.equalsIgnoreCase("all")) {
            xSql = "select * from User where UserName like '%" + sName + "%' and Role != 4";
        }else{
            xSql = "select * from User where UserName like '%" + sName + "%' and Role = 4";
        }
        List<User> userList = new ArrayList<>();
        try {
            int xUserID;
            String xUserName, xPassword, xEmail, xFirstName, xLastName;
            Date xDob;
            int xSex, xRole;
            String xPhone, xUserImg;
            User u;
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                xUserID = rs.getInt("UserID");
                xUserName = rs.getString("UserName");
                xPassword = rs.getString("Password");
                xEmail = rs.getString("Email");
                xFirstName = rs.getString("FirstName");
                xLastName = rs.getString("LastName");
                xDob = rs.getDate("Dob");
                xSex = rs.getInt("Sex");
                xRole = rs.getInt("Role");
                xPhone = rs.getString("Phone");
                xUserImg = rs.getString("UserImg");
                u = new User(xUserID, xUserName, xPassword, xEmail, xFirstName, xLastName, xDob, xSex, xRole, xPhone, xUserImg);
                userList.add(u);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    public List<User> getUsersByRoleAndSort(String role) {
        List<User> sortedUserList = new ArrayList<>();
        if (role.equalsIgnoreCase("all")) {
            xSql = "select * from user where Role != 4 ";
        } else {
            xSql = "select * from user where Role = ?";
        }
        try {
            ps = con.prepareStatement(xSql);
            if (!role.equalsIgnoreCase("all")) {
                ps.setString(1, role);
            }
            rs = ps.executeQuery();
            int xUserID;
            String xUserName, xPassword, xEmail, xFirstName, xLastName;
            Date xDob;
            int xSex, xRole;
            String xPhone, xUserImg;
            User u;
            while ((rs.next())) {
                xUserID = rs.getInt("UserID");
                xUserName = rs.getString("UserName");
                xPassword = rs.getString("Password");
                xEmail = rs.getString("Email");
                xFirstName = rs.getString("FirstName");
                xLastName = rs.getString("LastName");
                xDob = rs.getDate("Dob");
                xSex = rs.getInt("Sex");
                xRole = rs.getInt("Role");
                xPhone = rs.getString("Phone");
                xUserImg = rs.getString("UserImg");
                u = new User(xUserID, xUserName, xPassword, xEmail, xFirstName, xLastName, xDob, xSex, xRole, xPhone, xUserImg);
                sortedUserList.add(u);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sortedUserList;
    }

    public User getUserByEmail(String xEmail) {
        xSql = "select * from user where Email = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, xEmail);
            rs = ps.executeQuery();
            int xUserID;
            String xUserName, xPassword, xFirstName, xLastName;
            Date xDob;
            int xSex, xRole;
            String xPhone, xUserImg;
            while (rs.next()) {
                xUserID = rs.getInt("UserID");
                xUserName = rs.getString("UserName");
                xPassword = rs.getString("Password");
                xEmail = rs.getString("Email");
                xFirstName = rs.getString("FirstName");
                xLastName = rs.getString("LastName");
                xDob = rs.getDate("Dob");
                xSex = rs.getInt("Sex");
                xRole = rs.getInt("Role");
                xPhone = rs.getString("Phone");
                xUserImg = rs.getString("UserImg");
                User u = new User(xUserID, xUserName, xPassword, xEmail, xFirstName, xLastName, xDob, xSex, xRole, xPhone, xUserImg);
                return u;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserById(int uID) {
        xSql = "select * from user where UserID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, uID);
            rs = ps.executeQuery();
            String xUserName, xPassword, xEmail, xFirstName, xLastName;
            Date xDob;
            int xSex, xRole;
            String xPhone, xUserImg;
            User u;
            while (rs.next()) {
                uID = rs.getInt("UserID");
                xUserName = rs.getString("UserName");
                xPassword = rs.getString("Password");
                xEmail = rs.getString("Email");
                xFirstName = rs.getString("FirstName");
                xLastName = rs.getString("LastName");
                xDob = rs.getDate("Dob");
                xSex = rs.getInt("Sex");
                xRole = rs.getInt("Role");
                xPhone = rs.getString("Phone");
                xUserImg = rs.getString("UserImg");
                u = new User(uID, xUserName, xPassword, xEmail, xFirstName, xLastName, xDob, xSex, xRole, xPhone, xUserImg);
                return u;
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteUser(String userID) {
        int uID = Integer.parseInt(userID);
        xSql = "Delete from user where UserID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, uID);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Nguyễn Đắc Hoàng Đạt - HE170720
    public static boolean isValidDate(String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date currentDate = new java.util.Date();
            java.util.Date inputDate = dateFormat.parse(date);
            return !inputDate.after(currentDate) && !inputDate.equals(currentDate); // Ngày sinh không hợp lệ
// Ngày sinh hợp lệ
        } catch (Exception e) {
            return false; // Lỗi xảy ra khi chuyển đổi ngày
        }
    }

    public void UpdateAccount(String xusername, String xpassword, String xemail, String xfirstname, String xlastname, Date xdob, int xsex, int xrole, String xphone, int xuserid) {
        try {
            xSql = "update user set UserName = ?, Password = ?, Email = ?, FirstName=?, LastName = ?,Dob = ?, Sex =?, Role=?, Phone = ? where UserID=?";
            ps = con.prepareStatement(xSql);
            ps.setString(1, xusername);
            ps.setString(2, xpassword);
            ps.setString(3, xemail);
            ps.setString(4, xfirstname);
            ps.setString(5, xlastname);
            ps.setDate(6, xdob);
            ps.setInt(7, xsex);
            ps.setInt(8, xrole);
            ps.setString(9, xphone);
            ps.setInt(10,xuserid);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("UpdateAccount: " + e.getMessage());
        }
    }

    public void ChangePass(String xpassword, int userId) {
        try {
            xSql = "update user set Password =? where UserID=?";
            ps = con.prepareStatement(xSql);
            ps.setString(1, xpassword);
            ps.setInt(2, userId);
//            ps.setString(9, xRole);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("ChangePass: " + e.getMessage());
        }
    }

    public void insertDetailed(String FirstName, String phone, String userID){
        int xUserID = Integer.parseInt(userID);
        xSql = "update swp_project.user set FirstName = " + "'"+ FirstName + "'" + ", Phone = "+ "'"+ phone + "'" +" where UserID = " +userID;
        try {
            ps = con.prepareStatement(xSql);
//            ps.setString(1, firstName);
//            ps.setString(2, phoneNum);
//            ps.setInt(1, xUserID);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println("insertDetailed: " + e.getMessage());
        }
    }

}




