package service;

import java.sql.SQLException;
import java.util.List;

import entity.TaiKhoan;

public interface ITaiKhoanService {
	public boolean themTaiKhoan(TaiKhoan tk) throws SQLException ;
	public List<TaiKhoan> getDsTaiKhoan() throws SQLException ;
	public boolean suaTaiKhoan(String maTK, TaiKhoan tk) throws SQLException ;
	public TaiKhoan timTaiKhoan(String maTK) throws SQLException ;
	public boolean xoaTaiKhoan(String maTK) throws SQLException;
}
