package service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.KhachHangDao;
import entity.KhachHang;
import service.IKhachHangService;

public class KhachHangImpl implements IKhachHangService {
    private KhachHangDao khachHangDao = new KhachHangDao();

    @Override
    public boolean themKhachHang(KhachHang kh) {
        if (kh == null)
            return false;
        if (getDsKhachHang().contains(kh))
            return false;
        return khachHangDao.themKhachHang(kh);
    }

    @Override
    public List<KhachHang> getDsKhachHang() {
        return khachHangDao.getAllKhachHang();
    }

    @Override
    public boolean suaKhachHang(KhachHang kh) {
        KhachHang khachHang = timMa(kh.getMaKH());
        if (khachHang == null || kh == null)
            return false;
        if (kh.getTenKH() == null || kh.getTenKH().equals(""))
            kh.setTenKH(khachHang.getTenKH());
        if (kh.getSdt() == null || kh.getSdt().equals(""))
            kh.setSdt(khachHang.getSdt());
        if (kh.getEmail() == null || kh.getEmail().equals(""))
            kh.setEmail(khachHang.getEmail());
        if (kh.getGioiTinh() == null || kh.getGioiTinh().equals(""))
            kh.setGioiTinh(khachHang.getGioiTinh());
        if (kh.getDiaChi() == null || kh.getDiaChi().equals(""))
            kh.setDiaChi(khachHang.getDiaChi());
        return khachHangDao.suaKhachHang(kh);
    }

    @Override
    public boolean xoaKhachHang(String ma){
        if (ma == null || ma.equals(""))
            return false;
        return khachHangDao.xoaKhachHang(ma);
    }

    @Override
    public KhachHang timMa(String ma) {
        if (ma == null || ma.equals(""))
            return null;
        for (KhachHang kh : getDsKhachHang()) {
            if (kh.getMaKH().compareToIgnoreCase(ma) == 0)
                return kh;
        }
        return null;
    }

    @Override
    public List<KhachHang> timKiem(String ma, String ten, String sdt, String email, String gioiTinh, String diaChi) {
        List<KhachHang> ds = new ArrayList<KhachHang>();
        if (timMa(ma) != null) {
            ds.add(timMa(ma));
            return ds;
        }
        for (KhachHang kh : getDsKhachHang()) {
            if ((kh.getTenKH().equalsIgnoreCase(ten) || ten == null || ten.equals("")) &&
                    (kh.getSdt().equalsIgnoreCase(sdt) || sdt == null || sdt.equals("")) &&
                    (kh.getEmail().equalsIgnoreCase(email) || email == null || email.equals("")) &&
                    (kh.getGioiTinh().equalsIgnoreCase(gioiTinh) || gioiTinh == null || gioiTinh.equals("")) &&
                    (kh.getDiaChi().equalsIgnoreCase(diaChi) || diaChi == null || diaChi.equals("")))
                ds.add(kh);
        }
        return ds;
    }

}
