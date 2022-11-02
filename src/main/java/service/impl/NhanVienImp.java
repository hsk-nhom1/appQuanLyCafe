package service.impl;

import java.util.List;

import dao.NhanVienDao;
import entity.NhanVien;
import service.INhanVienService;

public class NhanVienImp implements INhanVienService {
    NhanVienDao dao = new NhanVienDao();

    @Override
    public List<NhanVien> getAllNhanVien() {
        // TODO Auto-generated method stub
        return dao.getAllNhanVien();
    }

}
