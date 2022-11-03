package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import db.DBConnection;
import entity.CTHoaDon;
import entity.HoaDon;
import entity.SanPham;

public class CTHoaDonDao {
	Connection con;
	public CTHoaDonDao() {
		con = DBConnection.getInstance().getCon();
	}
	
	public boolean themCTHoaDon (CTHoaDon cthd) throws SQLException {
		String sql = "insert cthoadon values(?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, cthd.getSanPham().getMaSP());
		stmt.setString(2, cthd.getHoaDon().getMaHD());
		stmt.setInt(3, cthd.getSoLuong());
		stmt.setString(4, cthd.getGhiChu());
		int num = stmt.executeUpdate();
		return num>0;
	}
	public List<CTHoaDon> getDsCTHoaDon () throws SQLException{
		List<CTHoaDon> ds = new ArrayList<CTHoaDon>();
		String sql = "select * from hoadon hd \r\n"
				+ "join CTHoaDon cthd on cthd.maHD = hd.maHD\r\n"
				+ "join SanPham sp on sp.maSP = cthd.maSP";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			CTHoaDon cthd = new CTHoaDon();
			cthd.setSanPham( new SanPham(rs.getString("maSP"),rs.getString("tenSP"),rs.getDouble("gia")));
			cthd.setHoaDon(new HoaDon(rs.getString("maHD"),rs.getDate("ngayLapHD").toLocalDate()));
			cthd.setSoLuong(rs.getInt("soLuong"));
			cthd.setGhiChu(rs.getString("ghiChu"));
			ds.add(cthd);
		}
		return ds;
	}
}
