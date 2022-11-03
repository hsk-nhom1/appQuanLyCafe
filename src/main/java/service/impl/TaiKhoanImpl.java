package service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.TaiKhoanDao;
import entity.TaiKhoan;
import service.ITaiKhoanService;

public class TaiKhoanImpl implements ITaiKhoanService{
	
	private TaiKhoanDao taiKhoanDao;
	public TaiKhoanImpl() {
		taiKhoanDao = new TaiKhoanDao();
	}

	public boolean themTaiKhoan(TaiKhoan tk) throws SQLException {
		List<TaiKhoan> ds = taiKhoanDao.getDsTaiKhoan();
		if(ds.contains(tk)) {
			return false;
		}
		return taiKhoanDao.themTaiKhoan(tk);
	}

	public List<TaiKhoan> getDsTaiKhoan() throws SQLException {
		return taiKhoanDao.getDsTaiKhoan();
	}

	public TaiKhoan timMa(String maTK) throws SQLException {
		List<TaiKhoan> ds= getDsTaiKhoan();
		if(maTK==null || maTK.equals(""))
			return null;
		TaiKhoan tk = ds.stream()
				.filter(o -> o.getUserName().equals(maTK))
				.findAny()
				.orElse(null);
		return tk;
	}

	@Override
	public boolean xoaTaiKhoan(String maTK) throws SQLException {
		if(timMa(maTK) == null) {
			return false;
		}
		return taiKhoanDao.xoaTaiKhoan(maTK);
	}

	@Override
	public boolean doiMatKhau(String userName, String password) throws SQLException {
		if(timMa(userName).getPassword().equals(password))
			return false;
		if(password.equals(""))
			return false;
		if(timMa(userName)==null)
			return false;
		return taiKhoanDao.doiMatKhau(userName, password);
	}

	@Override
	public boolean doiUserName(String userName, String newUserName) throws SQLException {
		if(timMa(userName) == null)
			return false;
		return taiKhoanDao.doiUserName(userName, newUserName);
	}

}
