/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import db.DBConnection;
import entity.NhanVien;
import service.impl.NhanVienImp;
import util.RoundedBorderWithColor;

/**
 *
 * @author Le Quoc Phong
 */
public class QuanLyHoaDon extends javax.swing.JFrame implements ActionListener {
    private JPanel pnMain;
    private JPanel pnMenuJPanel;
    private JButton btnTrangChu, btnSanPham, btnThongKe, btnNhanVien, btnKhachHang, btnHoaDon, btnLogOut;
    private JLabel lblIconUser, lblIconLogOut, lblTenUser;
    private static String maNVTK = "";

    private NhanVienImp nhanVienImp = new NhanVienImp();

    /**
     * Creates new form QuanLyHoaDon
     */
    public QuanLyHoaDon(String maNV) {
        maNVTK=maNV;
        try {
            DBConnection.getInstance().connect();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        setSize(1050, 700);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private void PnMenu() {
        pnMenuJPanel = new JPanel();
        pnMenuJPanel.setLayout(null);
        pnMenuJPanel.setBounds(0, 0, 1050, 100);
        pnMenuJPanel.setBackground(Color.decode("#1D81CE"));

        btnTrangChu = new JButton("Trang chủ");
        btnTrangChu.setHorizontalAlignment(JButton.CENTER);
        btnTrangChu.setBounds(18, 25, 128, 35);
        btnTrangChu.setIcon(new ImageIcon(
                new ImageIcon("public/icon/home.png").getImage().getScaledInstance(25, 25,
                        Image.SCALE_SMOOTH)));
        btnTrangChu.setBorder(new RoundedBorderWithColor(Color.decode("#1D81CE"), 1, 10));

        btnSanPham = new JButton("Sản phẩm");
        btnSanPham.setHorizontalAlignment(JButton.CENTER);
        btnSanPham.setBounds(150, 25, 128, 35);
        btnSanPham.setIcon(new ImageIcon(
                new ImageIcon("public/icon/product.png").getImage().getScaledInstance(30, 30,
                        Image.SCALE_SMOOTH)));
        btnSanPham.setBorder(new RoundedBorderWithColor(Color.decode("#1D81CE"), 1, 10));

        btnThongKe = new JButton("Thống kê");
        btnThongKe.setHorizontalAlignment(JButton.CENTER);
        btnThongKe.setBounds(280, 25, 135, 35);
        btnThongKe.setIcon(new ImageIcon(
                new ImageIcon("public/icon/money.png").getImage().getScaledInstance(25, 25,
                        Image.SCALE_SMOOTH)));
        btnThongKe.setBorder(new RoundedBorderWithColor(Color.decode("#1D81CE"), 1, 10));

        btnNhanVien = new JButton("Nhân viên");
        btnNhanVien.setHorizontalAlignment(JButton.CENTER);
        btnNhanVien.setBounds(418, 25, 128, 35);
        btnNhanVien.setIcon(new ImageIcon(
                new ImageIcon("public/icon/staff.png").getImage().getScaledInstance(25, 25,
                        Image.SCALE_SMOOTH)));
        btnNhanVien.setBorder(new RoundedBorderWithColor(Color.decode("#1D81CE"), 1, 10));

        btnKhachHang = new JButton("Khách hàng");
        btnKhachHang.setHorizontalAlignment(JButton.CENTER);
        btnKhachHang.setBounds(550, 25, 128, 35);
        btnKhachHang.setIcon(new ImageIcon(
                new ImageIcon("public/icon/customer.png").getImage().getScaledInstance(25, 25,
                        Image.SCALE_SMOOTH)));
        btnKhachHang.setBorder(new RoundedBorderWithColor(Color.decode("#1D81CE"), 1, 10));

        btnHoaDon = new JButton("Hóa đơn");
        btnHoaDon.setHorizontalAlignment(JButton.CENTER);
        btnHoaDon.setBounds(680, 25, 128, 35);
        btnHoaDon.setIcon(new ImageIcon(
                new ImageIcon("public/icon/bill.png").getImage().getScaledInstance(25, 25,
                        Image.SCALE_SMOOTH)));
        btnHoaDon.setBorder(new RoundedBorderWithColor(Color.decode("#1D81CE"), 1, 10));

        lblIconUser = new JLabel();
        lblIconUser.setBounds(850, 10, 30, 30);
        lblIconUser.setIcon(new ImageIcon(
                new ImageIcon("public/icon/user.png").getImage().getScaledInstance(30, 30,
                        Image.SCALE_SMOOTH)));

        lblTenUser = new JLabel("");
        for (NhanVien nv : nhanVienImp.getAllNhanVien()) {
            if (nv.getMaNV().equals(maNVTK)) {
                lblTenUser.setText(nv.getTenNV());
            }
        }
        lblTenUser.setBounds(900, 10, 100, 30);
        lblTenUser.setForeground(Color.WHITE);

        lblIconLogOut = new JLabel();
        lblIconLogOut.setBounds(850, 50, 30, 30);
        lblIconLogOut.setIcon(new ImageIcon(
                new ImageIcon("public/icon/logout.png").getImage().getScaledInstance(30, 30,
                        Image.SCALE_SMOOTH)));

        btnLogOut = new JButton("Đăng xuất");
        btnLogOut.setBackground(Color.decode("#1D81CE"));
        btnLogOut.setBounds(890, 50, 100, 30);
        btnLogOut.setForeground(Color.WHITE);

        add(pnMenuJPanel);
        pnMenuJPanel.add(btnTrangChu);
        pnMenuJPanel.add(btnSanPham);
        pnMenuJPanel.add(btnThongKe);
        pnMenuJPanel.add(btnNhanVien);
        pnMenuJPanel.add(btnKhachHang);
        pnMenuJPanel.add(btnHoaDon);
        pnMenuJPanel.add(lblIconUser);
        pnMenuJPanel.add(lblTenUser);
        pnMenuJPanel.add(lblIconLogOut);
        pnMenuJPanel.add(btnLogOut);

        btnKhachHang.addActionListener(this);
        btnTrangChu.addActionListener(this);
        btnSanPham.addActionListener(this);
        btnNhanVien.addActionListener(this);
        btnThongKe.addActionListener(this);
        btnHoaDon.addActionListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        PnMenu();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btntim = new javax.swing.JButton();
        btnxoa = new javax.swing.JButton();
        txtDate = new com.toedter.calendar.JDateChooser();
        btnrefersh = new javax.swing.JButton();
        btnxemchitiet = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1050, 700));
        setMinimumSize(new java.awt.Dimension(1050, 700));
        setPreferredSize(new java.awt.Dimension(1050, 700));
        setSize(new java.awt.Dimension(1050, 700));

        jPanel1.setBackground(new java.awt.Color(255, 204, 102));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("QUẢN LÝ HÓA ĐƠN");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Tìm hóa đơn");
        jLabel2.setPreferredSize(new java.awt.Dimension(81, 25));

        btntim.setText("TIM");
        btntim.setPreferredSize(new java.awt.Dimension(40, 23));
        btntim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntimActionPerformed(evt);
            }
        });

        btnxoa.setText("jButton3");

        txtDate.setPreferredSize(new java.awt.Dimension(200, 25));

        btnrefersh.setText("jButton4");
        btnrefersh.setPreferredSize(new java.awt.Dimension(40, 23));

        btnxemchitiet.setText("jButton2");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null }
                },
                new String[] {
                        "Title 1", "Title 2", "Title 3", "Title 4"
                }));
        jScrollPane1.setViewportView(jTable1);

        jLabel3.setText("SPORT STORE   Hotline 19001919");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout
                                                .createSequentialGroup()
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btntim, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(jLabel1)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(btnrefersh,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                        287, Short.MAX_VALUE)
                                                                .addComponent(btnxoa)
                                                                .addGap(20, 20, 20)
                                                                .addComponent(btnxemchitiet)))))
                                .addGap(104, 104, 104))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                layout.createSequentialGroup()
                                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(471, 471, 471))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                layout.createSequentialGroup()
                                                        .addComponent(jLabel3)
                                                        .addGap(422, 422, 422)))));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1)
                                .addGap(48, 48, 48)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnxoa)
                                                .addComponent(btnxemchitiet))
                                        .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnrefersh, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btntim, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addGap(0, 61, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>

    private void btntimActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLyHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if(maNVTK.equals("")) {
                    new DangNhap().setVisible(true);
                    new QuanLyHoaDon(null).dispose();
                }else {
                    new QuanLyHoaDon(maNVTK).setVisible(true);
                }
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton btnrefersh;
    private javax.swing.JButton btntim;
    private javax.swing.JButton btnxemchitiet;
    private javax.swing.JButton btnxoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private com.toedter.calendar.JDateChooser txtDate;
    // End of variables declaration

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        /**
         * Event button menu
         */
        if (o.equals(btnKhachHang)) {
            new QuanLyKhachHang(maNVTK).setVisible(true);
            this.dispose();
        } else if (o.equals(btnTrangChu)) {
            new ManHinhChinh(null, maNVTK).setVisible(true);
        } else if (o.equals(btnSanPham)) {
            new ui.SanPham(maNVTK).setVisible(true);
            this.dispose();
        }else if(o.equals(btnNhanVien)) {
            new QuanLyNhanVien(maNVTK).setVisible(true);
            this.dispose();
        }else if(o.equals(btnThongKe)) {
            new ThongKeDoanhThu(maNVTK).setVisible(true);
            this.dispose();
        }

    }
}
