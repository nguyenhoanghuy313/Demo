/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.LoginGoogle;
/**
 * @author heaty566
 */
public class Constants {

    public static String GOOGLE_CLIENT_ID = "377412618992-d6fdoanaf6nm1ivg4atd28c4aqffvnqo.apps.googleusercontent.com";

    public static String GOOGLE_CLIENT_SECRET = "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";

    public static String GOOGLE_REDIRECT_URI = "http://localhost:8080/LoginGoogle/LoginGoogleHandler";
//    public static String GOOGLE_REDIRECT_URI = "http://localhost:8080/controller/login-servlet";

    public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";

    public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";

    public static String GOOGLE_GRANT_TYPE = "authorization_code";

}
