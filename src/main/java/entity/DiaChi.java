package entity;

public class DiaChi {
	private String maDiaChi;
	private String xa;
	private String huyen;
	private String tinh;
	public String getMaDiaChi() {
		return maDiaChi;
	}
	public void setMaDiaChi(String maDiaChi) {
		this.maDiaChi = maDiaChi;
	}
	public String getXa() {
		return xa;
	}
	public void setXa(String xa) {
		this.xa = xa;
	}
	public String getHuyen() {
		return huyen;
	}
	public void setHuyen(String huyen) {
		this.huyen = huyen;
	}
	public String getTinh() {
		return tinh;
	}
	public void setTinh(String tinh) {
		this.tinh = tinh;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maDiaChi == null) ? 0 : maDiaChi.hashCode());
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
		DiaChi other = (DiaChi) obj;
		if (maDiaChi == null) {
			if (other.maDiaChi != null)
				return false;
		} else if (!maDiaChi.equals(other.maDiaChi))
			return false;
		return true;
	}
	public DiaChi(String maDiaChi, String xa, String huyen, String tinh) {
		super();
		this.maDiaChi = maDiaChi;
		this.xa = xa;
		this.huyen = huyen;
		this.tinh = tinh;
	}
	public DiaChi() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DiaChi(String maDiaChi) {
		super();
		this.maDiaChi = maDiaChi;
	}
	@Override
	public String toString() {
		return "DiaChi [maDiaChi=" + maDiaChi + ", xa=" + xa + ", huyen=" + huyen + ", tinh=" + tinh + "]";
	}
	
}	
