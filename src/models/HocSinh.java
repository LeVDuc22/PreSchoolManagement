package models;

import java.io.Serializable;
import java.util.Date;

public class HocSinh implements Serializable {
    private String maHS;
    private String hoTen;
    private Date ngaySinh;
    private boolean gioiTinh;
    private String danToc;
    private String hinhAnh;
    private String maPH;
    public HocSinh(){
        this.maHS = "NULL";
        this.hoTen = "NULL";
        this.ngaySinh = new Date();
        this.gioiTinh = true;
        this.danToc = "NULL";
        this.hinhAnh = "NULL";
        this.maPH = "NULL";
    }
    public HocSinh(String maHS, String hoTen, Date ngaySinh, boolean gioiTinh, String danToc, String hinhAnh, String maPH) {
        this.maHS = maHS;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.danToc = danToc;
        this.hinhAnh = hinhAnh;
        this.maPH = maPH;
    }
    public String getMaHS() {
        return maHS;
    }

    public void setMaHS(String ID) {
        this.maHS = ID;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDanToc() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMaPH() {
        return maPH;
    }

    public void setMaPH(String maPH) {
        this.maPH = maPH;
    }
}
