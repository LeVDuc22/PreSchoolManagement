/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import models.NguoiDung;

import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import models.HocSinh;

public class Controller_NguoiDung extends ConnectDB{
    
    //Tạo Hàm trả về list người dùng là Phụ huynh
    public ArrayList<NguoiDung> getListNguoiDung() {
        ArrayList<NguoiDung> listPH = new ArrayList<>();
        String sql = "SELECT *FROM Nguoidung";
        
        try{
            //Lấy toàn bộ dữ liệu trong Bảng người dùng
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            //Nếu người dùng với vai trò là phụ huynh thì thêm vào list
            while(rs.next()){
                NguoiDung nd = new NguoiDung();
                if(rs.getString(4).equals("phuhuynh")){
                    nd.setMaND(rs.getString(1));
                    nd.setTenDN(rs.getString((2)));
                    nd.setMatKhau(rs.getString(3));
                    nd.setVaiTro(rs.getString(4));
                    nd.setHoTen(rs.getString(5));
                    nd.setNamSinh(rs.getInt(6));
                    nd.setGioiTinh(rs.getBoolean(7));
                    nd.setCccd(rs.getString(8));
                    nd.setDanToc(rs.getString(9));
                    nd.setDiaChi(rs.getString(10));
                    nd.setSoDT(rs.getString(11));
                    nd.setEmail(rs.getString(12));
                } else {
                    continue;
                }
                
                listPH.add(nd);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return listPH;
    }
    
    public boolean addNguoiDung(NguoiDung ph){

        String sql = "INSERT INTO Nguoidung(maND, tenDN, matKhau, vaiTro, hoTen, namSinh, gioiTinh,"
                + "cccd, danToc, diaChi, soDT, email) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ph.getMaND());
            ps.setString(2, ph.getTenDN());
            ps.setString(3, ph.getMatKhau());
            ps.setString(4, ph.getVaiTro());
            ps.setString(5, ph.getHoTen());
            ps.setInt(6, ph.getNamSinh());
            ps.setBoolean(7, ph.isGioiTinh());
            ps.setString(8, ph.getCccd());
            ps.setString(9, ph.getDanToc());
            ps.setString(10, ph.getDiaChi());
            ps.setString(11, ph.getSoDT());
            ps.setString(12, ph.getEmail());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
      
        return false;
    }
    
    public boolean updateNguoiDung(NguoiDung ph){
        
        
        String sql = "UPDATE Nguoidung SET hoTen=?, namSinh=?, gioiTinh=?, cccd=?, danToc=?,"
                + "diaChi=?, SoDT=?, email=? WHERE maND=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, ph.getHoTen());
            ps.setInt(2, ph.getNamSinh());
            ps.setBoolean(3, ph.isGioiTinh());
            ps.setString(4, ph.getCccd());
            ps.setString(5, ph.getDanToc());
            ps.setString(6, ph.getDiaChi());
            ps.setString(7, ph.getSoDT());
            ps.setString(8, ph.getEmail());
            ps.setString(9, ph.getMaND());
                    

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public boolean deleteNguoiDung(String maPH){
        
        
        String sql = "DELETE Nguoidung WHERE maND=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maPH);
            
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    //Lấy ra người dùng
    public NguoiDung getNguoiDung(String maND){
        
        NguoiDung nd = new NguoiDung();
        try{
            
            String sql2 = "SELECT * FROM Nguoidung where maND = ?";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setString(1, maND);
            ResultSet rs2 = ps2.executeQuery();
            while(rs2.next()){
                nd.setMaND(rs2.getString(1));
                nd.setTenDN(rs2.getString((2)));
                nd.setMatKhau(rs2.getString(3));
                nd.setVaiTro(rs2.getString(4));
                nd.setHoTen(rs2.getString(5));
                nd.setNamSinh(rs2.getInt(6));
                nd.setGioiTinh(rs2.getBoolean(7));
                nd.setCccd(rs2.getString(8));
                nd.setDanToc(rs2.getString(9));
                nd.setDiaChi(rs2.getString(10));
                nd.setSoDT(rs2.getString(11));
                nd.setEmail(rs2.getString(12));

            }
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return nd;
    }
    
    public boolean doiMatKhau(NguoiDung nd){
        String sql = "UPDATE Nguoidung SET matKhau=? WHERE maND=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, nd.getMatKhau());
            ps.setString(2, nd.getMaND());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void main(String[] args) {
        new Controller_NguoiDung();
    }

}
