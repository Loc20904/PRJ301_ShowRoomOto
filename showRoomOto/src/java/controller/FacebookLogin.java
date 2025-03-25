package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import constant.Iconstant;

public class FacebookLogin {

    // Lấy access token từ Facebook
    public static String getToken(final String code) throws IOException {
        String response = Request.Post(Iconstant.FACEBOOK_LINK_GET_TOKEN)
            .bodyForm(Form.form()
                .add("client_id", Iconstant.FACEBOOK_APP_ID)
                .add("client_secret", Iconstant.FACEBOOK_APP_SECRET)
                .add("redirect_uri", Iconstant.FACEBOOK_REDIRECT_URI)
                .add("code", code)
                .build())
            .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);

        // Kiểm tra xem access_token có trong phản hồi không
        if (!jobj.has("access_token")) {
            throw new RuntimeException("Không lấy được access_token, phản hồi: " + response);
        }

        return jobj.get("access_token").getAsString();
    }

    // Lấy thông tin người dùng từ Facebook
    public static JsonObject getUserInfo(final String accessToken) throws IOException {
        String response = Request.Get(Iconstant.FACEBOOK_LINK_GET_USER_INFO + accessToken + "&fields=id,name,email")
            .execute().returnContent().asString();

        // Phân tích phản hồi JSON
        return new Gson().fromJson(response, JsonObject.class);
    }
}