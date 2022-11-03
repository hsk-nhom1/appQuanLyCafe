package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.Ban;
import entity.CTHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

public class HoaDonDao {
	Connection con;
	public HoaDonDao() {
		con = DBConnection.getInstance().getCon();
	}
	public boolean themHoaDon(HoaDon hd, String maNV, String maBan, String maKH) throws SQLException {
		String sql = "insert hoadon values (?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, hd.getMaHD());
		stmt.setDate(2, Date.valueOf(hd.getNgayLapHD()));
		stmt.setString(3, maNV);
		stmt.setString(4, maBan);
		stmt.setString(5, maKH);
		int num = stmt.executeUpdate();		
		return num>0;
	}
	public List<HoaDon> getDsHoaDon() throws SQLException{
		List<HoaDon> ds = new ArrayList<HoaDon>();
		String sql = "select * from hoadon hd \r\n"
				+ "join NhanVien nv on hd.maNV = nv.maNV \r\n"
				+ "join Ban ban on ban.maBan = hd.maBan\r\n"
				+ "join KhachHang kh on kh.maKH = hd.maKH ";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HoaDon kh = new HoaDon(
						rs.getString("maHD"),
						rs.getDate("ngayLapHD").toLocalDate(),
						new NhanVien(rs.getString("maNV"),rs.getString("tenNV")),
						new Ban(rs.getString("maBan"), rs.getString("tenBan")),
						new KhachHang(rs.getString("maKH"), rs.getString("tenKH"))
					);
			ds.add(kh);
		}
		return ds;
	}
	
}
