package controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import constant.Iconstant;
import entity.GoogleAccount;

public class GoogleLogin {
    public static String getToken(final String code) throws IOException {
        String response = Request.Post(Iconstant.GOOGLE_LINK_GET_TOKEN)
            .bodyForm(Form.form()
                .add("client_id", Iconstant.GOOGLE_CLIENT_ID)
                .add("client_secret", Iconstant.GOOGLE_CLIENT_SECRET)
                .add("redirect_uri", Iconstant.GOOGLE_REDIRECT_URI)
                .add("code", code)
                .add("grant_type", Iconstant.GOOGLE_GRANT_TYPE)
                .build())
            .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        
        // Kiểm tra xem access_token có trong phản hồi không
        if (!jobj.has("access_token")) {
            throw new RuntimeException("Không lấy được access_token, phản hồi: " + response);
        }

        return jobj.get("access_token").getAsString();
    }

    public static JsonObject getUserInfo(final String accessToken) throws IOException {
        String response = Request.Get(Iconstant.GOOGLE_LINK_GET_USER_INFO + accessToken)
            .execute().returnContent().asString();
        
        // Phân tích phản hồi JSON
        return new Gson().fromJson(response, JsonObject.class);
    }
}
