
package views;
import controller.Controller_QLLop;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Chitiethocsinh;
import models.HocSinh;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class QL_Lop extends javax.swing.JFrame {

 private int selectedRow = -1;
    public QL_Lop() {
        initComponents();
      // setSize(1920, 1080);
        this.setLocationRelativeTo(null);
        
        txtMaLop.setEnabled(false);
        txtMaHocSinh.setEnabled(false);
        txtHoTen.setEnabled(false);
        connectedDanhGiaDB = new Controller_QLLop();
        creatTable();
        connectedDanhGiaDB.layDanhSachNamHoc(ComboBoxNienKhoa);
        String namHoc = ComboBoxNienKhoa.getSelectedItem().toString();
        connectedDanhGiaDB.layDanhSachLopHoc(namHoc, ComboboxLopHoc);
        updateTable();
        
        
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        ComboPanel = new javax.swing.JTabbedPane();
        Panel_DiemDanh = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jTextField13 = new javax.swing.JTextField();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        panel_HachToanHocPhi = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jButton11 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jTextField14 = new javax.swing.JTextField();
        jComboBox8 = new javax.swing.JComboBox<>();
        jButton13 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        panel_DanhGia = new javax.swing.JPanel();
        buttonCreatFile = new javax.swing.JButton();
        maHocSinhLable = new javax.swing.JLabel();
        txtMaHocSinh = new javax.swing.JTextField();
        hoTenlabel = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        danhGialabel = new javax.swing.JLabel();
        ComboboxLopHoc = new javax.swing.JComboBox<>();
        lopHocLabel = new javax.swing.JLabel();
        NienKhoaLable = new javax.swing.JLabel();
        ComboBoxNienKhoa = new javax.swing.JComboBox<>();
        maLopLabel = new javax.swing.JLabel();
        txtMaLop = new javax.swing.JTextField();
        buttonExportFile = new javax.swing.JButton();
        buttonUpdate = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        buttonTimKiem = new javax.swing.JButton();
        txtMSHS = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDanhGia = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        btnDong = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ComboPanel.setToolTipText("");
        ComboPanel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ComboPanel.setPreferredSize(new java.awt.Dimension(40, 40));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã học sinh", "Họ tên", "Vắng"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jButton3.setText("Lưu");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton17.setText("Làm mới");

        jButton18.setText("Tìm kiếm");

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã học sinh", "Họ tên", " " }));

        jLabel2.setText("NGÀY");

        jButton2.setText("Tạo file");

        javax.swing.GroupLayout Panel_DiemDanhLayout = new javax.swing.GroupLayout(Panel_DiemDanh);
        Panel_DiemDanh.setLayout(Panel_DiemDanhLayout);
        Panel_DiemDanhLayout.setHorizontalGroup(
            Panel_DiemDanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_DiemDanhLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(Panel_DiemDanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton18)
                    .addComponent(jButton2))
                .addGroup(Panel_DiemDanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_DiemDanhLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(Panel_DiemDanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Panel_DiemDanhLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                        .addComponent(jButton17)))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 747, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        Panel_DiemDanhLayout.setVerticalGroup(
            Panel_DiemDanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel_DiemDanhLayout.createSequentialGroup()
                .addGroup(Panel_DiemDanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel_DiemDanhLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel2)
                        .addGap(47, 47, 47)
                        .addComponent(jButton18)
                        .addGap(67, 67, 67)
                        .addGroup(Panel_DiemDanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton3)))
                    .addGroup(Panel_DiemDanhLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Panel_DiemDanhLayout.createSequentialGroup()
                        .addGroup(Panel_DiemDanhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(Panel_DiemDanhLayout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(Panel_DiemDanhLayout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47)
                                .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(59, 59, 59)
                        .addComponent(jButton17)))
                .addGap(26, 26, 26))
        );

        ComboPanel.addTab("Điểm danh", Panel_DiemDanh);

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Mã học sinh", "Họ tên", "Đã nộp", "Dư nợ"
            }
        ));
        jScrollPane6.setViewportView(jTable6);
        if (jTable6.getColumnModel().getColumnCount() > 0) {
            jTable6.getColumnModel().getColumn(3).setHeaderValue("Dư nợ");
        }

        jButton11.setText("Cập nhật");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton19.setText("Làm mới");

        jButton20.setText("Tìm kiếm");

        jComboBox8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã học sinh", "Họ tên", " " }));

        jButton13.setText("Tạo file");

        jLabel3.setText("Mã học sinh");

        jLabel4.setText("Họ tên");

        jLabel14.setText("Đã nộp");

        jLabel15.setText("VND");

        javax.swing.GroupLayout panel_HachToanHocPhiLayout = new javax.swing.GroupLayout(panel_HachToanHocPhi);
        panel_HachToanHocPhi.setLayout(panel_HachToanHocPhiLayout);
        panel_HachToanHocPhiLayout.setHorizontalGroup(
            panel_HachToanHocPhiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_HachToanHocPhiLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(panel_HachToanHocPhiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_HachToanHocPhiLayout.createSequentialGroup()
                        .addComponent(jButton20)
                        .addGap(6, 6, 6)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_HachToanHocPhiLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(22, 22, 22)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_HachToanHocPhiLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(49, 49, 49)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_HachToanHocPhiLayout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_HachToanHocPhiLayout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jButton13)
                        .addGap(18, 18, 18)
                        .addComponent(jButton19)
                        .addGap(18, 18, 18)
                        .addComponent(jButton11)))
                .addGap(6, 6, 6)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel_HachToanHocPhiLayout.setVerticalGroup(
            panel_HachToanHocPhiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_HachToanHocPhiLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(panel_HachToanHocPhiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_HachToanHocPhiLayout.createSequentialGroup()
                        .addGroup(panel_HachToanHocPhiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton20)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel_HachToanHocPhiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_HachToanHocPhiLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel3))
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel_HachToanHocPhiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panel_HachToanHocPhiLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel4))
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panel_HachToanHocPhiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel_HachToanHocPhiLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(panel_HachToanHocPhiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15))))
                        .addGap(53, 53, 53)
                        .addGroup(panel_HachToanHocPhiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton13)
                            .addComponent(jButton19)
                            .addComponent(jButton11)))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        ComboPanel.addTab("Hạch toán học phí", panel_HachToanHocPhi);

        panel_DanhGia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel_DanhGiaMouseClicked(evt);
            }
        });

        buttonCreatFile.setText("Tạo file");

        maHocSinhLable.setText("MÃ HỌC SINH");

        txtMaHocSinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHocSinhActionPerformed(evt);
            }
        });

        hoTenlabel.setText("HỌ TÊN");

        danhGialabel.setText("ĐÁNH GIÁ");

        ComboboxLopHoc.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboboxLopHocItemStateChanged(evt);
            }
        });

        lopHocLabel.setText("LỚP HỌC");

        NienKhoaLable.setText("NIÊN KHÓA");

        ComboBoxNienKhoa.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                ComboBoxNienKhoaItemStateChanged(evt);
            }
        });

        maLopLabel.setText("MÃ LỚP");

        buttonExportFile.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/XuatFIle (1).png"))); // NOI18N
        buttonExportFile.setText("Xuất File");
        buttonExportFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExportFileActionPerformed(evt);
            }
        });

        buttonUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Save.png"))); // NOI18N
        buttonUpdate.setText("Cập Nhật");
        buttonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonUpdateActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Bảng đánh giá học sinh");

        buttonTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Search.png"))); // NOI18N
        buttonTimKiem.setText("Tìm Kiếm");
        buttonTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTimKiemActionPerformed(evt);
            }
        });

        txtMSHS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMSHSActionPerformed(evt);
            }
        });

        jLabel11.setText("MSHS");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable1);

        txtDanhGia.setColumns(20);
        txtDanhGia.setRows(5);
        jScrollPane1.setViewportView(txtDanhGia);

        javax.swing.GroupLayout panel_DanhGiaLayout = new javax.swing.GroupLayout(panel_DanhGia);
        panel_DanhGia.setLayout(panel_DanhGiaLayout);
        panel_DanhGiaLayout.setHorizontalGroup(
            panel_DanhGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                .addGroup(panel_DanhGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(maLopLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(lopHocLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(795, 795, 795)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(669, 669, 669)
                        .addComponent(buttonTimKiem))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(NienKhoaLable, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(hoTenlabel))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(maHocSinhLable, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(txtMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(689, 689, 689)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(danhGialabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(ComboboxLopHoc, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(856, 856, 856)
                        .addComponent(txtMSHS, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(314, 314, 314)
                        .addComponent(buttonExportFile, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(490, 490, 490)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(ComboBoxNienKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(txtMaHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(buttonUpdate))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(273, 273, 273)
                        .addComponent(buttonCreatFile)))
                .addGap(108, 108, 108))
        );
        panel_DanhGiaLayout.setVerticalGroup(
            panel_DanhGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                .addGroup(panel_DanhGiaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(maLopLabel))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(lopHocLabel))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel11))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(buttonTimKiem))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(NienKhoaLable))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(hoTenlabel))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(194, 194, 194)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addComponent(maHocSinhLable))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addComponent(txtMaLop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel10))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(270, 270, 270)
                        .addComponent(danhGialabel))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(ComboboxLopHoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(txtMSHS, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(385, 385, 385)
                        .addComponent(buttonExportFile, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(ComboBoxNienKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(246, 246, 246)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addComponent(txtMaHocSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panel_DanhGiaLayout.createSequentialGroup()
                        .addGap(385, 385, 385)
                        .addComponent(buttonUpdate)))
                .addGap(838, 838, 838)
                .addComponent(buttonCreatFile))
        );

        ComboPanel.addTab("Đánh giá", panel_DanhGia);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setText("Quản Lý Lớp");

        btnDong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Close24.png"))); // NOI18N
        btnDong.setText("Đóng");
        btnDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(500, 500, 500)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(346, 346, 346)
                        .addComponent(btnDong))
                    .addComponent(ComboPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btnDong)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(ComboPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnDongActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtMaHocSinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHocSinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHocSinhActionPerformed

    private void ComboboxLopHocItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboboxLopHocItemStateChanged
        updateTable();
        clearText();
    }//GEN-LAST:event_ComboboxLopHocItemStateChanged

    private void ComboBoxNienKhoaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_ComboBoxNienKhoaItemStateChanged
        if (ComboBoxNienKhoa.getSelectedItem() != null) {
            String namHoc = ComboBoxNienKhoa.getSelectedItem().toString();
            connectedDanhGiaDB.layDanhSachLopHoc(namHoc, ComboboxLopHoc);
            updateTable();
        }
    }//GEN-LAST:event_ComboBoxNienKhoaItemStateChanged

    private void buttonExportFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExportFileActionPerformed
        xuatFile();
    }//GEN-LAST:event_buttonExportFileActionPerformed

    private void buttonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUpdateActionPerformed
        updateDanhGia();
    }//GEN-LAST:event_buttonUpdateActionPerformed

    private void buttonTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTimKiemActionPerformed
        timKiemDanhGia();
    }//GEN-LAST:event_buttonTimKiemActionPerformed

    private void txtMSHSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMSHSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMSHSActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int selectedRow = jTable1.getSelectedRow();
        if(selectedRow != -1){
            String maHocSinh = tableModel.getValueAt(selectedRow, 0).toString();
            String tenHocSinh = tableModel.getValueAt(selectedRow, 1).toString();
            String maLop = tableModel.getValueAt(selectedRow, 2).toString();
            String danhGia = " ";
            if(tableModel.getValueAt(selectedRow, 3) != null){
                danhGia = tableModel.getValueAt(selectedRow, 3).toString();
            }

            txtMaHocSinh.setText(maHocSinh);
            txtMaLop.setText(maLop);
            txtHoTen.setText(tenHocSinh);
            txtDanhGia.setText(danhGia);
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void panel_DanhGiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_DanhGiaMouseClicked
        clearText();
    }//GEN-LAST:event_panel_DanhGiaMouseClicked
    private void creatTable(){
         tableModel = (DefaultTableModel) jTable1.getModel();        // viet them
         tableModel.setColumnIdentifiers(new Object[]{               // tao cac thuoc tinh ten cua cot trong bang
         "Mã Học Sinh", "Tên Học Sinh " ,"Mã Lớp", "Đánh Giá ", 
        });
    }
    private void clearText(){
           txtMaHocSinh.setText("");
           txtHoTen.setText("");
           txtMaLop.setText("");
           txtDanhGia.setText("");
           updateTable();
           txtMSHS.setText("");
}
    private void updateTable(){
    List<HocSinh> listHocSinh = new ArrayList<>();
     listChiTiet = new ArrayList<>();
    if (ComboboxLopHoc.getSelectedItem() != null) {
        String tenLophoc = ComboboxLopHoc.getSelectedItem().toString();
        connectedDanhGiaDB.layDachSachDanhGia(tenLophoc, listHocSinh , listChiTiet);
        tableModel.setRowCount(0); // Xóa dữ liệu trong bảng hiện tại
        for (HocSinh hs : listHocSinh){
            for(Chitiethocsinh ct :listChiTiet )
            {
                if(hs.getMaHS().equals(ct.getMaHS())){
                    Object[] rowData = {
                        hs.getMaHS(),
                        hs.getHoTen(),
                        ct.getMaLop(),
                        ct.getDanhGia(),
                    };
                    tableModel.addRow(rowData);
                } 
            }
        }
    }
}
//    private void updateTable() {
//    Map<String, HocSinh> mapHocSinh = new HashMap<>();
//    Map<String, Chitiethocsinh> mapChiTiet = new HashMap<>();
//
//    if (ComboboxLopHoc.getSelectedItem() != null) {
//        String tenLophoc = ComboboxLopHoc.getSelectedItem().toString();
//        connectedDanhGiaDB.layDachSachDanhGia(tenLophoc, mapHocSinh, mapChiTiet);
//        tableModel.setRowCount(0); // Xóa dữ liệu trong bảng hiện tại
//
//        for (HocSinh hs : mapHocSinh.values()) {
//            Chitiethocsinh ct = mapChiTiet.get(hs.getMaHS());
//            if (ct != null) {
//                Object[] rowData = {
//                    hs.getMaHS(),
//                    hs.getHoTen(),
//                    ct.getMaLop(),
//                    ct.getDanhGia(),
//                };
//                tableModel.addRow(rowData);
//            }
//        }
//    }
//}
private void updateDanhGia(){
    Chitiethocsinh ct = new Chitiethocsinh();
    ct.setMaHS(txtMaHocSinh.getText());
    ct.setMaLop(txtMaLop.getText());
    ct.setMaLop(txtMaLop.getText());
    ct.setDanhGia(txtDanhGia.getText());
    
    connectedDanhGiaDB.updateDanhGiaDB(ct);
    updateTable();
    clearText();
}
private void timKiemDanhGia() {
    String MSHS = txtMSHS.getText().toString();
    List<HocSinh> listHocSinh = new ArrayList<>();
    listChiTiet = new ArrayList<>();
    if (ComboboxLopHoc.getSelectedItem() != null) {
        String tenLophoc = ComboboxLopHoc.getSelectedItem().toString();
        connectedDanhGiaDB.layDachSachDanhGia(tenLophoc, listHocSinh , listChiTiet);
        tableModel.setRowCount(0); // Xóa dữ liệu trong bảng hiện tại
        for (HocSinh hs : listHocSinh){
            for(Chitiethocsinh ct :listChiTiet )
            {
                if(hs.getMaHS().equals(ct.getMaHS()) && hs.getMaHS().equals(MSHS) ){
                    Object[] rowData = {
                        hs.getMaHS(),
                        hs.getHoTen(),
                        ct.getMaLop(),
                        ct.getDanhGia(),
                    };
                    tableModel.addRow(rowData);
                } 
            }
        }
    }
}
   private void xuatFile() {
        if (tableModel.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Không có dữ liệu để xuất Excel!");
            return;
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Danh sách đánh giá");

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < tableModel.getColumnCount(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(tableModel.getColumnName(i));
        }

        for (int row = 0; row < tableModel.getRowCount(); row++) {
            Row excelRow = sheet.createRow(row + 1);
            for (int column = 0; column < tableModel.getColumnCount(); column++) {
                Cell cell = excelRow.createCell(column);
                cell.setCellValue(tableModel.getValueAt(row, column).toString());
            }
        }

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        LocalDateTime now = LocalDateTime.now();
        String namHoc = ComboBoxNienKhoa.getSelectedItem().toString();
        String tenLophoc = ComboboxLopHoc.getSelectedItem().toString();
        String fileName = namHoc +" " + tenLophoc + " " + "DanhSachDanhGiaHocSinh_" + dtf.format(now) + ".xlsx";

        try (FileOutputStream outputStream = new FileOutputStream(fileName)) {
            workbook.write(outputStream);
            JOptionPane.showMessageDialog(null, "Xuất file Excel thành công!\nTên file: " + fileName);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi xuất file Excel!");
        } finally {
            try {
                workbook.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QL_Lop().setVisible(true);
            }
        });
    }
private  DefaultTableModel tableModel;
private Controller_QLLop connectedDanhGiaDB;
private List<Chitiethocsinh> listChiTiet ;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxNienKhoa;
    private javax.swing.JTabbedPane ComboPanel;
    private javax.swing.JComboBox<String> ComboboxLopHoc;
    private javax.swing.JLabel NienKhoaLable;
    private javax.swing.JPanel Panel_DiemDanh;
    private javax.swing.JButton btnDong;
    private javax.swing.JButton buttonCreatFile;
    private javax.swing.JButton buttonExportFile;
    private javax.swing.JButton buttonTimKiem;
    private javax.swing.JButton buttonUpdate;
    private javax.swing.JLabel danhGialabel;
    private javax.swing.JLabel hoTenlabel;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel lopHocLabel;
    private javax.swing.JLabel maHocSinhLable;
    private javax.swing.JLabel maLopLabel;
    private javax.swing.JPanel panel_DanhGia;
    private javax.swing.JPanel panel_HachToanHocPhi;
    private javax.swing.JTextArea txtDanhGia;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMSHS;
    private javax.swing.JTextField txtMaHocSinh;
    private javax.swing.JTextField txtMaLop;
    // End of variables declaration//GEN-END:variables


}
