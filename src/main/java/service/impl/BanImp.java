package service.impl;

import java.util.List;

import dao.BanDao;
import entity.Ban;
import service.IBanService;

public class BanImp implements IBanService{
    BanDao dao = new BanDao();
    @Override
    public List<Ban> getDsBan() {
        return dao.getAllBan();
    }
    @Override
    public void UpdateTrangThaiBan(Ban b, String trangthai) {
        dao.updateTrangThaiBan(b, trangthai);
        
    }
    @Override
    public List<Ban> getDsBanActive() {
        return dao.getDsBanActive();
    }

}
