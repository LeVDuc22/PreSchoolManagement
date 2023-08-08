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
    float duNoHP;
    String danhGia;

    public Chitiethocsinh(){
        this.maLop = "NULL";
        this.maHS = "NULL";
        this.duNoHP = 0;
        this.danhGia = "NULL";
}
    public Chitiethocsinh(String maLop, String maHS, float DuNoHocPhi, String danhGia) {
        this.maLop = maLop;
        this.maHS = maHS;
        this.duNoHP = DuNoHocPhi;
        this.danhGia = danhGia;
    }

    public String getMaLop() {
        return maLop;
    }

    public String getMaHS() {
        return maHS;
    }

    public float getDuNoHP() {
        return duNoHP;
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

    public void setDuNoHP(float duNoHP) {
        this.duNoHP = duNoHP;
    }

    public void setDanhGia(String danhGia) {
        this.danhGia = danhGia;
    }
    
    
}
