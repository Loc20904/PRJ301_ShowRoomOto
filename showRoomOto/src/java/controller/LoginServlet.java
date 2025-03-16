package controller;

import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String code = request.getParameter("code");

        if (code != null) {
            GoogleLogin gg = new GoogleLogin();
            String accessToken = gg.getToken(code);
            
            if (accessToken != null) {
                System.out.println("Access Token: " + accessToken);
                
                // Lấy thông tin người dùng
                JsonObject userInfo = gg.getUserInfo(accessToken);
                System.out.println("User Info: " + userInfo);

                // Ví dụ: Lấy email và tên
                String email = userInfo.get("email").getAsString();
                String name = userInfo.get("name").getAsString();
                
                // Đăng nhập thành công, chuyển hướng về trang chính
                request.getSession().setAttribute("email", email);
                request.getSession().setAttribute("name", name);
                response.sendRedirect("index.jsp"); // Đổi thành trang chính của bạn

            } else {
                response.getWriter().println("Failed to get access token.");
            }
        } else {
            response.getWriter().println("No code found in the request.");
        }
    }
}
