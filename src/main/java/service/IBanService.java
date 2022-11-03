package service;

import java.sql.SQLException;
import java.util.List;

import entity.Ban;

public interface IBanService {
//	public boolean themBan(Ban ban) throws SQLException;
	public boolean doiTrangThai(String ma, String trangThai) throws SQLException;
	public List<Ban> getDsBan() throws SQLException;
	public Ban timMa(String ma) throws SQLException;
	
}
