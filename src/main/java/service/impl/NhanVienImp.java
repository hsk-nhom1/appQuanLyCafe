package service.impl;

import java.util.ArrayList;
import java.util.List;

import dao.NhanVienDao;
import entity.NhanVien;
import service.INhanVienService;

public class NhanVienImp implements INhanVienService {
    NhanVienDao dao = new NhanVienDao();

    @Override
    public List<NhanVien> getAllNhanVien() {
        return dao.getAllNhanVien();
    }

    @Override
    public boolean themNhanVien(NhanVien nv) {
        List<NhanVien> ds = dao.getAllNhanVien();
        if(ds.contains(nv))
            return false;
        return dao.themNhanVien(nv);
    }

    @Override
    public boolean suaNhanVien(NhanVien nv) {
        NhanVien temp = timMa(nv.getMaNV());
        List<NhanVien> ds = dao.getAllNhanVien();
        if(!ds.contains(temp))
            return false;
        if(nv.getTenNV()==null || nv.getTenNV().equals(""))
            nv.setTenNV(temp.getTenNV());
        if(nv.getSdt()==null || nv.getSdt().equals(""))
            nv.setSdt(temp.getSdt());
        if(nv.getEmail()==null || nv.getEmail().equals(""))
            nv.setEmail(temp.getEmail());
        if(nv.getGioiTinh()==null || nv.getGioiTinh().equals(""))
            nv.setGioiTinh(temp.getGioiTinh());
        if(nv.getCmnd()==null || nv.getCmnd().equals(""))
            nv.setCmnd(temp.getCmnd());
        if(nv.getCaTruc() ==0);
            nv.setCaTruc(temp.getCaTruc());
        if(nv.getDiaChi()==null || nv.getDiaChi().equals(""))
            nv.setDiaChi(temp.getDiaChi());
        if(nv.getLuong()==0.0)
            nv.setLuong(temp.getLuong());
        return dao.suaNhanVien(nv);
    }

    @Override
    public NhanVien timMa(String ma) {
        List<NhanVien> ds = dao.getAllNhanVien();
        if(ma == null || ma.equals(""))
            return null;
        for(NhanVien nv : ds) {
            if(nv.getMaNV().equalsIgnoreCase(ma))
                return nv;
        }
        return null;
    }

    @Override
    public boolean xoaNhanVien(String ma) {
        NhanVien temp = timMa(ma);
        if(temp == null)
            return false;
        return dao.xoaNhanVien(ma);
    }

    @Override
    public List<NhanVien> timKiem(String ma, String ten, String sdt, String email, String gioiTinh, String cmnd,
            int caTruc, String diaChi, double luong) {
        List<NhanVien> ds = new ArrayList<>();
        if(timMa(ma)!=null) {
            NhanVien nv = timMa(ma);
            ds.add(nv);
            return ds;
        }
        String re = ".*" + ten + ".*";
        for(NhanVien nv : dao.getAllNhanVien()) {
            if( 
                (nv.getTenNV().toLowerCase().matches(re.toLowerCase()) || ten.equals("") || ten == null) &&
                (nv.getSdt().equalsIgnoreCase(sdt) || sdt.equals("") || sdt == null) &&
                (nv.getEmail().equalsIgnoreCase(email) || email.equals("") || email == null) &&
                (nv.getGioiTinh().equalsIgnoreCase(gioiTinh) || gioiTinh.equals("") || gioiTinh == null) &&
                (nv.getCmnd().equalsIgnoreCase(cmnd) || cmnd.equals("") || cmnd == null) &&
                (nv.getCaTruc() == caTruc || caTruc == 0) &&
                (nv.getDiaChi().equalsIgnoreCase(diaChi) || diaChi.equals("") || diaChi == null) &&
                (nv.getLuong() == luong || luong == 0.0) 
            )
                ds.add(nv);
        }
        return ds;
    }

}
