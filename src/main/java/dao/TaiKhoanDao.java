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

public class TaiKhoanDao {
	private static Connection con;
	public TaiKhoanDao() {
		this.con = DBConnection.getInstance().getCon();
	}
	
	public boolean themTaiKhoan(TaiKhoan tk) throws SQLException {
		String sql = "insert into TaiKhoan values(?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, tk.getUserName());
		stmt.setString(2, tk.getPassword());
		int num = stmt.executeUpdate();
		return num>0;
	}
	
	public List<TaiKhoan> getDsTaiKhoan() throws SQLException{
		List<TaiKhoan> ds = new ArrayList<TaiKhoan>();
		String sql = "select * from TaiKhoan tk join nhanvien nv on nv.maNV = tk.maNV";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			TaiKhoan tk = new TaiKhoan(
					rs.getString(1),
					rs.getString(2),
					new NhanVien(
							rs.getString(4)
							,rs.getString(5)
							,rs.getString(6)
							,rs.getString(7)
							,rs.getString(8)
							,rs.getString(9)
							,rs.getInt(10)
							,rs.getString(11)
							,rs.getDouble(12)
							)
					);
			ds.add(tk);
		}
		return ds;
	}
	public boolean doiMatKhau(String userName, String password) throws SQLException {
		String sql = "update taikhoan set password = "+password+" where username = "+ userName;
		PreparedStatement stmt = con.prepareStatement(sql);
		int num = stmt.executeUpdate();
		return num>0;
	}
	public boolean doiUserName(String userName, String newUserName) throws SQLException {
		String sql = "update taikhoan set userName = "+newUserName+" where username = "+ userName;
		PreparedStatement stmt = con.prepareStatement(sql);
		int num =stmt.executeUpdate();
		return num>0;
	}
	
	public boolean xoaTaiKhoan(String userName) throws SQLException {
		String sql = "delete from TaiKhoan where userName = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, userName);
		int num = stmt.executeUpdate();
		return num>0;
	}
}
