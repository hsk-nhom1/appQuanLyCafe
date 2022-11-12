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
import entity.KhachHang;
import entity.NhanVien;
import service.IKhachHangService;
import service.INhanVienService;
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

public class QuanLyKhachHang extends JFrame implements ActionListener, MouseListener{
    private JPanel pnMain;
    private JPanel pnMenuJPanel;
    private JButton btnTrangChu, btnSanPham, btnThongKe, btnNhanVien, btnKhachHang, btnHoaDon, btnLogOut;
    private JLabel lblIconUser, lblIconLogOut, lblTenUser;
    
    private DefaultTableModel model;
    private String[] HEADER =  {"Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Giới tính"};
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JTable table;
    private JTextField textTen;
    private JTextField textMa;
    private JTextField textSdt;
    private JTextField textEmail;
    private JLabel lblDiaChi;
    private JTextField textDiaChi;
    private JButton btnXoaRong;
    private JComboBox comboBoxGioiTInh;
    private IKhachHangService serviceKH;
    private JTextField textTim;
    private JButton btnTim;

    public QuanLyKhachHang(){
        try {
            DBConnection.getInstance().connect();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        setTitle("Quản lý nhân viên");
        setSize(1050, 700);
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
        
        JLabel lblTitle = new JLabel("Quản Lý Khách Hàng");
        lblTitle.setBorder(new LineBorder(new Color(255, 200, 0), 10, true));
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblTitle.setForeground(new Color(0, 0, 0));
        lblTitle.setBackground(Color.ORANGE);
        lblTitle.setBounds(0, 100, 271, 53);
        lblTitle.setOpaque(true);
        pnMain.add(lblTitle);
        
        JPanel panel = new JPanel();
//        panel.setBackground(new Color(238, 232, 170));
        panel.setBounds(85, 353, 869, 53);
        pnMain.add(panel);
        panel.setLayout(null);
        
        btnThem = new JButton("Thêm");
        btnThem.setForeground(Color.BLACK);
        btnThem.setBackground(Color.GREEN);
        btnThem.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnThem.setBounds(259, 10, 127, 33);
        panel.add(btnThem);
        
        btnSua = new JButton("Sửa");
        btnSua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnSua.setForeground(Color.BLACK);
        btnSua.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnSua.setBackground(Color.YELLOW);
        btnSua.setBounds(408, 10, 127, 33);
        panel.add(btnSua);
        
        btnXoa = new JButton("Xóa");
        btnXoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnXoa.setForeground(Color.BLACK);
        btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnXoa.setBackground(Color.PINK);
        btnXoa.setBounds(560, 10, 127, 33);
        panel.add(btnXoa);
        
        btnXoaRong = new JButton("Xóa rỗng");
        btnXoaRong.setForeground(Color.BLACK);
        btnXoaRong.setFont(new Font("Tahoma", Font.PLAIN, 20));
        btnXoaRong.setBackground(new Color(192, 192, 192));
        btnXoaRong.setBounds(710, 10, 149, 33);
        panel.add(btnXoaRong);
        
        textTim = new JTextField();
        textTim.setBounds(55, 10, 142, 33);
        panel.add(textTim);
        textTim.setColumns(10);
        
        JLabel lblNewLabel = new JLabel("Tên:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblNewLabel.setBounds(10, 17, 45, 27);
        panel.add(lblNewLabel);
        
        btnTim = new JButton("");
        btnTim.setIcon(new ImageIcon(
                new ImageIcon("public/icon/search.png").getImage().getScaledInstance(20, 20,
                        Image.SCALE_SMOOTH)));
        
        btnTim.setBounds(207, 10, 35, 33);
        panel.add(btnTim);
        
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
                default:
                    return String.class;
                }
            }
        };
        table.setFont(new Font("Tahoma", Font.PLAIN, 15));
        model = new DefaultTableModel(HEADER,0);
        table.setModel(model);
        serviceKH = new KhachHangImpl();
        List<KhachHang> ds = serviceKH.getDsKhachHang();
        updateTable(ds);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(85, 416, 869, 237);
        pnMain.add(scrollPane);
        
