package entity;

public class Ban {
	private String maBan;
	private String tenBan;
	private String trangThai;
	public String getMaBan() {
		return maBan;
	}
	public void setMaBan(String maBan) {
		this.maBan = maBan;
	}
	public String getTenBan() {
		return tenBan;
	}
	public void setTenBan(String tenBan) {
		this.tenBan = tenBan;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((maBan == null) ? 0 : maBan.hashCode());
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
		Ban other = (Ban) obj;
		if (maBan == null) {
			if (other.maBan != null)
				return false;
		} else if (!maBan.equals(other.maBan))
			return false;
		return true;
	}
	public Ban(String maBan, String tenBan, String trangThai) {
		super();
		this.maBan = maBan;
		this.tenBan = tenBan;
		this.trangThai = trangThai;
	}
	public Ban() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ban(String maBan) {
		super();
		this.maBan = maBan;
	}
	@Override
	public String toString() {
		return "Ban [maBan=" + maBan + ", tenBan=" + tenBan + ", trangThai=" + trangThai + "]";
	}
	
	
	
	
}
