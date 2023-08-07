package models;

import java.util.Date;

public class NienKhoa {
    private String maNienKhoa;
    private String namHoc;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    public NienKhoa(){
        this.maNienKhoa = "NULL";
        this.namHoc = "NULL";
        this.ngayBatDau = new Date();
        this.ngayKetThuc = new Date();
    }
    public NienKhoa(String maNienKhoa, String namHoc, Date ngayBatDau, Date ngayKetThuc) {
        this.maNienKhoa = maNienKhoa;
        this.namHoc = namHoc;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getMaNienKhoa() {
        return maNienKhoa;
    }

    public void setMaNienKhoa(String maNienKhoa) {
        this.maNienKhoa = maNienKhoa;
    }

    public String getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(String namHoc) {
        this.namHoc = namHoc;
    }

    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

}