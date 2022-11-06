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
import entity.SanPham;

public class KhachHangDao {
    /**
     * lay danh sach khach hang
     * @return
     */
    public List<KhachHang> getAllKhachHang() {
        List<KhachHang> dS = new ArrayList<KhachHang>();
        DBConnection.getInstance();
        Connection con = DBConnection.getConnection();

        try {
            String sql = "select * from KhachHang";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {             
               KhachHang kh = new KhachHang(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                dS.add(kh);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dS;
    }
    
    public boolean themKhachHang(KhachHang kh){
        DBConnection.getInstance();
        Connection con = DBConnection.getConnection();
        String sql = "insert into khachhang values(?,?,?,?,?,?)";
        
        PreparedStatement stmt;
        int n=0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, kh.getMaKH());
            stmt.setString(2, kh.getTenKH());
            stmt.setString(3, kh.getSdt());
            stmt.setString(4, kh.getEmail());
            stmt.setString(5, kh.getGioiTinh());
            stmt.setString(6, kh.getDiaChi());
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        return n > 0;
    }
    
    /**
     * sửa khách hàng
     * @param kh
     * @return
     * @throws SQLException
     */
    public boolean suaKhachHang(KhachHang kh) {
        DBConnection.getInstance();
        Connection con = DBConnection.getConnection();
        String sql = "update khachhang set tenkh = ?, sdt = ?, email = ?, gioiTinh = ?, diachi = ? where makh = ?";
        
        PreparedStatement stmt;
        int n=0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, kh.getTenKH());
            stmt.setString(2, kh.getSdt());
            stmt.setString(3, kh.getEmail());
            stmt.setString(4, kh.getGioiTinh());
            stmt.setString(5, kh.getDiaChi());
            stmt.setString(6, kh.getMaKH());
           n = stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        return n > 0;
    }

    /**
     * xóa khách hàng
     * @param ma
     * @return
     * @throws SQLException
     */
    public boolean xoaKhachHang(String ma)  {
        DBConnection.getInstance();
        Connection con = DBConnection.getConnection();
        String sql = "delete from khachhang where makh = ?";
        PreparedStatement stmt;
        int n =0;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, ma);
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
        return n > 0;
    }
    /**
     * close sql
     * @param pst
     */
    private void close(PreparedStatement pst) {
        if (pst !=null) {
            try {
                pst.close();
            } catch (Exception e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }
}