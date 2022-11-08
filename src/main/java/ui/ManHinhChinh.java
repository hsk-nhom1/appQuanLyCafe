package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import dao.BanDao;
import dao.HoaDonDao;
import dao.NhanVienDao;
import dao.SanPhamDao;
import db.DBConnection;
import entity.Ban;
import entity.CTHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;
import service.impl.BanImp;
import service.impl.HoaDonImp;
import service.impl.KhachHangImpl;
import service.impl.NhanVienImp;
import service.impl.SanPhamImp;
import util.Generator;
import util.RoundedBorderWithColor;

public class ManHinhChinh extends JFrame implements ActionListener, MouseListener {
    private JPanel pnMain;
    private JPanel pnMenuJPanel;
    private JButton btnTrangChu, btnSanPham, btnThongKe, btnNhanVien, btnKhachHang, btnHoaDon, btnLogOut;
    private JLabel lblIconUser, lblIconLogOut, lblTenUser;
    
    /**
     * private
     */
    private JButton[] dsBtn;
    private JButton btnBan1, btnBan2, btnBan3, btnBan4, btnBan5, btnBan6, btnBan7, btnBan8, btnBan9, btnBan10, btnBan11,
            btnBan12, btnBan13, btnBan14, btnBan15, btnBan16, btnBan17, btnBan18, btnBan19, btnBan20, btnBan21,
            btnBan22, btnBan23, btnBan24, btnBan25, btnBan26, btnBan27, btnBan28, btnBan29, btnBan30, btnBan31,
            btnBan32, btnThemOrd, btnXoaOrd, btnTruOrd, btnUpdate, btnTaoHoaDon, btnAdd, btnThanhToan;

    private JLabel lblChuThichGreen, lblChuThichYellow, lblMaBan, lblNgay, lblTenKH, lblSDT, lblTenSPOrd,
            lblSlSPPnRight,
            lblSoLuongSPOrd, lblTongTien, lblTongTienLabel, lblTenSPPnRight;
    private JComboBox<String> cmbMaBan, cmbSLOrd, cbmKhachHang;
    private JTextField txtSDT, txtSoLuongSPOrd, txtTenSPOrd, txtTenSPPnRight;
    private JTable tableOrder, tableSP;
    private DefaultTableModel tableModelOrder, tableModelSP;
    private DatePickerSettings dateSettings;
    private DatePicker txtNgay;

    private SanPhamImp spimp = new SanPhamImp();
    private KhachHangImpl khachHangImp = new KhachHangImpl();
    private HoaDonImp hoaDonImp = new HoaDonImp();
    private BanImp banImp = new BanImp();
    private NhanVienImp nhanVienImp = new NhanVienImp();
    private HoaDonDao hdDao = new HoaDonDao();

    public ManHinhChinh(String maBan) {
        try {
            DBConnection.getInstance().connect();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        setTitle("Màn hình chính");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1050, 700);
        setLocationRelativeTo(null);
        setResizable(false);

        MainUI();
        PnMenu();
        PnLeft();
        PnCenter(maBan);
        PnRight();
    }

