package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.DatabaseInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConfirmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String token = request.getParameter("token");
        if (token == null || token.isEmpty()) {
            out.println("<h3>Lỗi: Token không hợp lệ!</h3>");
            out.println("<a href='signup.jsp'>Quay lại</a>");
            return;
        }

        try (Connection conn = DatabaseInfo.getConnection()) {
            if (conn == null) {
                out.println("<h3>Lỗi: Không thể kết nối database!</h3>");
                out.println("<a href='signup.jsp'>Quay lại</a>");
                return;
            }

            // Kiểm tra token
            String checkTokenQuery = "SELECT COUNT(*) FROM Account WHERE verification_token = ? AND is_verified = 0";
            PreparedStatement checkStmt = conn.prepareStatement(checkTokenQuery);
            checkStmt.setString(1, token);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            if (rs.getInt(1) == 0) {
                out.println("<h3>Lỗi: Token không hợp lệ hoặc đã hết hạn!</h3>");
                out.println("<a href='signup.jsp'>Quay lại</a>");
                return;
            }

            // Cập nhật trạng thái xác nhận
            String updateQuery = "UPDATE Account SET is_verified = 1, verification_token = NULL WHERE verification_token = ?";
            PreparedStatement updateStmt = conn.prepareStatement(updateQuery);
            updateStmt.setString(1, token);
            updateStmt.executeUpdate();

            out.println("<h3>Xác nhận thành công!</h3>");
            out.println("<p>Tài khoản của bạn đã được kích hoạt.</p>");
            out.println("<a href='login.jsp'>Đăng nhập ngay</a>");

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Lỗi: " + e.getMessage() + "</h3>");
            out.println("<a href='signup.jsp'>Quay lại</a>");
        }
    }
}