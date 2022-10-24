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
            
            while(rs.next()) {
                String masp= rs.getString(1);
                String tensp= rs.getString(2);
                Double gia = rs.getDouble(3);
                String congThuc = rs.getString(4);
                String loai = rs.getString(5);
                
                SanPham sp = new SanPham(masp, tensp, gia, congThuc, loai);
                dSanPhams.add(sp);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dSanPhams;
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
