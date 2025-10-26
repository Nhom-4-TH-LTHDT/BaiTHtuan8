import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
interface IGiaBan {
    double tinhGiaBan();
}
interface IKiemKe {
    boolean kiemTraTonKho(int soLuongToiThieu);
    void capNhatViTri(String viTriMoi);
}
abstract class Sach implements IGiaBan, IKiemKe {
    private String tieuDe;
    private String nhaXuatBan;
    private int soLuong;
    private String viTri;
    private double giaCoBan; 
    public Sach(String tieuDe, String nhaXuatBan, int soLuong, String viTri, double giaCoBan) {
        this.tieuDe = tieuDe;
        this.nhaXuatBan = nhaXuatBan;
        this.soLuong = soLuong;
        this.viTri = viTri;
        this.giaCoBan = giaCoBan;
    }
    public String getTieuDe() { return tieuDe; }
    public void setTieuDe(String tieuDe) { this.tieuDe = tieuDe; }
    public String getNhaXuatBan() { return nhaXuatBan; }
    public void setNhaXuatBan(String nhaXuatBan) { this.nhaXuatBan = nhaXuatBan; }
    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }
    public String getViTri() { return viTri; }
    public void setViTri(String viTri) { this.viTri = viTri; }
    public double getGiaCoBan() { return giaCoBan; }
    public void setGiaCoBan(double giaCoBan) { this.giaCoBan = giaCoBan; }
    @Override
    public boolean kiemTraTonKho(int soLuongToiThieu) {
        return this.soLuong >= soLuongToiThieu;
    }
    @Override
    public void capNhatViTri(String viTriMoi) {
        this.viTri = viTriMoi;
        System.out.println("-> Đã chuyển sách '" + this.tieuDe + "' đến khu vực: " + viTriMoi);
    }
    @Override
    public abstract double tinhGiaBan();
    @Override
    public String toString() {
        return "Tiêu đề: " + tieuDe +
                ", NXB: " + nhaXuatBan +
                ", Giá cơ bản: " + String.format("%,.0f", giaCoBan) +
                ", Số lượng: " + soLuong +
                ", Vị trí: " + viTri;
    }
}
class SachGiaoTrinh extends Sach {
    private String monHoc;

    public SachGiaoTrinh(String tieuDe, String nhaXuatBan, int soLuong, String viTri, double giaCoBan, String monHoc) {
        super(tieuDe, nhaXuatBan, soLuong, viTri, giaCoBan); 
        this.monHoc = monHoc;
    }

    public String getMonHoc() { return monHoc; }
    public void setMonHoc(String monHoc) { this.monHoc = monHoc; }
    @Override
    public double tinhGiaBan() {
        return this.getGiaCoBan() * 1.10; 
    }
    @Override
    public String toString() {
        return "[SÁCH GIÁO TRÌNH] " + super.toString() +
                ", Môn học: " + monHoc +
                ", Giá bán: " + String.format("%,.0f", tinhGiaBan());
    }
}
class SachTieuThuyet extends Sach {
    private String theLoai;
    private boolean laSachSeries;

    public SachTieuThuyet(String tieuDe, String nhaXuatBan, int soLuong, String viTri, double giaCoBan, String theLoai, boolean laSachSeries) {
        super(tieuDe, nhaXuatBan, soLuong, viTri, giaCoBan); 
        this.theLoai = theLoai;
        this.laSachSeries = laSachSeries;
    }

    public String getTheLoai() { return theLoai; }
    public void setTheLoai(String theLoai) { this.theLoai = theLoai; }
    public boolean isLaSachSeries() { return laSachSeries; }
    public void setLaSachSeries(boolean laSachSeries) { this.laSachSeries = laSachSeries; }
    @Override
    public double tinhGiaBan() {
        if (this.laSachSeries) {
            return this.getGiaCoBan() * 1.25;
        } else {
            return this.getGiaCoBan() * 1.20;
        }
    }
    @Override
    public String toString() {
        return "[SÁCH TIỂU THUYẾT] " + super.toString() +
                ", Thể loại: " + theLoai +
                ", Series: " + (laSachSeries ? "Có" : "Không") +
                ", Giá bán: " + String.format("%,.0f", tinhGiaBan());
    }
}
interface IQuanLySach {
    void themSach(Sach sach);
    Sach timKiemSach(String tieuDe);
    boolean xoaSach(String tieuDe);
    void hienThiDanhSach();
    void capNhatViTriSach(String tieuDe, String viTriMoi);
    void kiemTraTonKhoSach(String tieuDe, int soLuongToiThieu);
}
class QuanLySachImpl implements IQuanLySach {
    private List<Sach> danhSachSach;

