/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaiTHtuan8;

/**
 *
 * @author ADMIN
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        IQuanLySach quanLy = new QuanLySachImpl();
        try (Scanner scanner = new Scanner(System.in)) {
            int choice = -1;
            
            quanLy.themSach(new SachGiaoTrinh("GT001", "Toán Cao Cấp A1", 2020, 150, 80000.0, "Toán"));
            SachTieuThuyet s2 = new SachTieuThuyet("TT001", "Hồn Ma Thành Phố", 2024, 80, 120000.0, false);
            quanLy.themSach(s2);
            SachTieuThuyet s3 = new SachTieuThuyet("TT002", "Dòng Máu Lạc Hồng - Tập 5", 2025, 200, 150000.0, true);
            quanLy.themSach(s3);
            
            do {
                System.out.println("\n===== CHƯƠNG TRÌNH QUẢN LÝ SÁCH =====");
                System.out.println("1. Hiển thị danh sách sách");
                System.out.println("2. Thêm sách mới (Không tự động)");
                System.out.println("3. Kiểm tra tồn kho và cập nhật vị trí (Test IKiemKe)");
                System.out.println("0. Thoát");
                System.out.print("Chọn chức năng: ");
                
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    choice = -1;
                }
                
                switch (choice) {
                    case 1:
                        quanLy.hienThiDanhSachSach();
                        break;
                    case 2:
                        System.out.println("Chức năng thêm sách thủ công chưa được triển khai chi tiết.");
                        break;
                    case 3:
                        System.out.println("\n--- KIỂM TRA GIAO DIỆN IKiemKe ---");
                        
                        IKiemKe kiemKeTieuThuyet = s2;
                        int soLuongCan = 100;
                        
                        boolean duTonKho = kiemKeTieuThuyet.kiemTraTonKho(soLuongCan);
                        System.out.println("Sách [" + s2.getTieuDe() + "] có " + s2.getSoLuong() + " cuốn.");
                        System.out.println("Kiểm tra tồn kho (>= " + soLuongCan + "): " + duTonKho);
                        
                        kiemKeTieuThuyet.capNhatViTri("Kho B2-Kệ 12");
                        break;
                    case 0:
                        System.out.println("Đã thoát khỏi chương trình.");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                }
            } while (choice != 0);
        }
    }
}