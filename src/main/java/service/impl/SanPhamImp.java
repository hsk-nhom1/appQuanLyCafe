package service.impl;

import java.util.List;

import dao.SanPhamDao;
import entity.SanPham;
import service.ISanPhamService;

public class SanPhamImp implements ISanPhamService {
    SanPhamDao dao = new SanPhamDao();

    @Override
    public List<SanPham> getDsSP() {
        return dao.getAllSanPhams();
    }

    @Override
    public boolean themSanPham(SanPham sp) {
        if (dao.themSanPham(sp))
            return true;
        else
            return false;
    }

    @Override
    public boolean suaSanPham(SanPham sanPham) {
        if (dao.suaSanPham(sanPham))
            return true;
        else
            return false;
    }

    @Override
    public void xoaSanPham(String maSP) {
       dao.xoaSanPham(maSP);
    }

}
