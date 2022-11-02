package dao;

import java.sql.Connection;
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

public class BanDao {
    
    public List<Ban> getDsBanActive() {
        List<Ban> ds = new ArrayList<Ban>();
        DBConnection.getInstance();
        Connection con = DBConnection.getConnection();

        try {
            String sql = "SELECT *\r\n"
                    + "FROM     Ban INNER JOIN\r\n"
                    + "                  HoaDon ON Ban.maBan = HoaDon.maBan";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String maBan = rs.getString("maBan");           
                String tenBan = rs.getString("tenBan");
                String trangThai = rs.getString("trangThai");
                
                LocalDate ngayLapHD = LocalDate.parse(rs.getDate("ngayLapHD").toString(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                
                HoaDon hoaDon = new HoaDon(rs.getString("maHD"),ngayLapHD);
                
                Ban ban = new Ban(maBan, tenBan, trangThai);
                ban.setHoaDon(hoaDon);
                ds.add(ban);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ds;
    }
    /**
     * lay danh sach san pham
     * 
     * @return
     */
    public List<Ban> getAllBan() {
        List<Ban> ds = new ArrayList<Ban>();
        DBConnection.getInstance();
        Connection con = DBConnection.getConnection();

        try {
            String sql = "select * from Ban";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String maBan = rs.getString(1);           
                String tenBan = rs.getString(2);
                String trangThai = rs.getString(3);
                
                Ban ban = new Ban(maBan, tenBan, trangThai);
                ds.add(ban);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return ds;
    }
    
    public boolean updateTrangThaiBan(Ban b,String trangThai) {
        DBConnection.getInstance();
        Connection con = DBConnection.getConnection();
        PreparedStatement pst = null;
        int n = 0;
        try {
            String sql = "update Ban set trangThai=? where maBan=?";
            pst = con.prepareStatement(sql);
            pst.setString(1,trangThai);
            pst.setString(2, b.getMaBan());
                  
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
