/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package BaiTHtuan8;

/**
 *
 * @author ADMIN
 */
import java.util.ArrayList;

public interface IQuanLySach {
    void themSach(Sach sach);
    void hienThiDanhSachSach();
    
    Sach timKiemSach(String maSach);
    boolean xoaSach(String maSach);

    public void themSach(SachGiaoTrinh sachGiaoTrinh);
}
