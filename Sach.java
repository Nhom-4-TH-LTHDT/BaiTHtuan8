/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaiTHtuan8;

/**
 *
 * @author ADMIN
 */
public abstract class Sach implements IGiaBan, IKiemKe {
    // Thuộc tính đã có
    private String maSach;
    private String tieuDe;
    private int namXuatBan;
    private int soLuong;

    private double giaCoBan;

    public Sach(String maSach, String tieuDe, int namXuatBan, int soLuong, double giaCoBan) {
        this.maSach = maSach;
        this.tieuDe = tieuDe;
        this.namXuatBan = namXuatBan;
        this.soLuong = soLuong;
        this.giaCoBan = giaCoBan;
    }

    public String getTieuDe() {
        return tieuDe;
    }
    public int getSoLuong() {
        return soLuong;
    }
    public int getNamXuatBan() {
        return namXuatBan;
    }

    public double getGiaCoBan() {
        return giaCoBan;
    }
    public void setGiaCoBan(double giaCoBan) {
        this.giaCoBan = giaCoBan;
    }

    @Override
    public String toString() {
        return "Mã: " + maSach + ", Tiêu đề: " + tieuDe + ", Năm XB: " + namXuatBan + ", SL: " + soLuong;
    }
}