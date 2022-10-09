package service;

import java.sql.SQLException;
import java.util.List;

import entity.DiaChi;

public interface IDiaChiService {
	public boolean themDiaChi(DiaChi dc) throws SQLException ;
	public List<DiaChi> getDsDiaChi() throws SQLException ;
	public boolean suaDiaChi(String ma, DiaChi dc) throws SQLException ;
	public boolean xoaDiaChi(String ma) throws SQLException ;
	public DiaChi timDiaChi(String ma) throws SQLException;
}
