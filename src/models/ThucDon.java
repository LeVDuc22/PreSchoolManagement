package models;



/**
 *
 * @author onlyb
 */
import java.util.Date;

public class ThucDon {
    private String maThucDon ;
    private String tenMonAnSang;
    private String tenMonAnTrua;
    private Date ngay;
    private String maNienKhoa;

    public ThucDon(){
        this.maThucDon = "NULL";
        this.tenMonAnSang = "NULL";
      this.tenMonAnTrua = "NULL";
       this.ngay = new Date();
       this.maNienKhoa = "NULL";
    }
    public ThucDon(String maThucDon, String tenMonAnSang, String tenMonAnTrua, Date ngay, String maNienKhoa) {
        this.maThucDon = maThucDon;
        this.tenMonAnSang = tenMonAnSang;
        this.tenMonAnTrua = tenMonAnTrua;
        this.ngay = ngay;
        this.maNienKhoa = maNienKhoa;
    }
    public String getMaThucDon() {
        return maThucDon;
    }

    public void setMaThucDon(String maThucDon) {
        this.maThucDon = maThucDon;
    }

    public String getTenMonAnSang() {
        return tenMonAnSang;
    }

    public void setTenMonAnSang(String tenMonAnSang) {
        this.tenMonAnSang = tenMonAnSang;
    }

    public String getTenMonAnTrua() {
        return tenMonAnTrua;
    }

    public void setTenMonAnTrua(String tenMonAnTrua) {
        this.tenMonAnTrua = tenMonAnTrua;
    }

    public String getMaNienKhoa() {
        return maNienKhoa;
    }

    public void setMaNienKhoa(String maNienKhoa) {
        this.maNienKhoa = maNienKhoa;
    }

    public Date getNgay() {
        return ngay;
    }

    public void setNgay(Date ngay) {
        this.ngay = ngay;
    }
}
