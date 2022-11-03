package service;

import java.sql.SQLException;
import java.util.List;

import entity.CTHoaDon;
import entity.HoaDon;

public interface IHoaDonService {
	public boolean themHoaDon(HoaDon hd, String maNV, String maBan, String maKH, List<CTHoaDon> ds) throws SQLException;
	public List<HoaDon> getDsHoaDon() throws SQLException;
}
