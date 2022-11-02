package entity;

public class TaiKhoan {
    /**
     * attributes
     */
    private String userName;
    private String password;
    private String maNV;

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

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public TaiKhoan() {
        super();
        // TODO Auto-generated constructor stub
    }

    public TaiKhoan(String userName, String password, String maNV) {
        super();
        this.userName = userName;
        this.password = password;
        this.maNV = maNV;
    }

    @Override
    public String toString() {
        return "TaiKhoan [userName=" + userName + ", password=" + password + ", maNV=" + maNV + "]";
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
        TaiKhoan other = (TaiKhoan) obj;
        if (maNV == null) {
            if (other.maNV != null)
                return false;
        } else if (!maNV.equals(other.maNV))
            return false;
        return true;
    }

}
