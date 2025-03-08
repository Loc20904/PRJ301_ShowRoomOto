
            function toggleChat() {
                let chatContainer = document.getElementById("chatContainer");
                chatContainer.style.display = (chatContainer.style.display === "none" || chatContainer.style.display === "") ? "block" : "none";
            }

            function sendMessage() {
                let userInput = document.getElementById("userInput").value.trim();
                if (userInput === "")
                    return;
                document.getElementById("chatBox").innerHTML += "<b>Bạn:</b> " + userInput + "<br>";
                document.getElementById("userInput").value = "";

                fetch("/testAIGrok/apiGrok", {
                    method: "POST",
                    headers: {"Content-Type": "application/x-www-form-urlencoded"},
                    body: "message=" + encodeURIComponent(userInput)
                })
                        .then(response => response.json())
                        .then(data => {
                            let botResponse = data.response || "Không có phản hồi từ AI.";
                            document.getElementById("chatBox").innerHTML += "<b>Gemini:</b> " + botResponse + "<br>";
                        })
                        .catch(error => {
                            document.getElementById("chatBox").innerHTML += "<b style='color:red;'>Lỗi:</b> Không thể kết nối AI!<br>";
                        });
            }

            function handleKeyPress(event) {
                if (event.key === "Enter") {
                    sendMessage();
                }
            }