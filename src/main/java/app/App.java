package app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import db.DBConnection;
import entity.DiaChi;
import entity.NhanVien;
import entity.TaiKhoan;
import service.IDiaChiService;
import service.INhanVienService;
import service.ITaiKhoanService;
import service.impl.DiaChiImpl;
import service.impl.NhanVienImpl;
import service.impl.TaiKhoanImpl;

public class App {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = DBConnection.getInstance().getCon();
//		testTaiKhoan(con);
//		testDiaChi(con);
		testNhanVien(con);
	}

	private static void testNhanVien(Connection con) throws SQLException {
		INhanVienService until = new NhanVienImpl(con);
		ITaiKhoanService ultk = new TaiKhoanImpl(con);
		IDiaChiService uldc = new DiaChiImpl(con);
		
		TaiKhoan tk = ultk.timTaiKhoan("nam");
		DiaChi dc = uldc.timDiaChi("DC004");
		System.out.println(tk+ "\n" + dc);
		until.themNhanVien(new NhanVien("NV004","Phạm Hà Nam", "123456", "pnam55588@gmail.com","nam", "123456789", 1,tk,dc,100000.0 ));
		
		NhanVien nv = until.timMa("NV004");
		nv.setGioiTinh("nam");
		nv.setCmnd(null);
		DiaChi dc2 = uldc.timDiaChi("DC002");
		nv.setDiaChi(dc2);
		System.out.println(nv);
		until.suaNhanVien("NV004", nv);
		until.xoaNhanVien("NV004");
		
		List<NhanVien> ds = until.getDsNhanVien();
		ds.forEach(o -> System.out.println(o));
	}

	private static void testDiaChi(Connection con) throws SQLException {
		IDiaChiService until = new DiaChiImpl(con);
		until.themDiaChi(new DiaChi("DC005","Long Bình", "Biên Hòa", "Đồng Nai"));
		until.themDiaChi(new DiaChi("DC002","Phường 4", "Gò Vấp", "TP HCM"));
		until.themDiaChi(new DiaChi("DC004","Lai Châu", "Cẩm Mỹ", "Hưng Yên"));
		
//		DiaChi dc = new DiaChi("DC003");
//		dc.setXa("Lai Châu 2");
//		until.suaDiaChi("DC003", dc);
//		until.xoaDiaChi("DC002");
		List<DiaChi> ds = until.getDsDiaChi();
		ds.forEach(o -> System.out.println(o));
		
	}

	private static void testTaiKhoan(Connection con) throws SQLException {
		ITaiKhoanService until = new TaiKhoanImpl(con);
		until.themTaiKhoan(new TaiKhoan("nam", "123"));
		until.themTaiKhoan(new TaiKhoan("nam", "123"));
		until.themTaiKhoan(new TaiKhoan("phong", "1234a"));
		until.themTaiKhoan(new TaiKhoan("chuong", "aa123"));
		
		TaiKhoan tk = until.timTaiKhoan("nam");
		System.out.println("tim tai khoan: " + tk);
		tk.setPassword("hahaha");
		until.suaTaiKhoan("nam",tk);
		until.xoaTaiKhoan("nu");
		
		List<TaiKhoan> ds = until.getDsTaiKhoan();
		ds.forEach(o -> System.out.println(o));		
	}
	

}
