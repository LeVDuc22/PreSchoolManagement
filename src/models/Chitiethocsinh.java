/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Duc
 */
public class Chitiethocsinh {
    String maLop;
    String maHS;
    float DuNoHocPhi;
    String danhGia;

    public Chitiethocsinh(){
        this.maLop = "NULL";
        this.maHS = "NULL";
        this.DuNoHocPhi = 0;
        this.danhGia = "NULL";
}
    public Chitiethocsinh(String maLop, String maHS, float DuNoHocPhi, String danhGia) {
        this.maLop = maLop;
        this.maHS = maHS;
        this.DuNoHocPhi = DuNoHocPhi;
        this.danhGia = danhGia;
    }

    public String getMaLop() {
        return maLop;
    }

    public String getMaHS() {
        return maHS;
    }

    public float getDuNoHocPhi() {
        return DuNoHocPhi;
    }

    public String getDanhGia() {
        return danhGia;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public void setMaHS(String maHS) {
        this.maHS = maHS;
    }

    public void setDuNoHocPhi(float DuNoHocPhi) {
        this.DuNoHocPhi = DuNoHocPhi;
    }

    public void setDanhGia(String danhGia) {
        this.danhGia = danhGia;
    }
    
    
}
