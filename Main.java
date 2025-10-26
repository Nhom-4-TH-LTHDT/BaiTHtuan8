import java.util.*;

// -------------------- INTERFACES --------------------

// Interface tính giá bán
interface IGiaBan {
    double tinhGiaBan();
}

// Interface kiểm kê kho
interface IKiemKe {
    boolean kiemTraTonKho(int soLuongToiThieu);
    void capNhatViTri(String viTriMoi);
}

// Interface quản lý sách
interface IQuanLySach {
    void themSach(Sach s);
    void hienThiDanhSach();
    Sach timKiemSach(String maSach);
    void xoaSach(String maSach);
}

// -------------------- ABSTRACT CLASS --------------------
abstract class Sach implements IGiaBan, IKiemKe {
    private String maSach;
    private String tieuDe;
    private String tacGia;
    private int namXuatBan;
    private int soLuong;
    private double giaCoBan;

    public Sach(String maSach, String tieuDe, String tacGia, int namXuatBan, int soLuong, double giaCoBan) {
        this.maSach = maSach;
        this.tieuDe = tieuDe;
        this.tacGia = tacGia;
        this.namXuatBan = namXuatBan;
        this.soLuong = soLuong;
        this.giaCoBan = giaCoBan;
    }

    // Getters & Setters
    public String getMaSach() { return maSach; }
    public String getTieuDe() { return tieuDe; }
    public String getTacGia() { return tacGia; }
    public int getNamXuatBan() { return namXuatBan; }
    public int getSoLuong() { return soLuong; }
    public double getGiaCoBan() { return giaCoBan; }

    public void setMaSach(String maSach) { this.maSach = maSach; }
    public void setTieuDe(String tieuDe) { this.tieuDe = tieuDe; }
    public void setTacGia(String tacGia) { this.tacGia = tacGia; }
    public void setNamXuatBan(int namXuatBan) { this.namXuatBan = namXuatBan; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public void setGiaCoBan(double giaCoBan) { this.giaCoBan = giaCoBan; }

    // Abstract method
    public abstract double tinhGiaBan();

    // Triển khai phương thức từ IKiemKe
    @Override
    public boolean kiemTraTonKho(int soLuongToiThieu) {
        return this.soLuong >= soLuongToiThieu;
    }

    @Override
    public void capNhatViTri(String viTriMoi) {
        System.out.println("📦 Đã chuyển sách \"" + tieuDe + "\" đến khu vực: " + viTriMoi);
    }

    @Override
    public String toString() {
        return "Mã: " + maSach + " | Tên: " + tieuDe + " | Tác giả: " + tacGia +
                " | Năm: " + namXuatBan + " | SL: " + soLuong + " | Giá cơ bản: " + giaCoBan;
    }
}

// -------------------- LỚP CON 1 --------------------
class SachGiaoTrinh extends Sach {
    private String monHoc;

    public SachGiaoTrinh(String maSach, String tieuDe, String tacGia, int namXuatBan,
                         int soLuong, double giaCoBan, String monHoc) {
        super(maSach, tieuDe, tacGia, namXuatBan, soLuong, giaCoBan);
        this.monHoc = monHoc;
    }

    @Override
    public double tinhGiaBan() {
        int nam = 2025 - getNamXuatBan();
        return getGiaCoBan() + nam * 5000;
    }

    @Override
    public String toString() {
        return "[Giáo Trình] " + super.toString() + " | Môn học: " + monHoc +
               " | Giá bán: " + tinhGiaBan();
    }
}

// -------------------- LỚP CON 2 --------------------
class SachTieuThuyet extends Sach {
    private String theLoai;
    private boolean laSachSeries;

    public SachTieuThuyet(String maSach, String tieuDe, String tacGia, int namXuatBan,
                          int soLuong, double giaCoBan, String theLoai, boolean laSachSeries) {
        super(maSach, tieuDe, tacGia, namXuatBan, soLuong, giaCoBan);
        this.theLoai = theLoai;
        this.laSachSeries = laSachSeries;
    }

    @Override
    public double tinhGiaBan() {
        return getGiaCoBan() + (laSachSeries ? 15000 : 0);
    }

