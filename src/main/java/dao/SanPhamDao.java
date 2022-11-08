package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.SanPham;

public class SanPhamDao {
    /**
     * lay danh sach san pham
     * 
     * @return
     */
    public List<SanPham> getAllSanPhams() {
        List<SanPham> dSanPhams = new ArrayList<SanPham>();
        DBConnection.getInstance();
        Connection con = DBConnection.getConnection();

        try {
            String sql = "select * from SanPham";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String masp = rs.getString(1);
                String tensp = rs.getString(2);
                Double gia = rs.getDouble(3);
                String congThuc = rs.getString(4);

                SanPham sp = new SanPham(masp, tensp, gia, congThuc);
                dSanPhams.add(sp);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dSanPhams;
    }

    /**
     * thêm sản phẩm
     * 
     * @param sanPham
     * @return
     */
    public boolean themSanPham(SanPham sanPham) {
        DBConnection.getInstance();
        Connection con = DBConnection.getConnection();
        int n = 0;
        String sql = "insert SanPham values(?,?,?,?)";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, sanPham.getMaSP());
            preparedStatement.setString(2, sanPham.getTenSP());
            preparedStatement.setDouble(3, sanPham.getGia());
            preparedStatement.setString(4, sanPham.getCongThuc());

            n = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return n > 0;
    }

    /**
     * cập nhật sản phẩm
     * 
     * @param sanPham
     * @return
     */
    public boolean suaSanPham(SanPham sanPham) {
        DBConnection.getInstance();
        Connection con = DBConnection.getConnection();
        int n = 0;
        String sql = "update SanPham set tenSP=?,gia=?,congThuc=? where maSP=?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(4, sanPham.getMaSP());
            preparedStatement.setString(1, sanPham.getTenSP());
            preparedStatement.setDouble(2, sanPham.getGia());
            preparedStatement.setString(3, sanPham.getCongThuc());

            n = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return n > 0;
    }

    /**
     * xóa sản phẩm
     * @param maSP
     */
    public void xoaSanPham(String maSP) {
        DBConnection.getInstance();
        Connection con = DBConnection.getConnection();
        String sql="delete from SanPham where maSP=?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, maSP);
            int n = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
