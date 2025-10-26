package baitap8;

import java.util.Scanner;

public class Test {
    
    private static IQuanLySach quanLy;
    private static Scanner scanner;

    public static void main(String[] args) {
        // Khởi tạo
        scanner = new Scanner(System.in);
        quanLy = new QuanLySachImpl(scanner);

        // Thêm dữ liệu mẫu (dựa trên tệp Tuần 7 của bạn)
        themDuLieuMau();

        boolean running = true;
        while (running) {
            hienThiMenuChinh();
            
            // Sửa cảnh báo 1: Khai báo mà không khởi tạo
            int luaChon; 
            
            try {
                System.out.print("Vui lòng chọn chức năng (0-6): ");
                luaChon = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Lựa chọn không hợp lệ, vui lòng nhập số.");
                continue; // Quay lại vòng lặp
            }

            // Sửa cảnh báo 2: Dùng "Rule Switch" với cú pháp ->
            switch (luaChon) {
                case 1 -> quanLy.hienThiDanhSach();
                case 2 -> themMoiSach();
                case 3 -> timSachTheoMa();
                case 4 -> xoaSachTheoMa();
                case 5 -> capNhatSoLuongSach();
                case 6 -> quanLy.thucHienKiemKe();
                case 0 -> {
                    running = false;
                    System.out.println("Đã thoát chương trình. Tạm biệt!");
                }
                default -> System.out.println("Lựa chọn không hợp lệ, vui lòng chọn từ 0 đến 6.");
            }
        }
        
        scanner.close();
    }

    private static void hienThiMenuChinh() {
        System.out.println("\n======== MENU QUẢN LÝ SÁCH (TUẦN 8) ========");
        System.out.println("1. Hiển thị danh sách sách");
        System.out.println("2. Thêm sách mới");
        System.out.println("3. Tìm sách theo mã");
        System.out.println("4. Xóa sách theo mã");
        System.out.println("5. Cập nhật số lượng sách");
        System.out.println("6. Thực hiện kiểm kê (Vị trí / Tồn kho)");
        System.out.println("0. Thoát");
        System.out.println("=============================================");
    }

    private static void themDuLieuMau() {
        System.out.println("--- THÊM DỮ LIỆU MẪU VÀO DANH SÁCH ---");
        quanLy.themSach(new SachGiaoTrinh("GT01", "Cơ sở dữ liệu", "Phạm Văn C", 2021, 50, 80000.0, "Cơ sở dữ liệu", "Đại học"));
        quanLy.themSach(new SachTieuThuyet("TT01", "Đắc nhân tâm", "Dale Carnegie", 1936, 100, 55000.0, "Phát triển bản thân", false));
        quanLy.themSach(new SachGiaoTrinh("GT02", "Giải tích 1", "Trần Đình D", 2020, 75, 90000.0, "Toán học", "Đại học"));
        quanLy.themSach(new SachTieuThuyet("TT02", "Harry Potter: Hòn đá phù thủy", "J.K. Rowling", 1997, 120, 150000.0, "Huyền ảo", true));
    }

    private static void themMoiSach() {
        System.out.println("\n--- Thêm Sách Mới ---");
        System.out.print("Chọn loại sách (1: Sách Giáo Trình, 2: Sách Tiểu Thuyết): ");
        String loai = scanner.nextLine();

        if (!loai.equals("1") && !loai.equals("2")) {
            System.out.println("Lựa chọn không hợp lệ. Hủy thêm sách.");
            return;
        }

        System.out.print("Nhập mã sách: ");
        String maSach = scanner.nextLine();
        System.out.print("Nhập tiêu đề: ");
        String tieuDe = scanner.nextLine();
        System.out.print("Nhập tác giả: ");
        String tacGia = scanner.nextLine();

        int namXuatBan;
        int soLuong;
        double giaCoBan;
        try {
            System.out.print("Nhập năm xuất bản: ");
            namXuatBan = Integer.parseInt(scanner.nextLine());
            System.out.print("Nhập số lượng: ");
            soLuong = Integer.parseInt(scanner.nextLine());
            System.out.print("Nhập giá cơ bản: ");
            giaCoBan = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Nhập số không hợp lệ. Hủy thêm sách.");
            return;
        }
        
        // Sửa cảnh báo 3: Khai báo mà không khởi tạo
        Sach sachMoi; 

        if (loai.equals("1")) {
            System.out.print("Nhập môn học: ");
            String monHoc = scanner.nextLine();
            System.out.print("Nhập cấp độ: ");
            String capDo = scanner.nextLine();
            sachMoi = new SachGiaoTrinh(maSach, tieuDe, tacGia, namXuatBan, soLuong, giaCoBan, monHoc, capDo);
        
        } else { // Đã kiểm tra ở trên nên chỉ còn là "2"
            System.out.print("Nhập thể loại: ");
            String theLoai = scanner.nextLine();
            System.out.print("Là sách series? (true/false): ");
            boolean laSeries = Boolean.parseBoolean(scanner.nextLine());
            sachMoi = new SachTieuThuyet(maSach, tieuDe, tacGia, namXuatBan, soLuong, giaCoBan, theLoai, laSeries);
        }
        
        quanLy.themSach(sachMoi);
    }

    private static void timSachTheoMa() {
        System.out.println("\n--- Tìm Sách ---");
        System.out.print("Nhập mã sách cần tìm: ");
        String ma = scanner.nextLine();
        Sach s = quanLy.timKiemSach(ma);
        if (s != null) {
            System.out.println("Tìm thấy sách:");
            s.hienThiThongTin();
        } else {
            System.out.println("Không tìm thấy sách có mã " + ma);
        }
    }

    private static void xoaSachTheoMa() {
        System.out.println("\n--- Xóa Sách ---");
        System.out.print("Nhập mã sách cần xóa: ");
        String ma = scanner.nextLine();
        quanLy.xoaSach(ma);
    }

    private static void capNhatSoLuongSach() {
        System.out.println("\n--- Cập Nhật Số Lượng ---");
        System.out.print("Nhập mã sách cần cập nhật: ");
        String ma = scanner.nextLine();
        try {
            System.out.print("Nhập số lượng mới: ");
            int slMoi = Integer.parseInt(scanner.nextLine());
            quanLy.capNhatSoLuong(ma, slMoi);
        } catch (NumberFormatException e) {
            System.out.println("Lỗi: Số lượng nhập không hợp lệ.");
        }
    }
}

