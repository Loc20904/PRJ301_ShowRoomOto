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
import java.util.List;
import model.Car;
import repository.CarRep;

public class s_chatBox extends HttpServlet {
    private static final String API_URL = "https://generativelanguage.googleapis.com/v1/models/gemini-1.5-pro:generateContent";
    private static final String API_KEY = "AIzaSyABHoY1vWF7VH5Z2XD82u4aVMts8C_nyAY"; // Thay bằng API key của bạn

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        StringBuilder requestData = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            requestData.append(line);
        }

        JsonObject requestJson = JsonParser.parseString(requestData.toString()).getAsJsonObject();
        String userMessage = requestJson.has("message") ? requestJson.get("message").getAsString() : "";

        if (userMessage.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Message cannot be empty\"}");
            return;
        }

        // Lấy danh sách xe từ database
        List<Car> allCars = CarRep.getall();
        StringBuilder carData = new StringBuilder();
        for (Car car : allCars) {
            carData.append("Car Name: ").append(car.getCarName())
                    .append(", Type: ").append(car.getType())
                    .append(", Brand: ").append(car.getBrand())
                    .append(", Price: ").append(car.getPrice())
                    .append(", Year: ").append(car.getYearOfManufacture())
                    .append(", Stock: ").append(car.getStockQuantity())
                    .append("\n");
        }

        if (carData.length() == 0) {
            response.getWriter().write("{\"response\": \"Vui lòng hỏi câu liên quan đến danh sách xe.\"}");
            return;
        }

        // Chuẩn bị request gửi AI
        JsonObject requestBody = new JsonObject();
        JsonArray contents = new JsonArray();
        JsonObject contentObj = new JsonObject();
        JsonArray parts = new JsonArray();

        JsonObject textPart = new JsonObject();
        textPart.addProperty("text", "Database Car:\n" + carData.toString() + "\nUser Question: " + userMessage);
        parts.add(textPart);
        contentObj.add("parts", parts);
        contents.add(contentObj);
        requestBody.add("contents", contents);

        // Gửi request đến AI
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

        // Nhận response từ AI
        int status = conn.getResponseCode();
        InputStream inputStream = (status < 400) ? conn.getInputStream() : conn.getErrorStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));

        StringBuilder responseStr = new StringBuilder();
        while ((line = br.readLine()) != null) {
            responseStr.append(line.trim());
        }

        String aiResponseText = "Vui lòng hỏi câu liên quan đến danh sách xe.";
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
            aiResponseText = "Lỗi xử lý phản hồi từ AI.";
        }

        JsonObject clientResponse = new JsonObject();
        clientResponse.addProperty("response", aiResponseText.replace("**", "<br>").replace("database", "Showroom"));

        response.setContentType("application/json");
        response.getWriter().write(clientResponse.toString());
    }
}
