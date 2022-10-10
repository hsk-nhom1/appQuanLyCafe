package app;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import db.DBConnection;
import entity.TaiKhoan;
import service.ITaiKhoanService;
import service.impl.TaiKhoanImpl;

public class App {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = DBConnection.getInstance().getCon();
		
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
