package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.NhanVien;
import entity.TaiKhoan;

public class NhanVienDao {
	private Connection con;
	public NhanVienDao() {
		con = DBConnection.getInstance().getCon();
	}
	
	public boolean themNhanVien(NhanVien nv) throws SQLException {
		String sql = "insert into NhanVien values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, nv.getMaNV());
		stmt.setString(2, nv.getTenNV());
		stmt.setString(3, nv.getSdt());
		stmt.setString(4, nv.getEmail());
		stmt.setString(5, nv.getGioiTinh());
		stmt.setString(6, nv.getCmnd());
		stmt.setInt(7, nv.getCaTruc());
		stmt.setString(8, nv.getDiaChi());
		stmt.setDouble(9, nv.getLuong());
		int num = stmt.executeUpdate();
		return num>0;
	}
	
	public List<NhanVien> getDsNhanVien() throws SQLException {
		List<NhanVien> ds = new ArrayList<NhanVien>();
		String sql = "select * from NhanVien";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			NhanVien nv = new NhanVien(
						rs.getString("maNV"),
						rs.getString("tenNV"),
						rs.getString("sdt"),
						rs.getString("email"),
						rs.getString("gioiTinh"),
						rs.getString("cmnd"),
						rs.getInt("caTruc"),
						rs.getString("diachi"),
						rs.getDouble("luong")
					);
			ds.add(nv);
		}
		return ds;
	}
	
	public boolean suaNhanVien(NhanVien nv) throws SQLException {
		String sql = "update NhanVien set tenNV = ?, sdt = ?, email = ?, gioiTinh = ?, cmnd = ?, caTruc = ?, diachi = ?,luong = ? where maNV = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, nv.getTenNV());
		stmt.setString(2, nv.getSdt());
		stmt.setString(3, nv.getEmail());
		stmt.setString(4, nv.getGioiTinh());
		stmt.setString(5, nv.getCmnd());
		stmt.setInt(6, nv.getCaTruc());
		stmt.setString(7, nv.getDiaChi());
		stmt.setDouble(8, nv.getLuong());
		stmt.setString(9, nv.getMaNV());
		int num = stmt.executeUpdate();
		return num>0;
	}
	public boolean xoaNhanVien(String ma) throws SQLException {
		String sqlnull = "update hoadon set manv = null where manv = ?" ;
		PreparedStatement stmtnull = con.prepareStatement(sqlnull);
		stmtnull.setString(1, ma);
		stmtnull.execute();
		String sql = "delete from NhanVien where maNV = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, ma);
		int num = stmt.executeUpdate();
		return num>0;
	}
	
}
