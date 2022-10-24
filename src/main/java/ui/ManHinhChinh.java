package ui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;

import dao.SanPhamDao;
import db.DBConnection;
import entity.SanPham;
import service.impl.SanPhamImp;
import util.RoundedBorderWithColor;

public class ManHinhChinh extends JFrame implements ActionListener, MouseListener {
    private JPanel pnMain;
    private JPanel pnMenuJPanel;
    private JButton btnTrangChu, btnSanPham, btnQLBan, btnNhanVien, btnHoaDon, btnLogOut;
    private JLabel lblIconUser, lblIconLogOut, lblTenUser;
    private JButton btnBan1, btnBan2, btnBan3, btnBan4, btnBan5, btnBan6, btnBan7, btnBan8, btnBan9, btnBan10, btnBan11,
            btnBan12, btnBan13, btnBan14, btnBan15, btnBan16, btnBan17, btnBan18, btnBan19, btnBan20, btnBan21,
            btnBan22, btnBan23, btnBan24, btnBan25, btnBan26, btnBan27, btnBan28, btnBan29, btnBan30, btnBan31,
            btnBan32, btnThemOrd, btnXoaOrd, btnTruOrd, btnUpdate, btnThanhToan, btnAdd;

    private JLabel lblChuThichGreen, lblChuThichYellow, lblMaBan, lblNgay, lblTenKH, lblSDT, lblTenSPOrd,
            lblSlSPPnRight,
            lblSoLuongSPOrd, lblTongTien, lblTongTienLabel, lblTenSPPnRight;
    private JComboBox<String> cmbMaBan, cmbSLOrd;
    private JTextField txtTenKH, txtSDT, txtSoLuongSPOrd, txtTenSPOrd, txtTenSPPnRight;
    private JTable tableOrder, tableSP;
    private DefaultTableModel tableModelOrder, tableModelSP;
    private DatePickerSettings dateSettings;
    private DatePicker txtNgay;
    private SanPhamDao spdao = new SanPhamDao();
    private SanPhamImp imp = new SanPhamImp();

    public ManHinhChinh() {
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
        PnCenter();
        PnRight();

    }

    private void PnRight() {
        Box bRight, bTable;
        pnMain.add(bRight = Box.createVerticalBox());
        bRight.add(Box.createVerticalStrut(10));
        bRight.setBounds(770, 100, 260, 560);
        bRight.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Danh sách sản phẩm"));

        String[] headStrings = { "Tên sản phẩm", "Giá" };
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
        tableSP.addMouseListener(this);
        btnAdd.addActionListener(this);
    }

    private void UpdataTableSanPham() {
        Locale localeEN = new Locale("en", "EN");
        NumberFormat en = NumberFormat.getInstance(localeEN);

        tableModelSP.setRowCount(0);
        for (SanPham sp : imp.getDsSP()) {
            String[] row = { sp.getTenSP(), String.valueOf(en.format(sp.getGia())) };
            tableModelSP.addRow(row);
        }
        tableSP.setModel(tableModelSP);
        tableSP.updateUI();
    }

