package service.impl;

import java.sql.SQLException;
import java.util.List;

import dao.BanDao;
import entity.Ban;
import service.IBanService;

public class BanImpl implements IBanService{
	private BanDao banDao;
	public BanImpl() {
		banDao = new BanDao();
	}

//	@Override
//	public boolean themBan(Ban ban) throws SQLException {
//		return true;
//	}

	@Override
	public boolean doiTrangThai(String ma, String trangThai) throws SQLException {
		if(timMa(ma)==null)
			return false;
		return doiTrangThai(ma, trangThai);
	}

	@Override
	public List<Ban> getDsBan() throws SQLException {
		return banDao.getDsBan();
	}

	@Override
	public Ban timMa(String ma) throws SQLException {
		if(ma == null || ma.equals(""))
			return null;
		for(Ban b : getDsBan()) {
			if(b.getMaBan().compareToIgnoreCase(ma)==0)
				return b;
		}
		return null;
	}

}
