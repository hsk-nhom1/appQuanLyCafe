package entity;

public class TaiKhoan {
	private String userName;
	private String password;
	private NhanVien nhanVien;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public TaiKhoan(String userName, String password, NhanVien nhanVien) {
		super();
		this.userName = userName;
		this.password = password;
		this.nhanVien = nhanVien;
	}
	public TaiKhoan(String userName) {
		super();
		this.userName = userName;
	}
	public TaiKhoan() {
		super();
	}
	@Override
	public String toString() {
		return "TaiKhoan [userName=" + userName + ", password=" + password + ", nhanVien=" + nhanVien + "]";
	}
	
	
	
}
