package app;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.CTHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import entity.TaiKhoan;
import service.ICTHoaDonService;
import service.IHoaDonService;
import service.IKhachHangService;
import service.INhanVienService;
import service.ISanPhamService;
import service.ITaiKhoanService;
import service.impl.CTHoaDonImpl;
import service.impl.HoaDonImpl;
import service.impl.KhachHangImpl;
import service.impl.NhanVienImpl;
import service.impl.SanPhamImpl;
import service.impl.TaiKhoanImpl;

public class App {

	public static void main(String[] args) throws SQLException {
		Connection con = DBConnection.getInstance().getCon();
		INhanVienService iNhanVienService = new NhanVienImpl();
		ISanPhamService iSanPhamService = new SanPhamImpl();
		IKhachHangService iKhachHangService = new KhachHangImpl();
		IHoaDonService iHoaDonService = new HoaDonImpl();
		ICTHoaDonService ictHoaDonService = new CTHoaDonImpl();
		List<NhanVien> dsNhanVien = iNhanVienService.getDsNhanVien();
		List<SanPham> dsSanPham = iSanPhamService.getDsSanPham();
		List<KhachHang> dsKhachHang = iKhachHangService.getDsKhachHang();
		List<HoaDon> dsHoaDon = iHoaDonService.getDsHoaDon();
		List<CTHoaDon> dsCtHoaDon = ictHoaDonService.getDsCTHoaDon();
		
//		HoaDon hd = new HoaDon("HD001", LocalDate.parse("2022-10-05"));
//		List<CTHoaDon> ds = new ArrayList<CTHoaDon>();
//		ds.add(new CTHoaDon(new SanPham("SP001"), new HoaDon("HD001"), 5, ""));
//		ds.add(new CTHoaDon(new SanPham("SP002"), new HoaDon("HD001"), 4, ""));
//		iHoaDonService.themHoaDon(hd, "NV001", "B01", "KH001", ds);
		
//		for(KhachHang o:dsKhachHang) {
//			System.out.println(o);
//		}
		NhanVien nv = new NhanVien("NV003", "Nguyen van a", "11223344", "a@gmail.com", "nam", "12233456", 1, "222a32a", 1500000);
		iNhanVienService.themNhanVien(nv);
		
		
	}

	
}
