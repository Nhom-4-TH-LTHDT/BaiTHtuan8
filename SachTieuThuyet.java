/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaiTHtuan8;

/**
 *
 * @author ADMIN
 */
public class SachTieuThuyet extends Sach {
    private boolean laSachSeries;

    public SachTieuThuyet(String maSach, String tieuDe, int namXuatBan, int soLuong, double giaCoBan, boolean laSachSeries) {
        super(maSach, tieuDe, namXuatBan, soLuong, giaCoBan);
        this.laSachSeries = laSachSeries;
    }

    @Override
    public double tinhGiaBan() {
        double phuPhiSeries = laSachSeries ? 15000.0 : 0.0;
        return getGiaCoBan() + phuPhiSeries;
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
        String seriesInfo = laSachSeries ? " (Series)" : "";
        return super.toString() + ", TT: TTiểu Thuyết" + seriesInfo +
               ", Giá Bán Ước Tính: " + String.format("%,.0f VNĐ", tinhGiaBan());
    }
}