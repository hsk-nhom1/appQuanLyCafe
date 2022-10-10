package dao;

import java.sql.Connection;

public class NhanVienDao {
	private Connection con;
	public NhanVienDao(Connection con) {
		this.con = con;
	}
}
