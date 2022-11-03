package service;

import java.sql.SQLException;
import java.util.List;

import entity.SanPham;

public interface ISanPhamService {
	public boolean themSanPham(SanPham sp) throws SQLException;
	public boolean xoaSanPham(String ma) throws SQLException;
	public boolean suaSanPham( SanPham sp) throws SQLException;
	public List<SanPham> getDsSanPham() throws SQLException;
	public SanPham timMa(String ma) throws SQLException;
	public List<SanPham> timKiem(String ma, String ten, double gia, String congThuc, String loai) throws SQLException;
}
