package Models;

public class GiaoVien {
    private String Ma;
    private String HinhAnh;
    private float NamCongTac;
    private String MaNguoiDung;

    public GiaoVien(String ma, String hinhAnh, float namCongTac, String maNguoiDung) {
        Ma = ma;
        HinhAnh = hinhAnh;
        NamCongTac = namCongTac;
        MaNguoiDung = maNguoiDung;
    }

    public String getMa() {
        return Ma;
    }

    public void setMa(String ma) {
        Ma = ma;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public float getNamCongTac() {
        return NamCongTac;
    }

    public void setNamCongTac(float namCongTac) {
        NamCongTac = namCongTac;
    }

    public String getMaNguoiDung() {
        return MaNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        MaNguoiDung = maNguoiDung;
    }
}
