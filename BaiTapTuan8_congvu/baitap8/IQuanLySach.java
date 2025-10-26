package baitap8;


public interface IQuanLySach {
    
    void themSach(Sach s);
    
    void hienThiDanhSach();
    
    Sach timKiemSach(String maSach);
    
    boolean xoaSach(String maSach);
    
    boolean capNhatSoLuong(String maSach, int soLuongMoi);

    void thucHienKiemKe();
}

