package service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.TaiKhoanDao;
import entity.TaiKhoan;
import service.ITaiKhoanService;

public class TaiKhoanImpl implements ITaiKhoanService {

    private TaiKhoanDao taiKhoanDao = new TaiKhoanDao();

    public boolean themTaiKhoan(TaiKhoan tk) {
        List<TaiKhoan> ds = taiKhoanDao.getDsTaiKhoan();
        if (ds.contains(tk)) {
            return false;
        }
        taiKhoanDao.themTaiKhoan(tk);
        return true;
    }

    public List<TaiKhoan> getDsTaiKhoan() {
        return taiKhoanDao.getDsTaiKhoan();
    }

    public boolean suaTaiKhoan(String maTK, TaiKhoan tk) {
        if (timTaiKhoan(maTK) == null)
            return false;
        taiKhoanDao.suaTaiKhoan(maTK, tk);
        return true;
    }

    public TaiKhoan timTaiKhoan(String maTK) {
        TaiKhoan tk = taiKhoanDao.timTaiKhoan(maTK);
        return tk;
    }

    @Override
    public boolean xoaTaiKhoan(String maTK) {
        if (timTaiKhoan(maTK) == null) {
            return false;
        }
        taiKhoanDao.xoaTaiKhoan(maTK);
        return true;
    }

}
