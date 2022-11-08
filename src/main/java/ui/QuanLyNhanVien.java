package ui;

import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import util.RoundedBorderWithColor;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import db.DBConnection;
import entity.NhanVien;
import service.INhanVienService;
import service.impl.NhanVienImp;

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
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;

public class QuanLyNhanVien extends JFrame implements ActionListener, MouseListener{
    private JPanel pnMain;
    private JPanel pnMenuJPanel;
    private JButton btnTrangChu, btnSanPham, btnThongKe, btnNhanVien, btnKhachHang, btnHoaDon, btnLogOut;
    private JLabel lblIconUser, lblIconLogOut, lblTenUser;
    private DefaultTableModel model;
    private NhanVienImp serviceNV = new NhanVienImp();
    private String[] HEADER =  {"Mã nhân viên", "Tên nhân viên", "Số CMND", "Ca Trực"};
    private JComboBox comboBoxTim;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JTable table;
    private JTextField textTen;
    private JTextField textMa;
    private JTextField textSdt;
    private JTextField textEmail;
    private JTextField textCmnd;
    private JLabel lblCaTruc;
    private JComboBox comboBoxCaTruc;
    private JLabel lblDiaChi;
    private JTextField textDiaChi;
    private JLabel lblLuong;
    private JTextField textLuong;
    private JButton btnXoaRong;
    private JComboBox comboBoxGioiTInh;

