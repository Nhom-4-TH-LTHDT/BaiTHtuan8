package baitap8;

/**
 * Yêu cầu 2: Lớp Con SachTieuThuyet
 * Kế thừa Sach.
 * Kế thừa thuộc tính (theLoai, laSachSeries) từ file Tuần 7.
 */
public class SachTieuThuyet extends Sach {

    private final String theLoai;
    private final boolean laSachSeries;

    public SachTieuThuyet(String maSach, String tieuDe, String tacGia, int namXuatBan, int soLuong, double giaCoBan, String theLoai, boolean laSachSeries) {
        super(maSach, tieuDe, tacGia, namXuatBan, soLuong, giaCoBan);
        this.theLoai = theLoai;
        this.laSachSeries = laSachSeries;
    }

    // Getters riêng
    public String getTheLoai() {
        return theLoai;
    }

    public boolean isLaSachSeries() {
        return laSachSeries;
    }

  
    @Override
    public double tinhGiaBan() {
        double phuPhiSeries = this.laSachSeries ? 15000.0 : 0.0;
        return this.getGiaCoBan() + phuPhiSeries;
    }

    @Override
    public String toString() {
        String seriesStatus = laSachSeries ? "Có" : "Không";
        return super.toString() +
               "\n   Thể loại: " + theLoai +
               "\n   Là sách series: " + seriesStatus +
               "\n   Giá Bán Ước Tính: " + String.format("%,.0f VNĐ", this.tinhGiaBan());
    }
    
 
    @Override
    public void hienThiThongTin() {
        System.out.println("--- THÔNG TIN SÁCH TIỂU THUYẾT ---");
        System.out.println(this.toString());
        System.out.println("---------------------------------");
    }
}

