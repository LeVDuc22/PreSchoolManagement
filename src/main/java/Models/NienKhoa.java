package Models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NienKhoa {
    private String Ma;
    private String NamHoc;
    private Date NgayBatDau;
    private Date NgayKetThuc;


    public String getMa() {
        return Ma;
    }

    public void setMa(String ma) {
        Ma = ma;
    }

    public NienKhoa(String ma, Date ngayBatDau, Date ngayKetThuc, String namHoc) {
        Ma = ma;
        NgayBatDau = ngayBatDau;
        NgayKetThuc = ngayKetThuc;
        NamHoc = namHoc;
    }

    public NienKhoa(String namHoc, Date ngayBatDau, Date ngayKetThuc) {
        NamHoc = namHoc;
        NgayBatDau = ngayBatDau;
        NgayKetThuc = ngayKetThuc;
    }

    public NienKhoa(String ma, String _namHoc, String ngayBatDau, String ngayKetThuc) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        NamHoc = _namHoc;
        Ma = ma;
        NgayBatDau = dateFormat.parse(ngayBatDau);
        NgayKetThuc =  dateFormat.parse(ngayKetThuc);

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

    public String getNamHoc() {
        return NamHoc;
    }

    public void setNamHoc(String namHoc) {
        NamHoc = namHoc;
    }
}
