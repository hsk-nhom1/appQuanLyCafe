package entity;

public class SanPham {
	private String maSP;
	private String tenSP;
	private double gia;
	private String congThuc;
	private String loai;
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public String getTenSP() {
		return tenSP;
	}
	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}
	public double getGia() {
		return gia;
	}
	public void setGia(double gia) {
		this.gia = gia;
	}
	public String getCongThuc() {
		return congThuc;
	}
	public void setCongThuc(String congThuc) {
		this.congThuc = congThuc;
	}
	public String getLoai() {
		return loai;
	}
	public void setLoai(String loai) {
		this.loai = loai;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maSP == null) ? 0 : maSP.hashCode());
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
		SanPham other = (SanPham) obj;
		if (maSP == null) {
			if (other.maSP != null)
				return false;
		} else if (!maSP.equals(other.maSP))
			return false;
		return true;
	}
	public SanPham(String maSP, String tenSP, double gia, String congThuc, String loai) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.gia = gia;
		this.congThuc = congThuc;
		this.loai = loai;
	}
	public SanPham() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SanPham(String maSP) {
		super();
		this.maSP = maSP;
	}
	@Override
	public String toString() {
		return "SanPham [maSP=" + maSP + ", tenSP=" + tenSP + ", gia=" + gia + ", congThuc=" + congThuc + ", loai="
				+ loai + "]";
	}
	
	
	
}
