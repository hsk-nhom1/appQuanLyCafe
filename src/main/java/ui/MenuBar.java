package ui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.RoundedBorderWithColor;

public class MenuBar extends JFrame implements ActionListener {
    private JPanel pnMain;
    private JPanel pnMenuJPanel;
    private JButton btnTrangChu, btnSanPham, btnQLBan, btnNhanVien, btnHoaDon, btnClose, btnMenu, btnLogOut;
    private JLabel lblIconUser, lblIconLogOut, lblTenUser;

    public MenuBar() {
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

        btnMenu = new JButton("");
        btnMenu.setBounds(10, 10, 40, 40);
        btnMenu.setIcon(new ImageIcon(
                new ImageIcon("public/icon/menu.png").getImage().getScaledInstance(40, 40,
                        Image.SCALE_SMOOTH)));
        pnMain.add(btnMenu);

        btnMenu.addActionListener(this);
    }

    public void PnMenu() {
        pnMenuJPanel = new JPanel();
        pnMenuJPanel.setLayout(null);
        pnMenuJPanel.setBounds(0, 0, 0, 0);
        pnMenuJPanel.setBackground(Color.decode("#1D81CE"));

        btnClose = new JButton("");
        btnClose.setBounds(165, 5, 30, 30);
        btnClose.setBackground(Color.decode("#1D81CE"));
        btnClose.setIcon(new ImageIcon(
                new ImageIcon("public/icon/close.png").getImage().getScaledInstance(30, 30,
                        Image.SCALE_SMOOTH)));

        btnTrangChu = new JButton("Trang chủ");
        btnTrangChu.setHorizontalAlignment(JButton.CENTER);
        btnTrangChu.setBounds(40, 100, 128, 35);
        btnTrangChu.setBorder(new RoundedBorderWithColor(Color.decode("#1D81CE"), 1, 20));
        btnTrangChu.setIcon(new ImageIcon(
                new ImageIcon("public/icon/home.png").getImage().getScaledInstance(30, 30,
                        Image.SCALE_SMOOTH)));

        btnSanPham = new JButton("Sản phẩm");
        btnSanPham.setHorizontalAlignment(JButton.CENTER);
        btnSanPham.setBounds(40, 150, 128, 35);
        btnSanPham.setBorder(new RoundedBorderWithColor(Color.decode("#1D81CE"), 1, 20));
        btnSanPham.setIcon(new ImageIcon(
                new ImageIcon("public/icon/product.png").getImage().getScaledInstance(30, 30,
                        Image.SCALE_SMOOTH)));

        btnQLBan = new JButton("QL Bàn");
        btnQLBan.setHorizontalAlignment(JButton.CENTER);
        btnQLBan.setBounds(40, 200, 128, 35);
        btnQLBan.setBorder(new RoundedBorderWithColor(Color.decode("#1D81CE"), 1, 20));
        btnQLBan.setIcon(new ImageIcon(
                new ImageIcon("public/icon/table.png").getImage().getScaledInstance(30, 30,
                        Image.SCALE_SMOOTH)));

        btnNhanVien = new JButton("Nhân viên");
        btnNhanVien.setHorizontalAlignment(JButton.CENTER);
        btnNhanVien.setBounds(40, 250, 128, 35);
        btnNhanVien.setBorder(new RoundedBorderWithColor(Color.decode("#1D81CE"), 1, 20));
        btnNhanVien.setIcon(new ImageIcon(
                new ImageIcon("public/icon/staff.png").getImage().getScaledInstance(30, 30,
                        Image.SCALE_SMOOTH)));

        btnHoaDon = new JButton("Hóa đơn");
        btnHoaDon.setHorizontalAlignment(JButton.CENTER);
        btnHoaDon.setBounds(40, 300, 128, 35);
        btnHoaDon.setBorder(new RoundedBorderWithColor(Color.decode("#1D81CE"), 1, 20));
        btnHoaDon.setIcon(new ImageIcon(
                new ImageIcon("public/icon/bill.png").getImage().getScaledInstance(30, 30,
                        Image.SCALE_SMOOTH)));

        JLabel lblLine = new JLabel("");
        lblLine.setBounds(0, 400, 200, 1);
        lblLine.setBackground(Color.decode("#FFFFFF"));
        lblLine.setOpaque(true);

        lblIconUser = new JLabel();
        lblIconUser.setBounds(40, 410, 30, 30);
        lblIconUser.setIcon(new ImageIcon(
                new ImageIcon("public/icon/user.png").getImage().getScaledInstance(30, 30,
                        Image.SCALE_SMOOTH)));

        lblTenUser = new JLabel("Nguyễn Văn A");
        lblTenUser.setBounds(80, 410, 100, 30);
        lblTenUser.setForeground(Color.WHITE);

        lblIconLogOut = new JLabel();
        lblIconLogOut.setBounds(40, 460, 30, 30);
        lblIconLogOut.setIcon(new ImageIcon(
                new ImageIcon("public/icon/logout.png").getImage().getScaledInstance(30, 30,
                        Image.SCALE_SMOOTH)));

        btnLogOut = new JButton("Đăng xuất");
        btnLogOut.setBounds(70, 460, 100, 30);
        btnLogOut.setBackground(Color.decode("#1D81CE"));
        btnLogOut.setHorizontalAlignment(JButton.LEFT);
        btnLogOut.setForeground(Color.WHITE);

        JLabel lblHoline = new JLabel("Hotline: 0123456789");
        lblHoline.setBounds(0, 600, 200, 30);
        lblHoline.setHorizontalAlignment(JLabel.CENTER);
        lblHoline.setForeground(Color.WHITE);

        JLabel lblEmail = new JLabel("Email:phongle@gmail.com");
        lblEmail.setBounds(0, 630, 200, 30);
        lblEmail.setHorizontalAlignment(JLabel.CENTER);
        lblEmail.setForeground(Color.WHITE);

        pnMain.add(pnMenuJPanel);
        pnMenuJPanel.add(btnClose);
        pnMenuJPanel.add(btnTrangChu);
        pnMenuJPanel.add(btnSanPham);
        pnMenuJPanel.add(btnQLBan);
        pnMenuJPanel.add(btnNhanVien);
        pnMenuJPanel.add(btnHoaDon);
        pnMenuJPanel.add(lblLine);
        pnMenuJPanel.add(lblIconUser);
        pnMenuJPanel.add(lblTenUser);
        pnMenuJPanel.add(lblIconLogOut);
        pnMenuJPanel.add(btnLogOut);
        pnMenuJPanel.add(lblHoline);
        pnMenuJPanel.add(lblEmail);

        btnClose.addActionListener(this);

    }

    public static void main(String[] args) {
        new MenuBar().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnMenu)) {
            btnMenu.setBounds(0, 0, 0, 0);
            pnMenuJPanel.setBounds(0, 0, 200, 700);
        } else if (o.equals(btnClose)) {
            btnMenu.setBounds(10, 10, 40, 40);
            for (int i = 200; i >= 0; i = i - 10) {
                pnMenuJPanel.setBounds(0, 0, i, 700);
            }          

        }

    }

}
