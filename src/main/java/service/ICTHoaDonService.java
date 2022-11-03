package service;

import java.sql.SQLException;
import java.util.List;

import entity.CTHoaDon;
import entity.HoaDon;

public interface ICTHoaDonService {
	public List<CTHoaDon> getDsCTHoaDon () throws SQLException;
	public List<CTHoaDon> getDsCTHoaDon (String maHD) throws SQLException;
	public boolean themCTHoaDon (CTHoaDon cthd) throws SQLException ;
}
