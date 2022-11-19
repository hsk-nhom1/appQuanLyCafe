package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import db.DBConnection;
import entity.TaiKhoan;
import service.ITaiKhoanService;
import service.impl.TaiKhoanImpl;
import util.RoundedBorderWithColor;

public class DangNhap extends JFrame implements ActionListener {
    private JPanel pnMain, pnCenter;
    private JLabel lblUserName, lblPassWord;
    private JTextField txtUserName;
    private JPasswordField txtPasssWord;
    private JButton btnLogin;
    private TaiKhoanImpl taiKhoan = new TaiKhoanImpl();

    public DangNhap() {
        try {
            DBConnection.getInstance().connect();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        setTitle("Giao diện đăng nhập");
        setSize(1400, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        MainUI();
        PnlCenter();
    }

    public void MainUI() {
        pnMain = new JPanel();
        pnMain.setLayout(null);

        setContentPane(pnMain);
    }

    public void PnlCenter() {
        pnCenter = new JPanel();
        pnCenter.setLayout(null);
        pnCenter.setBounds(0, 0, 1400, 700);
        JLabel lblBgLoginJLabel = new JLabel();
        lblBgLoginJLabel.setBounds(0, 0, 1400, 700);
        try {
            lblBgLoginJLabel.setIcon(
                    new ImageIcon(ImageIO.read(new File("public/image/imgLogin.png")).getScaledInstance(1400, 700,
                            Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        lblUserName = new JLabel("Username: ");
        lblUserName.setBounds(770, 230, 200, 50);
        lblUserName.setFont(new Font("Bell MT", Font.BOLD, 30));
        lblUserName.setForeground(Color.black);

        lblPassWord = new JLabel("Password:");
        lblPassWord.setBounds(770, 300, 200, 50);
        lblPassWord.setFont(new Font("Bell MT", Font.BOLD, 30));
        lblPassWord.setForeground(Color.black);

        txtUserName = new JTextField();
        txtUserName.setBounds(960, 232, 350, 50);
        txtUserName.setHorizontalAlignment(SwingConstants.CENTER);
        txtUserName.setFont(new Font("Arial", Font.BOLD, 20));
        txtUserName.setBackground(Color.white);
        txtUserName.setBorder(new RoundedBorderWithColor(Color.decode("#FBD8A4"), 1, 50));

        txtPasssWord = new JPasswordField();
        txtPasssWord.setBounds(960, 300, 350, 50);
        txtPasssWord.setHorizontalAlignment(SwingConstants.CENTER);
        txtPasssWord.setFont(new Font("Arial", Font.BOLD, 20));
        txtPasssWord.setBackground(Color.white);
        txtPasssWord.setBorder(new RoundedBorderWithColor(Color.decode("#FBD8A4"), 1, 50));

        btnLogin = new JButton("Login");
        btnLogin.setBounds(1200, 380, 100, 40);
        btnLogin.setForeground(Color.black);
        btnLogin.setFont(new Font("Bell MT", Font.BOLD, 20));
        btnLogin.setBackground(Color.decode("#008037"));
        btnLogin.setBorder(new RoundedBorderWithColor(Color.decode("#FBD8A4"), 1, 40));

        pnMain.add(pnCenter);
        pnCenter.add(btnLogin);
        pnCenter.add(txtPasssWord);
        pnCenter.add(txtUserName);
        pnCenter.add(lblPassWord);
        pnCenter.add(lblUserName);
        pnCenter.add(lblBgLoginJLabel);

        btnLogin.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o.equals(btnLogin)) {
            String username = txtUserName.getText().trim();
            String password = txtPasssWord.getText().trim();
            TaiKhoan tk = new TaiKhoan();

            if (taiKhoan.timTaiKhoan(username) != null) {
                tk = taiKhoan.timTaiKhoan(username);
                JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
                this.dispose();
                new ManHinhChinh(null, tk.getMaNV()).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu sai");
                txtUserName.setText("");
                txtPasssWord.setText("");
            }
        }

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
                new DangNhap().setVisible(true);
            }
        });

    }

}
