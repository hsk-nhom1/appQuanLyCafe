package service.impl;

import java.util.List;

import dao.KhachHangDao;
import entity.KhachHang;
import service.IKhachHangService;

public class KhachHangImp implements IKhachHangService{
    KhachHangDao dao = new  KhachHangDao();

    @Override
    public List<KhachHang> getDsKhachHang() {
        return dao.getAllKhachHang();
    }

}
