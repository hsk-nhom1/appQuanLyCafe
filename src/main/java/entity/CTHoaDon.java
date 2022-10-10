package entity;

public class CTHoaDon {
	private SanPham sanPham;
	private HoaDon hoaDon;
	private int soLuong;
	private String ghiChu;
	public SanPham getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}
	public HoaDon getHoaDon() {
		return hoaDon;
	}
	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((hoaDon == null) ? 0 : hoaDon.hashCode());
		result = prime * result + ((sanPham == null) ? 0 : sanPham.hashCode());
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
		CTHoaDon other = (CTHoaDon) obj;
		if (hoaDon == null) {
			if (other.hoaDon != null)
				return false;
		} else if (!hoaDon.equals(other.hoaDon))
			return false;
		if (sanPham == null) {
			if (other.sanPham != null)
				return false;
		} else if (!sanPham.equals(other.sanPham))
			return false;
		return true;
	}
	public CTHoaDon(SanPham sanPham, HoaDon hoaDon, int soLuong, String ghiChu) {
		super();
		this.sanPham = sanPham;
		this.hoaDon = hoaDon;
		this.soLuong = soLuong;
		this.ghiChu = ghiChu;
	}
	public CTHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CTHoaDon(SanPham sanPham, HoaDon hoaDon) {
		super();
		this.sanPham = sanPham;
		this.hoaDon = hoaDon;
	}
	@Override
	public String toString() {
		return "CTHoaDon [sanPham=" + sanPham + ", hoaDon=" + hoaDon + ", soLuong=" + soLuong + ", ghiChu=" + ghiChu
				+ "]";
	}
	
	
	
}