    public QuanLyNhanVien(){
        
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
        
       
        btnTrangChu.addActionListener(this);
        btnSanPham.addActionListener(this);
        btnKhachHang.addActionListener(this);
        btnThongKe.addActionListener(this);
        btnHoaDon.addActionListener(this);
        btnNhanVien.addActionListener(this);
        
        JLabel lblTitle = new JLabel("Quản Lý Nhân Viên");
        lblTitle.setBorder(new LineBorder(new Color(255, 200, 0), 10, true));
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblTitle.setForeground(new Color(0, 0, 0));
        lblTitle.setBackground(Color.ORANGE);
        lblTitle.setBounds(0, 100, 230, 53);
        lblTitle.setOpaque(true);
        pnMain.add(lblTitle);
        
        JPanel panel = new JPanel();
        panel.setBounds(85, 353, 869, 53);
        pnMain.add(panel);
        panel.setLayout(null);
        
        String[] dsCaTruc = {"Tất cả","Ca Trực 1", "Ca Trực 2", "Ca Trực 3"};
        comboBoxTim = new JComboBox(dsCaTruc);
        comboBoxTim.setBounds(28, 10, 149, 33);
        panel.add(comboBoxTim);
        
        btnThem = new JButton("Thêm");
        btnThem.setForeground(Color.BLACK);
        btnThem.setBackground(Color.GREEN);
        btnThem.setFont(new Font("Tahoma", Font.PLAIN, 24));
        btnThem.setBounds(187, 10, 127, 33);
        panel.add(btnThem);
        
        btnSua = new JButton("sửa");
        btnSua.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnSua.setForeground(Color.BLACK);
        btnSua.setFont(new Font("Tahoma", Font.PLAIN, 24));
        btnSua.setBackground(Color.YELLOW);
        btnSua.setBounds(324, 10, 127, 33);
        panel.add(btnSua);
        
        btnXoa = new JButton("Xóa");
        btnXoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnXoa.setForeground(Color.BLACK);
        btnXoa.setFont(new Font("Tahoma", Font.PLAIN, 24));
        btnXoa.setBackground(Color.PINK);
        btnXoa.setBounds(474, 10, 127, 33);
        panel.add(btnXoa);
        
        btnXoaRong = new JButton("Xóa rỗng");
        btnXoaRong.setForeground(Color.BLACK);
        btnXoaRong.setFont(new Font("Tahoma", Font.PLAIN, 24));
        btnXoaRong.setBackground(new Color(192, 192, 192));
        btnXoaRong.setBounds(636, 10, 164, 33);
        panel.add(btnXoaRong);
        
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
                    return Integer.class;
                }
                default:
                    return String.class;
                }
            }
        };
        table.setFont(new Font("Tahoma", Font.PLAIN, 15));
        model = new DefaultTableModel(HEADER,0);
        table.setModel(model);
        List<NhanVien> ds = serviceNV.getAllNhanVien();
        updateTable(ds);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(85, 416, 869, 237);
        pnMain.add(scrollPane);
        
        JLabel lblTen = new JLabel("Tên nhân viên:");
        lblTen.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblTen.setBounds(85, 163, 122, 30);
        pnMain.add(lblTen);
        
        textTen = new JTextField("Trần Anh Duy");
        textTen.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textTen.setBounds(213, 163, 263, 30);
        pnMain.add(textTen);
        textTen.setColumns(10);
        
        JLabel lblMa = new JLabel("Mã nhân viên:");
        lblMa.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblMa.setBounds(85, 199, 122, 30);
        pnMain.add(lblMa);
        
        textMa = new JTextField("NV123");
        textMa.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textMa.setColumns(10);
        textMa.setBounds(213, 195, 263, 30);
        pnMain.add(textMa);
        
        JLabel lblSdt = new JLabel("Số điện thoại:");
        lblSdt.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblSdt.setBounds(85, 235, 122, 30);
        pnMain.add(lblSdt);
        
        textSdt = new JTextField("0372087633");
        textSdt.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textSdt.setColumns(10);
        textSdt.setBounds(213, 231, 263, 30);
        pnMain.add(textSdt);
        
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblEmail.setBounds(85, 269, 122, 20);
        pnMain.add(lblEmail);
        
        textEmail = new JTextField("hahh@gmail.com");
        textEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textEmail.setColumns(10);
        textEmail.setBounds(213, 265, 263, 30);
        pnMain.add(textEmail);
        
        JLabel lblGioiTinh = new JLabel("Giới tính:");
        lblGioiTinh.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblGioiTinh.setBounds(85, 304, 122, 30);
        pnMain.add(lblGioiTinh);
        
        comboBoxGioiTInh = new JComboBox();
        comboBoxGioiTInh.setFont(new Font("Tahoma", Font.PLAIN, 18));
        comboBoxGioiTInh.setModel(new DefaultComboBoxModel(new String[] {"nam", "nữ"}));
        comboBoxGioiTInh.setBounds(213, 303, 72, 30);
        pnMain.add(comboBoxGioiTInh);
        
        JLabel lblCmnd = new JLabel("CMND:");
        lblCmnd.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblCmnd.setBounds(559, 166, 122, 20);
        pnMain.add(lblCmnd);
        
        textCmnd = new JTextField("111222333");
        textCmnd.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textCmnd.setColumns(10);
        textCmnd.setBounds(691, 163, 263, 30);
        pnMain.add(textCmnd);
        
        lblCaTruc = new JLabel("Ca Trực:");
        lblCaTruc.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblCaTruc.setBounds(559, 202, 122, 30);
        pnMain.add(lblCaTruc);
        
        comboBoxCaTruc = new JComboBox();
        comboBoxCaTruc.setFont(new Font("Tahoma", Font.PLAIN, 18));
        comboBoxCaTruc.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3"}));
        comboBoxCaTruc.setBounds(691, 198, 108, 30);
        pnMain.add(comboBoxCaTruc);
        
        lblDiaChi = new JLabel("Địa Chỉ:");
        lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblDiaChi.setBounds(559, 235, 122, 21);
        pnMain.add(lblDiaChi);
        
        textDiaChi = new JTextField("Thanh Hóa");
        textDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textDiaChi.setColumns(10);
        textDiaChi.setBounds(691, 231, 263, 30);
        pnMain.add(textDiaChi);
        
        lblLuong = new JLabel("Lương:");
        lblLuong.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblLuong.setBounds(559, 269, 122, 30);
        pnMain.add(lblLuong);
        
        textLuong = new JTextField("1500000");
        textLuong.setFont(new Font("Tahoma", Font.PLAIN, 18));
        textLuong.setColumns(10);
        textLuong.setBounds(691, 265, 263, 30);
        pnMain.add(textLuong);
        
        
        comboBoxTim.addActionListener(this);
        btnThem.addActionListener(this);
        btnSua.addActionListener(this);
        btnXoa.addActionListener(this);
        btnXoaRong.addActionListener(this);
        table.addMouseListener(this);
        
    }
    
     
    private void updateTable(List<NhanVien> ds) {
        model.setRowCount(0);
        for(NhanVien nv :ds) {
            Object row[] = {nv.getMaNV(),nv.getTenNV(),nv.getCmnd(), nv.getCaTruc()};
            model.addRow(row);
        }
    }

    public static void main(String[] args)  {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QuanLyNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QuanLyNhanVien().setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e){
        Object obj = e.getSource();
        if(obj.equals(comboBoxTim)) {
            String caTruc = (String) comboBoxTim.getSelectedItem();
            int ca = 0;
            if(caTruc.equals("Tất cả"))
                ca = 0;
            if(caTruc.equals("Ca Trực 1"))
                ca = 1;
            if(caTruc.equals("Ca Trực 2"))
                ca = 2;
            if(caTruc.equals("Ca Trực 3"))
                ca = 3;
            List<NhanVien> ds = new ArrayList<NhanVien>();
            ds = serviceNV.timKiem("", "", "", "", "", "", ca, "", 0.0);
            updateTable(ds);
        }
        if(obj.equals(btnXoa)) {
            int index[] = table.getSelectedRows();
            if(index.length>0) {
                for(int i= 0; i< index.length;i++) {
                    String ma = (String) table.getValueAt(index[i], 0);
                    serviceNV.xoaNhanVien(ma);
                }
                List<NhanVien> ds = null;
                ds = serviceNV.getAllNhanVien();
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
                String cmnd = textCmnd.getText().trim();
                String diaChi = textDiaChi.getText().trim();
                int caTruc = Integer.parseInt(comboBoxCaTruc.getSelectedItem().toString());
                double luong =  Double.parseDouble(textLuong.getText().trim());
                NhanVien nv = new NhanVien(ma, ten, sdt, email, gioiTinh, cmnd, caTruc, diaChi, luong);
                if(serviceNV.themNhanVien(nv))
                    showMessage("thêm nhân viên thành công");
                else
                    showMessage("trùng mã nhân viên");
            }
        }
        if(obj.equals(btnXoaRong)) {
            try {
                resetUI();
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        if(obj.equals(btnSua)) {
            if(tableIsSelected()) {
                if(validData()) {
                    String ma = textMa.getText().trim();
                    String ten = textTen.getText().trim();
                    String sdt = textSdt.getText().trim();
                    String email = textEmail.getText().trim();
                    String gioiTinh = (String) comboBoxGioiTInh.getSelectedItem();
                    String cmnd = textCmnd.getText().trim();
                    String diaChi = textDiaChi.getText().trim();
                    int caTruc = Integer.parseInt(comboBoxCaTruc.getSelectedItem().toString());
                    double luong =  Double.parseDouble(textLuong.getText().trim());
                    NhanVien nv = new NhanVien(ma, ten, sdt, email, gioiTinh, cmnd, caTruc, diaChi, luong);
                    if(serviceNV.suaNhanVien(nv)) {
                        showMessage("đã cập nhập thông tin");
                        try {
                            resetUI();
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                    else
                        showMessage("cập nhập thông tin thất bại");
                }
            }
        }
    }

    private void resetUI() throws SQLException {
        List<NhanVien> ds = serviceNV.getAllNhanVien();
        updateTable(ds);
        textTen.setText("");
        textMa.setText("");
        textMa.setEditable(true);
        textSdt.setText("");
        textDiaChi.setText("");
        textEmail.setText("");
        textCmnd.setText("");
        textLuong.setText("");
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
        String cmnd = textCmnd.getText().trim();
        String diaChi = textDiaChi.getText().trim();
        String caTruc = (String) comboBoxCaTruc.getSelectedItem();
        double luong = 0.0;
        
        if(!ma.matches("NV[0-9]{3}")) {
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
        if(cmnd.equals("") || cmnd == null) {
            showMessage("cmnd không được rỗng");
            return false;
        }
        if(diaChi.equals("") || diaChi == null) {
            showMessage("địa chỉ không được rỗng");
            return false;
        }
        try {
            luong = Double.parseDouble(textLuong.getText().trim());
        } catch (Exception e) {
            showMessage("Lương phải là số");
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
    public void mousePressed(MouseEvent e){
        Object obj = e.getSource();
        if(obj.equals(table)) {
            NhanVien nv;
            nv = serviceNV.timMa((String) table.getValueAt(table.getSelectedRow(), 0));
            textTen.setText(nv.getTenNV());
            textMa.setText(nv.getMaNV());
            textMa.setEditable(false);
            textSdt.setText(nv.getSdt());
            textDiaChi.setText(nv.getDiaChi());
            textEmail.setText(nv.getEmail());
            textCmnd.setText(nv.getCmnd());
            textLuong.setText(String.format("%.0f", nv.getLuong()));
            comboBoxGioiTInh.setSelectedIndex(nv.getGioiTinh().equals("nam")?0:1);
            comboBoxCaTruc.setSelectedIndex(nv.getCaTruc()-1);
            
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