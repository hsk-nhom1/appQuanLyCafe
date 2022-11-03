package service;

import java.sql.SQLException;
import java.util.List;

import entity.NhanVien;

public interface INhanVienService {
	public boolean themNhanVien(NhanVien nv) throws SQLException;
	public List<NhanVien> getDsNhanVien() throws SQLException;
	public boolean suaNhanVien(NhanVien nv) throws SQLException;
	public NhanVien timMa(String ma) throws SQLException;
	public boolean xoaNhanVien(String ma) throws SQLException;
	public List<NhanVien> timKiem(String ma, String ten, String sdt, String email, 
			String gioiTinh,String cmnd,int caTruc, String diaChi, double luong) throws SQLException;
}	
