package service;

import java.sql.SQLException;
import java.util.List;

import entity.KhachHang;

public interface IKhachHangService {
	public boolean themKhachHang(KhachHang kh);
	public List<KhachHang> getDsKhachHang();
	public boolean suaKhachHang(KhachHang kh);
	public boolean xoaKhachHang(String ma);
	public KhachHang timMa(String ma);
	public List<KhachHang> timKiem(String ma,String ten, String sdt, String email, String gioiTinh, String diaChi);
	
}
