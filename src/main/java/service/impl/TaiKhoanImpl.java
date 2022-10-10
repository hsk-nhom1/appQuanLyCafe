package service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.TaiKhoanDao;
import entity.TaiKhoan;
import service.ITaiKhoanService;

public class TaiKhoanImpl implements ITaiKhoanService{
	
	private TaiKhoanDao taiKhoanDao;
	public TaiKhoanImpl(Connection con) {
		taiKhoanDao = new TaiKhoanDao(con);
	}

	public boolean themTaiKhoan(TaiKhoan tk) throws SQLException {
		List<TaiKhoan> ds = taiKhoanDao.getDsTaiKhoan();
		if(ds.contains(tk)) {
			return false;
		}
		taiKhoanDao.themTaiKhoan(tk);
		return true;
	}

	public List<TaiKhoan> getDsTaiKhoan() throws SQLException {
		return taiKhoanDao.getDsTaiKhoan();
	}

	public boolean suaTaiKhoan(String maTK, TaiKhoan tk) throws SQLException{
		if(timTaiKhoan(maTK) == null)
			return false;
		taiKhoanDao.suaTaiKhoan(maTK, tk);
		return true;
	}

	public TaiKhoan timTaiKhoan(String maTK) throws SQLException {
		List<TaiKhoan> ds= getDsTaiKhoan();
		TaiKhoan tk = ds.stream()
				.filter(o -> o.getUserName().equals(maTK))
				.findAny()
				.orElse(null);
		return tk;
	}

	@Override
	public boolean xoaTaiKhoan(String maTK) throws SQLException {
		if(timTaiKhoan(maTK) == null) {
			return false;
		}
		taiKhoanDao.xoaTaiKhoan(maTK);
		return true;
	}

}
