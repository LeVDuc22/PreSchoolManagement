/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

/**
 *
 * @author My laptop
 */
public class ChangePasswordModel {
    public int MaNguoiDung;
    public String MatKhau;
    public String MatKhauMoi;

    public ChangePasswordModel(int MaNguoiDung, String MatKhau, String MatKhauMoi) {
        this.MaNguoiDung = MaNguoiDung;
        this.MatKhau = MatKhau;
        this.MatKhauMoi = MatKhauMoi;
    }

    public int getMaNguoiDung() {
        return MaNguoiDung;
    }

    public void setMaNguoiDung(int MaNguoiDung) {
        this.MaNguoiDung = MaNguoiDung;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    public String getMatKhauMoi() {
        return MatKhauMoi;
    }

    public void setMatKhauMoi(String MatKhauMoi) {
        this.MatKhauMoi = MatKhauMoi;
    }
    
}
