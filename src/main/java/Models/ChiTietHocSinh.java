package Models;

public class ChiTietHocSinh {
    private String MaHocSinh;
    private String MaLopHoc;
    private float DuNoHocPhi;
    private String DanhGia;

    public ChiTietHocSinh(String maHocSinh, String maLopHoc) {
        MaHocSinh = maHocSinh;
        MaLopHoc = maLopHoc;
    }

    public ChiTietHocSinh(String maHocSinh, String maLopHoc, float duNoHocPhi, String danhGia) {
        MaHocSinh = maHocSinh;
        MaLopHoc = maLopHoc;
        DuNoHocPhi = duNoHocPhi;
        DanhGia = danhGia;
    }

    public String getMaHocSinh() {
        return MaHocSinh;
    }

    public void setMaHocSinh(String maHocSinh) {
        MaHocSinh = maHocSinh;
    }

    public String getMaLopHoc() {
        return MaLopHoc;
    }

    public void setMaLopHoc(String maLopHoc) {
        MaLopHoc = maLopHoc;
    }

    public float getDuNoHocPhi() {
        return DuNoHocPhi;
    }

    public void setDuNoHocPhi(float duNoHocPhi) {
        DuNoHocPhi = duNoHocPhi;
    }

    public String getDanhGia() {
        return DanhGia;
    }

    public void setDanhGia(String danhGia) {
        DanhGia = danhGia;
    }
}
