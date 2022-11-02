package service.impl;

import java.util.List;

import dao.HoaDonDao;
import entity.CTHoaDon;
import entity.HoaDon;
import service.IHoaDonService;

public class HoaDonImp implements IHoaDonService {
    private HoaDonDao dao = new HoaDonDao();

    @Override
    public boolean themHoaDon(HoaDon don) {
        dao.createHoaDon(don);
        return false;
    }

    @Override
    public boolean themCTHoaDon(CTHoaDon ctdon) {
        dao.createCTHoaDon(ctdon);
        return false;
    }

    @Override
    public List<HoaDon> getAllHoaDon() {
        // TODO Auto-generated method stub
        return dao.getAllHoaDon();
    }

    @Override
    public List<HoaDon> getHoaDonWhereBan(String maBan) {
        return dao.getHoaDonWhereBan(maBan);
    }

    @Override
    public List<HoaDon> getHoaDon() {
        // TODO Auto-generated method stub
        return dao.getHoaDon();
    }

    @Override
    public boolean resetCTHD(String maHD) {
        dao.resetCTHD(maHD);
        return true;

    }

}
