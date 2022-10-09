package service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.DiaChiDao;
import dao.NhanVienDao;
import dao.TaiKhoanDao;
import entity.NhanVien;
import service.INhanVienService;

public class NhanVienImpl implements INhanVienService{

	NhanVienDao nhanVienDao;
	TaiKhoanDao taiKhoanDao;
	DiaChiDao diaChiDao;
	public NhanVienImpl(Connection con) {
		nhanVienDao = new NhanVienDao(con);
		taiKhoanDao = new TaiKhoanDao(con);
		diaChiDao = new DiaChiDao(con);
	}
	
	@Override
	public boolean themNhanVien(NhanVien nv) throws SQLException {
		List<NhanVien> ds = nhanVienDao.getDsNhanVien();
		if(ds.contains(nv))
			return false;
		nhanVienDao.themNhanVien(nv);
		return true;
	}

	@Override
	public List<NhanVien> getDsNhanVien() throws SQLException {
		return nhanVienDao.getDsNhanVien();
	}

	@Override
	public boolean suaNhanVien(String ma, NhanVien nv) throws SQLException {
		NhanVien temp = timMa(ma);
		List<NhanVien> ds = nhanVienDao.getDsNhanVien();
		System.out.println(nv.getDiaChi());
		System.out.println(temp.getDiaChi());
		if(!ds.contains(temp))
			return false;
		if(nv.getTenNV()==null || nv.getTenNV()=="")
			nv.setTenNV(temp.getTenNV());
		if(nv.getSdt()==null || nv.getSdt()=="")
			nv.setSdt(temp.getSdt());
		if(nv.getEmail()==null || nv.getEmail()=="")
			nv.setEmail(temp.getEmail());
		if(nv.getGioiTinh()==null || nv.getGioiTinh()=="")
			nv.setGioiTinh(temp.getGioiTinh());
		if(nv.getCmnd()==null || nv.getCmnd()=="")
			nv.setCmnd(temp.getCmnd());
		if(nv.getCaTruc() ==0);
			nv.setCaTruc(temp.getCaTruc());
		if(nv.getTaiKhoan()==null)
			nv.setTaiKhoan(nv.getTaiKhoan());
		if(nv.getDiaChi()==null)
			nv.setDiaChi(temp.getDiaChi());
		if(nv.getLuong()==0.0)
			nv.setLuong(temp.getLuong());
		nhanVienDao.suaNhanVien(ma, nv);
		taiKhoanDao.suaTaiKhoan(temp.getTaiKhoan().getUserName(), nv.getTaiKhoan());
		diaChiDao.suaDiaChi(temp.getDiaChi().getMaDiaChi(), nv.getDiaChi());
		return true;
	}

	@Override
	public NhanVien timMa(String ma) throws SQLException {
		List<NhanVien> ds = nhanVienDao.getDsNhanVien();
		for(NhanVien nv : ds) {
			if(nv.getMaNV().equals(ma))
				return nv;
		}
		return null;
	}

	@Override
	public boolean xoaNhanVien(String ma) throws SQLException {
		NhanVien temp = timMa(ma);
		if(temp == null)
			return false;
		nhanVienDao.xoaNhanVien(ma);
		return true;
	}


	
}
