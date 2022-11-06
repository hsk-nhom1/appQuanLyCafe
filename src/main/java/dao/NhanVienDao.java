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
