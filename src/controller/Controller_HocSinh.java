/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import models.HocSinh;

import java.sql.PreparedStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Controller_HocSinh extends ConnectDB{
    public boolean addHocSinh(HocSinh s){

        String sql = "INSERT INTO Hocsinh(maHS, hoTen, ngaySinh, gioiTinh, danToc, hinhAnh, maND) "
                + "VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, s.getMaHS());
            ps.setString(2, s.getHoTen());
            ps.setDate(3, new Date(s.getNgaySinh().getTime()));
            ps.setBoolean(4, s.isGioiTinh());
            ps.setString(5, s.getDanToc());
            ps.setString(6, s.getHinhAnh());
            ps.setString(7, s.getMaPH());
            

            
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public boolean updateHocSinh(HocSinh hs){
        
        
        String sql = "UPDATE Hocsinh SET hoTen=?, ngaySinh=?, gioiTinh=?, danToc=?, hinhAnh=?"
                + " WHERE maHS=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, hs.getHoTen());
            ps.setDate(2, new Date(hs.getNgaySinh().getTime()));
            ps.setBoolean(3, hs.isGioiTinh());
            ps.setString(4, hs.getDanToc());
            ps.setString(5, hs.getHinhAnh());
            ps.setString(6, hs.getMaHS());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean deleteHocSinh(HocSinh hs){
        
        
        String sql = "DELETE Hocsinh WHERE maHS=?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, hs.getMaHS());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public ArrayList<HocSinh> getListHocSinh(){
        ArrayList<HocSinh> list = new ArrayList<>();
        String sql = "SELECT * FROM Hocsinh";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                HocSinh s = new HocSinh();
                s.setMaHS(rs.getString("maHS"));
                s.setHoTen(rs.getString("hoTen"));
                s.setNgaySinh(rs.getDate("ngaySinh"));
                s.setGioiTinh(rs.getBoolean("gioiTinh"));
                s.setDanToc(rs.getString("danToc"));
                s.setHinhAnh(rs.getString("hinhAnh"));
                s.setMaPH(rs.getString("maND"));

                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return list;
    }

    public static void main(String[] args) {
        new Controller_HocSinh();
    }

}
