package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import db.DBConnection;
import entity.SanPham;

public class SanPhamDao {
	private Connection con;
	public SanPhamDao() {
		con = DBConnection.getInstance().getCon();
	}
	
	public boolean themSanPham(SanPham sp) throws SQLException {
		String sql = "insert sanpham values(?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, sp.getMaSP());
		stmt.setString(2, sp.getTenSP());
		stmt.setDouble(3, sp.getGia());
		stmt.setString(4, sp.getCongThuc());
		stmt.setString(5, sp.getLoai());
		int num = stmt.executeUpdate();
		return num>0;
	}
	public boolean xoaSanPham(String ma) throws SQLException{
		String sql = "detele from sanpham where masp = "+ma;
		PreparedStatement stmt = con.prepareStatement(sql);
		int num = stmt.executeUpdate();
		return num >0;
	}
	
	public boolean suaSanPham(SanPham sp) throws SQLException{
		String sql = "update sanpham set tensp = ?, gia = ?, congthuc = ?, loai = ? where masp = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, sp.getTenSP());
		stmt.setDouble(2, sp.getGia());
		stmt.setString(3, sp.getCongThuc());
		stmt.setString(4, sp.getLoai());
		stmt.setString(5, sp.getMaSP());
		int num = stmt.executeUpdate();
		return num>0;
	}
	
	public List<SanPham> getDsSanPham() throws SQLException{
		List<SanPham> ds = new ArrayList<SanPham>();
		String sql = "select * from sanpham";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			SanPham sp = new SanPham();
			sp.setMaSP(rs.getString(1));
			sp.setTenSP(rs.getString(2));
			sp.setGia(rs.getDouble(3));
			sp.setCongThuc(rs.getString(4));
			sp.setLoai(rs.getString(5));
			ds.add(sp);
		}
		return ds;
	}
}
