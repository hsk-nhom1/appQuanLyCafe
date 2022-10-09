package service;

import java.sql.SQLException;
import java.util.List;

import entity.NhanVien;

public interface INhanVienService {
	public boolean themNhanVien(NhanVien nv) throws SQLException;
	public List<NhanVien> getDsNhanVien() throws SQLException;
	public boolean suaNhanVien(String ma, NhanVien nv) throws SQLException;
	public NhanVien timMa(String ma) throws SQLException;
	public boolean xoaNhanVien(String ma) throws SQLException;
}	
