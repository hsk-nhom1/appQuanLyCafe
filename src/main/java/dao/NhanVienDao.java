package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.KhachHang;
import entity.NhanVien;
import entity.TaiKhoan;

public class NhanVienDao {
    /**
     * lay danh sach khach hang
     * 
     * @return
     */
    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> dS = new ArrayList<NhanVien>();
        DBConnection.getInstance();
        Connection con = DBConnection.getConnection();

        try {
            String sql = "select * from NhanVien";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV(rs.getString(1));
                nhanVien.setTenNV(rs.getString(2));
                nhanVien.setSdt(rs.getString(3));
                nhanVien.setEmail(rs.getString(4));
                nhanVien.setGioiTinh(rs.getString(5));
                nhanVien.setCmnd(rs.getString(6));
                nhanVien.setCaTruc(rs.getInt(7));
                nhanVien.setDiaChi(rs.getString(8));
                nhanVien.setLuong(rs.getDouble(9));
                dS.add(nhanVien);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dS;
    }

    /**
     * thêm nhân viên
     * 
     * @param nv
     * @return
     * @throws SQLException
     */
    public boolean themNhanVien(NhanVien nv) {
        DBConnection.getInstance();
        Connection con = DBConnection.getConnection();
        String sql = "insert into NhanVien values(?,?,?,?,?,?,?,?,?)";
        int n = 0;
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nv.getMaNV());
            stmt.setString(2, nv.getTenNV());
            stmt.setString(3, nv.getSdt());
            stmt.setString(4, nv.getEmail());
            stmt.setString(5, nv.getGioiTinh());
            stmt.setString(6, nv.getCmnd());
            stmt.setInt(7, nv.getCaTruc());
            stmt.setString(8, nv.getDiaChi());
            stmt.setDouble(9, nv.getLuong());
            n = stmt.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return n > 0;
    }

    /**
     * sửa nhân viên
     * 
     * @param nv
     * @return
     * @throws SQLException
     */
    public boolean suaNhanVien(NhanVien nv) {
        DBConnection.getInstance();
        Connection con = DBConnection.getConnection();
        String sql = "update NhanVien set tenNV = ?, sdt = ?, email = ?, gioiTinh = ?, cmnd = ?, caTruc = ?, diachi = ?,luong = ? where maNV = ?";
        int n = 0;
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nv.getTenNV());
            stmt.setString(2, nv.getSdt());
            stmt.setString(3, nv.getEmail());
            stmt.setString(4, nv.getGioiTinh());
            stmt.setString(5, nv.getCmnd());
            stmt.setInt(6, nv.getCaTruc());
            stmt.setString(7, nv.getDiaChi());
            stmt.setDouble(8, nv.getLuong());
            stmt.setString(9, nv.getMaNV());
            n = stmt.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return n > 0;
    }

    /**
     * xóa nhân viên
     * 
     * @param ma
     * @return
     * @throws SQLException
     */
    public boolean xoaNhanVien(String ma) {
        DBConnection.getInstance();
        Connection con = DBConnection.getConnection();
        int n = 0;
        try {
            String sqlnull = "update hoadon set manv = null where manv = ?";
            PreparedStatement stmtnull = con.prepareStatement(sqlnull);
            stmtnull.setString(1, ma);
            stmtnull.execute();
            String sql = "delete from NhanVien where maNV = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, ma);
            n = stmt.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
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
