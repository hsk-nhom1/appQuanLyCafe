package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.DiaChi;
import entity.NhanVien;
import entity.TaiKhoan;

public class NhanVienDao {
	private Connection con;
	public NhanVienDao(Connection con) {
		this.con = con;
	}
	
	public void themNhanVien(NhanVien nv) throws SQLException {
		String sql = "insert into NhanVien values(?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, nv.getMaNV());
		stmt.setString(2, nv.getTenNV());
		stmt.setString(3, nv.getSdt());
		stmt.setString(4, nv.getEmail());
		stmt.setString(5, nv.getGioiTinh());
		stmt.setString(6, nv.getCmnd());
		stmt.setInt(7, nv.getCaTruc());
		stmt.setString(8, nv.getTaiKhoan().getUserName());
		stmt.setString(9, nv.getDiaChi().getMaDiaChi());
		stmt.setDouble(10, nv.getLuong());
		stmt.execute();
	}
	
	public List<NhanVien> getDsNhanVien() throws SQLException {
		List<NhanVien> ds = new ArrayList<NhanVien>();
		String sql = "select * from NhanVien";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			TaiKhoan tk = new TaiKhoan();
			String matk = rs.getString("userName");
			String gettk = "select * from TaiKhoan where userName = ?";
			PreparedStatement stmt2 = con.prepareStatement(gettk);
			stmt2.setString(1, matk);
			ResultSet rs2 = stmt2.executeQuery();
			while(rs2.next()) {
				tk.setUserName(rs2.getString("userName"));
				tk.setPassword(rs2.getString("password"));
			}
			DiaChi dc = new DiaChi();
			String madc = rs.getString("maDiaChi");
			String getdc = "select * from DiaChi where maDiaChi = ?";
			PreparedStatement stmt3 = con.prepareStatement(getdc);
			stmt3.setString(1, madc);
			ResultSet rs3 = stmt3.executeQuery();
			while(rs3.next()) {
				dc.setMaDiaChi(rs3.getString("maDiaChi"));
				dc.setXa(rs3.getString("xa"));
				dc.setHuyen(rs3.getString("huyen"));
				dc.setTinh(rs3.getString("tinh"));
			}
			NhanVien nv = new NhanVien(
						rs.getString("maNV"),
						rs.getString("tenNV"),
						rs.getString("sdt"),
						rs.getString("email"),
						rs.getString("gioiTinh"),
						rs.getString("cmnd"),
						rs.getInt("caTruc"),
						tk,
						dc,
						rs.getDouble("luong")
					);
			ds.add(nv);
		}
		return ds;
	}
	
	public void suaNhanVien(String ma, NhanVien nv) throws SQLException {
		String sql = "update NhanVien set tenNV = ?, sdt = ?, email = ?, gioiTinh = ?, cmnd = ?, caTruc = ?, luong = ? where maNV = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, nv.getTenNV());
		stmt.setString(2, nv.getMaNV());
		stmt.setString(3, nv.getEmail());
		stmt.setString(4, nv.getGioiTinh());
		stmt.setString(5, nv.getCmnd());
		stmt.setInt(6, nv.getCaTruc());
		stmt.setDouble(7, nv.getLuong());
		stmt.setString(8, ma);
		stmt.execute();
	}
	public void xoaNhanVien(String ma) throws SQLException {
		String sql = "delete from NhanVien where maNV = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, ma);
		stmt.execute();
	}
	
}