    private void PnCenter() {
        Box boxCenTer, bCenter1, bCenter2, bCenter3, bButton, bBtnChild1, bBtnChild2, bTongTien;
        pnMain.add(boxCenTer = Box.createVerticalBox());
        boxCenTer.add(Box.createVerticalStrut(10));
        boxCenTer.setBounds(360, 100, 400, 560);
        boxCenTer.setBorder(new TitledBorder(new LineBorder(Color.BLACK), "Hóa đơn"));

        boxCenTer.add(bCenter1 = Box.createHorizontalBox());
        bCenter1.add(lblMaBan = new JLabel("Bàn: "));
        bCenter1.add(cmbMaBan = new JComboBox<String>());
        bCenter1.add(Box.createHorizontalStrut(95));

        for (int i = 1; i < 33; i++) {
            if (i <= 9)
                cmbMaBan.addItem("B0" + i);
            else
                cmbMaBan.addItem("B" + i);
        }

        bCenter1.add(lblNgay = new JLabel("Ngày: "));
        dateSettings = new DatePickerSettings();
        dateSettings.setAllowKeyboardEditing(false);
        dateSettings.setFirstDayOfWeek(DayOfWeek.MONDAY);
        txtNgay = new DatePicker(dateSettings);
        bCenter1.add(txtNgay);

        boxCenTer.add(Box.createVerticalStrut(10));
        boxCenTer.add(bCenter2 = Box.createHorizontalBox());
        bCenter2.add(lblTenKH = new JLabel("Tên KH: "));
        bCenter2.add(txtTenKH = new JTextField());
        bCenter2.add(lblSDT = new JLabel("SĐT: "));
        bCenter2.add(txtSDT = new JTextField());

        boxCenTer.add(Box.createVerticalStrut(10));
        boxCenTer.add(bButton = Box.createVerticalBox());
        bButton.add(bBtnChild1 = Box.createHorizontalBox());
        bButton.setBorder(new TitledBorder(new LineBorder(Color.black), "Chỉnh sửa sản phẩm trong hóa đơn"));

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

        lblTenSPOrd.setPreferredSize(lblSoLuongSPOrd.getPreferredSize());

        String[] header = { "Tên sản phẩm", "Số lượng", "Thành tiền" };
        tableModelOrder = new DefaultTableModel(header, 0);
        tableOrder = new JTable(tableModelOrder);
        JScrollPane scrollPane = new JScrollPane(tableOrder);
        boxCenTer.add(bCenter3 = Box.createHorizontalBox());
        bCenter3.add(scrollPane);

        boxCenTer.add(bTongTien = Box.createHorizontalBox());
        bTongTien.add(Box.createVerticalStrut(80));
        bTongTien.add(boxCenTer.createHorizontalStrut(50));
        bTongTien.add(lblTongTien = new JLabel("TỔNG TIỀN: "));
        bTongTien.add(lblTongTienLabel = new JLabel("100000"));
        bTongTien.add(boxCenTer.createHorizontalStrut(50));
        bTongTien.add(btnThanhToan = new JButton("Tạo hóa đơn"));
        bTongTien.add(boxCenTer.createHorizontalStrut(50));

    }

    public void MainUI() {
        pnMain = new JPanel();
        pnMain.setLayout(null);
        setContentPane(pnMain);
    }

