package app;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dao.HoaDonDao;
import db.DBConnection;
import entity.CTHoaDon;
import entity.HoaDon;
import entity.TaiKhoan;
import service.ITaiKhoanService;
import service.impl.HoaDonImp;
import service.impl.TaiKhoanImpl;

public class App {

	public static void main(String[] args) {
		
	    try {
            DBConnection.getInstance().connect();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
	    
//		LocalTime time = LocalTime.now();
//		System.out.println(time);
//		
//		String[] thoiGian = time.toString().split(":");
//		String h = thoiGian[0]+thoiGian[1];
//		System.out.println(h);
	    
	    LocalDate valueNgay = LocalDate.parse("2025-10-1",
                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        
        System.out.println(valueNgay.toString());
		
	}

}
