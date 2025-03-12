function searchCar() {
    let query = document.getElementById("searchInput").value.trim();
    let resultContainer = document.getElementById("carResults");

    if (query === "") {
        resultContainer.innerHTML = "";
        return;
    }

    fetch(`s_search?query=${encodeURIComponent(query)}`)
            .then(response => response.json())
            .then(data => {
                resultContainer.innerHTML = "";

                if (data.length === 0) {
                    resultContainer.innerHTML = "<p>Không tìm thấy xe nào.</p>";
                } else {
                    data.forEach(car => {
                        resultContainer.innerHTML += `
                        <div class="car-item">
                            <img src="images/${car.imageURL}" alt="${car.carName}" style="width: 100%; height:100px; object-fit: cover; margin-left: 3%;">
                            <h3>${car.carName}</h3>
                            <p><b>Loại xe:</b> ${car.type}</p>
                            <p><b>Hãng:</b> ${car.brand}</p>                       
                            <p><b>Giá:</b> ${parseInt(car.price).toLocaleString()}$</p>
                            <div>${generateBookNowButton(car)}</div>
                        </div>
                    `;
                    });
                }
            })
            .catch(error => console.error("Lỗi tìm kiếm:", error));
}

function generateBookNowButton(car) {
    return `
            <form action="s_Car" method="POST">
                <input type="hidden" name="carID" value="${car.carID}">
                <button class="booknow" type="submit">thêm thông tin</button>
            </form>
        `;

}