        JLabel lblTen = new JLabel("Tên khách hàng:");
        lblTen.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblTen.setBounds(85, 163, 137, 19);
        pnMain.add(lblTen);
        
        textTen = new JTextField("Trần Anh Duy");
        textTen.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textTen.setBounds(227, 160, 443, 30);
        pnMain.add(textTen);
        textTen.setColumns(10);
        
        JLabel lblMa = new JLabel("Mã khách hàng:");
        lblMa.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblMa.setBounds(85, 199, 137, 19);
        pnMain.add(lblMa);
        
        textMa = new JTextField("KH123");
        textMa.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textMa.setColumns(10);
        textMa.setBounds(227, 196, 443, 30);
        pnMain.add(textMa);
        
        JLabel lblSdt = new JLabel("Số điện thoại:");
        lblSdt.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblSdt.setBounds(85, 235, 122, 19);
        pnMain.add(lblSdt);
        
        textSdt = new JTextField("0372087633");
        textSdt.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textSdt.setColumns(10);
        textSdt.setBounds(227, 229, 443, 30);
        pnMain.add(textSdt);
        
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblEmail.setBounds(85, 269, 122, 30);
        pnMain.add(lblEmail);
        
        textEmail = new JTextField("hahh@gmail.com");
        textEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textEmail.setColumns(10);
        textEmail.setBounds(227, 262, 443, 30);
        pnMain.add(textEmail);
        
        JLabel lblGioiTinh = new JLabel("Giới tính:");
        lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblGioiTinh.setBounds(85, 304, 122, 30);
        pnMain.add(lblGioiTinh);
        
        comboBoxGioiTInh = new JComboBox();
        comboBoxGioiTInh.setFont(new Font("Tahoma", Font.PLAIN, 15));
        comboBoxGioiTInh.setModel(new DefaultComboBoxModel(new String[] {"nam", "nữ"}));
        comboBoxGioiTInh.setBounds(227, 304, 72, 30);
        pnMain.add(comboBoxGioiTInh);
        
        lblDiaChi = new JLabel("Địa Chỉ:");
        lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblDiaChi.setBounds(311, 304, 122, 30);
        pnMain.add(lblDiaChi);
        
