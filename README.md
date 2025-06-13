📌 User Management API – DTS Fresher Developer 2025
REST API quản lý user với các chức năng đăng nhập, phân quyền, soft delete, và đóng gói bằng Docker.

📁 Technologies Used
Công nghệ	        Mô tả
Java(21)	        Ngôn ngữ lập trình chính
Spring Boot 3.5.0	Framework backend RESTful API
Spring Security 	Xác thực và phân quyền
MySQL	            Cơ sở dữ liệu
Docker	            Đóng gói và triển khai service
JWT	                Authentication Token
Lombok	            Tự động generate constructor/get/set

🧑‍💼 Tính năng chính
Thêm/Sửa/Xóa (soft delete) người dùng
Đăng nhập, đăng ký, xác thực bằng JWT
Phân quyền người dùng (ROLE_USER, ROLE_ADMIN)
Tải avatar người dùng

🛠️ Cài đặt thủ công (Local)
1. Clone project
git clone https://github.com/MNghiaDev/dts.git
2. Cấu hình database (src/main/resources/application.properties)
spring:
  datasource:
    url: "jdbc:mysql://localhost:3306/dts"
    username: your_username
    password: your_password
jwt.secret=your_jwt_secret
3. Tạo database userdb trong MySQL
Run file - "dts.sql"
4. Kiểm tra API (qua Postman)
Danh sách user có phân trang
http://localhost:8080/api/dts/users/list?page=0&size=5

Tạo mới user
http://localhost:8080/api/dts/users/create

Sửa thông tin user
http://localhost:8080/api/dts/users/update/2

Lấy thông tin 1 user
http://localhost:8080/api/dts/users/4

Upload ảnh cho user
http://localhost:8080/api/dts/users/upload/6

Tạo token
http://localhost:8080/api/dts/auth/token

Kiểm tra token
http://localhost:8080/api/dts/auth/inspect

Refresh token
http://localhost:8080/api/dts/auth/refresh

Logout 
http://localhost:8080/api/dts/auth/logout

🐳 Docker đóng gói service
1. Build image
docker build -t user-dts:0.0.1 .
2. Run bằng Docker
docker run -d -p 8080:8080 user-dts:0.0.1

Docker hub
1. Push image
docker push mnghia02/user-dts:0.0.1
2. Run
docker run -p 8080:8080 mnghia02/user-dts:0.0.1


🔐 Tài khoản mặc định
{
  "username": "admin",
  "password": "admin",
  "role": "ADMIN"
}

📎 Ghi chú thêm
Mật khẩu được mã hóa bằng BCrypt
Ảnh đại diện (avatar) được lưu trong thư mục /uploads

📬 Thông tin liên hệ
Họ và tên: Hoàng Minh Nghĩa
Email: nm204633@gmail.com
Github: https://github.com/MNghiaDev