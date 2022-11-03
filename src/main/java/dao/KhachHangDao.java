package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.KhachHang;
import entity.NhanVien;

public class KhachHangDao {
	Connection con;
	public KhachHangDao() {
		con = DBConnection.getInstance().getCon();
	}
	
	public boolean themKhachHang(KhachHang kh) throws SQLException {
		String sql = "insert into khachhang values(?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, kh.getMaKH());
		stmt.setString(2, kh.getTenKH());
		stmt.setString(3, kh.getSdt());
		stmt.setString(4, kh.getEmail());
		stmt.setString(5, kh.getGioiTinh());
		stmt.setString(6, kh.getDiaChi());
		int num = stmt.executeUpdate();
		return num>0;
	}
	
	public List<KhachHang> getDsKhachHang() throws SQLException {
		List<KhachHang> ds = new ArrayList<KhachHang>();
		String sql = "select * from khachhang";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			KhachHang kh = new KhachHang(
						rs.getString("maKH"),
						rs.getString("tenKH"),
						rs.getString("sdt"),
						rs.getString("email"),
						rs.getString("gioiTinh"),
						rs.getString("diachi")
					);
			ds.add(kh);
		}
		return ds;
	}
	public boolean suaKhachHang(KhachHang kh) throws SQLException {
		String sql = "update khachhang set tenkh = ?, sdt = ?, email = ?, gioiTinh = ?, diachi = ? where makh = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, kh.getTenKH());
		stmt.setString(2, kh.getSdt());
		stmt.setString(3, kh.getEmail());
		stmt.setString(4, kh.getGioiTinh());
		stmt.setString(5, kh.getDiaChi());
		stmt.setString(6, kh.getMaKH());
		int num = stmt.executeUpdate();
		return num>0;
	}
	public boolean xoaKhachHang(String ma) throws SQLException {
		String sql = "delete from khachhang where makh = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, ma);
		int num = stmt.executeUpdate();
		return num>0;
	}
}
