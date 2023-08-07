/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import models.Chitiethocsinh;
import models.HocSinh;
import models.LopHoc;
import models.NienKhoa;

/**
 *
 * @author Duc
 */
public class DanhGiaController {
    private Connection conn;

    public DanhGiaController() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String url = "jdbc:sqlserver://localhost:1433; databaseName=He_Thong_Truong_Mam_Non;encrypt=true;trustServerCertificate=true";
            String username = "sa";
            String password = "sa";

            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Database connected");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
        public void layDanhSachNamHoc(JComboBox<String> cmbNamHoc) {
        String query = "SELECT namHoc FROM Nienkhoa";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            cmbNamHoc.removeAllItems();

            NienKhoa nk = new NienKhoa();
            while (resultSet.next()) {
                nk.setNamHoc(resultSet.getString("namHoc"));
                cmbNamHoc.addItem(nk.getNamHoc());
                
            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy danh sách năm học từ cơ sở dữ liệu!");
        }
    }
        
           public void layDanhSachLopHoc(String namHoc, JComboBox<String> cmbLopHoc) {
        String query = "SELECT Lophoc.tenLop FROM Lophoc " +
                    "INNER JOIN Nienkhoa ON Lophoc.maNienKhoa = Nienkhoa.maNienKhoa " +
                    "WHERE Nienkhoa.namHoc = ?";

        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, namHoc);
            ResultSet resultSet = statement.executeQuery();
             
            cmbLopHoc.removeAllItems();

            LopHoc ld = new LopHoc();
            while (resultSet.next()) {
                ld.setTenLop(resultSet.getString("tenLop"));
                cmbLopHoc.addItem(ld.getTenLop());
            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy danh sách lớp học từ cơ sở dữ liệu!");
        }
     }
//           public void layDachSachDanhGia(String tenLop, Map<String, HocSinh> mapHocSinh, Map<String, Chitiethocsinh> mapChiTiet) {
    public void layDachSachDanhGia(String tenLop, List<HocSinh> listHocSinh , List<Chitiethocsinh> listChiTiet){
        String query = "SELECT hs.maHS, hs.hoTen, lh.maLop, cth.danhGia " +
                    "FROM Hocsinh hs " +
                    "INNER JOIN Chitiethocsinh cth ON hs.maHS = cth.maHS " +
                    "INNER JOIN Lophoc lh ON cth.maLop = lh.maLop " +
                    "WHERE lh.tenLop = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, tenLop);
            ResultSet resultSet = statement.executeQuery();
             
            while (resultSet.next()) {
              HocSinh hs = new HocSinh();
             Chitiethocsinh ct = new Chitiethocsinh();
                 hs.setMaHS(resultSet.getString("maHS"));
                 ct.setMaHS(resultSet.getString("maHS"));
                 hs.setHoTen(resultSet.getString("hoTen"));
                 ct.setMaLop(resultSet.getString("maLop"));
                 ct.setDanhGia(resultSet.getString("danhGia"));
                listHocSinh.add(hs);
                listChiTiet.add(ct);
            }
//while (resultSet.next()) {
//        HocSinh hs = new HocSinh();
//        Chitiethocsinh ct = new Chitiethocsinh();
//        hs.setMaHS(resultSet.getString("maHS"));
//        ct.setMaHS(resultSet.getString("maHS"));
//        hs.setHoTen(resultSet.getString("hoTen"));
//        ct.setMaLop(resultSet.getString("maLop"));
//        ct.setDanhGia(resultSet.getString("danhGia"));
//        mapHocSinh.put(hs.getMaHS(), hs); // Lưu vào Map
//        mapChiTiet.put(ct.getMaHS(), ct); // Lưu vào Map
//    }
            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy danh sách đánh giá từ cơ sở dữ liệu!");
        }
    }

    public void updateDanhGiaDB(Chitiethocsinh hs) {
        String sqlUpdate = "UPDATE Chitiethocsinh SET danhGia = ? WHERE maHS = ? AND maLop = ?";
        try{
        PreparedStatement statement = conn.prepareStatement(sqlUpdate);
        
            statement.setString(1, hs.getDanhGia());
            statement.setString(2, hs.getMaHS());
            statement.setString(3, hs.getMaLop());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cập nhật thông tin đánh giá thành công!");

            statement.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật thông tin thực đơn!");
        }
      }
}
