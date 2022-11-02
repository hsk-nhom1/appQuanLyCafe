package service;

import java.util.List;

import entity.Ban;

public interface IBanService {
    public List<Ban> getDsBan();
    public List<Ban> getDsBanActive();
    public void UpdateTrangThaiBan(Ban b, String trangthai);
}
