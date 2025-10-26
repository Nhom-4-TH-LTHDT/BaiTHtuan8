package baitap8;

/**
 * Yêu cầu 1.3: Abstract Class Sach
 * Lớp cơ sở cho các loại sách, triển khai các giao diện chung.
 */
public abstract class Sach implements IGiaBan, IKiemKe {

    // Các thuộc tính cơ bản
    private String maSach;
    private String tieuDe;
    private String tacGia;
    private int namXuatBan;
    private int soLuong;
    private double giaCoBan; // Thuộc tính mới của Tuần 8

    // Constructors
    public Sach() {
    }

    public Sach(String maSach, String tieuDe, String tacGia, int namXuatBan, int soLuong, double giaCoBan) {
        this.maSach = maSach;
        this.tieuDe = tieuDe;
        this.tacGia = tacGia;
        this.namXuatBan = namXuatBan;
        this.soLuong = soLuong;
        this.giaCoBan = giaCoBan;
    }

    // --- Triển khai Interface IGiaBan ---
    // Để lại phương thức này là abstract để các lớp con định nghĩa
    @Override
    public abstract double tinhGiaBan();

    // --- Triển khai Interface IKiemKe (Yêu cầu 2.3) ---
    // Cung cấp logic chung cho tất cả các loại sách
    @Override
    public boolean kiemTraTonKho(int soLuongToiThieu) {
        // Trả về true nếu soLuong hiện có của sách ≥ soLuongToiThieu
        return this.soLuong >= soLuongToiThieu;
    }

    @Override
    public void capNhatViTri(String viTriMoi) {
        // In ra màn hình thông báo
        System.out.println("Đã chuyển sách [" + this.tieuDe + "] đến khu vực: " + viTriMoi);
    }

    // --- Getters và Setters ---
    // (Bắt buộc phải có để các lớp con truy cập, ví dụ: getGiaCoBan())

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public int getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaCoBan() {
        return giaCoBan;
    }

    public void setGiaCoBan(double giaCoBan) {
        this.giaCoBan = giaCoBan;
    }

    // --- Phương thức toString và hiển thị ---

    @Override
    public String toString() {
        return "Mã sách: " + maSach +
                "\n   Tiêu đề: " + tieuDe +
                "\n   Tác giả: " + tacGia +
                "\n   Năm: " + namXuatBan + ", Số lượng: " + soLuong +
                "\n   Giá cơ bản: " + String.format("%,.0f VNĐ", giaCoBan);
    }

    // Phương thức này được các lớp con ghi đè
    public void hienThiThongTin() {
        System.out.println("---------------------------------");
        System.out.println(this.toString());
        System.out.println("---------------------------------");
    }
}
