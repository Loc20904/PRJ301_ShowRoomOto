package constant;

/**
 *
 * @author thanh
 */
public class Iconstant {
    public static final String GOOGLE_CLIENT_ID = "660310171953-iuvt1hhiqfu0lvd64m1v6so0p8g7gkh4.apps.googleusercontent.com";
    public static final String GOOGLE_CLIENT_SECRET = "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";
    public static final String GOOGLE_REDIRECT_URI = "http://localhost:8080/showRoomOto/login";
    public static final String GOOGLE_GRANT_TYPE = "authorization_code";

    // ✅ Sửa URL lấy token
    public static final String GOOGLE_LINK_GET_TOKEN = "https://oauth2.googleapis.com/token";

    public static final String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
    
    public static final String FACEBOOK_APP_ID = "1645032696144522";
    public static final String FACEBOOK_APP_SECRET = "125544f766b4cdda2586b1db227d6340";
    public static final String FACEBOOK_REDIRECT_URI = "http://localhost:8080/showRoomOto/login";
    public static final String FACEBOOK_LINK_GET_TOKEN = "https://graph.facebook.com/v20.0/oauth/access_token?";
    public static final String FACEBOOK_LINK_GET_USER_INFO = "https://graph.facebook.com/me?access_token=";
}
