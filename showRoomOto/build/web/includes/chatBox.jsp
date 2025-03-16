<%-- 
    Document   : chatBox
    Created on : Mar 8, 2025, 8:29:46 AM
    Author     : phuc2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<section id="chatbox">
    <div id="chatButton" onclick="toggleChat()">
        💬Hổ trợ thông tin
    </div>

    <!-- Hộp chat ẩn ban đầu -->
    <div id="chatContainer">
        <div id="chatHeader">
            <span>Chat với Gemini AI</span>
            <button onclick="toggleChat()">✖</button>
        </div>
        <div id="chatBox"></div>
        <div id="butt">
            <input type="text" id="userInput" placeholder="Nhập tin nhắn..." onkeypress="handleKeyPress(event)" />
            <button id="buttonChat" onclick="sendMessage()">Gửi</button>
        </div>
    </div>
</section>
