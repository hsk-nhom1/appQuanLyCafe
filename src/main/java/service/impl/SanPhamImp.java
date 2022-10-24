package service.impl;

import java.util.List;

import dao.SanPhamDao;
import entity.SanPham;
import service.ISanPhamService;

public class SanPhamImp implements ISanPhamService{
    SanPhamDao dao = new SanPhamDao();

    @Override
    public List<SanPham> getDsSP() {
        return dao.getAllSanPhams();
    }

}
