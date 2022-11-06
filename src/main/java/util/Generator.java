package util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Random;


public class Generator {
    public String tuTaoMaNV(String loaiNV, String namSinh) {
        String[] arrDate = namSinh.split("/");

        String year = arrDate[2].substring(2);

        String maNV = "";
        maNV += loaiNV;
        maNV += year;
        maNV += random3SoNguyen();

        return maNV;
    }

    public String tuTaoMaPhong(String loaiPhong) {
        String maPhong = "";
        maPhong += loaiPhong;
        maPhong += random3SoNguyen();
        return maPhong;
    }

    public String tuTaoMaHoaDon(String maBan, String maKH, LocalDate ngayLapHD) {

        String[] day = ngayLapHD.toString().split("-");
        String ngayLapHDs= day[0]+day[1]+day[2];
        
        LocalTime time = LocalTime.now();
        
        String[] thoiGian = time.toString().split(":");
        String h = thoiGian[0]+thoiGian[1];

        String maHD = "";
        maHD += ngayLapHDs;
        maHD += h;
        maHD += maKH;
        maHD+= maBan;
        return maHD;
    }

    // nó k có crud nên k đc tạo ngẫu nhiên mã dịch vụ
    // loại phòng và trạng thái phòng tương tự
    public String tuTaoMaDichVu() {
        return "DV" + random3SoNguyen();
    }

    public String tuTaoMaCaTruc(LocalDate ngayLapPhanCong, int caLam) {
        // split ngayLapPhanCong
        String[] arrDate = ngayLapPhanCong.toString().split("-");
        String year = arrDate[0].substring(2);
        String month = arrDate[1];
        String day = arrDate[2];

        String thuTuCaLam = caLam == 0 ? "1" : caLam == 1 ? "2" : "3";

        String[] dayOfWeek = { "Monday", "Tuesday", "Wednesday", "Thursday",
                "Friday", "Saturday", "Sunday" };
        String thuTuNgay = "";

        for (int i = 0; i < dayOfWeek.length; i++) {
            if (ngayLapPhanCong.getDayOfWeek().name().equalsIgnoreCase(dayOfWeek[i])) {
                thuTuNgay = String.valueOf(i + 2);
            }
        }

        String maCaTruc = "";
        maCaTruc += day + month + year;
        maCaTruc += thuTuNgay;
        maCaTruc += thuTuCaLam;
        return maCaTruc;
    }

    public String tuTaoMaDC() {
        return "DC" + random4SoNguyen();
    }

    public String tuTaoMaLoaiTV(String soDinhDanh) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String ngayDangKy = formatter.format(date);
        String[] arrDate = ngayDangKy.split("/");
        String month = arrDate[1];
        String year = arrDate[2].substring(2);

        String maLoaiTV = "";
        maLoaiTV += soDinhDanh;
        maLoaiTV += month + year;
        return maLoaiTV;
    }

    // mã khách hàng lấy trực tiép số điện thoại cắt ra nên k cần hàm

    // có thể kết hợp với mã nhân viên + mã phòng + mã khách hàng
    public String tuTaoDatPhong() {
        return "DP" + random3SoNguyen();
    }

    public int random4SoNguyen() {
        return (int) (Math.random() * 9000 + 1000);
    }

    public int random3SoNguyen() {
        return (int) (Math.random() * 900 + 100);
    }

}
