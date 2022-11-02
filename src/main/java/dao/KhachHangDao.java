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
