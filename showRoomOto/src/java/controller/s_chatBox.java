package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import com.google.gson.*;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Paths;

public class s_chatBox extends HttpServlet {
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-pro:generateContent";
    private static final String API_KEY = "AIzaSyABHoY1vWF7VH5Z2XD82u4aVMts8C_nyAY"; // Thay bằng API key của bạn

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userMessage = request.getParameter("message");

        // Kiểm tra nếu message rỗng
        if (userMessage == null || userMessage.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Message cannot be empty\"}");
            return;
        }
        
        // Tạo JSON request body
        JsonObject requestBody = new JsonObject();
        JsonArray contents = new JsonArray();
        JsonObject contentObj = new JsonObject();
        JsonArray parts = new JsonArray();
        JsonObject textPart = new JsonObject();

        textPart.addProperty("text", userMessage);
        parts.add(textPart);
        contentObj.add("parts", parts);
        contents.add(contentObj);
        requestBody.add("contents", contents);

        // Gửi request đến Google API
        URL url = new URL(API_URL + "?key=" + API_KEY);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            byte[] input = requestBody.toString().getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Nhận response từ API
        int status = conn.getResponseCode();
        InputStream inputStream = (status < 400) ? conn.getInputStream() : conn.getErrorStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
        
        StringBuilder responseStr = new StringBuilder();
        String responseLine;
        while ((responseLine = br.readLine()) != null) {
            responseStr.append(responseLine.trim());
        }
        
        // Kiểm tra phản hồi JSON từ API
        String aiResponseText = "No response from AI";
        try {
            JsonObject jsonResponse = JsonParser.parseString(responseStr.toString()).getAsJsonObject();

            if (jsonResponse.has("candidates")) {
                JsonArray candidates = jsonResponse.getAsJsonArray("candidates");
                if (!candidates.isEmpty()) {
                    JsonObject firstCandidate = candidates.get(0).getAsJsonObject();
                    JsonObject content = firstCandidate.getAsJsonObject("content");
                    JsonArray partsArray = content.getAsJsonArray("parts");

                    if (!partsArray.isEmpty() && partsArray.get(0).getAsJsonObject().has("text")) {
                        aiResponseText = partsArray.get(0).getAsJsonObject().get("text").getAsString();
                    }
                }
            }
        } catch (Exception e) {
            aiResponseText = "Error parsing AI response";
        }

        // Trả kết quả về client
        JsonObject clientResponse = new JsonObject();
        clientResponse.addProperty("response", aiResponseText.replace("**", "<br>"));

        response.setContentType("application/json");
        response.getWriter().write(clientResponse.toString());
    }
    // Đọc nội dung file và trả về một chuỗi String
    public static void main(String[] args) {
    String filePath = "db.txt";
    String fileContent = "";
    try {
        fileContent = new String(Files.readAllBytes(Paths.get(filePath)), "UTF-8");
    } catch (Exception e) {
        fileContent = "Lỗi đọc file!";
    }
       
    }
}
