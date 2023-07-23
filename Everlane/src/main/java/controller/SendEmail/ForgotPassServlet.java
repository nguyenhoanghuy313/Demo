package controller.SendEmail;

import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import entity.Encryptor;
import entity.User;
import model.UserDAO;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import java.util.Random;

@WebServlet(name = "ForgotPassServlet", value = "/ForgotPassServlet")
public class ForgotPassServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fromEmail = "ghub31291@gmail.com";
        String fromPassword = "wlwgwerbsfqipnan";

        //provide Mailtrap's host address
        String host = "smtp.gmail.com";

        //configure Mailtrap's SMTP server details
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromEmail, fromPassword);
                    }
                });
        //dang nhap duoc email
        String mod = request.getParameter("mod").trim();
        String toEmail = request.getParameter("email").trim();
        UserDAO userDAO = new UserDAO();
        User getUser = userDAO.getUserByEmail(toEmail);
        if(getUser != null){
            //random mk
            int leftLimit = 97; // letter 'a'
            int rightLimit = 122; // letter 'z'
            int targetStringLength = 10;
            Random random = new Random();
            String generatedPassword = random.ints(leftLimit, rightLimit + 1)
                    .limit(targetStringLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
            Encryptor encryptor = new Encryptor();
            try {
                userDAO.ChangePass(encryptor.encryptString(generatedPassword), getUser.getUserID());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

            String emailSubject = request.getParameter("subject").trim();
            String emailContent =  request.getParameter("content").trim() +": "+ generatedPassword;
            try {
                //create a MimeMessage object
                Message message = new MimeMessage(session);

                //set From email field
                message.setFrom(new InternetAddress(fromEmail));

                //set To email field
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(toEmail));

                //set email subject field
                message.setSubject(emailSubject);
                message.setText(emailContent);

                //send the email message
                Transport.send(message);
                System.out.println("Done");

                if(mod.equals("1")){
                    request.setAttribute("success", "New password sent");
                    request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
                }else{
                    request.setAttribute("success", "New password sent");
                    request.getRequestDispatcher("forgotPasswordHighUser.jsp").forward(request, response);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            if(mod.equals("1")){
                request.setAttribute("Message", "This email is not exist");
                request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
            }else{
                request.setAttribute("Message", "This email is not exist");
                request.getRequestDispatcher("forgotPasswordHighUser.jsp").forward(request, response);
            }
        }
    }
}
