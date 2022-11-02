package service;

import java.util.List;

import entity.CTHoaDon;
import entity.HoaDon;

public interface IHoaDonService {
    public boolean resetCTHD(String maHD);
    public boolean themHoaDon(HoaDon don);
    public boolean themCTHoaDon(CTHoaDon ctdon);
    public List<HoaDon> getHoaDonWhereBan(String maBan);
    public List<HoaDon> getAllHoaDon();
    public List<HoaDon> getHoaDon();
    
}
