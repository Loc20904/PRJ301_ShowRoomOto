package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repository.AccountRep;

import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@WebServlet(name = "ForgotPasswordServlet", urlPatterns = {"/forgotPassword"})
public class ForgotPasswordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("email");

        try {
            // Kiểm tra email có tồn tại trong cơ sở dữ liệu không
            if (!AccountRep.emailExists(email)) {
                request.setAttribute("errorMessage", "Email not found!");
                request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
                return;
            }

            // Tạo token đặt lại mật khẩu
            String token = UUID.randomUUID().toString();
            // Thời gian hết hạn: 1 giờ
            long expiryTime = new Date().getTime() + 60 * 60 * 1000; // 1 giờ
            Date expiryDate = new Date(expiryTime);

            // Lưu token vào cơ sở dữ liệu
            AccountRep.savePasswordResetToken(email, token, expiryDate);

            // Gửi email chứa liên kết đặt lại mật khẩu
            String resetLink = "http://localhost:8080/showRoomOto/resetPassword?token=" + token;
            sendResetEmail(email, resetLink);

            request.setAttribute("message", "A password reset link has been sent to your email.");
            request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(ForgotPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "Error: " + ex.getMessage());
            request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(ForgotPasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "Failed to send email: " + ex.getMessage());
            request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
        }
    }

    private void sendResetEmail(String toEmail, String resetLink) throws MessagingException {
        // Cấu hình email server (dùng Gmail SMTP làm ví dụ)
        String fromEmail = "tindtde180794@fpt.edu.vn"; // Thay bằng email của bạn
        String password = "lxhp ujyd bxqc iqyi"; // Thay bằng App Password của Gmail

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Tạo session
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        // Tạo email
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject("Password Reset Request");
        message.setText("Click the link below to reset your password:\n\n" + resetLink + "\n\nThis link will expire in 1 hour.");

        // Gửi email
        Transport.send(message);
    }
}