    private void PnRight() {
        Box bRight, bTable;
        pnMain.add(bRight = Box.createVerticalBox());
        bRight.add(Box.createVerticalStrut(10));
        bRight.setBounds(770, 100, 260, 560);
        bRight.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Danh sách sản phẩm của cửa hàng"));

        String[] headStrings = { "Mã SP", "Tên sản phẩm", "Giá" };
        tableModelSP = new DefaultTableModel(headStrings, 0);
        tableSP = new JTable(tableModelSP);
        JScrollPane scrollPane = new JScrollPane(tableSP);

        bRight.add(bTable = Box.createHorizontalBox());
        bTable.add(scrollPane);

        Box bTenSP;
        bRight.add(Box.createVerticalStrut(10));
        bRight.add(bTenSP = Box.createHorizontalBox());
        bTenSP.add(lblTenSPPnRight = new JLabel("Tên sản phẩm: "));
        bTenSP.add(txtTenSPPnRight = new JTextField());
        bRight.add(Box.createVerticalStrut(10));

        Box bSlSP;
        bRight.add(bSlSP = Box.createHorizontalBox());
        bSlSP.add(lblSlSPPnRight = new JLabel("Số lượng: "));
        bSlSP.add(cmbSLOrd = new JComboBox<String>());
        for (int i = 0; i < 20; i++) {
            cmbSLOrd.addItem("" + i);
        }

        lblSlSPPnRight.setPreferredSize(lblTenSPPnRight.getPreferredSize());
        bRight.add(Box.createVerticalStrut(10));

        bRight.add(btnAdd = new JButton("ADD"));

        UpdataTableSanPham();
        UpdateTongTien();

        tableSP.addMouseListener(this);
        btnAdd.addActionListener(this);
        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    /**
     * đổ data sản phẩm vào table sản phẩm
     */
    private void UpdataTableSanPham() {
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);

        tableModelSP.setRowCount(0);
        for (SanPham sp : spimp.getDsSP()) {
            String[] row = { sp.getMaSP(), sp.getTenSP(), String.valueOf(en.format(sp.getGia())) };
            tableModelSP.addRow(row);
        }
        tableSP.setModel(tableModelSP);
        tableSP.updateUI();
        cmbSLOrd.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void PnCenter(String maBan) {
        Box boxCenTer, bCenter1, bCenter2, bCenter3, bButton, bBtnChild1, bBtnChild2, bBtnChild3, bTongTien;
        pnMain.add(boxCenTer = Box.createVerticalBox());
        boxCenTer.add(Box.createVerticalStrut(10));
        boxCenTer.setBounds(360, 100, 400, 560);
        boxCenTer.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Danh sách sản phẩm mà khách hàng order"));

        boxCenTer.add(bCenter1 = Box.createHorizontalBox());
        bCenter1.add(lblMaBan = new JLabel("Bàn: "));
        bCenter1.add(cmbMaBan = new JComboBox<String>());
        bCenter1.add(Box.createHorizontalStrut(95));

    
        for (Ban b : banImp.getDsBan()) {
            cmbMaBan.addItem(b.getMaBan());
        }
        
        cmbMaBan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maBans = cmbMaBan.getSelectedItem().toString().trim();
                UpdataHoaDonBan(maBans);
                btnTaoHoaDon.setEnabled(false);
            }
        });
        
        bCenter1.add(lblNgay = new JLabel("Ngày: "));
        dateSettings = new DatePickerSettings();
        dateSettings.setAllowKeyboardEditing(false);
        dateSettings.setFirstDayOfWeek(DayOfWeek.MONDAY);
        txtNgay = new DatePicker(dateSettings);
        txtNgay.setDate(LocalDate.now());
        bCenter1.add(txtNgay);

        boxCenTer.add(Box.createVerticalStrut(10));
        boxCenTer.add(bCenter2 = Box.createHorizontalBox());
        bCenter2.add(lblTenKH = new JLabel("Tên KH: "));
        bCenter2.add(cbmKhachHang = new JComboBox<String>());
        bCenter2.add(Box.createHorizontalStrut(20));
        bCenter2.add(lblSDT = new JLabel("SĐT: "));
        bCenter2.add(txtSDT = new JTextField());

        JLabel lblCreateKH;
        Box boxCreateKH;
        boxCenTer.add(Box.createVerticalStrut(10));
        boxCenTer.add(boxCreateKH = Box.createHorizontalBox());
        boxCreateKH.add(lblCreateKH = new JLabel("Nếu chưa có khách hàng, tạo mới tại đây!"));
        lblCreateKH.setForeground(Color.decode("#ff6666"));
        lblCreateKH.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblCreateKH.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "Open ui KhachHang");

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                // TODO Auto-generated method stub

            }
        });

        lblMaBan.setPreferredSize(lblTenKH.getPreferredSize());

        boxCenTer.add(Box.createVerticalStrut(10));
        boxCenTer.add(bButton = Box.createVerticalBox());
        bButton.add(bBtnChild1 = Box.createHorizontalBox());
        bButton.setBorder(new TitledBorder(new LineBorder(Color.black), "Chỉnh sửa sản phẩm sau khi đã order"));

        bBtnChild1.add(Box.createHorizontalStrut(10));
        bBtnChild1.add(lblTenSPOrd = new JLabel("Tên SP: "));
        bBtnChild1.add(txtTenSPOrd = new JTextField());
        txtTenSPOrd.setEditable(false);
        bBtnChild1.add(Box.createHorizontalStrut(10));
        bBtnChild1.add(btnXoaOrd = new JButton("Xóa"));
        bBtnChild1.add(Box.createHorizontalStrut(5));
        bBtnChild1.add(btnUpdate = new JButton("Update"));
        bBtnChild1.add(Box.createHorizontalStrut(10));

        bButton.add(Box.createVerticalStrut(10));

        bButton.add(bBtnChild2 = Box.createHorizontalBox());
        bBtnChild2.add(Box.createHorizontalStrut(10));
        bBtnChild2.add(lblSoLuongSPOrd = new JLabel("Số lượng: "));
        bBtnChild2.add(txtSoLuongSPOrd = new JTextField());
        bBtnChild2.add(Box.createHorizontalStrut(60));
        bBtnChild2.add(btnThemOrd = new JButton("+"));
        bBtnChild2.add(Box.createHorizontalStrut(5));
        bBtnChild2.add(btnTruOrd = new JButton("-"));
        bBtnChild2.add(Box.createHorizontalStrut(10));
        boxCenTer.add(Box.createVerticalStrut(10));
        
        

        if (txtSoLuongSPOrd.getText().equals("")) {
            txtSoLuongSPOrd.setText("0");
        }

        lblTenSPOrd.setPreferredSize(lblSoLuongSPOrd.getPreferredSize());

        String[] header = { "Tên sản phẩm", "Giá", "Số lượng", "Thành tiền" };
        tableModelOrder = new DefaultTableModel(header, 0);
        tableOrder = new JTable(tableModelOrder);
        JScrollPane scrollPane = new JScrollPane(tableOrder);
        boxCenTer.add(bCenter3 = Box.createHorizontalBox());
        bCenter3.add(scrollPane);

        boxCenTer.add(Box.createVerticalStrut(10));
        boxCenTer.add(bTongTien = Box.createHorizontalBox());
        bTongTien.add(lblTongTien = new JLabel("TỔNG TIỀN: "));
        bTongTien.add(lblTongTienLabel = new JLabel(""));

        boxCenTer.add(Box.createVerticalStrut(10));
        boxCenTer.add(bBtnChild3 = Box.createHorizontalBox());
        bBtnChild3.add(btnTaoHoaDon = new JButton("Tạo hóa đơn"));
        bBtnChild3.add(boxCenTer.createHorizontalStrut(10));
        bBtnChild3.add(btnThanhToan = new JButton("Thanh toán"));
        boxCenTer.add(Box.createVerticalStrut(10));

        UpdataCbmKhachHang();
        UpdateTongTien();
        btnThemOrd.addActionListener(this);
        btnTruOrd.addActionListener(this);
        btnXoaOrd.addActionListener(this);
        tableOrder.addMouseListener(this);
        btnUpdate.addActionListener(this);
        btnTaoHoaDon.addActionListener(this);
        btnThanhToan.addActionListener(this);

        btnThemOrd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTruOrd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnXoaOrd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnUpdate.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnTaoHoaDon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnThanhToan.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmbMaBan.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cbmKhachHang.setCursor(new Cursor(Cursor.HAND_CURSOR));

        String tenKH = cbmKhachHang.getSelectedItem().toString().trim();
        for (KhachHang kh : khachHangImp.getDsKhachHang()) {
            if (kh.getTenKH().equals(tenKH)) {
                txtSDT.setText(kh.getSdt());
            }
        }

        cbmKhachHang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tenKH = cbmKhachHang.getSelectedItem().toString().trim();
                for (KhachHang kh : khachHangImp.getDsKhachHang()) {
                    if (kh.getTenKH().equals(tenKH)) {
                        txtSDT.setText(kh.getSdt());
                    }
                }
            }
        });
        if(maBan == null) {
        }else {
            for (HoaDon h : hoaDonImp.getAllHoaDon()) {
                if (h.getBan().getMaBan().equals(maBan)) {
                    hdDao.updateTrangThaiHoaDon(h.getMaHD());
                    banImp.UpdateTrangThaiBan(h.getBan(), "trong");
                    UpdateTrangThaiBan();
                    tableModelOrder.setRowCount(0);
                }
            }
            JOptionPane.showMessageDialog(this, "Thanh toán thành công");
        }
        
    }

    /**
     * đổ danh sách khách hàng vào combobox khách hàng
     */
    private void UpdataCbmKhachHang() {
        cbmKhachHang.removeAll();
        for (KhachHang kh : khachHangImp.getDsKhachHang()) {
           cbmKhachHang.addItem(kh.getTenKH());
        }
        cbmKhachHang.updateUI();
    }

    public void MainUI() {
        pnMain = new JPanel();
        pnMain.setLayout(null);
        setContentPane(pnMain);
        pnMain.setBackground(Color.decode("#D6D9DF"));
    }

    private void PnLeft() {
        JPanel pnLeft = new JPanel();
        pnLeft.setLayout(null);
        pnLeft.setBounds(0, 0, 350, 700);
        pnLeft.setBackground(Color.decode("#D6D9DF"));

        Box dsBanBox, dsBanNgoaiSan, chuThichBox, dsBan1Box, dsBan2Box, dsBan3Box, dsBan4Box, dsBan5Box, dsBan6Box,
                dsBan7Box,
                dsBan8Box,
                dsBan9Box, dsBan10Box, dsBan11Box, dsBan12Box, dsBan13Box, dsBan14Box, dsBan15Box, dsBan16Box,
                dsBan17Box,
                dsBan18Box, dsBan19Box, dsBan20Box;
        dsBtn = new JButton[17];

        pnLeft.add(dsBanBox = Box.createVerticalBox());
        dsBanBox.setBounds(0, 100, 350, 200);
        dsBanBox.setBorder(new TitledBorder(new LineBorder(Color.black), "Danh sách bàn trong nhà"));
        dsBanBox.add(dsBan1Box = Box.createHorizontalBox());
        for (int i = 1; i <= 4; i++) {
            dsBan1Box.add(dsBtn[i] = new JButton("B0" + i));
            dsBan1Box.add(Box.createHorizontalStrut(5));
        }
        dsBan1Box.add(Box.createHorizontalStrut(5));
        dsBanBox.add(dsBan2Box = Box.createHorizontalBox());
        for (int i = 5; i <= 8; i++) {
            dsBan2Box.add(dsBtn[i] = new JButton("B0" + i));
            dsBan2Box.add(Box.createHorizontalStrut(5));
        }
        dsBanBox.add(dsBan3Box = Box.createHorizontalBox());
        for (int i = 9; i <= 12; i++) {
            if (i < 10) {
                dsBan3Box.add(dsBtn[i] = new JButton("B0" + i));
                dsBan3Box.add(Box.createHorizontalStrut(5));
            } else {
                dsBan3Box.add(dsBtn[i] = new JButton("B" + i));
                dsBan3Box.add(Box.createHorizontalStrut(5));
            }
        }
        dsBanBox.add(dsBan4Box = Box.createHorizontalBox());
        for (int i = 13; i <= 16; i++) {
            dsBan4Box.add(dsBtn[i] = new JButton("B" + i));
            dsBan4Box.add(Box.createHorizontalStrut(5));
        }
        dsBan4Box.add(Box.createHorizontalStrut(5));

        UpdateTrangThaiBan();

        pnLeft.add(dsBanNgoaiSan = Box.createVerticalBox());
        dsBanNgoaiSan.setBorder(new TitledBorder(new LineBorder(Color.black), "Danh sách bàn ngoài sân"));
        dsBanNgoaiSan.setBounds(0, 300, 350, 200);
        dsBanNgoaiSan.add(dsBan17Box = Box.createHorizontalBox());
        dsBan17Box.add(btnBan17 = new JButton("B17"));
        dsBan17Box.add(Box.createHorizontalStrut(5));
        dsBan17Box.add(btnBan18 = new JButton("B18"));
        dsBan17Box.add(Box.createHorizontalStrut(5));
        dsBan17Box.add(btnBan19 = new JButton("B19"));
        dsBan17Box.add(Box.createHorizontalStrut(5));
        dsBan17Box.add(btnBan20 = new JButton("B20"));
        dsBan17Box.add(Box.createHorizontalStrut(5));
        dsBanNgoaiSan.add(dsBan18Box = Box.createHorizontalBox());
        dsBan18Box.add(btnBan21 = new JButton("B21"));
        dsBan18Box.add(Box.createHorizontalStrut(5));
        dsBan18Box.add(btnBan22 = new JButton("B22"));
        dsBan18Box.add(Box.createHorizontalStrut(5));
        dsBan18Box.add(btnBan23 = new JButton("B23"));
        dsBan18Box.add(Box.createHorizontalStrut(5));
        dsBan18Box.add(btnBan24 = new JButton("B24"));
        dsBan18Box.add(Box.createHorizontalStrut(5));
        dsBanNgoaiSan.add(dsBan19Box = Box.createHorizontalBox());
        dsBan19Box.add(btnBan25 = new JButton("B25"));
        dsBan19Box.add(Box.createHorizontalStrut(5));
        dsBan19Box.add(btnBan26 = new JButton("B26"));
        dsBan19Box.add(Box.createHorizontalStrut(5));
        dsBan19Box.add(btnBan27 = new JButton("B27"));
        dsBan19Box.add(Box.createHorizontalStrut(5));
        dsBan19Box.add(btnBan28 = new JButton("B28"));
        dsBan19Box.add(Box.createHorizontalStrut(5));
        dsBanNgoaiSan.add(dsBan20Box = Box.createHorizontalBox());
        dsBan20Box.add(btnBan29 = new JButton("B29"));
        dsBan20Box.add(Box.createHorizontalStrut(5));
        dsBan20Box.add(btnBan30 = new JButton("B30"));
        dsBan20Box.add(Box.createHorizontalStrut(5));
        dsBan20Box.add(btnBan31 = new JButton("B31"));
        dsBan20Box.add(Box.createHorizontalStrut(5));
        dsBan20Box.add(btnBan32 = new JButton("B32"));
        dsBan20Box.add(Box.createHorizontalStrut(5));

        pnLeft.add(chuThichBox = Box.createVerticalBox());
        chuThichBox.setBorder(new TitledBorder(new LineBorder(Color.black), "Chú thích"));
        chuThichBox.setBounds(0, 500, 350, 160);
        chuThichBox.add(Box.createVerticalStrut(15));
        chuThichBox.add(lblChuThichGreen = new JLabel("Bàn đang sử dụng"));
        lblChuThichGreen.setIcon(new ImageIcon(
                new ImageIcon("public/image/lblBanActive.png").getImage().getScaledInstance(30, 30,
                        Image.SCALE_SMOOTH)));
        chuThichBox.add(Box.createVerticalStrut(10));
        chuThichBox.add(lblChuThichYellow = new JLabel("Bàn trống"));
        lblChuThichYellow.setIcon(new ImageIcon(
                new ImageIcon("public/image/lblBanTrong.png").getImage().getScaledInstance(30, 30,
                        Image.SCALE_SMOOTH)));

        JLabel lblGhiChu;
        chuThichBox.add(Box.createVerticalStrut(10));

        chuThichBox.add(lblGhiChu = new JLabel("Thanh toán trước khi nhận bàn"));

        pnMain.add(pnLeft);

        for (int i = 1; i < dsBtn.length; i++) {
            dsBtn[i].addActionListener(this);
        }
    }

    /**
     * cập nhật trạng thái bàn
     */
    private void UpdateTrangThaiBan() {
        int k = 0;
        for (Ban b : banImp.getDsBan()) {
            k++;
            if (b.getTrangThai().trim().equals("active")) {
                dsBtn[k].setBackground(Color.decode("#389238"));
            } else {
                dsBtn[k].setBackground(Color.decode("#F0FF43"));
            }
        }

    }

    public void PnMenu() {
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

        pnMain.add(pnMenuJPanel);
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
    }

    public static void main(String[] args) {
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
        // </editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManHinhChinh(null).setVisible(true);
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        /**
         * Event button menu
         */
        if(o.equals(btnKhachHang)) {
            new QuanLyKhachHang().setVisible(true);
            new ManHinhChinh(null).setVisible(false);
        }else if(o.equals(btnTrangChu)) {
            new ManHinhChinh(null).setVisible(false);
        }else if(o.equals(btnSanPham)) {
           new ui.SanPham().setVisible(true);
        }
        
        /**
         * Event button chức năng
         */
        if (o.equals(btnThemOrd)) {
            String value = txtSoLuongSPOrd.getText();
            if (value.equals("")) {
                value = "0";
                int sl = Integer.parseInt(value);
                sl++;
                txtSoLuongSPOrd.setText(String.valueOf(sl));
            } else {
                int sl = Integer.parseInt(value);
                sl++;
                txtSoLuongSPOrd.setText(String.valueOf(sl));
            }
        } else if (o.equals(btnTruOrd)) {
            String value = txtSoLuongSPOrd.getText();
            if (value.equals("")) {
                value = "0";
                int sl = Integer.parseInt(value);
                sl--;
                txtSoLuongSPOrd.setText(String.valueOf(sl));
            } else if (Integer.parseInt(value) <= 1) {
                JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn bằng 1");
                txtSoLuongSPOrd.setText("1");
            } else {
                int sl = Integer.parseInt(value);
                sl--;
                txtSoLuongSPOrd.setText(String.valueOf(sl));
            }
        } else if (o.equals(btnAdd)) {
            if (txtTenSPPnRight.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Bạn chưa chọn sản phẩm");
            } else {
                UpdataOrder();
                UpdateTongTien();
                JOptionPane.showMessageDialog(this, "Thêm thành công");
                btnTaoHoaDon.setEnabled(true);
            }
        } else if (o.equals(btnXoaOrd)) {
            int row = tableOrder.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm để xóa");
            } else {
                tableModelOrder.removeRow(row);
                JOptionPane.showMessageDialog(this, "Xóa sản phẩm thành công");
                btnTaoHoaDon.setEnabled(true);
                UpdateTongTien();
                XoaRong();
            }
        } else if (o.equals(btnUpdate)) {
            int row = tableOrder.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm để cập nhật");
            } else {
                tableModelOrder.setValueAt(txtSoLuongSPOrd.getText(), row, 2);
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                btnTaoHoaDon.setEnabled(true);
                UpdateTongTien();
                XoaRong();
            }
        } else if (o.equals(btnTaoHoaDon)) {
            if (btnTaoHoaDon.getText().equals("Tạo hóa đơn")) {
                JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công");
                TaoHoaDon();
                UpdataHoaDonBan(cmbMaBan.getSelectedItem().toString());
                UpdateTongTien();
            } else if (btnTaoHoaDon.getText().equals("Cập nhật hóa đơn")) {
                String maHD = "";
                for (Ban b : banImp.getDsBanActive()) {
                    if (b.getMaBan().equals(cmbMaBan.getSelectedItem().toString()))
                        maHD = b.getHoaDon().getMaHD();
                }
                hoaDonImp.resetCTHD(maHD);
                ThemSanPhamVaoHoaDon(maHD);
                btnTaoHoaDon.setEnabled(false);
                JOptionPane.showMessageDialog(this, "Cập nhật hóa đơn thành công");
            }
        } else if (o.equals(btnThanhToan)) {
            String maBan = cmbMaBan.getSelectedItem().toString();
            if(btnTaoHoaDon.isEnabled() == true) {
                JOptionPane.showMessageDialog(this, "Hóa đơn chưa được cập nhật");
            }else {
                OpenUiThanhToan(maBan);
            }
        } else {
            for (int i = 1; i < dsBtn.length; i++) {
                if (o.equals(dsBtn[i])) {
                    UpdataHoaDonBan(dsBtn[i].getText());
                    cmbMaBan.setSelectedItem(dsBtn[i].getText());
                    UpdateTongTien();
                }
            }
        }
    }

    private void OpenUiThanhToan(String maBan) {
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
                new ThanhToan(maBan).setVisible(true);
            }
        });

    }

    /**
     * thêm sản phẩm vào hóa đơn khi đã có hóa đơn
     * 
     * @param maHD
     */
    private void ThemSanPhamVaoHoaDon(String maHD) {
        for (int i = 0; i < tableOrder.getRowCount(); i++) {
            CTHoaDon cthd = new CTHoaDon();
            for (SanPham sp : spimp.getDsSP()) {
                if (sp.getTenSP().equals(tableModelOrder.getValueAt(i, 0).toString().trim())) {
                    for (HoaDon h : hoaDonImp.getHoaDon()) {
                        if (h.getMaHD().equals(maHD))
                            cthd.setHoaDon(h);
                    }
                    cthd.setSanPham(sp);
                    cthd.setSoLuong(Integer.parseInt(tableModelOrder.getValueAt(i, 2).toString()));
                    hoaDonImp.themCTHoaDon(cthd);
                    System.out.println(sp);
                }
            }
        }
    }

    /**
     * đổ dữ liệu hóa đơn vào màn hình tạo hóa đơn
     * 
     * @param maBan
     */
    private void UpdataHoaDonBan(String maBan) {
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);
        tableModelOrder.setRowCount(0);

        if (hoaDonImp.getHoaDonWhereBan(maBan).size() == 0) {
            btnTaoHoaDon.setText("Tạo hóa đơn");
            btnThanhToan.setEnabled(false);
        } else {
            btnThanhToan.setEnabled(true);
            btnTaoHoaDon.setText("Cập nhật hóa đơn");
            for (HoaDon h : hoaDonImp.getHoaDonWhereBan(maBan)) {
                cmbMaBan.setSelectedItem(h.getBan().getMaBan());
                cbmKhachHang.setSelectedItem(h.getKhachHang().getTenKH());
                txtSDT.setText(h.getKhachHang().getSdt());
                txtNgay.setDate(h.getNgayLapHD());

                double thanhTien = h.getDsCTHD().get(0).getSoLuong() * h.getDsCTHD().get(0).getSanPham().getGia();
                String[] row = { h.getDsCTHD().get(0).getSanPham().getTenSP(),
                        en.format(h.getDsCTHD().get(0).getSanPham().getGia()),
                        String.valueOf(h.getDsCTHD().get(0).getSoLuong()), en.format(thanhTien) };
                tableModelOrder.addRow(row);
            }
        }

        tableOrder.setModel(tableModelOrder);
        tableOrder.updateUI();
    }

    /**
     * tạo mới hóa đơn
     */
    private void TaoHoaDon() {
        String maHD = AutoMaHoaDon();
        LocalDate ngayLapHD = txtNgay.getDate();

        HoaDon hoaDon = new HoaDon(maHD, ngayLapHD);

        for (NhanVien nv : nhanVienImp.getAllNhanVien()) {
            if (nv.getTenNV().equals(lblTenUser.getText()))
                hoaDon.setNhanVien(nv);
        }
        for (Ban b : banImp.getDsBan()) {
            if (b.getMaBan().equals(cmbMaBan.getSelectedItem().toString().trim())) {
                hoaDon.setBan(b);
                banImp.UpdateTrangThaiBan(b, "active");
                UpdateTrangThaiBan();
            }
        }
        for (KhachHang kh : khachHangImp.getDsKhachHang()) {
            if (kh.getTenKH().equals(cbmKhachHang.getSelectedItem().toString().trim()))
                hoaDon.setKhachHang(kh);
        }

        hoaDonImp.themHoaDon(hoaDon);

        for (int i = 0; i < tableOrder.getRowCount(); i++) {
            CTHoaDon cthd = new CTHoaDon();
            for (SanPham sp : spimp.getDsSP()) {
                if (sp.getTenSP().equals(tableModelOrder.getValueAt(i, 0).toString().trim())) {
                    cthd.setSanPham(sp);
                    cthd.setHoaDon(hoaDon);
                    cthd.setSoLuong(Integer.parseInt(tableModelOrder.getValueAt(i, 2).toString()));
                    hoaDonImp.themCTHoaDon(cthd);
                    System.out.println(sp);
                }
            }
        }

    }

    /**
     * update tong tien trong tabelorder
     */
    private void UpdateTongTien() {
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);

        int index = tableOrder.getSelectedRow();
        if (index != -1) {
            String[] prices = tableModelOrder.getValueAt(index, 1).toString().trim().split(",");
            String price = prices[0] + prices[1];
            double thanhTien = Double.parseDouble(price)
                    * Integer.parseInt(tableModelOrder.getValueAt(index, 2).toString());
            tableModelOrder.setValueAt(en.format(thanhTien), index, 3);
        }

        int row = tableOrder.getRowCount();
        double tongTien = 0;
        for (int i = 0; i < row; i++) {
            String[] values = tableModelOrder.getValueAt(i, 3).toString().split(",");
            String value = values[0].trim() + values[1].trim();
            tongTien += Double.parseDouble(value);
        }
        System.out.println(tongTien);
        lblTongTienLabel.setText(en.format(tongTien) + " VND");
    }

    /**
     * chuyển sản phẩm từ Ds sản phẩm sang table order khi order sản phẩm
     */
    private void UpdataOrder() {
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);

        int index = tableSP.getSelectedRow();

        String[] parse = tableModelSP.getValueAt(index, 2).toString().trim().split(",");
        String value = parse[0] + parse[1];

        double gia = Double.parseDouble(value);
        int sl = Integer.parseInt(cmbSLOrd.getSelectedItem().toString());

        String[] row = { tableModelSP.getValueAt(index, 1).toString(),
                tableModelSP.getValueAt(index, 2).toString().trim(),
                String.valueOf(cmbSLOrd.getSelectedItem().toString()), en.format(sl * gia) };

        tableModelOrder.addRow(row);
        tableOrder.setModel(tableModelOrder);
        tableOrder.updateUI();

        txtTenSPPnRight.setText("");
        cmbSLOrd.setSelectedIndex(0);
    }

    private void XoaRong() {
        txtTenSPOrd.setText("");
        txtSoLuongSPOrd.setText("");

    }

    /**
     * tạo mã hóa đơn
     * 
     * @return
     */
    private String AutoMaHoaDon() {
        String maBan = cmbMaBan.getSelectedItem().toString().trim();
        String makh = "";
        for (KhachHang kh : khachHangImp.getDsKhachHang()) {
            if (kh.getTenKH().equals(cbmKhachHang.getSelectedItem().toString().trim()))
                makh = kh.getMaKH();
        }

        System.out.println(txtNgay.getDate());
        return new Generator().tuTaoMaHoaDon(maBan, makh, txtNgay.getDate());
    }

    /**
     * event click chuột
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        Object o = e.getSource();
        if (o.equals(tableSP)) {
            int i = tableSP.getSelectedRow();
            txtTenSPPnRight.setText(tableModelSP.getValueAt(i, 1).toString());
        } else if (o.equals(tableOrder)) {
            int row = tableOrder.getSelectedRow();
            txtTenSPOrd.setText(tableModelOrder.getValueAt(row, 0).toString());
            txtSoLuongSPOrd.setText(tableModelOrder.getValueAt(row, 2).toString());
        }

    }

    /**
     * select khách hàng
     */
    private void selectKhachHangBySDT() {
        String sdt = txtSDT.getText();
        System.out.println(sdt);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

}
