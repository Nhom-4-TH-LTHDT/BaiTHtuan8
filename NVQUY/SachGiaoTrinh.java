/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaiTHtuan8;

/**
 *
 * @author ADMIN
 */
public class SachGiaoTrinh extends Sach {
    private String monHoc;

    public SachGiaoTrinh(String maSach, String tieuDe, int namXuatBan, int soLuong, double giaCoBan, String monHoc) {
        super(maSach, tieuDe, namXuatBan, soLuong, giaCoBan);
        this.monHoc = monHoc;
    }

    @Override
    public double tinhGiaBan() {
        int namHienTai = 2025;
        int soNamDaXuatBan = namHienTai - getNamXuatBan();
        return getGiaCoBan() + (soNamDaXuatBan * 5000.0);
    }

    @Override
    public boolean kiemTraTonKho(int soLuongToiThieu) {
        return getSoLuong() >= soLuongToiThieu;
    }

    @Override
    public void capNhatViTri(String viTriMoi) {
        System.out.println("Đã chuyển sách [" + getTieuDe() + "] đến khu vực: " + viTriMoi + ".");
    }

    @Override
    public String toString() {
        return super.toString() + ", Môn học: " + monHoc +
               ", Giá Bán Ước Tính: " + String.format("%,.0f VNĐ", tinhGiaBan());
    }
}