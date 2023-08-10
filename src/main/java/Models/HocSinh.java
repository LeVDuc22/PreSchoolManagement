package Models;


import java.util.Date;

public class HocSinh {
    private String Ma;
    private Date NamSinh;
    private boolean GioiTinh;
    private String DanToc;
    private String HinhAnh;
    private Date NgayBatDau;
    private Date NgayKetThuc;
    private String MaNguoiDung;

    public HocSinh(String ma, Date namSinh, boolean gioiTinh, String danToc, String hinhAnh, Date ngayBatDau, Date ngayKetThuc, String maNguoiDung) {
        Ma = ma;
        NamSinh = namSinh;
        GioiTinh = gioiTinh;
        DanToc = danToc;
        HinhAnh = hinhAnh;
        NgayBatDau = ngayBatDau;
        NgayKetThuc = ngayKetThuc;
        MaNguoiDung = maNguoiDung;
    }

    public String getMa() {
        return Ma;
    }

    public void setMa(String ma) {
        Ma = ma;
    }

    public Date getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(Date namSinh) {
        NamSinh = namSinh;
    }

    public boolean isGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getDanToc() {
        return DanToc;
    }

    public void setDanToc(String danToc) {
        DanToc = danToc;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public Date getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        NgayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        NgayKetThuc = ngayKetThuc;
    }

    public String getMaNguoiDung() {
        return MaNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        MaNguoiDung = maNguoiDung;
    }
}