        textDiaChi = new JTextField("Thanh Hóa");
        textDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textDiaChi.setColumns(10);
        textDiaChi.setBounds(393, 302, 277, 30);
        pnMain.add(textDiaChi);
        btnThem.addActionListener(this);
        btnSua.addActionListener(this);
        btnXoa.addActionListener(this);
        btnXoaRong.addActionListener(this);
        table.addMouseListener(this);
        btnTim.addActionListener(this);
    }
    
     
    private void updateTable(List<KhachHang> ds) {
        model.setRowCount(0);
        for(KhachHang kh :ds) {
            Object row[] = {kh.getMaKH(),kh.getTenKH(),kh.getSdt(), kh.getGioiTinh()};
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
                new QuanLyKhachHang().setVisible(true);
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
            new QuanLyKhachHang().setVisible(false);
        }
        
        /**
         * Event button chức năng
         */
        if(obj.equals(btnTim)) {
            String ten = textTim.getText();
            List<KhachHang> ds = new ArrayList<KhachHang>();
            ds = serviceKH.timKiem("",ten,"","","","");
            updateTable(ds);
        }
        if(obj.equals(btnXoa)) {
            int index[] = table.getSelectedRows();
            if(index.length>0) {
                for(int i= 0; i< index.length;i++) {
                    String ma = (String) table.getValueAt(index[i], 0);
                    serviceKH.xoaKhachHang(ma);
                }
                List<KhachHang> ds = null;
                ds = serviceKH.getDsKhachHang();
                updateTable(ds);
            }
            else {
                JOptionPane.showMessageDialog(pnMain, "Hãy chọn 1 nhân viên muốn xóa");
            }
        }
        if(obj.equals(btnThem)) {
            if(validData()) {
                String ma = textMa.getText().trim();
                String ten = textTen.getText().trim();
                String sdt = textSdt.getText().trim();
                String email = textEmail.getText().trim();
                String gioiTinh = (String) comboBoxGioiTInh.getSelectedItem();
                String diaChi = textDiaChi.getText().trim();
                KhachHang nv = new KhachHang(ma, ten, sdt, email, gioiTinh, diaChi);
                if(serviceKH.themKhachHang(nv)) {
                    showMessage("thêm nhân viên thành công");
                    updateTable(serviceKH.getDsKhachHang());
                }
                else
                    showMessage("trùng mã nhân viên");
            }
        }
        if(obj.equals(btnXoaRong)) {
            resetUI();
        }
        if(obj.equals(btnSua)) {
            if(tableIsSelected()) {
                if(validData()) {
                    String ma = textMa.getText().trim();
                    String ten = textTen.getText().trim();
                    String sdt = textSdt.getText().trim();
                    String email = textEmail.getText().trim();
                    String gioiTinh = (String) comboBoxGioiTInh.getSelectedItem();
                    String diaChi = textDiaChi.getText().trim();
                    KhachHang kh = new KhachHang(ma, ten, sdt, email, gioiTinh, diaChi);
                    if(serviceKH.suaKhachHang(kh)) {
                        showMessage("đã cập nhập thông tin");
                        resetUI();
                    }
                    else
                        showMessage("cập nhập thông tin thất bại");
                }
            }
        }
    }

    private void resetUI(){
        List<KhachHang> ds = serviceKH.getDsKhachHang();
        updateTable(ds);
        textTen.setText("");
        textMa.setText("");
        textMa.setEditable(true);
        textSdt.setText("");
        textDiaChi.setText("");
        textEmail.setText("");
    }

    private boolean tableIsSelected() {
        int i = table.getSelectedRow();
        if(i<0) {
            showMessage("hãy chọn một nhân viên muốn sửa thông tin");
            return false;
        }
        return true;
    }

    private boolean validData() {
        String ma = textMa.getText().trim();
        String ten = textTen.getText().trim();
        String sdt = textSdt.getText().trim();
        String email = textEmail.getText().trim();
        String gioiTinh = (String) comboBoxGioiTInh.getSelectedItem();
        String diaChi = textDiaChi.getText().trim();
        double luong = 0.0;
        
        if(!ma.matches("KH[0-9]{3}")) {
            showMessage("mã nhân viên không hợp lệ");
            return false;
        }
        if(ten.equals("") || ten == null) {
            showMessage("tên nhân viên không được rỗng");
            return false;
        }
        if(!sdt.matches("0[0-9]{9}")) {
            showMessage("số điện thoại phải bắt đầu bằng 0 và phải có đủ 10 số");
            return false;
        }
        if(!email.matches("[a-zA-Z0-9]+@gmail\\.com")) {
            showMessage("chỉ chấp nhận email ...@gmail.com");
            return false;
        }
        if(diaChi.equals("") || diaChi == null) {
            showMessage("địa chỉ không được rỗng");
            return false;
        }
        
        return true;
    }
    private void showMessage(String s) {
        JOptionPane.showMessageDialog(pnMain, s);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object obj = e.getSource();
        if(obj.equals(table)) {
            KhachHang kh;
            kh = serviceKH.timMa((String) table.getValueAt(table.getSelectedRow(), 0));
            textTen.setText(kh.getTenKH());
            textMa.setText(kh.getMaKH());
            textMa.setEditable(false);
            textSdt.setText(kh.getSdt());
            textDiaChi.setText(kh.getDiaChi());
            textEmail.setText(kh.getEmail());
            comboBoxGioiTInh.setSelectedIndex(kh.getGioiTinh().equals("nam")?0:1);
            
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
