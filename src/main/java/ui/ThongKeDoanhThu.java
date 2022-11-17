package ui;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import util.RoundedBorderWithColor;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;

import db.DBConnection;
import entity.CTHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import service.IHoaDonService;
import service.IKhachHangService;
import service.INhanVienService;
import service.impl.HoaDonImp;
import service.impl.KhachHangImpl;

import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;

public class ThongKeDoanhThu extends JFrame implements ActionListener{
    private JPanel pnMain;
    private JPanel pnMenuJPanel;
    private JButton btnTrangChu, btnSanPham, btnThongKe, btnNhanVien, btnKhachHang, btnHoaDon, btnLogOut;
    private JLabel lblIconUser, lblIconLogOut, lblTenUser;
    
    private DefaultTableModel model;
    private String[] HEADER =  {"Mã hóa đơn", "Ngày lập hóa đơn", "Nhân viên", "Bàn", "Khách hàng", "Thành tiền"};
    private JTable table;
    private IHoaDonService serviceHD;
    private JTextField textFieldDoanhThu;
    private JComboBox comboBoxLoai;
    private JComboBox comboBoxNgay;
    private JComboBox comboBoxThang;
    private JComboBox comboBoxNam;
    private JButton btnThongKeDoanhThu;
    private JLabel lblNgay;

