/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BaiTHtuan8;

/**
 *
 * @author ADMIN
 */
import java.util.ArrayList;

public class QuanLySachImpl implements IQuanLySach {
    private final ArrayList<Sach> danhSachSach;

    public QuanLySachImpl() {
        this.danhSachSach = new ArrayList<>();
    }

    public void themSach(Sach sach) {
        danhSachSach.add(sach);
    }

    public void hienThiDanhSachSach() {
        System.out.println("\n--- DANH SÁCH SÁCH HIỆN CÓ ---");
        if (danhSachSach.isEmpty()) {
            System.out.println("Danh sách trống.");
            return;
        }

        for (Sach sach : danhSachSach) {
            System.out.println(sach);
        }
    }

    public Sach timKiemSach(String maSach) {
        return null;
    }

    public boolean xoaSach(String maSach) {
        return false;
    }
}