package ui;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.RoundedBorderWithColor;

public class Menu extends JFrame {
	private JPanel pnMain;
	private JPanel pnMenuJPanel;
	private JButton btnTrangChu, btnSanPham, btnQLBan, btnNhanVien, btnHoaDon;
	private JLabel lblIconUser, lblIconLogOut, lblTenUser, lblLogOut;

	public Menu() {
		setTitle("Thanh Menu");
		setSize(1050, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		MainUI();
		PnMenu();
	}

	public void MainUI() {
		pnMain = new JPanel();
		pnMain.setLayout(null);
		setContentPane(pnMain);
	}
	
	public void PnMenu() {
		pnMenuJPanel = new JPanel();
		pnMenuJPanel.setLayout(null);
		pnMenuJPanel.setBounds(0, 0, 1050, 100);
		pnMenuJPanel.setBackground(Color.decode("#1D81CE"));
		 
		
		btnTrangChu = new JButton("Trang chủ");
		btnTrangChu.setHorizontalAlignment(JButton.CENTER);
		btnTrangChu.setBounds(50, 25, 128, 35);
		btnTrangChu.setIcon(new ImageIcon(
		        new ImageIcon("public/icon/home.png").getImage().getScaledInstance(30, 30,
		                Image.SCALE_SMOOTH)));
		btnTrangChu.setBorder(new RoundedBorderWithColor(Color.decode("#1D81CE"), 1, 10));					
		
		btnSanPham = new JButton("Sản phẩm");
		btnSanPham.setHorizontalAlignment(JButton.CENTER);
		btnSanPham.setBounds(200, 25, 128, 35);
		btnSanPham.setIcon(new ImageIcon(
		        new ImageIcon("public/icon/cfMenu.png").getImage().getScaledInstance(30, 30,
		                Image.SCALE_SMOOTH)));
		btnSanPham.setBorder(new RoundedBorderWithColor(Color.decode("#1D81CE"), 1, 10));				

		btnQLBan = new JButton("Quản lý bàn");
		btnQLBan.setHorizontalAlignment(JButton.CENTER);
		btnQLBan.setBounds(350, 25, 135, 35);
		btnQLBan.setIcon(new ImageIcon(
		        new ImageIcon("public/icon/table.png").getImage().getScaledInstance(30, 30,
		                Image.SCALE_SMOOTH)));
		btnQLBan.setBorder(new RoundedBorderWithColor(Color.decode("#1D81CE"), 1, 10));
		
		btnNhanVien = new JButton("Nhân viên");
		btnNhanVien.setHorizontalAlignment(JButton.CENTER);
		btnNhanVien.setBounds(500, 25, 128, 35);
		btnNhanVien.setIcon(new ImageIcon(
		        new ImageIcon("public/icon/staff.png").getImage().getScaledInstance(30, 30,
		                Image.SCALE_SMOOTH)));
		btnNhanVien.setBorder(new RoundedBorderWithColor(Color.decode("#1D81CE"), 1, 10));

		btnHoaDon = new JButton("Hóa đơn");
		btnHoaDon.setHorizontalAlignment(JButton.CENTER);
		btnHoaDon.setBounds(650, 25, 128, 35);
		btnHoaDon.setIcon(new ImageIcon(
		        new ImageIcon("public/icon/bill.png").getImage().getScaledInstance(30, 30,
		                Image.SCALE_SMOOTH)));
		btnHoaDon.setBorder(new RoundedBorderWithColor(Color.decode("#1D81CE"), 1, 10));
		

		lblIconUser = new JLabel();
		lblIconUser.setBounds(850, 10, 30, 30);
		lblIconUser.setIcon(new ImageIcon(
		        new ImageIcon("public/icon/user.png").getImage().getScaledInstance(30, 30,
		                Image.SCALE_SMOOTH)));

		lblTenUser = new JLabel("Nguyễn Văn A");
		lblTenUser.setBounds(900, 10, 100, 30);
		lblTenUser.setForeground(Color.WHITE);


		lblIconLogOut = new JLabel();
		lblIconLogOut.setBounds(850, 50, 30, 30);
		lblIconLogOut.setIcon(new ImageIcon(
		        new ImageIcon("public/icon/logout.png").getImage().getScaledInstance(30, 30,
		                Image.SCALE_SMOOTH)));

		lblLogOut = new JLabel("Đăng xuất");
		lblLogOut.setBounds(900, 50, 100, 30);
		lblLogOut.setForeground(Color.WHITE);


		pnMain.add(pnMenuJPanel);
		pnMenuJPanel.add(btnTrangChu);
		pnMenuJPanel.add(btnSanPham);
		pnMenuJPanel.add(btnQLBan);
		pnMenuJPanel.add(btnNhanVien);
		pnMenuJPanel.add(btnHoaDon);
		pnMenuJPanel.add(lblIconUser);
		pnMenuJPanel.add(lblTenUser);
		pnMenuJPanel.add(lblIconLogOut);
		pnMenuJPanel.add(lblLogOut);
	}
	 
	public static void main(String[] args) {
		new Menu().setVisible(true);
	}
}
