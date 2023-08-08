
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class StudentAttendanceTable {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        
        tableModel.addColumn("Mã HS");
        tableModel.addColumn("Mã Lớp");
        tableModel.addColumn("Tên Học Sinh");
        tableModel.addColumn("Trạng Thái Đi Học");
        
        try {
            // Kết nối cơ sở dữ liệu
            String url = "jdbc:sqlserver://localhost:1433;databaseName=He_Thong_Truong_Mam_Non;";
            String user = "sa";
            String password = "sa";
            Connection connection = DriverManager.getConnection(url, user, password);

            // Thực hiện truy vấn để lấy thông tin học sinh và trạng thái đi học
            String query = "SELECT hs.maHS, hs.maLop, hs.hoTen, dd.trangThai " +
                    "FROM Hocsinh hs " +
                    "INNER JOIN Diemdanh dd ON hs.maHS = dd.maHS " +
                    "WHERE dd.ngayHoc = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, new Date(System.currentTimeMillis())); // Lấy thông tin điểm danh cho ngày hôm nay
            ResultSet resultSet = statement.executeQuery();

            // Đổ dữ liệu vào bảng
            while (resultSet.next()) {
                Vector<String> row = new Vector<>();
                row.add(resultSet.getString("maHS"));
                row.add(resultSet.getString("maLop"));
                row.add(resultSet.getString("hoTen"));
                boolean trangThai = resultSet.getBoolean("trangThai");
                row.add(trangThai ? "Đã đi học" : "Vắng");
                tableModel.addRow(row);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy dữ liệu từ cơ sở dữ liệu!");
        }

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane);

        frame.add(panel);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
