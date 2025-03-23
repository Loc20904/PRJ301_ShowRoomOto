function toggleChat() {
    let chatContainer = document.getElementById("chatContainer");
    chatContainer.style.display = (chatContainer.style.display === "none" || chatContainer.style.display === "") ? "block" : "none";
}

async function sendMessage() {
    let userInput = document.getElementById("userInput").value.trim();
    if (userInput === "") return;

    // Dữ liệu mô phỏng database
    let databaseScript = `
        INSERT INTO [dbo].[Car] ([carName], [type], [brand], [description], [price], [year_of_manufacture], [weight], [stockQuantity], [imageURL]) VALUES
        (N'VinFast Fadil', N'regular', N'VinFast', N'Compact hatchback', 400000, 2023, 1200, 20, 'VinFast.jpg'),
        (N'Toyota Camry', N'regular', N'Toyota', N'Reliable sedan', 600000, 2023, 1400, 25, 'ToyotaCamry.jpg'),
        (N'Ferrari 488', N'sports', N'Ferrari', N'Italian sports car', 3000000, 2023, 1500, 3, 'Ferrari488.jpg');
    `;

    let requestData = {
        message: userInput,
//        database: databaseScript,
        instruction: "Chỉ trả lời dựa trên dữ liệu trong database. Nếu không có thông tin, hãy trả lời: 'Vui lòng hỏi câu liên quan đến danh sách xe ô tô có sẵn.'"
    };

    let chatBox = document.getElementById("chatBox");
    chatBox.innerHTML += `<div><b>Bạn:</b> ${userInput}</div>`;
    document.getElementById("userInput").value = "";

    try {
        let response = await fetch("/showRoomOto/s_chatBox", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(requestData)
        });

        let data = await response.json();
        let botResponse = data.response || "Không có phản hồi từ AI.";
        chatBox.innerHTML += `<div><b>OTOZO:</b> ${botResponse}</div>`;
    } catch (error) {
        console.error("Lỗi kết nối AI:", error);
        chatBox.innerHTML += `<div style="color:red;"><b>Lỗi:</b> Không thể kết nối AI!</div>`;
    }

    chatBox.scrollTop = chatBox.scrollHeight;
}

function handleKeyPress(event) {
    if (event.key === "Enter") {
        event.preventDefault();
        sendMessage();
    }
}