    public ThongKeDoanhThu(){
        try {
            DBConnection.getInstance().connect();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        setTitle("Quản lý nhân viên");
        setSize(1050, 700);
//        setSize(1300, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        MainUI();
        PnMenu();
    }

    public void MainUI() {
        pnMain = new JPanel();
        pnMain.setBorder(null);
        pnMain.setPreferredSize(new Dimension(20, 20));
        pnMain.setLayout(null);
        setContentPane(pnMain);
    }
    
    public void PnMenu()  {
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

        lblTenUser = new JLabel("Pham Ha Nam");
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

       getContentPane().add(pnMenuJPanel);
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
        
       
        btnTrangChu.addActionListener(this);
        btnSanPham.addActionListener(this);
        btnKhachHang.addActionListener(this);
        btnThongKe.addActionListener(this);
        btnHoaDon.addActionListener(this);
        btnNhanVien.addActionListener(this);
        
        JLabel lblTitle = new JLabel("Thống kê doanh thu");
        lblTitle.setBorder(new LineBorder(new Color(255, 200, 0), 10, true));
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblTitle.setForeground(new Color(0, 0, 0));
        lblTitle.setBackground(Color.ORANGE);
        lblTitle.setBounds(0, 100, 271, 53);
        lblTitle.setOpaque(true);
        pnMain.add(lblTitle);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(192, 192, 192));
//        panel.setBackground(new Color(238, 232, 170));
        panel.setBounds(85, 163, 869, 53);
        pnMain.add(panel);
        panel.setLayout(null);
        
        comboBoxLoai = new JComboBox();
        comboBoxLoai.setFont(new Font("Tahoma", Font.PLAIN, 18));
        comboBoxLoai.setModel(new DefaultComboBoxModel(new String[] {"Thống kê theo ngày", "Thống kê theo tháng"}));
        comboBoxLoai.setBounds(10, 13, 204, 33);
        panel.add(comboBoxLoai);
        
        comboBoxNgay = new JComboBox();
        comboBoxNgay.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
        comboBoxNgay.setFont(new Font("Tahoma", Font.PLAIN, 18));
        comboBoxNgay.setBounds(305, 13, 77, 33);
        panel.add(comboBoxNgay);
        
        comboBoxThang = new JComboBox();
        comboBoxThang.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
        comboBoxThang.setFont(new Font("Tahoma", Font.PLAIN, 18));
        comboBoxThang.setBounds(451, 13, 77, 33);
        panel.add(comboBoxThang);
        
        lblNgay = new JLabel("Ngày:");
        lblNgay.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNgay.setBounds(256, 13, 56, 33);
        panel.add(lblNgay);
        
        JLabel lblThng = new JLabel("Tháng:");
        lblThng.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblThng.setBounds(392, 13, 67, 33);
        panel.add(lblThng);
        
        JLabel lblNm = new JLabel("Năm:");
        lblNm.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNm.setBounds(538, 13, 67, 33);
        panel.add(lblNm);
        
        comboBoxNam = new JComboBox();
        comboBoxNam.setModel(new DefaultComboBoxModel(new String[] {"2022", "2023"}));
        comboBoxNam.setFont(new Font("Tahoma", Font.PLAIN, 18));
        comboBoxNam.setBounds(587, 13, 77, 33);
        panel.add(comboBoxNam);
        
        btnThongKeDoanhThu = new JButton("Thống kê");
        btnThongKeDoanhThu.setBackground(new Color(255, 255, 128));
        btnThongKeDoanhThu.setFont(new Font("Tahoma", Font.PLAIN, 18));
        btnThongKeDoanhThu.setBounds(686, 13, 142, 33);
        panel.add(btnThongKeDoanhThu);
        
        table = new JTable() {
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                case 0:{
                    return String.class;
                }
                case 1:{
                    return String.class;
                }
                case 2:{
                    return String.class;
                }
                case 3:{
                    return String.class;
                }
                case 4:{
                    return String.class;
                }
                case 5:{
                    return String.class;
                }
                case 6:{
                    return Double.class;
                }
                default:
                    return String.class;
                }
            }
        };
        table.setFont(new Font("Tahoma", Font.PLAIN, 15));
        model = new DefaultTableModel(HEADER,0);
        table.setModel(model);
        serviceHD = new HoaDonImp();
        List<HoaDon> ds = serviceHD.getAllHoaDon();
        updateTable(ds);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(85, 241, 869, 354);
        pnMain.add(scrollPane);
        
        textFieldDoanhThu = new JTextField();
        textFieldDoanhThu.setText("0.0");
        textFieldDoanhThu.setFont(new Font("Tahoma", Font.PLAIN, 25));
        textFieldDoanhThu.setBounds(354, 605, 300, 53);
        pnMain.add(textFieldDoanhThu);
        textFieldDoanhThu.setColumns(10);
        textFieldDoanhThu.setEditable(false);
        
        JLabel lblNewLabel_1 = new JLabel("Tổng doanh thu:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 30));
        lblNewLabel_1.setBounds(110, 606, 234, 47);
        pnMain.add(lblNewLabel_1);
        
        
        
        btnThongKeDoanhThu.addActionListener(this);
        comboBoxLoai.addActionListener(this);
        
    }
    
     
    private void updateTable(List<HoaDon> ds) {
        model.setRowCount(0);
        for(HoaDon hd :ds) {
            double tongTien = 0;
            for(CTHoaDon cthd: hd.getDsCTHD()) {
                tongTien += cthd.getSanPham().getGia() * cthd.getSoLuong();
            }
            Object row[] = {hd.getMaHD(), hd.getNgayLapHD(), hd.getNhanVien().getTenNV(), hd.getBan().getTenBan(), hd.getKhachHang().getTenKH(), tongTien};
            model.addRow(row);
        }
    }

    public static void main(String[] args){
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongKeDoanhThu().setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        /**
         * Event button menu
         */
        if(obj.equals(btnTrangChu)) {
            new ManHinhChinh(null).setVisible(true);
            new ThongKeDoanhThu().setVisible(false);
        }
        
        /**
         * Event button chức năng
         */
        if(obj.equals(comboBoxLoai)) {
            if(comboBoxLoai.getSelectedIndex()==1) {
                lblNgay.setVisible(false);
                comboBoxNgay.setVisible(false);
            }else {
                lblNgay.setVisible(true);
                comboBoxNgay.setVisible(true);
            }
        }
        if(obj.equals(btnThongKeDoanhThu)) {
            if(comboBoxLoai.getSelectedIndex()==0) {
                int ngay = Integer.parseInt(comboBoxNgay.getSelectedItem().toString());
                int thang = Integer.parseInt(comboBoxThang.getSelectedItem().toString());
                int nam = Integer.parseInt(comboBoxNam.getSelectedItem().toString());
                List<HoaDon> dshd = new ArrayList<HoaDon>();
                for(HoaDon hd: serviceHD.getAllHoaDon()) {
                    if(hd.getNgayLapHD().getDayOfMonth()==ngay && hd.getNgayLapHD().getMonthValue()==thang && hd.getNgayLapHD().getYear()==nam) {
                        dshd.add(hd);
                        
                    }
                }
                updateTable(dshd);
                double tongDoanhThu = 0;
                for(int i = 0; i< table.getRowCount();i++) {
                    tongDoanhThu += (double) table.getValueAt(i, 5);
                }
                textFieldDoanhThu.setText(String.format("%.0f", tongDoanhThu));
            }else {
                int thang = Integer.parseInt(comboBoxThang.getSelectedItem().toString());
                int nam = Integer.parseInt(comboBoxNam.getSelectedItem().toString());
                List<HoaDon> dshd = new ArrayList<HoaDon>();
                for(HoaDon hd: serviceHD.getAllHoaDon()) {
                    if( hd.getNgayLapHD().getMonthValue()==thang && hd.getNgayLapHD().getYear()==nam) {
                        dshd.add(hd);
                    }
                }
                updateTable(dshd);
                double tongDoanhThu = 0;
                for(int i = 0; i< table.getRowCount();i++) {
                    tongDoanhThu += (double) table.getValueAt(i, 5);
                }
                textFieldDoanhThu.setText(String.format("%.0f", tongDoanhThu));
            }
        }
    }

    private void showMessage(String s) {
        JOptionPane.showMessageDialog(pnMain, s);
    }
}
