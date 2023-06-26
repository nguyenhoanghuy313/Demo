package model;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//import java.util.Date;
import java.util.List;
import java.util.Random;

public class UserDAO extends myDAO {

    public List<User> getAllUser() {
        List<User> t = new ArrayList<>();
        xSql = "select * from user";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            int xUserID, xSex, xPhone;
            String xUserName, xPassword, xEmail, xFirstName, xLastName, xRole;
            Date xDob;
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
                        rs.getDate(7),
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

    public void addUser(String xusername,String xpassword, String xemail, String xfirstname, String xlastname, String xdob, int xsex, int xphone) {
        String xRole = "Customer";
        try {
            xSql = "INSERT INTO user (UserID,UserName,Password,Email,FirstName,LastName,Dob,Sex,Phone,Role)\n" +
                    "SELECT IFNULL(MAX(UserID), 0) + 1, ?,?,?,?,?,?,?,LPAD(?, 10, '0'),?\n" +
                    "FROM user";
            ps = con.prepareStatement(xSql);
            ps.setString(1, xusername);
            ps.setString(2, xpassword);
            ps.setString(3, xemail);
            ps.setString(4, xfirstname);
            ps.setString(5, xlastname);
            ps.setDate(6, Date.valueOf(xdob));
            ps.setInt(7, xsex);
            ps.setInt(8, xphone);
            ps.setString(9, xRole);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("addUser: " + e.getMessage());
        }
    }
    //Nguyễn Đắc Hoàng Đạt - HE70720
//Đoàn Phan Hưng - HE170721
    public List<User> searchByName(String sName) {
        xSql = "select * from User where UserName like '%" + sName + "%'";
        List<User> userList = new ArrayList<>();
        try {
            int xUserID;
            String xUserName;
            String xPassword;
            String xEmail;
            String xFirstName;
            String xLastName;
            Date xDob;
            int xGender;
            int xPhone;
            String xRole;

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
                xGender = rs.getInt("Sex");
                xPhone = rs.getInt("Phone");
                xRole = rs.getString("Role");
                u = new User(xUserID, xUserName, xPassword, xEmail, xFirstName, xLastName, xDob, xGender, xPhone, xRole);
                userList.add(u);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }


    //Đoàn Phan Hưng - HE170721
    public void setUserID(User u) {
        xSql = "INSERT INTO user (UserID, UserName, Password, Email, FirstName, LastName, Dob, Sex, Phone, Role) " +
                "                     VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(xSql, Statement.RETURN_GENERATED_KEYS);
            Random rand = new Random();
            int userID = rand.nextInt(900) + 100;
            ps.setInt(1, userID);
            ps.setString(2, u.getUserName());
            ps.setString(3, u.getPassword());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getFirstName());
            ps.setString(6, u.getLastName());
            ps.setDate(7, (java.sql.Date) u.getDob());
            ps.setInt(8, u.getSex());
            ps.setInt(9, u.getPhone());
            ps.setString(10, u.getRole());
            ps.executeUpdate();
            // Lấy giá trị UserID được tự động tạo
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int generatedUserID = generatedKeys.getInt(1);
                u.setUserID(generatedUserID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getUsersByRoleAndSort(String role) {
        List<User> sortedUserList = new ArrayList<>();
        if(role.equalsIgnoreCase("all")){
            xSql = "select * from user";
        }else {
            xSql = "select * from user where Role = ?";
        }
        try {
            ps = con.prepareStatement(xSql);
            if(!role.equalsIgnoreCase("all")){
                ps.setString(1, role);
            }
            rs = ps.executeQuery();
            int xUserID;
            String xUserName;
            String xPassword;
            String xEmail;
            String xFirstName;
            String xLastName;
            Date xDob;
            int xGender;
            int xPhone;
            String xRole;
            User u;
            while ((rs.next())){
                xUserID = rs.getInt("UserID");
                xUserName = rs.getString("UserName");
                xPassword = rs.getString("Password");
                xEmail = rs.getString("Email");
                xFirstName = rs.getString("FirstName");
                xLastName = rs.getString("LastName");
                xDob = rs.getDate("Dob");
                xGender = rs.getInt("Sex");
                xPhone = rs.getInt("Phone");
                xRole = rs.getString("Role");
                u = new User(xUserID, xUserName, xPassword, xEmail,
                        xFirstName, xLastName, xDob, xGender, xPhone, xRole);
                sortedUserList.add(u);
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return sortedUserList;
    }

    public User getUserById(String xID){
        User u = null;
        int uID = Integer.parseInt(xID);
        xSql = "";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, uID);
            rs = ps.executeQuery();
            String xUserName;
            String xPassword;
            String xEmail;
            String xFirstName;
            String xLastName;
            Date xDob;
            int xGender;
            int xPhone;
            String xRole;
            while (rs.next()){
                uID = rs.getInt("UserID");
                xUserName = rs.getString("UserName");
                xPassword = rs.getString("Password");
                xEmail = rs.getString("Email");
                xFirstName = rs.getString("FirstName");
                xLastName = rs.getString("LastName");
                xDob = rs.getDate("Dob");
                xGender = rs.getInt("Sex");
                xPhone = rs.getInt("Phone");
                xRole = rs.getString("Role");
                u = new User(uID, xUserName, xPassword, xEmail, xFirstName, xLastName, xDob, xGender, xPhone, xRole);
            }
            rs.close();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return u;
    }

    public void deleteUser(String userID){
        int uID = Integer.parseInt(userID);
        xSql = "Delete from user where UserID = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, uID);
            ps.executeUpdate();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
//Nguyễn Đắc Hoàng Đạt - HE170720
    public static boolean isValidDate(String date) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date currentDate = new java.util.Date();
            java.util.Date inputDate = dateFormat.parse(date);
            if (inputDate.after(currentDate) || inputDate.equals(currentDate)) {
                return false; // Ngày sinh không hợp lệ
            }
            return true; // Ngày sinh hợp lệ
        } catch (Exception e) {
            return false; // Lỗi xảy ra khi chuyển đổi ngày
        }
    }

//    public void updateUser() {
//        try {
//            String strUpdate="update Schedule set SubjectCode=?,TeacherID=?,ClassID=?, Slot=?,Date=?, Status=? where ID=?";
////            pstm=cnn.prepareStatement(strUpdate);
//            pstm.setString(1, subject);
//            pstm.setString(2, id);
//            pstm.setString(3, classid);
//            pstm.setString(4, slot);
//            pstm.setDate(5, java.sql.Date.valueOf(date));
//            pstm.setString(6, status);
//            pstm.setInt(7, Integer.parseInt(stt));
//            pstm.execute();
//        } catch (Exception e) {
//            System.out.println("updateSchedule:" + e.getMessage());
//        }
//    }

//Nguyễn Đắc Hoàng Đạt - HE170720

//    public static void main(String[] args) {
//        UserDAO test = new UserDAO();
//        String date = "2024-12-12";
////        String date = "12-12-2020";
//        if (isValidDate(date)) {
//            System.out.println("Ngày sinh hợp lệ");
//        } else {
//            System.out.println("Ngày sinh không hợp lệ");
//        }
//    }
}




