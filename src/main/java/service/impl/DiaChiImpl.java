package service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.DiaChiDao;
import entity.DiaChi;
import service.IDiaChiService;

public class DiaChiImpl implements IDiaChiService{
	private DiaChiDao diaChiDao;
	public DiaChiImpl(Connection con) {
		diaChiDao = new DiaChiDao(con);
	}
	
	@Override
	public boolean themDiaChi(DiaChi dc) throws SQLException {
		List<DiaChi> ds = getDsDiaChi();
		if(ds.contains(dc))
			return false;
		else
			diaChiDao.themDiaChi(dc);
		return true;
	}

	@Override
	public List<DiaChi> getDsDiaChi() throws SQLException {
		return diaChiDao.getDsDiaChi();
	}

	@Override
	public boolean suaDiaChi(String ma, DiaChi dc) throws SQLException {
		DiaChi temp = new DiaChi(ma);
		List<DiaChi> ds = getDsDiaChi();
		if(!ds.contains(temp))
			return false;
		else
			diaChiDao.suaDiaChi(ma, dc);;
		return true;

	}

	@Override
	public boolean xoaDiaChi(String ma) throws SQLException {
		DiaChi temp = new DiaChi(ma);
		List<DiaChi> ds = getDsDiaChi();
		if(!ds.contains(temp))
			return false;
		else
			diaChiDao.xoaDiaChi(ma);
		return true;
	}

	@Override
	public DiaChi timDiaChi(String ma) throws SQLException {
		DiaChi temp = new DiaChi();
		List<DiaChi> ds = getDsDiaChi();
		for(DiaChi dc : ds) {
			if(dc.getMaDiaChi().equals(ma))
				temp = dc;
		}
		return temp;
	}
	
}
