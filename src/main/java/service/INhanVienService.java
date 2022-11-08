package service;

import java.util.List;

import entity.NhanVien;

public interface INhanVienService {
    public List<NhanVien> getAllNhanVien();

    public boolean themNhanVien(NhanVien nv);

    public boolean suaNhanVien(NhanVien nv);

    public NhanVien timMa(String ma);

    public boolean xoaNhanVien(String ma);

    public List<NhanVien> timKiem(String ma, String ten, String sdt, String email,
            String gioiTinh, String cmnd, int caTruc, String diaChi, double luong);
}
