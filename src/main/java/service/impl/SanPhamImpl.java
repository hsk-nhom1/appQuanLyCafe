package service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.SanPhamDao;
import entity.SanPham;
import service.ISanPhamService;

public class SanPhamImpl implements ISanPhamService{
	private SanPhamDao sanPhamDao;
	public SanPhamImpl() {
		sanPhamDao = new SanPhamDao();
	}
	@Override
	public boolean themSanPham(SanPham sp) throws SQLException {
		if(sp == null)
			return false;
		if(getDsSanPham().contains(sp))
			return false;
		return sanPhamDao.themSanPham(sp);
	}

	@Override
	public boolean xoaSanPham(String ma) throws SQLException {
		if(timMa(ma) == null)
			return false;
		return sanPhamDao.xoaSanPham(ma);
	}

	@Override
	public boolean suaSanPham(SanPham sp) throws SQLException {
		SanPham sanPham = timMa(sp.getMaSP());
		if(sanPham==null)
			return false;
		if(sp.getTenSP()==null || sp.getTenSP().equals(""))
			sp.setTenSP(sanPham.getTenSP());
		if(sp.getGia()== 0.0)
			sp.setGia(sanPham.getGia());
		if(sp.getCongThuc()==null || sp.getCongThuc().equals(""))
			sp.setCongThuc(sanPham.getCongThuc());
		if(sp.getLoai()==null || sp.getLoai().equals(""))
			sp.setLoai(sanPham.getLoai());
		return sanPhamDao.suaSanPham(sp);
	}

	@Override
	public List<SanPham> getDsSanPham() throws SQLException {
		return sanPhamDao.getDsSanPham();
	}

	@Override
	public SanPham timMa(String ma) throws SQLException {
		if(ma == null || ma.equals(""))
			return null;
		for(SanPham sp : getDsSanPham()) {
			if(sp.getMaSP().compareToIgnoreCase(ma)==0)
				return sp;
		}
		return null;
	}

	@Override
	public List<SanPham> timKiem(String ma, String ten, double gia, String congThuc, String loai) throws SQLException {
		List<SanPham> ds = new ArrayList<SanPham>();
		if(timMa(ma)!=null) {
			ds.add(timMa(ma));
			return ds;
		}
		for(SanPham sp :getDsSanPham()) {
			if(
				(sp.getTenSP().equalsIgnoreCase(ten) || ten == null || ten.equals("")) &&
				(sp.getGia() == gia || gia == 0.0) &&
				(sp.getCongThuc().equalsIgnoreCase(congThuc) || congThuc == null || congThuc.equals("")) &&
				(sp.getLoai().equalsIgnoreCase(loai) || loai == null || loai.equals(""))
			)
				ds.add(sp);
		}
		return ds;
	}
	
}
