package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBConnection;
import entity.TaiKhoan;

public class TaiKhoanDao {

    public void themTaiKhoan(TaiKhoan tk) {
        DBConnection.getInstance();
        Connection con = DBConnection.getConnection();
        String sql = "insert into TaiKhoan values(?,?)";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, tk.getUserName());
            stmt.setString(2, tk.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public List<TaiKhoan> getDsTaiKhoan() {
        DBConnection.getInstance();
        Connection con = DBConnection.getConnection();
        List<TaiKhoan> ds = new ArrayList<TaiKhoan>();
        String sql = "select * from TaiKhoan";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
//      TaiKhoan tk = new TaiKhoan(
//              rs.getString("userName"),
//              rs.getString("password")
//              );
//      ds.add(tk);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
        return ds;
    }

    public TaiKhoan timTaiKhoan(String username) {
        DBConnection.getInstance();
        Connection con = DBConnection.getConnection();
        String sql = "select * from TaiKhoan where userName=?";
        TaiKhoan taiKhoan = new TaiKhoan();
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                taiKhoan = new TaiKhoan(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
                return taiKhoan;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public void suaTaiKhoan(String maTK, TaiKhoan tk) {
        DBConnection.getInstance();
        Connection con = DBConnection.getConnection();
        String sql = "update TaiKhoan set password = ? where userName = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, tk.getPassword());
            stmt.setString(2, maTK);
            stmt.executeUpdate();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void xoaTaiKhoan(String userName) {
        DBConnection.getInstance();
        Connection con = DBConnection.getConnection();
        String sql = "delete from TaiKhoan where userName = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, userName);
            stmt.execute();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
