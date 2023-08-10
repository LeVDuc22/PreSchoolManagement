package Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NienKhoa {
    private String Ma;
    private String NamHoc;
    private String NgayBatDau;
    private String NgayKetThuc;

    public NienKhoa() {
    }

    public NienKhoa(String ma, String namHoc, String ngayBatDau, String ngayKetThuc) {
        Ma = ma;
        NamHoc = namHoc;
        NgayBatDau = ngayBatDau;
        NgayKetThuc = ngayKetThuc;
    }

    public NienKhoa(String namHoc, String ngayBatDau, String ngayKetThuc) {
        NamHoc = namHoc;
        NgayBatDau = ngayBatDau;
        NgayKetThuc = ngayKetThuc;
    }

    public String getMa() {
        return Ma;
    }

    public void setMa(String ma) {
        Ma = ma;
    }

    public String getNamHoc() {
        return NamHoc;
    }

    public void setNamHoc(String namHoc) {
        NamHoc = namHoc;
    }

    public String getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        NgayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        NgayKetThuc = ngayKetThuc;
    }
}
