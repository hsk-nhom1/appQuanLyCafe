package entity;

public class NhanVien {
	private String maNV;
	private String tenNV;
	private String sdt;
	private String email;
	private String gioiTinh;
	private String cmnd;
	private String caTruc;
	private TaiKhoan taiKhoan;
	private DiaChi diaChi;
	private double luong;
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getTenNV() {
		return tenNV;
	}
	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getCmnd() {
		return cmnd;
	}
	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}
	public String getCaTruc() {
		return caTruc;
	}
	public void setCaTruc(String caTruc) {
		this.caTruc = caTruc;
	}
	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}
	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}
	public DiaChi getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(DiaChi diaChi) {
		this.diaChi = diaChi;
	}
	public double getLuong() {
		return luong;
	}
	public void setLuong(double luong) {
		this.luong = luong;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maNV == null) ? 0 : maNV.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		if (maNV == null) {
			if (other.maNV != null)
				return false;
		} else if (!maNV.equals(other.maNV))
			return false;
		return true;
	}
	public NhanVien(String maNV, String tenNV, String sdt, String email, String gioiTinh, String cmnd, String caTruc,
			TaiKhoan taiKhoan, DiaChi diaChi, double luong) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.sdt = sdt;
		this.email = email;
		this.gioiTinh = gioiTinh;
		this.cmnd = cmnd;
		this.caTruc = caTruc;
		this.taiKhoan = taiKhoan;
		this.diaChi = diaChi;
		this.luong = luong;
	}
	public NhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}
	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", sdt=" + sdt + ", email=" + email + ", gioiTinh="
				+ gioiTinh + ", cmnd=" + cmnd + ", caTruc=" + caTruc + ", taiKhoan=" + taiKhoan + ", diaChi=" + diaChi
				+ ", luong=" + luong + "]";
	}
	
	
}
