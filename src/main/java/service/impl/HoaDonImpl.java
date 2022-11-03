package service.impl;

import java.sql.SQLException;
import java.util.List;

import dao.CTHoaDonDao;
import dao.HoaDonDao;
import entity.CTHoaDon;
import entity.HoaDon;
import service.IHoaDonService;

public class HoaDonImpl implements IHoaDonService{
	private HoaDonDao hoaDonDao;
	private CTHoaDonDao ctHoaDonDao;
	public HoaDonImpl() {
		hoaDonDao = new HoaDonDao();
		ctHoaDonDao = new CTHoaDonDao();
	}
	


	@Override
	public List<HoaDon> getDsHoaDon() throws SQLException {
		return hoaDonDao.getDsHoaDon();
	}

	@Override
	public boolean themHoaDon(HoaDon hd, String maNV, String maBan, String maKH, List<CTHoaDon> ds)
			throws SQLException {
		boolean b = hoaDonDao.themHoaDon(hd, maNV, maBan, maKH);
		for(CTHoaDon cthd :ds) {
			ctHoaDonDao.themCTHoaDon(cthd);
		}
		return b;
	}

}
