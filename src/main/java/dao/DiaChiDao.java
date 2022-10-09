package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.DiaChi;

public class DiaChiDao {
	private  Connection con;
	public DiaChiDao(Connection con) {
		this.con = con;
	}
	public void themDiaChi(DiaChi dc) throws SQLException {
		String sql = "insert into DiaChi values(?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, dc.getMaDiaChi());
		stmt.setString(2, dc.getXa());
		stmt.setString(3, dc.getHuyen());
		stmt.setString(4, dc.getTinh());
		stmt.execute();
	}
	public List<DiaChi> getDsDiaChi() throws SQLException{
		List<DiaChi> ds = new ArrayList<DiaChi>();
		String sql = "select * from DiaChi";
		PreparedStatement stmt= con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			DiaChi dc = new DiaChi(
					rs.getString("maDiaChi"),
					rs.getString("xa"),
					rs.getString("huyen"),
					rs.getString("tinh")
					);
			ds.add(dc);
		}
		return ds;
	}
	public  void suaDiaChi(String ma, DiaChi dc) throws SQLException {
		String sql = "update DiaChi set huyen = ?, xa = ?, tinh = ? where maDiaChi = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, dc.getHuyen());
		stmt.setString(2, dc.getXa());
		stmt.setString(3, dc.getTinh());
		stmt.setString(4, ma);
		stmt.execute();
	}
	
	public void xoaDiaChi(String ma) throws SQLException {
		String sql = "delete from DiaChi where maDiaChi = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, ma);
		stmt.execute();
	}
}	
