package baitap8;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class QuanLySachImpl implements IQuanLySach {

    private final List<Sach> danhSachSach;
    private Scanner scanner; // Dùng cho nghiệp vụ kiểm kê

    public QuanLySachImpl(Scanner scanner) {
        this.danhSachSach = new ArrayList<>();
        this.scanner = scanner;
    }

    @Override
    public void themSach(Sach sach) {
        if (sach == null) {
            System.out.println("Lỗi: Không thể thêm sách null.");
            return;
        }
        // Kiểm tra trùng lặp mã sách (dựa theo logic Tuần 7)
        if (this.timKiemSach(sach.getMaSach()) != null) {
            System.out.println("Lỗi: Sách với mã '" + sach.getMaSach() + "' đã tồn tại.");
            return;
        }
        this.danhSachSach.add(sach);
        System.out.println("Đã thêm sách thành công: " + sach.getTieuDe());
    }

    @Override
    public void hienThiDanhSach() {
        if (danhSachSach.isEmpty()) {
            System.out.println("Danh sách sách hiện đang trống.");
            return;
        }
        System.out.println("\n===== DANH SÁCH TẤT CẢ SÁCH =====");
        for (Sach sach : danhSachSach) {
            // Sử dụng phương thức hienThiThongTin từ Tuần 7
            sach.hienThiThongTin(); 
        }
        System.out.println("==================================");
    }

    @Override
    public Sach timKiemSach(String maSach) {
        for (Sach sach : danhSachSach) {
            if (sach.getMaSach().equalsIgnoreCase(maSach.trim())) {
                return sach;
            }
        }
        return null; // Không tìm thấy
    }

    @Override
    public boolean xoaSach(String maSach) {
        Iterator<Sach> iterator = danhSachSach.iterator();
        while (iterator.hasNext()) {
            Sach sach = iterator.next();
            if (sach.getMaSach().equalsIgnoreCase(maSach.trim())) {
                iterator.remove();
                System.out.println("Đã xóa sách có mã " + maSach + ".");
                return true;
            }
        }
        System.out.println("Không tìm thấy sách có mã " + maSach + " để xóa.");
        return false;
    }

    @Override
    public boolean capNhatSoLuong(String maSach, int soLuongMoi) {
        Sach sach = timKiemSach(maSach);
        if (sach != null) {
            sach.setSoLuong(soLuongMoi);
            System.out.println("Đã cập nhật số lượng sách " + maSach + " thành " + soLuongMoi);
            return true;
        }
        System.out.println("Không tìm thấy sách có mã " + maSach + " để cập nhật.");
        return false;
    }

    @Override
    public void thucHienKiemKe() {
        System.out.print("Nhập mã sách cần kiểm kê: ");
        String maSach = scanner.nextLine();
        
        Sach s = this.timKiemSach(maSach);
        
        if (s == null) {
            System.out.println("Lỗi: Không tìm thấy sách '" + maSach + "'.");
            return;
        }

        System.out.println("Đang thực hiện kiểm kê cho: " + s.getTieuDe());
        
        // 1. Kiểm tra tồn kho
        System.out.print("Nhập số lượng tồn kho tối thiểu cần kiểm tra: ");
        int soLuongToiThieu = 0;
        try {
            soLuongToiThieu = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Số lượng không hợp lệ, mặc định là 10.");
            soLuongToiThieu = 10;
        }

        // Gọi phương thức từ interface (đã được triển khai ở lớp Sach)
        boolean ketQuaKiemKho = s.kiemTraTonKho(soLuongToiThieu);
        System.out.println("   -> Kết quả kiểm tra (Tối thiểu " + soLuongToiThieu + "): " + 
                           (ketQuaKiemKho ? "Đạt yêu cầu" : "Không đạt (Hiện có: " + s.getSoLuong() + ")"));

        // 2. Cập nhật vị trí
        System.out.print("Nhập vị trí mới (hoặc bỏ trống để giữ nguyên): ");
        String viTriMoi = scanner.nextLine();
        
        if (viTriMoi != null && !viTriMoi.trim().isEmpty()) {
            // Gọi phương thức từ interface (đã được triển khai ở lớp Sach)
            s.capNhatViTri(viTriMoi);
        } else {
            System.out.println("   -> Không thay đổi vị trí.");
        }
    }
}

