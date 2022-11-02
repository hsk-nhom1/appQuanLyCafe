package entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class HoaDon {
    private String maHD;
    private LocalDate ngayLapHD;
    private String trangThaiThanhToan;

    private NhanVien nhanVien;
    private Ban ban;
    private KhachHang khachHang;
    private List<CTHoaDon> dsCTHD;
    
    

    public List<CTHoaDon> getDsCTHD() {
        return dsCTHD;
    }
    
    

    public String getTrangThaiThanhToan() {
        return trangThaiThanhToan;
    }



    public void setTrangThaiThanhToan(String trangThaiThanhToan) {
        this.trangThaiThanhToan = trangThaiThanhToan;
    }



    public void setDsCTHD(List<CTHoaDon> dsCTHD) {
        this.dsCTHD = dsCTHD;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public LocalDate getNgayLapHD() {
        return ngayLapHD;
    }

    public void setNgayLapHD(LocalDate ngayLapHD) {
        this.ngayLapHD = ngayLapHD;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public Ban getBan() {
        return ban;
    }

    public void setBan(Ban ban) {
        this.ban = ban;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }


    public HoaDon() {
        super();
        // TODO Auto-generated constructor stub
    }

    public HoaDon(String maHD, LocalDate ngayLapHD) {
        super();
        this.maHD = maHD;
        this.ngayLapHD = ngayLapHD;
    }

    @Override
    public String toString() {
        return "HoaDon [maHD=" + maHD + ", ngayLapHD=" + ngayLapHD + ", nhanVien=" + nhanVien + ", ban=" + ban
                + ", khachHang=" + khachHang + ", dsCTHD=" + dsCTHD + "]";
    }

   

}