    public QuanLySachImpl() {
        this.danhSachSach = new ArrayList<>();
    }
    @Override
    public void themSach(Sach sach) {
        danhSachSach.add(sach);
        System.out.println("-> Đã thêm sách: " + sach.getTieuDe());
    }
    @Override
    public Sach timKiemSach(String tieuDe) {
        for (Sach sach : danhSachSach) {
            if (sach.getTieuDe().equalsIgnoreCase(tieuDe)) {
                return sach;
            }
        }
        return null;
    }
    @Override
    public boolean xoaSach(String tieuDe) {
        Sach sachCanXoa = timKiemSach(tieuDe);
        if (sachCanXoa != null) {
            danhSachSach.remove(sachCanXoa);
            System.out.println("-> Đã xóa sách: " + tieuDe);
            return true;
        }
        System.out.println("-> Lỗi: Không tìm thấy sách với tiêu đề '" + tieuDe + "' để xóa.");
        return false;
    }
    @Override
    public void hienThiDanhSach() {
        if (danhSachSach.isEmpty()) {
            System.out.println("--- Danh sách sách trống ---");
            return;
        }
        System.out.println("\n--- DANH SÁCH SÁCH HIỆN CÓ ---");
        for (Sach sach : danhSachSach) {
            System.out.println(sach.toString());
        }
        System.out.println("---------------------------------");
    }
    @Override
    public void capNhatViTriSach(String tieuDe, String viTriMoi) {
        Sach sach = timKiemSach(tieuDe);
        if (sach != null) {
            sach.capNhatViTri(viTriMoi);
        } else {
            System.out.println("-> Lỗi: Không tìm thấy sách '" + tieuDe + "' để cập nhật vị trí.");
        }
    }
    @Override
    public void kiemTraTonKhoSach(String tieuDe, int soLuongToiThieu) {
        Sach sach = timKiemSach(tieuDe);
        if (sach != null) {
            boolean duHang = sach.kiemTraTonKho(soLuongToiThieu);
            if (duHang) {
                System.out.println("-> Tình trạng kho: Sách '" + tieuDe + "' CÒN ĐỦ HÀNG (Tồn: " + sach.getSoLuong() + " / Yêu cầu: " + soLuongToiThieu + ")");
            } else {
                System.out.println("-> Tình trạng kho: Sách '" + tieuDe + "' KHÔNG ĐỦ HÀNG (Tồn: " + sach.getSoLuong() + " / Yêu cầu: " + soLuongToiThieu + ")");
            }
        } else {
            System.out.println("-> Lỗi: Không tìm thấy sách '" + tieuDe + "' để kiểm tra kho.");
        }
    }
}
public class QuanLySach {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        IQuanLySach quanLy = new QuanLySachImpl();
        quanLy.themSach(new SachGiaoTrinh("Giải tích 1", "NXB Giáo Dục", 50, "Kệ A1", 100000, "Toán cao cấp"));
        quanLy.themSach(new SachTieuThuyet("Nhà Giả Kim", "NXB Văn Học", 120, "Kệ B3", 80000, "Phiêu lưu", false));
        quanLy.themSach(new SachTieuThuyet("Harry Potter 1", "NXB Trẻ", 200, "Kệ B5", 150000, "Fantasy", true));
        System.out.println("---------------------------------");
        while (true) {
            hienThiMenu();
            int luaChon = nhapLuaChon();

            switch (luaChon) {
                case 1:
                    themSachGiaoTrinh(quanLy);
                    break;
                case 2:
                    themSachTieuThuyet(quanLy);
                    break;
                case 3:
                    quanLy.hienThiDanhSach();
                    break;
                case 4:
                    timKiemSach(quanLy);
                    break;
                case 5:
                    xoaSach(quanLy);
                    break;
                case 6:
                    capNhatViTri(quanLy);
                    break;
                case 7:
                    kiemTraTonKho(quanLy);
                    break;
                case 0:
                    System.out.println("Đã thoát chương trình.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
    private static void hienThiMenu() {
        System.out.println("\n======= MENU QUẢN LÝ SÁCH =======");
        System.out.println("1. Thêm Sách Giáo Trình mới");
        System.out.println("2. Thêm Sách Tiểu Thuyết mới");
        System.out.println("3. Hiển thị toàn bộ danh sách sách");
        System.out.println("4. Tìm kiếm sách theo Tiêu đề");
        System.out.println("5. Xóa sách theo Tiêu đề");
        System.out.println("6. Cập nhật vị trí sách");
        System.out.println("7. Kiểm tra tồn kho");
        System.out.println("0. Thoát");
        System.out.print("Nhập lựa chọn của bạn: ");
    }
    private static int nhapLuaChon() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; 
        }
    }
    private static Object[] nhapThongTinCoBan() {
        System.out.print("Nhập tiêu đề: ");
        String tieuDe = scanner.nextLine();
        System.out.print("Nhập nhà xuất bản: ");
        String nxb = scanner.nextLine();
        System.out.print("Nhập số lượng: ");
        int soLuong = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập vị trí (Kệ A1, B2...): ");
        String viTri = scanner.nextLine();
        System.out.print("Nhập giá cơ bản: ");
        double giaCoBan = Double.parseDouble(scanner.nextLine());
        return new Object[]{tieuDe, nxb, soLuong, viTri, giaCoBan};
    }

    private static void themSachGiaoTrinh(IQuanLySach quanLy) {
        try {
            System.out.println("--- Thêm Sách Giáo Trình ---");
            Object[] thongTin = nhapThongTinCoBan();
            System.out.print("Nhập môn học: ");
            String monHoc = scanner.nextLine();

            SachGiaoTrinh sgt = new SachGiaoTrinh(
                    (String) thongTin[0], (String) thongTin[1], (int) thongTin[2],
                    (String) thongTin[3], (double) thongTin[4], monHoc
            );
            quanLy.themSach(sgt);
        } catch (Exception e) {
            System.out.println("Lỗi nhập liệu: " + e.getMessage());
        }
    }

    private static void themSachTieuThuyet(IQuanLySach quanLy) {
        try {
            System.out.println("--- Thêm Sách Tiểu Thuyết ---");
            Object[] thongTin = nhapThongTinCoBan();
            System.out.print("Nhập thể loại (Fantasy, Lãng mạn...): ");
            String theLoai = scanner.nextLine();
            System.out.print("Có phải sách series không? (true/false): ");
            boolean isSeries = Boolean.parseBoolean(scanner.nextLine());

            SachTieuThuyet stt = new SachTieuThuyet(
                    (String) thongTin[0], (String) thongTin[1], (int) thongTin[2],
                    (String) thongTin[3], (double) thongTin[4], theLoai, isSeries
            );
            quanLy.themSach(stt);
        } catch (Exception e) {
            System.out.println("Lỗi nhập liệu: " + e.getMessage());
        }
    }

    private static void timKiemSach(IQuanLySach quanLy) {
        System.out.print("Nhập tiêu đề sách cần tìm: ");
        String tieuDe = scanner.nextLine();
        Sach sach = quanLy.timKiemSach(tieuDe);
        if (sach != null) {
            System.out.println("--- Tìm thấy sách ---");
            System.out.println(sach.toString());
        } else {
            System.out.println("-> Không tìm thấy sách: " + tieuDe);
        }
    }

    private static void xoaSach(IQuanLySach quanLy) {
        System.out.print("Nhập tiêu đề sách cần xóa: ");
        String tieuDe = scanner.nextLine();
        quanLy.xoaSach(tieuDe);
    }

    private static void capNhatViTri(IQuanLySach quanLy) {
        System.out.print("Nhập tiêu đề sách cần cập nhật vị trí: ");
        String tieuDe = scanner.nextLine();
        System.out.print("Nhập vị trí MỚI: ");
        String viTriMoi = scanner.nextLine();
        quanLy.capNhatViTriSach(tieuDe, viTriMoi);
    }

    private static void kiemTraTonKho(IQuanLySach quanLy) {
        System.out.print("Nhập tiêu đề sách cần kiểm tra: ");
        String tieuDe = scanner.nextLine();
        System.out.print("Nhập số lượng tối thiểu cần kiểm tra: ");
        int soLuongMin = Integer.parseInt(scanner.nextLine());
        quanLy.kiemTraTonKhoSach(tieuDe, soLuongMin);
    }
}