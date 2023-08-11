package Models;

public class LopHoc {
    private String Ma;
    private String TenLopHoc;
    private int SiSoLop;
    private float HocPhi;
    private String MaNienKhoa;

    public LopHoc(String ma, String tenLopHoc, int siSoLop, float hocPhi, String maNienKhoa) {
        Ma = ma;
        TenLopHoc = tenLopHoc;
        SiSoLop = siSoLop;
        HocPhi = hocPhi;
        MaNienKhoa = maNienKhoa;
    }

    public LopHoc() {
    }

    public String getMa() {
        return Ma;
    }

    public void setMa(String ma) {
        Ma = ma;
    }

    public String getTenLopHoc() {
        return TenLopHoc;
    }

    public void setTenLopHoc(String tenLopHoc) {
        TenLopHoc = tenLopHoc;
    }

    public int getSiSoLop() {
        return SiSoLop;
    }

    public void setSiSoLop(int siSoLop) {
        SiSoLop = siSoLop;
    }

    public float getHocPhi() {
        return HocPhi;
    }

    public void setHocPhi(float hocPhi) {
        HocPhi = hocPhi;
    }

    public String getMaNienKhoa() {
        return MaNienKhoa;
    }

    public void setMaNienKhoa(String maNienKhoa) {
        MaNienKhoa = maNienKhoa;
    }
}
