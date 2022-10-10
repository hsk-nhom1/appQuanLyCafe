package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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

import util.RoundedBorderWithColor;

public class DangNhap extends JFrame implements ActionListener {
    private JPanel pnMain, pnCenter;
    private JLabel lblUserName, lblPassWord;
    private JTextField txtUserName;
    private JPasswordField txtPasssWord;
    private JButton btnLogin;

    public DangNhap() {
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
			lblBgLoginJLabel.setIcon(new ImageIcon(ImageIO.read(new File("public/image/imgLogin.png")).getScaledInstance(1400, 700,
			        Image.SCALE_SMOOTH)));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                       
        lblUserName = new JLabel("Username: ");
        lblUserName.setBounds(770, 230, 200, 50);
        lblUserName.setFont(new Font("Bell MT",Font.BOLD,30));
        lblUserName.setForeground(Color.black);
        
        lblPassWord= new JLabel("Password:");
        lblPassWord.setBounds(770, 300, 200, 50);
        lblPassWord.setFont(new Font("Bell MT",Font.BOLD,30));
        lblPassWord.setForeground(Color.black);
        
        txtUserName= new JTextField();
        txtUserName.setBounds(960, 232, 350, 50);
        txtUserName.setHorizontalAlignment(SwingConstants.CENTER);
        txtUserName.setFont(new Font("Arial",Font.BOLD,20));
        txtUserName.setBackground(Color.white);
        txtUserName.setBorder(new RoundedBorderWithColor(Color.decode("#FBD8A4"), 1, 50));
        
        txtPasssWord= new JPasswordField();
        txtPasssWord.setBounds(960, 300, 350, 50);
        txtPasssWord.setHorizontalAlignment(SwingConstants.CENTER);
        txtPasssWord.setFont(new Font("Arial",Font.BOLD,20));
        txtPasssWord.setBackground(Color.white);
        txtPasssWord.setBorder(new RoundedBorderWithColor(Color.decode("#FBD8A4"), 1, 50));
        
        btnLogin = new JButton("Login");
        btnLogin.setBounds(1200, 380, 100, 40);
        btnLogin.setForeground(Color.black);
        btnLogin.setFont(new Font("Bell MT",Font.BOLD,20));
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
        Object o =e.getSource();
        if(o.equals(btnLogin)){
            JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
        }

    }

    public static void main(String[] args) {
        new DangNhap().setVisible(true);
    }

}
