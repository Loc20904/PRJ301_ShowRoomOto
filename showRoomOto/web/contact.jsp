<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Us</title>
    
    <!-- Bootstrap CSS (cho header/footer giống trang blog) -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
    <!-- Tailwind CSS (cho form Contact Us) -->
    <script src="https://cdn.tailwindcss.com"></script>
    <!-- Custom Header CSS -->
    <link rel="stylesheet" href="css/CSS_header.css">
    <!-- Custom Footer CSS -->
    <link rel="stylesheet" href="css/CSS_footer.css">
    
    <!-- Inline Styles -->
    <style>
        body {
            background: url('images/loginback.jpg') no-repeat center center/cover;
            min-height: 100vh;
            font-family: 'Arial', sans-serif;
            margin: 0;
            padding: 0;
        }

        /* Header Placeholder */
        .header-placeholder {
            min-height: 80px; /* Dự phòng chiều cao tối thiểu cho header */
        }

        /* Contact Container */
        .contact-container {
            max-width: 1200px;
            margin: 20px auto 0; /* Khoảng cách từ header xuống */
            padding: 40px 15px;
            background: rgba(255, 255, 255, 0.9); /* Nền trắng mờ */
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            position: relative;
            z-index: 1; /* Đảm bảo nằm dưới header nếu header fixed */
        }

        /* Footer */
        #dev-footer {
            position: relative;
            height: 150px;
            margin-top: 40px;
            overflow: hidden;
        }

        #dev-footer::before {
            content: "";
            position: absolute;
            width: 100%;
            height: 100%;
            top: 0;
            left: 0;
            background: url("data:image/svg+xml,%3Csvg viewBox='0 0 1200 120' xmlns='http://www.w3.org/2000/svg' preserveAspectRatio='none' style='fill:rgba(0,0,0,0.1);'%3E%3Cpath d='M0,10 C150,40 350,-30 600,30 C850,90 1050,10 1200,40 V120 H0 Z'/%3E%3C/svg%3E");
            background-size: cover;
            opacity: 0.15;
            z-index: 1;
        }

/*         Fix Header Styles 
        header {
            background-color: #1e3a8a;  Màu xanh đậm 
            padding: 15px 0;
            position: sticky;
            top: 0;
            z-index: 1000;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }*/

/*        .header-container {
            max-width: 1200px;
            margin: 0 auto;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 0 20px;
        }*/

        .logo img {
            height: 40px;
        }

        .user-menu {
            position: relative;
        }

        .dropdown-toggle {
            background-color: #ffffff;
            color: #1e3a8a;
            padding: 8px 16px;
            border-radius: 5px;
            font-weight: bold;
            cursor: pointer;
            border: none;
            display: flex;
            align-items: center;
            gap: 8px;
        }

        .dropdown-menu {
            display: none;
            position: absolute;
            right: 0;
            top: 100%;
            background-color: #ffffff;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
            min-width: 200px;
        }

        .user-menu:hover .dropdown-menu {
            display: block;
        }

        .dropdown-menu a {
            display: block;
            padding: 10px 15px;
            color: #333 !important; /* Đảm bảo chữ hiển thị */
            text-decoration: none;
            font-size: 14px;
        }

        .dropdown-menu a:hover {
            background-color: #f3f4f6;
        }

        /* Responsive Adjustments */
        @media (max-width: 768px) {
            .header-placeholder {
                min-height: 60px; /* Giảm chiều cao trên mobile */
            }
        }
    </style>
