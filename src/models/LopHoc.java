/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Duc
 */
public class LopHoc {
    private String maLop;
    private String tenLop;
    private int siSo;
    private float hocPhi;
    private String maNienKhoa;

    
    public LopHoc(){
        this.maLop = "NULL";
        this.tenLop = "NULL";
        this.siSo = 0;
        this.hocPhi = 0;
        this.maNienKhoa = "NULL";
    }
    public LopHoc(String maLop, String tenLop, int siSo, float hocPhi, String maNienKhoa) {
        this.maLop = maLop;
        this.tenLop = tenLop;
        this.siSo = siSo;
        this.hocPhi = hocPhi;
        this.maNienKhoa = maNienKhoa;
    }

    
    public String getMaLop() {
        return maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public int getSiSo() {
        return siSo;
    }

    public float getHocPhi() {
        return hocPhi;
    }

    public String getMaNienKhoa() {
        return maNienKhoa;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public void setSiSo(int siSo) {
        this.siSo = siSo;
    }

    public void setHocPhi(float hocPhi) {
        this.hocPhi = hocPhi;
    }

    public void setMaNienKhoa(String maNienKhoa) {
        this.maNienKhoa = maNienKhoa;
    }
}