    private void PnLeft() {
        JPanel pnLeft = new JPanel();
        pnLeft.setLayout(null);
        pnLeft.setBounds(0, 0, 350, 700);

        Box dsBanBox, dsBanNgoaiSan, chuThichBox, dsBan1Box, dsBan2Box, dsBan3Box, dsBan4Box, dsBan5Box, dsBan6Box,
                dsBan7Box,
                dsBan8Box,
                dsBan9Box, dsBan10Box, dsBan11Box, dsBan12Box, dsBan13Box, dsBan14Box, dsBan15Box, dsBan16Box,
                dsBan17Box,
                dsBan18Box, dsBan19Box, dsBan20Box;

        pnLeft.add(dsBanBox = Box.createVerticalBox());
        dsBanBox.setBounds(0, 100, 350, 200);
        dsBanBox.setBorder(new TitledBorder(new LineBorder(Color.black), "Danh sách bàn trong nhà"));
        dsBanBox.add(dsBan1Box = Box.createHorizontalBox());
        dsBan1Box.add(btnBan1 = new JButton("B01"));
        dsBan1Box.add(Box.createHorizontalStrut(5));
        dsBan1Box.add(btnBan2 = new JButton("B02"));
        dsBan1Box.add(Box.createHorizontalStrut(5));
        dsBan1Box.add(btnBan3 = new JButton("B03"));
        dsBan1Box.add(Box.createHorizontalStrut(5));
        dsBan1Box.add(btnBan4 = new JButton("B04"));
        dsBan1Box.add(Box.createHorizontalStrut(5));
        dsBanBox.add(dsBan2Box = Box.createHorizontalBox());
        dsBan2Box.add(btnBan5 = new JButton("B05"));
        dsBan2Box.add(Box.createHorizontalStrut(5));
        dsBan2Box.add(btnBan6 = new JButton("B06"));
        dsBan2Box.add(Box.createHorizontalStrut(5));
        dsBan2Box.add(btnBan7 = new JButton("B07"));
        dsBan2Box.add(Box.createHorizontalStrut(5));
        dsBan2Box.add(btnBan8 = new JButton("B08"));
        dsBan2Box.add(Box.createHorizontalStrut(5));
        dsBanBox.add(dsBan3Box = Box.createHorizontalBox());
        dsBan3Box.add(btnBan9 = new JButton("B09"));
        dsBan3Box.add(Box.createHorizontalStrut(5));
        dsBan3Box.add(btnBan10 = new JButton("B10"));
        dsBan3Box.add(Box.createHorizontalStrut(5));
        dsBan3Box.add(btnBan11 = new JButton("B11"));
        dsBan3Box.add(Box.createHorizontalStrut(5));
        dsBan3Box.add(btnBan12 = new JButton("B12"));
        dsBan3Box.add(Box.createHorizontalStrut(5));
        dsBanBox.add(dsBan4Box = Box.createHorizontalBox());
        dsBan4Box.add(btnBan13 = new JButton("B13"));
        dsBan4Box.add(Box.createHorizontalStrut(5));
        dsBan4Box.add(btnBan14 = new JButton("B14"));
        dsBan4Box.add(Box.createHorizontalStrut(5));
        dsBan4Box.add(btnBan15 = new JButton("B15"));
        dsBan4Box.add(Box.createHorizontalStrut(5));
        dsBan4Box.add(btnBan16 = new JButton("B16"));
        dsBan4Box.add(Box.createHorizontalStrut(5));

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

        btnBan1.addMouseListener(this);
        btnBan21.addMouseListener(this);
        btnBan2.addMouseListener(this);
        btnBan22.addMouseListener(this);
        btnBan3.addMouseListener(this);
        btnBan23.addMouseListener(this);
        btnBan4.addMouseListener(this);
        btnBan24.addMouseListener(this);
        btnBan5.addMouseListener(this);
        btnBan25.addMouseListener(this);
        btnBan6.addMouseListener(this);
        btnBan26.addMouseListener(this);
        btnBan7.addMouseListener(this);
        btnBan27.addMouseListener(this);
        btnBan8.addMouseListener(this);
        btnBan28.addMouseListener(this);
        btnBan9.addMouseListener(this);
        btnBan29.addMouseListener(this);
        btnBan10.addMouseListener(this);
        btnBan30.addMouseListener(this);
        btnBan11.addMouseListener(this);
        btnBan31.addMouseListener(this);
        btnBan12.addMouseListener(this);
        btnBan32.addMouseListener(this);
        btnBan13.addMouseListener(this);
        btnBan17.addMouseListener(this);
        btnBan18.addMouseListener(this);
        btnBan16.addMouseListener(this);
        btnBan19.addMouseListener(this);
        btnBan20.addMouseListener(this);
        btnBan14.addMouseListener(this);
        btnBan15.addMouseListener(this);

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
                new ImageIcon("public/icon/product.png").getImage().getScaledInstance(30, 30,
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

        btnLogOut = new JButton("Đăng xuất");
        btnLogOut.setBackground(Color.decode("#1D81CE"));
        btnLogOut.setBounds(890, 50, 100, 30);
        btnLogOut.setForeground(Color.WHITE);

        pnMain.add(pnMenuJPanel);
        pnMenuJPanel.add(btnTrangChu);
        pnMenuJPanel.add(btnSanPham);
        pnMenuJPanel.add(btnQLBan);
        pnMenuJPanel.add(btnNhanVien);
        pnMenuJPanel.add(btnHoaDon);
        pnMenuJPanel.add(lblIconUser);
        pnMenuJPanel.add(lblTenUser);
        pnMenuJPanel.add(lblIconLogOut);
        pnMenuJPanel.add(btnLogOut);
    }

    public static void main(String[] args) {
        new ManHinhChinh().setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int i = tableSP.getSelectedRow();
        txtTenSPPnRight.setText(tableModelSP.getValueAt(i, 0).toString());
    }

    /**
     * event click chuột
     */
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
