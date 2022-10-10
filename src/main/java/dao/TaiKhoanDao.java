package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.TaiKhoan;

public class TaiKhoanDao {
	private static Connection con;
	public TaiKhoanDao(Connection con) {
		this.con = con;
	}
	
	public void themTaiKhoan(TaiKhoan tk) throws SQLException {
		String sql = "insert into TaiKhoan values(?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, tk.getUserName());
		stmt.setString(2, tk.getPassword());
		stmt.executeUpdate();
	}
	
	public List<TaiKhoan> getDsTaiKhoan() throws SQLException{
		List<TaiKhoan> ds = new ArrayList<TaiKhoan>();
		String sql = "select * from TaiKhoan";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			TaiKhoan tk = new TaiKhoan(
					rs.getString("userName"),
					rs.getString("password")
					);
			ds.add(tk);
		}
		return ds;
	}
	public void suaTaiKhoan(String maTK, TaiKhoan tk) throws SQLException {
		String sql = "update TaiKhoan set password = ? where userName = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, tk.getPassword());
		stmt.setString(2, maTK);
		stmt.executeUpdate();
	}
	public void xoaTaiKhoan(String userName) throws SQLException {
		String sql = "delete from TaiKhoan where userName = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, userName);
		stmt.execute();
	}
}
