/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import models.GiaoVien;
import models.NguoiDung;
import controller.Controller_NguoiDung;

/**
 *
 * @author Admin
 */
public class Controller_GiaoVien extends ConnectDB{
    public boolean addGiaoVien(GiaoVien gv){

        new Controller_NguoiDung().addNguoiDung((NguoiDung) gv);

        String sql2 = "INSERT INTO GiaoVien(maGV, namCT, hinhAnh, maND) "
                + "VALUES(?,?,?,?)";
        try {
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setString(1, gv.getMaGV());
            ps2.setInt(2, gv.getNamCT());
            ps2.setString(3, gv.getHinhAnh());
            ps2.setString(4, gv.getMaND());
            
            return ps2.executeUpdate() >0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return  false;
    }
    
    public boolean updateGiaoVien(GiaoVien gv){
        

        String sql2 = "UPDATE Giaovien SET namCT=?, hinhAnh=? WHERE maGV=?";
        try {
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, gv.getNamCT());
            ps2.setString(2, gv.getHinhAnh());
            ps2.setString(3, gv.getMaGV());
            
            return (ps2.executeUpdate() > 0) && (new Controller_NguoiDung().updateNguoiDung((NguoiDung) gv));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public boolean deleteGiaoVien(GiaoVien gv){
        
        String sql2 = "DELETE Giaovien WHERE maGV=?";
        try {
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setString(1, gv.getMaGV());

            
            return (ps2.executeUpdate() > 0)&&(new Controller_NguoiDung().deleteNguoiDung(gv.getMaND()));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    
    public GiaoVien getGiaoVien(String maGV){
        
        GiaoVien gv = new GiaoVien();
        try{
            
            String sql2 = "SELECT * FROM Nguoidung where maND = ?";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setString(1, maGV);
            ResultSet rs2 = ps2.executeQuery();
            while(rs2.next()){
                gv.setMaND(rs2.getString(1));
                gv.setTenDN(rs2.getString((2)));
                gv.setMatKhau(rs2.getString(3));
                gv.setVaiTro(rs2.getString(4));
                gv.setHoTen(rs2.getString(5));
                gv.setNamSinh(rs2.getInt(6));
                gv.setGioiTinh(rs2.getBoolean(7));
                gv.setCccd(rs2.getString(8));
                gv.setDanToc(rs2.getString(9));
                gv.setDiaChi(rs2.getString(10));
                gv.setSoDT(rs2.getString(11));
                gv.setEmail(rs2.getString(12));

            }
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return gv;
    }
    
    public ArrayList<GiaoVien> getListGiaoVien(){
        ArrayList<GiaoVien> listGV = new ArrayList<>();

        String sql1 = "SELECT * FROM Giaovien";
        try {
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            ResultSet rs1 = ps1.executeQuery();
            
            
            while(rs1.next()){
                    GiaoVien gv = new GiaoVien();
                    gv = getGiaoVien(rs1.getString(1));
                    gv.setMaGV(rs1.getString(1));
                    gv.setNamCT(rs1.getInt(2));
                    gv.setHinhAnh(rs1.getString(3));
                    gv.setMaND(rs1.getString(4));

                    listGV.add(gv);
                }

            } catch(Exception e) {
                e.printStackTrace();
            }
            return listGV;
    }
    public static void main(String[] args) {
        ArrayList<GiaoVien> list = new Controller_GiaoVien().getListGiaoVien();
        System.out.println(list.size());
    }
}