    @Override
    public String toString() {
        return "[Tiểu Thuyết] " + super.toString() + " | Thể loại: " + theLoai +
               " | Series: " + (laSachSeries ? "Có" : "Không") +
               " | Giá bán: " + tinhGiaBan();
    }
}

// -------------------- QUẢN LÝ SÁCH --------------------
class QuanLySachImpl implements IQuanLySach {
    private List<Sach> danhSach = new ArrayList<>();

    @Override
    public void themSach(Sach s) {
        danhSach.add(s);
        System.out.println(" Đã thêm sách: " + s.getTieuDe());
    }

    @Override
    public void hienThiDanhSach() {
        System.out.println("\n📚 DANH SÁCH SÁCH:");
        for (Sach s : danhSach) {
            System.out.println(s);
        }
    }

    @Override
    public Sach timKiemSach(String maSach) {
        for (Sach s : danhSach) {
            if (s.getMaSach().equalsIgnoreCase(maSach)) return s;
        }
        return null;
    }

    @Override
    public void xoaSach(String maSach) {
        Sach s = timKiemSach(maSach);
        if (s != null) {
            danhSach.remove(s);
            System.out.println(" Đã xóa sách có mã: " + maSach);
        } else {
            System.out.println(" Không tìm thấy sách để xóa.");
        }
    }
}

// -------------------- MAIN --------------------
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        IQuanLySach quanLy = new QuanLySachImpl();

        while (true) {
            System.out.println("\n========= MENU =========");
            System.out.println("1. Thêm Sách Giáo Trình");
            System.out.println("2. Thêm Sách Tiểu Thuyết");
            System.out.println("3. Hiển Thị Danh Sách");
            System.out.println("4. Tìm Kiếm Sách");
            System.out.println("5. Xóa Sách");
            System.out.println("6. Thoát");
            System.out.print("Chọn: ");
            int chon = sc.nextInt();
            sc.nextLine();

            switch (chon) {
                case 1:
                    System.out.print("Mã: "); String ma1 = sc.nextLine();
                    System.out.print("Tên: "); String ten1 = sc.nextLine();
                    System.out.print("Tác giả: "); String tg1 = sc.nextLine();
                    System.out.print("Năm XB: "); int nam1 = sc.nextInt();
                    System.out.print("Số lượng: "); int sl1 = sc.nextInt();
                    System.out.print("Giá cơ bản: "); double gb1 = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Môn học: "); String mh = sc.nextLine();
                    quanLy.themSach(new SachGiaoTrinh(ma1, ten1, tg1, nam1, sl1, gb1, mh));
                    break;

                case 2:
                    System.out.print("Mã: "); String ma2 = sc.nextLine();
                    System.out.print("Tên: "); String ten2 = sc.nextLine();
                    System.out.print("Tác giả: "); String tg2 = sc.nextLine();
                    System.out.print("Năm XB: "); int nam2 = sc.nextInt();
                    System.out.print("Số lượng: "); int sl2 = sc.nextInt();
                    System.out.print("Giá cơ bản: "); double gb2 = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Thể loại: "); String tl = sc.nextLine();
                    System.out.print("Là sách series (true/false): ");
                    boolean ser = sc.nextBoolean();
                    quanLy.themSach(new SachTieuThuyet(ma2, ten2, tg2, nam2, sl2, gb2, tl, ser));
                    break;

                case 3:
                    quanLy.hienThiDanhSach();
                    break;

                case 4:
                    System.out.print("Nhập mã sách cần tìm: ");
                    String maTim = sc.nextLine();
                    Sach kq = quanLy.timKiemSach(maTim);
                    if (kq != null) System.out.println(" Tìm thấy: " + kq);
                    else System.out.println(" Không tìm thấy sách!");
                    break;

                case 5:
                    System.out.print("Nhập mã sách cần xóa: ");
                    String maXoa = sc.nextLine();
                    quanLy.xoaSach(maXoa);
                    break;

                case 6:
                    System.out.println(" Thoát chương trình!");
                    return;
            }
        }
    }
}