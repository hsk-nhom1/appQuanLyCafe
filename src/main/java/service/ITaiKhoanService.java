package service;

import java.sql.SQLException;
import java.util.List;

import entity.TaiKhoan;

public interface ITaiKhoanService {
	public boolean themTaiKhoan(TaiKhoan tk) throws SQLException ;
	public List<TaiKhoan> getDsTaiKhoan() throws SQLException ;
	public boolean doiMatKhau(String userName, String password) throws SQLException ;
	public boolean doiUserName(String userName, String newUserName) throws SQLException;
	public TaiKhoan timMa(String maTK) throws SQLException ;
	public boolean xoaTaiKhoan(String maTK) throws SQLException;
}
