package baitap8;

/**
 * Yêu cầu 2: Lớp Con SachGiaoTrinh
 * Kế thừa Sach.
 * Không cần "implements IKiemKe" nữa, vì lớp cha Sach đã làm.
 * Kế thừa thuộc tính (monHoc, capDo) từ file Tuần 7.
 */
public class SachGiaoTrinh extends Sach {
    
    private final String monHoc;
    private final String capDo;
    // Sử dụng hằng số từ file Tuần 7
    private static final int NAM_HIEN_TAI = 2025; 

    public SachGiaoTrinh(String maSach, String tieuDe, String tacGia, int namXuatBan, int soLuong, double giaCoBan, String monHoc, String capDo) {
        // Gọi constructor của lớp cha (Sach)
        super(maSach, tieuDe, tacGia, namXuatBan, soLuong, giaCoBan);
        this.monHoc = monHoc;
        this.capDo = capDo;
    }

    // Getters riêng
    public String getMonHoc() {
        return monHoc;
    }

    public String getCapDo() {
        return capDo;
    }

    /**
     * YC 2.2: Hoàn thành IGiaBan (tinhGiaBan)
     * Logic: Dựa theo file Tuần 7 của bạn.
     */
    @Override
    public double tinhGiaBan() {
        int soNamDaXuatBan = NAM_HIEN_TAI - this.getNamXuatBan();
        if (soNamDaXuatBan < 0) soNamDaXuatBan = 0; // Đề phòng năm âm
        return this.getGiaCoBan() + (soNamDaXuatBan * 5000.0);
    }

    /**
     * YC 2.4: Cập nhật toString() (theo logic file Tuần 7)
     */
    @Override
    public String toString() {
        return super.toString() +
               "\n   Môn học: " + monHoc +
               "\n   Cấp độ: " + capDo +
               "\n   Giá Bán Ước Tính: " + String.format("%,.0f VNĐ", this.tinhGiaBan());
    }

    /**
     * Ghi đè phương thức hienThiThongTin (từ file Tuần 7)
     */
    @Override
    public void hienThiThongTin() {
        System.out.println("--- THÔNG TIN SÁCH GIÁO TRÌNH ---");
        System.out.println(this.toString());
        System.out.println("---------------------------------");
    }
}

