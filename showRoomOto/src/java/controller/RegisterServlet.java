package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;
import repository.DatabaseInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.UUID;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;
import repository.AccountRep;

public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String agree = request.getParameter("iAgree");

        if (firstName == null || lastName == null || email == null || password == null || agree == null ||
            firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            out.println("<h3>Lỗi: Vui lòng điền đầy đủ thông tin!</h3>");
            out.println("<a href='signup.jsp'>Quay lại</a>");
            return;
        }

        try (Connection conn = AccountRep.getConnect()) {
            if (conn == null) {
                out.println("<h3>Lỗi: Không thể kết nối database!</h3>");
                out.println("<a href='signup.jsp'>Quay lại</a>");
                return;
            }

            // Kiểm tra email đã tồn tại
            String checkEmailQuery = "SELECT COUNT(*) FROM Account WHERE email = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkEmailQuery);
            checkStmt.setString(1, email);
            ResultSet rs = checkStmt.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                out.println("<h3>Lỗi: Email đã được sử dụng!</h3>");
                out.println("<a href='signup.jsp'>Quay lại</a>");
                return;
            }

            // Mã hóa mật khẩu
            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            // Tạo token xác nhận
            String token = UUID.randomUUID().toString();

            // Lưu vào bảng Account
            String insertQuery = "INSERT INTO Account (username, email, password, role, authority, regisDate, customerId, verification_token) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(insertQuery);
            stmt.setString(1, firstName + " " + lastName); // Gộp firstName và lastName thành username
            stmt.setString(2, email);
            stmt.setString(3, hashedPassword);
            stmt.setString(4, "user"); // Role mặc định
            stmt.setString(5, "basic"); // Authority mặc định
            stmt.setDate(6, java.sql.Date.valueOf(java.time.LocalDate.now())); // Ngày đăng ký
            stmt.setInt(7, 1); // Sử dụng customerId hợp lệ (thay 1 bằng giá trị thực tế từ bảng Customer)
            stmt.setString(8, token);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                // Gửi email xác nhận
                boolean emailSent = sendConfirmationEmail(email, token);
                if (emailSent) {
                    out.println("<h3>Đăng ký thành công!</h3>");
                    out.println("<p>Vui lòng kiểm tra email (" + email + ") để xác nhận tài khoản.</p>");
                    out.println("<a href='login.jsp'>Đăng nhập</a>");
                } else {
                    out.println("<h3>Lỗi: Không thể gửi email xác nhận!</h3>");
                    out.println("<a href='signup.jsp'>Thử lại</a>");
                }
            } else {
                out.println("<h3>Lỗi: Đăng ký thất bại!</h3>");
                out.println("<a href='signup.jsp'>Quay lại</a>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Lỗi: " + e.getMessage() + "</h3>");
            out.println("<a href='signup.jsp'>Quay lại</a>");
        }
    }

    private boolean sendConfirmationEmail(String email, String token) {
        String host = "smtp.gmail.com";
        String from = "tindtde180794@fpt.edu.vn"; // Thay bằng email của bạn
        String pass = "lxhp ujyd bxqc iqyi"; // Thay bằng App Password

        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.debug", "true");

        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected jakarta.mail.PasswordAuthentication getPasswordAuthentication() {
                return new jakarta.mail.PasswordAuthentication(from, pass);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Xác nhận đăng ký tài khoản");
            String confirmationLink = "http://localhost:8080/showRoomOto/ConfirmServlet?token=" + token;
            message.setText("Nhấn vào link để xác nhận: " + confirmationLink);

            Transport.send(message);
            System.out.println("Email sent successfully to " + email);
            return true;
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}