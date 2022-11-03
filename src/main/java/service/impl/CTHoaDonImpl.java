package service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CTHoaDonDao;
import entity.CTHoaDon;
import entity.HoaDon;
import service.ICTHoaDonService;

public class CTHoaDonImpl implements ICTHoaDonService{

	private CTHoaDonDao ctHoaDonDao;
	public CTHoaDonImpl() {
		ctHoaDonDao = new CTHoaDonDao();
	}
	

	@Override
	public List<CTHoaDon> getDsCTHoaDon() throws SQLException {
		return ctHoaDonDao.getDsCTHoaDon();
	}

	@Override
	public boolean themCTHoaDon(CTHoaDon cthd) throws SQLException {
		if(cthd == null)
			return false;
		return ctHoaDonDao.themCTHoaDon(cthd);
	}


	@Override
	public List<CTHoaDon> getDsCTHoaDon(String maHD) throws SQLException {
		List<CTHoaDon> ds = new ArrayList<CTHoaDon>();
		for(CTHoaDon cthd : getDsCTHoaDon()) {
			if(maHD.equalsIgnoreCase(cthd.getHoaDon().getMaHD()))
				ds.add(cthd);
		}
		return ds;
	}



}
