package service;

import java.util.List;

import entity.SanPham;

public interface ISanPhamService {
    public List<SanPham> getDsSP();
    public boolean themSanPham(SanPham sp);
    public boolean suaSanPham(SanPham sanPham);
    public void xoaSanPham(String maSP);
}