</head>
<body>
    <!-- Header Include -->
    <%@include file="includes/headerMainPage.jsp" %>


    <div class="header-placeholder"></div>

    <!-- Contact Section -->
    <section class="contact-container">
        <div class="container mx-auto px-4 py-8">
            <div class="grid grid-cols-1 md:grid-cols-2 gap-8">
                <!-- Google Map Section -->
                <div class="bg-white rounded-lg shadow-md overflow-hidden">
                    <iframe 
                        src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d14623.425462335306!2d90.3854!3d23.7994!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0x0!2zMjPCsDQ3JzU2LjYiTiA5MMKwMjMnMDcuNCJF!5e0!3m2!1sen!2sbd!4v1234567890"
                        width="100%" 
                        height="450" 
                        style="border:0;" 
                        allowfullscreen="" 
                        loading="lazy">
                    </iframe>
                    <div class="p-4 text-right">
                        <a href="#" class="text-blue-600 hover:underline">View larger map</a>
                    </div>
                </div>

                <!-- Contact Form Section -->
                <div class="bg-white rounded-lg shadow-md p-6">
                    <h2 class="text-2xl font-bold mb-6 text-center">Feedback</h2>
                    <form>
                        <div class="mb-4">
                            <label class="block text-gray-700 text-sm mb-2 flex items-center">
                                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2 text-gray-500" viewBox="0 0 20 20" fill="currentColor">
                                    <path fill-rule="evenodd" d="M10 9a3 3 0 100-6 3 3 0 000 6zm-7 9a7 7 0 1114 0H3z" clip-rule="evenodd" />
                                </svg>
                                What is your name?
                            </label>
                            <input type="text" class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" placeholder="Enter your name">
                        </div>

                        <div class="mb-4">
                            <label class="block text-gray-700 text-sm mb-2 flex items-center">
                                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2 text-gray-500" viewBox="0 0 20 20" fill="currentColor">
                                    <path d="M2.003 5.884L10 9.882l7.997-3.998A2 2 0 0016 4H4a2 2 0 00-1.997 1.884z" />
                                    <path d="M18 8.118l-8 4-8-4V14a2 2 0 002 2h12a2 2 0 002-2V8.118z" />
                                </svg>
                                Email Address
                            </label>
                            <input type="email" class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" placeholder="Enter your email">
                        </div>

                        <div class="mb-4">
                            <label class="block text-gray-700 text-sm mb-2 flex items-center">
                                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2 text-gray-500" viewBox="0 0 20 20" fill="currentColor">
                                    <path d="M2 3a1 1 0 011-1h2.153a1 1 0 01.986.836l.74 4.435a1 1 0 01-.54 1.06l-1.548.773a11.037 11.037 0 006.105 6.105l.774-1.548a1 1 0 011.059-.54l4.435.74a1 1 0 01.836.986V17a1 1 0 01-1 1h-2C7.82 18 2 12.18 2 5V3z" />
                                </svg>
                                Phone Number
                            </label>
                            <input type="tel" class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" placeholder="Enter your phone number">
                        </div>

                        <div class="mb-4">
                            <label class="block text-gray-700 text-sm mb-2 flex items-center">
                                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2 text-gray-500" viewBox="0 0 20 20" fill="currentColor">
                                    <path fill-rule="evenodd" d="M10 2a1 1 0 00-1 1v1a1 1 0 002 0V3a1 1 0 00-1-1zM4 4h3a3 3 0 006 0h3a2 2 0 012 2v9a2 2 0 01-2 2H4a2 2 0 01-2-2V6a2 2 0 012-2zm2.5 7a1.5 1.5 0 100-3 1.5 1.5 0 000 3zm2.45 4a2.5 2.5 0 10-4.9 0h4.9zM12 9a1 1 0 100 2h3a1 1 0 100-2h-3zm-1 4a1 1 0 011-1h2a1 1 0 110 2h-2a1 1 0 01-1-1z" clip-rule="evenodd" />
                                </svg>
                                Car Type
                            </label>
                            <select class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500">
                                <option>Select Car Type</option>
                                <option>Sedan</option>
                                <option>SUV</option>
                                <option>Hatchback</option>
                                <option>Sports Car</option>
                            </select>
                        </div>

                        <div class="mb-4">
                            <label class="block text-gray-700 text-sm mb-2 flex items-center">
                                <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 mr-2 text-gray-500" viewBox="0 0 20 20" fill="currentColor">
                                    <path d="M2 5a2 2 0 012-2h7a2 2 0 012 2v4a2 2 0 01-2 2H9l-3 3v-3H4a2 2 0 01-2-2V5z" />
                                    <path d="M15 7v2a4 4 0 01-4 4H9.828l-1.766 1.767c.28.149.599.233.938.233h2l3 3v-3h2a2 2 0 002-2V7a2 2 0 00-2-2h-1a2 2 0 00-2 2z" />
                                </svg>
                                Message
                            </label>
                            <textarea class="w-full px-3 py-2 border rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500" rows="4" placeholder="Enter your message"></textarea>
                        </div>

                        <button type="submit" class="w-full bg-blue-900 text-white py-3 rounded-lg hover:bg-blue-800 transition duration-300">
                            Submit Now
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </section>

    <!-- Footer -->
    <div id="dev-footer"></div>
    <%@include file="includes/footer.jsp" %>

    <!-- External Scripts -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>