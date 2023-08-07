package controller;

import models.ThucDon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.ArrayList;
import models.NienKhoa;

public class ConnectDB_ThucDon {

    private Connection conn;

    public ConnectDB_ThucDon() {
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

    public NienKhoa layThoiGianNienKhoa(String NamHoc) {
        String query = "SELECT * FROM Nienkhoa WHERE namHoc = ?";
        NienKhoa nk = new NienKhoa();
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, NamHoc);
            ResultSet resultSet = statement.executeQuery();

            // NienKhoa nk = new NienKhoa();
            while (resultSet.next()) {
                nk.setMaNienKhoa(resultSet.getString("maNienKhoa"));
                nk.setMaNienKhoa(resultSet.getString("namHoc"));
                nk.setNgayBatDau(resultSet.getDate("ngayBD"));
                nk.setNgayKetThuc(resultSet.getDate("ngayKT"));
            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy Nien Khoa từ CSDL!");
        }
        return nk;
    }

    public void layDanhSachNamHoc(JComboBox<String> cmbNamHoc) {
        String query = "SELECT namHoc FROM Nienkhoa";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            cmbNamHoc.removeAllItems();

            NienKhoa nk = new NienKhoa();
            while (resultSet.next()) {
                nk.setNamHoc(resultSet.getString("NamHoc"));
                cmbNamHoc.addItem(nk.getNamHoc());
            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy danh sách năm học từ cơ sở dữ liệu!");
        }
    }

    public List<ThucDon> layDanhSachThucDonTheoNamHoc(String namHoc) {
        List<ThucDon> thucDonList = new ArrayList<>();

        String query = "SELECT *FROM Thucdon INNER JOIN Nienkhoa ON Thucdon.maNienKhoa = Nienkhoa.maNienKhoa WHERE Nienkhoa.namHoc = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, namHoc);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ThucDon td = new ThucDon();
                td.setMaThucDon(resultSet.getString("maThucDon"));
                td.setTenMonAnSang(resultSet.getString("buaSang"));
                td.setTenMonAnTrua(resultSet.getString("buaTrua"));
                td.setNgay(resultSet.getDate("ngayHoc"));
                td.setMaNienKhoa(resultSet.getString("maNienKhoa"));

                thucDonList.add(td);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi không lấy được danh sách thực đơn!");
        }

        return thucDonList;
    }

    public List<ThucDon> layDanhSachThucDon() {
        List<ThucDon> thucDonList = new ArrayList<>();
        String query = "SELECT * FROM Thucdon";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ThucDon td = new ThucDon();
                td.setMaThucDon(resultSet.getString("maThucDon"));
                td.setTenMonAnSang(resultSet.getString("buaSang"));
                td.setTenMonAnTrua(resultSet.getString("buaTrua"));
                td.setNgay(resultSet.getDate("ngayHoc"));
                td.setMaNienKhoa(resultSet.getString("maNienKhoa"));
                thucDonList.add(td);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi không lấy được danh sách thực đơn !");
        }

        return thucDonList;
    }

    public void themThucDonDB(ThucDon td, String namHoc) {
        String queryNienKhoa = "SELECT maNienKhoa FROM Nienkhoa WHERE namHoc = ?";
        try {
            NienKhoa nk = new NienKhoa();
            PreparedStatement nienKhoaStatement = conn.prepareStatement(queryNienKhoa);
            nienKhoaStatement.setString(1, namHoc);
            ResultSet nienKhoaResultSet = nienKhoaStatement.executeQuery();

            String maNienKhoa = "a";
            if (nienKhoaResultSet.next()) {
                maNienKhoa = nienKhoaResultSet.getString("maNienKhoa");
            }

            nienKhoaResultSet.close();
            nienKhoaStatement.close();

            String query = "INSERT INTO ThucDon (maThucDon, buaSang, buaTrua, ngayHoc,maNienKhoa ) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, td.getMaThucDon());
            statement.setString(2, td.getTenMonAnSang());
            statement.setString(3, td.getTenMonAnTrua());
            statement.setDate(4, new Date(td.getNgay().getTime()));
            statement.setString(5, maNienKhoa);
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Thêm thực đơn thành công!");

            statement.close();
        } catch (SQLException ex) {
           // ex.printStackTrace();
            //JOptionPane.showMessageDialog(null, "Lỗi khi thêm thực đơn!");
   String errorMessage = "Lỗi khi thêm dữ liệu: " + ex.getMessage();
    JOptionPane.showMessageDialog(null, errorMessage, "Lỗi", JOptionPane.ERROR_MESSAGE);
    ex.printStackTrace();
        }
    }

    public void capNhatThucDonDB(ThucDon td) {
        String query = "UPDATE Thucdon SET buaSang = ?, buaTrua = ?, ngayHoc = ?, maNienKhoa = ? WHERE maThucDon = ?";
        try {

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, td.getTenMonAnSang());
            statement.setString(2, td.getTenMonAnTrua());
            statement.setDate(3, new java.sql.Date(td.getNgay().getTime()));
            statement.setString(4, td.getMaNienKhoa());
            statement.setString(5, td.getMaThucDon());
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Cập nhật thông tin thực đơn thành công!");

            statement.close();
        } catch (SQLException ex) {
    String errorMessage = "Lỗi khi cập nhật dữ liệu: " + ex.getMessage();
    JOptionPane.showMessageDialog(null, errorMessage, "Lỗi", JOptionPane.ERROR_MESSAGE);
    ex.printStackTrace();
        }
    }

    public void xoaThucDonDB(String maThucDon) {
        String query = "DELETE FROM Thucdon WHERE maThucDon = ?";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, maThucDon);
            statement.executeUpdate();

            JOptionPane.showMessageDialog(null, "Xóa thực đơn thành công!");

            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa thực đơn!");
        }
    }

}
