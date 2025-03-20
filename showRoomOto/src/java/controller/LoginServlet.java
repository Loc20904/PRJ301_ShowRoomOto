package controller;

import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import repository.AccountRep;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
                Account acc=new Account(name, email);
                // Đăng nhập thành công, chuyển hướng về trang chính
                request.getSession().setAttribute("user", acc);
                
                response.sendRedirect("index.jsp"); // Đổi thành trang chính của bạn

            } else {
                response.getWriter().println("Failed to get access token.");
            }
        } else {
            response.getWriter().println("No code found in the request.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String rememberMe = request.getParameter("remember_me"); // Nếu không check thì sẽ là null
        Account acc;
        try {
            acc = AccountRep.checkLogin(email, password);
            if (acc != null) {
                // Nếu chọn "Remember Me", lưu email vào cookie
                if ("on".equals(rememberMe)) {
                    Cookie emailCookie = new Cookie("userEmail", email);
                    emailCookie.setMaxAge(7 * 24 * 60 * 60); // Lưu trong 7 ngày
                    Cookie passCookie = new Cookie("pw", password);
                    passCookie.setMaxAge(7 * 24 * 60 * 60); // Lưu trong 7 ngày
                    response.addCookie(emailCookie);
                }

                // Lưu user vào session
                request.getSession().setAttribute("user", acc);

                String redirectUrl = (String) request.getSession().getAttribute("redirectUrl");
            if (redirectUrl != null) {
                request.getSession().removeAttribute("redirectUrl"); // Xóa URL sau khi sử dụng
                response.sendRedirect(redirectUrl); // Chuyển hướng đến trang đặt xe
            } else {
                response.sendRedirect("index.jsp"); // Chuyển hướng đến trang chính nếu không có URL
            }
            } else {
                // Nếu sai thông tin, quay lại trang login với thông báo lỗi
                request.setAttribute("errorMessage", "Invalid email or password!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }
}
