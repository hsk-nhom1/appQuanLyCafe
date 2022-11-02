package entity;

public class CTHoaDon {
    private int soLuong;

	private SanPham sanPham;
	private HoaDon hoaDon;
	
	public HoaDon getHoaDon() {
        return hoaDon;
    }
    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }
    public SanPham getSanPham() {
		return sanPham;
	}
	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public CTHoaDon() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public CTHoaDon(int soLuong, SanPham sanPham) {
        super();
        this.soLuong = soLuong;
        this.sanPham = sanPham;
    }
   
	public double tinhTien() {
        return soLuong* sanPham.getGia();	    
	}
    @Override
    public String toString() {
        return "CTHoaDon [soLuong=" + soLuong + ", sanPham=" + sanPham + ",]";
    }
   
	
}
