package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.Ban;
import entity.SanPham;

public class BanDao {
	private Connection con;
	public BanDao() {
		con = DBConnection.getInstance().getCon();
	}
	public boolean themBan(Ban ban) throws SQLException {
		String sql = "insert ban values(?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, ban.getMaBan());
		stmt.setString(2, ban.getTenBan());
		stmt.setString(3, ban.getTrangThai());
		int num = stmt.executeUpdate();
		return num>0;
	}
	
	public boolean doiTrangThai(String ma, String trangThai) throws SQLException{
		String sql = "update sanpham set trangthai = ? where maban = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, trangThai);
		stmt.setString(2, ma);
		int num = stmt.executeUpdate();
		return num>0;
	}
	
	public List<Ban> getDsBan() throws SQLException{
		List<Ban> ds = new ArrayList<Ban>();
		String sql = "select * from ban";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Ban ban = new Ban();
			ban.setMaBan(rs.getString(1));
			ban.setTenBan(rs.getString(2));
			ban.setTrangThai(rs.getString(3));
			ds.add(ban);
		}
		return ds;
	}
	
}
