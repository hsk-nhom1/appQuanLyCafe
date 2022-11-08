package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.Ban;
import entity.CTHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;

public class HoaDonDao {
    private NhanVienDao nvDao = new NhanVienDao();
    private BanDao banDao = new BanDao();
    private KhachHangDao khDao = new KhachHangDao();

    /**
     * reset danh sách chi tiết hóa đơn về 0
     * 
     * @param mHD
     */
    public void resetCTHD(String mHD) {
        DBConnection.getInstance();
        Connection con = DBConnection.getConnection();
        PreparedStatement stmt = null;
        try {
            String sql = "delete CTHoaDon where maHD=?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, mHD);
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            close(stmt);
        }
    }

    /**
     * lấy hóa đơn tại bàn số ?
     * 
     * @param maBan
     * @return
     */
    public List<HoaDon> getHoaDonWhereBan(String maBan) {
        List<HoaDon> ds = new ArrayList<HoaDon>();
        DBConnection.getInstance();
        Connection con = DBConnection.getConnection();
        try {
            String sql = "SELECT *\r\n"
                    + "FROM     HoaDon INNER JOIN\r\n"
                    + "                  CTHoaDon ON HoaDon.maHD = CTHoaDon.maHD INNER JOIN\r\n"
                    + "                  Ban ON HoaDon.maBan = Ban.maBan INNER JOIN\r\n"
                    + "                  KhachHang ON HoaDon.maKH = KhachHang.maKH INNER JOIN\r\n"
                    + "                  NhanVien ON HoaDon.maNV = NhanVien.maNV INNER JOIN\r\n"
                    + "                  SanPham ON CTHoaDon.maSP = SanPham.maSP\r\n"
                    + "where Ban.maBan=? and HoaDon.trangThai='false'";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, maBan);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String maHD = rs.getString("maHD");
                LocalDate ngayLapHD = LocalDate.parse(rs.getDate("ngayLapHD").toString(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                HoaDon hoaDon = new HoaDon(maHD, ngayLapHD);

                NhanVien nhanVien = new NhanVien(
                        rs.getString("maNV"),
                        rs.getString("tenNV"),
                        rs.getString("sdt"),
                        rs.getString("email"),
                        rs.getString("gioiTinh"),
                        rs.getString("cmnd"),
                        rs.getInt("caTruc"),
                        rs.getString("diaChi"),
                        rs.getDouble("luong"));

                KhachHang khachHang = new KhachHang(
                        rs.getString("maKH"),
                        rs.getString("tenKH"),
                        rs.getString("sdt"),
                        rs.getString("email"),
                        rs.getString("gioiTinh"),
                        rs.getString("diaChi"));

                Ban ban = new Ban(
                        rs.getString("maBan"),
                        rs.getString("tenBan"),
                        rs.getString("trangThai"));

                SanPham sanPham = new SanPham(
                        rs.getString("maSP"),
                        rs.getString("tenSP"),
                        rs.getDouble("gia"),
                        rs.getString("congThuc"));
                       
                List<CTHoaDon> dss = new ArrayList<CTHoaDon>();
                CTHoaDon ctHoaDon = new CTHoaDon(rs.getInt("soLuong"), sanPham);
                dss.add(ctHoaDon);

                hoaDon.setDsCTHD(dss);
                hoaDon.setBan(ban);
                hoaDon.setKhachHang(khachHang);
                hoaDon.setNhanVien(nhanVien);

                ds.add(hoaDon);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ds;
    }

    /**
     * lấy danh sách hóa đơn
     * 
     * @return
     */
    public List<HoaDon> getHoaDon() {
        List<HoaDon> ds = new ArrayList<HoaDon>();
        DBConnection.getInstance();
        Connection con = DBConnection.getConnection();
        try {
            String sql = "select * from HoaDon";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String maHD = rs.getString("maHD");
                LocalDate ngayLapHD = LocalDate.parse(rs.getDate("ngayLapHD").toString(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                HoaDon hoaDon = new HoaDon(maHD, ngayLapHD);

                ds.add(hoaDon);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ds;
    }

    /**
     * lay tat ca hóa đơn đã có sản phẩm
     * 
     * @return
     */
    public List<HoaDon> getAllHoaDon() {
        List<HoaDon> ds = new ArrayList<HoaDon>();
        DBConnection.getInstance();
        Connection con = DBConnection.getConnection();
        try {
            String sql = "SELECT *\r\n"
                    + "FROM     HoaDon INNER JOIN\r\n"
                    + "                  CTHoaDon ON HoaDon.maHD = CTHoaDon.maHD INNER JOIN\r\n"
                    + "                  Ban ON HoaDon.maBan = Ban.maBan INNER JOIN\r\n"
                    + "                  KhachHang ON HoaDon.maKH = KhachHang.maKH INNER JOIN\r\n"
                    + "                  NhanVien ON HoaDon.maNV = NhanVien.maNV INNER JOIN\r\n"
                    + "                  SanPham ON CTHoaDon.maSP = SanPham.maSP";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String maHD = rs.getString("maHD");
                LocalDate ngayLapHD = LocalDate.parse(rs.getDate("ngayLapHD").toString(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                ;

                HoaDon hoaDon = new HoaDon(maHD, ngayLapHD);

                NhanVien nhanVien = new NhanVien(
                        rs.getString("maNV"),
                        rs.getString("tenNV"),
                        rs.getString("sdt"),
                        rs.getString("email"),
                        rs.getString("gioiTinh"),
                        rs.getString("cmnd"),
                        rs.getInt("caTruc"),
                        rs.getString("diaChi"),
                        rs.getDouble("luong"));

                KhachHang khachHang = new KhachHang(
                        rs.getString("maKH"),
                        rs.getString("tenKH"),
                        rs.getString("sdt"),
                        rs.getString("email"),
                        rs.getString("gioiTinh"),
                        rs.getString("diaChi"));

                Ban ban = new Ban(
                        rs.getString("maBan"),
                        rs.getString("tenBan"),
                        rs.getString("trangThai"));

                SanPham sanPham = new SanPham(
                        rs.getString("maSP"),
                        rs.getString("tenSP"),
                        rs.getDouble("gia"),
                        rs.getString("congThuc"));
                List<CTHoaDon> dss = new ArrayList<CTHoaDon>();
                CTHoaDon ctHoaDon = new CTHoaDon(rs.getInt("soLuong"), sanPham);
                dss.add(ctHoaDon);

                hoaDon.setDsCTHD(dss);
                hoaDon.setBan(ban);
                hoaDon.setKhachHang(khachHang);
                hoaDon.setNhanVien(nhanVien);

                ds.add(hoaDon);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ds;
    }

    public void updateTrangThaiHoaDon(String maHD) {
        DBConnection.getInstance();
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = null;

        String sql = "update HoaDon set trangThai='true' where maHD=?";
        try {
            pst = con.prepareStatement(sql);
            pst = con.prepareStatement(sql);

            pst.setString(1, maHD);
            pst.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        } finally {
            close(pst);
        }
    }

    /**
     * tao hoa don
     * 
     * @param hoaDon
     * @return
     */
    public boolean createHoaDon(HoaDon hoaDon) {
        DBConnection.getInstance();
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = null;
        int n = 0;
        try {
            String sql = "insert HoaDon values(?,?,?,?,?,?)";
            pst = con.prepareStatement(sql);

            pst.setString(1, hoaDon.getMaHD());
            pst.setDate(2, Date.valueOf(hoaDon.getNgayLapHD()));
            pst.setString(3, "false");
            pst.setString(4, hoaDon.getNhanVien().getMaNV());
            pst.setString(5, hoaDon.getBan().getMaBan());
            pst.setString(6, hoaDon.getKhachHang().getMaKH());

            n = pst.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                pst.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return n > 0;
    }

    /**
     * tao chi tiet hoa don
     * 
     * @param chiTietHD
     * @return
     */
    public boolean createCTHoaDon(CTHoaDon chiTietHD) {
        DBConnection.getInstance();
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = null;
        int n = 0;
        try {
            String sql = "insert  CTHoaDon values(?,?,?)";
            pst = con.prepareStatement(sql);

            pst.setString(1, chiTietHD.getSanPham().getMaSP());
            pst.setString(2, chiTietHD.getHoaDon().getMaHD());
            pst.setInt(3, chiTietHD.getSoLuong());

            n = pst.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            close(pst);
        }
        return n > 0;
    }

    /**
     * close sql
     * 
     * @param pst
     */
    private void close(PreparedStatement pst) {
        if (pst != null) {
            try {
                pst.close();
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }
}
