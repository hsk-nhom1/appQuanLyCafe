package dao;

import java.sql.Connection;

import entity.DiaChi;

public class DiaChiDao {
	private Connection con;
	public DiaChiDao() {
		this.con = con;
	}
	public void themDiaChi(DiaChi dc) {
		String sql = "insert into DiaChi values(?,?)";
	}
}	
