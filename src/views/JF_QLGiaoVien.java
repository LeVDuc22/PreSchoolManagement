/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import controller.Controller_GiaoVien;
import controller.Validation;
import java.awt.Color;
import java.awt.Image;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.awt.image.BufferedImage;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import models.BienToanCuc;
import models.GiaoVien;
import models.NguoiDung;
/**
 *
 * @author Admin
 */
public class JF_QLGiaoVien extends javax.swing.JFrame {

    //Tạo listHS để chứa toàn bộ HS từ CSDL để update Bảng trong Phần mềm
    private ArrayList<GiaoVien> listGV;
//    private ArrayList<NguoiDung> listND;
    //Khai báo đối tượng để sử dụng bảng
    DefaultTableModel model;
    //Biến đếm STT HS
    private int demGV;
    
    public JF_QLGiaoVien() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        txt_MaGV.setEnabled(false);
        txt_TenDN.setEnabled(false);
        //Tải Data HS từ CSDL vào listHS
        listGV = new Controller_GiaoVien().getListGiaoVien();
        //Sử dụng bảng để hiển thị
        model = (DefaultTableModel) tbl_GiaoVien.getModel();
        //Lấy dữ luêụ từ bảng khi click dòng trong bảng
        LayDuLieuTuBang();
        lamMoi();
    }
    
    //Tạo tự tăng cho mã HS
    public void taoMaGV(){
        int id = 1;
        boolean state;
        while(true){
            state = false;
            for(GiaoVien gv: listGV){
                if(gv.getMaGV().equals("gv"+id)){
                    state = true;
                    break;
                }
            }
            if(!state) break;
            ++id;
        }
        txt_MaGV.setText("gv"+ id);
        txt_TenDN.setText("gv"+ id);
    }
    
    //Hàm lưu bản copy
    public void luuHinhAnh(GiaoVien gv){
        try{
            File inputFile = new File(lbl_HinhAnh.getText()); //lbl_HinhAnh chứa path
            //Ta lưu bản copy hình ảnh với tên file = mã hs + ".png"
            BufferedImage image = ImageIO.read(inputFile);
            if(!new File("src\\Teacher_Img").exists()) {
                new File("src\\Teacher_Img").mkdir();
            }
            File outFile = new File("src\\Teacher_Img" + "\\" + gv.getMaGV() + ".png");
            ImageIO.write(image, "png", outFile);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    //Tạo hàm hiển thị hình ảnh cho label
    public void setImageLabel(String path){
        try {
            File file = new File(path);
                        
            BufferedImage imageFile = ImageIO.read(file);
//          Image img = ImageIO.read(imageFile);
            int width = lbl_HinhAnh.getWidth();
            int height = lbl_HinhAnh.getHeight();
            lbl_HinhAnh.setIcon(new ImageIcon(imageFile.getScaledInstance(width, height, 0)));
            } catch (IOException ex) {
                System.out.println("Error: " + ex.toString());
            }
    }
    
    public void lamMoi(){
        btn_Them.setEnabled(false);// ẩn hiện
        btn_CapNhat.setEnabled(false);// ẩn hiện
        btn_Xoa.setEnabled(false);// ẩn hiện
        lbl_ValidCCCD.setText("(*)");
        txt_CCCD.setBackground(Color.white);
        lbl_ValidDanToc.setText("(*)");
        txt_DanToc.setBackground(Color.white);
        lbl_ValidDiaChi.setText("(*)");
        txt_DiaChi.setBackground(Color.white);
        lbl_ValidEmail.setText("(*)");
        txt_Email.setBackground(Color.white);
        lbl_ValidHoTen.setText("(*)");
        txt_HoTen.setBackground(Color.white);
        lbl_ValidNamCT.setText("(*)");
        txt_NamCT.setBackground(Color.white);
        lbl_ValidNamSinh.setText("(*)");
        txt_NamSinh.setBackground(Color.white);
        lbl_ValidSDT.setText("(*)");
        txt_SDT.setBackground(Color.white);
        
        btn_Them.setEnabled(false);
        btn_CapNhat.setEnabled(false);
        
        txt_MaGV.setText("");
        txt_HoTen.setText("");
        txt_NamSinh.setText("");
        btn_Nam.setSelected(true);
        txt_DanToc.setText("");
        txt_NamCT.setText("");
        txt_DiaChi.setText("");
        txt_TenDN.setText("");
        txt_CCCD.setText("");
        txt_SDT.setText("");
        txt_Email.setText("");
        lbl_HinhAnh.setText("");
        txt_TimKiem.setText("");
        cbb_TimKiem.setSelectedIndex(0);
        setImageLabel("src\\images\\user.128.png");

        BienToanCuc.resetBTC();
        //hiển thị lại mã HS tự tăng
        //Mã HS tự tăng
        
        //Tải dữ liệu mới nhất HS từ CSDL
        listGV = new Controller_GiaoVien().getListGiaoVien();
        model.setRowCount(0);
        showTable(listGV);
        taoMaGV();
    }
    //Hàm lấy thông tin giáo viên từ biểu mẫu
    public GiaoVien layGiaoVien(){
        GiaoVien gv = new GiaoVien();
        
        // Lấy dữ liệu từ biểu mẫu gán cho đối tượng HS
        gv.setMaGV(txt_MaGV.getText());
        gv.setMaND(txt_MaGV.getText());
        gv.setVaiTro("giaovien");
        gv.setHoTen(txt_HoTen.getText());
        gv.setDanToc(txt_DanToc.getText());
        gv.setNamCT(Integer.parseInt(txt_NamCT.getText()));
        gv.setNamSinh(Integer.parseInt(txt_NamSinh.getText()));
        gv.setDiaChi(txt_DiaChi.getText());
        
        //Mỗi lần lấy có thể có giá trị khác nhau nhưng ở controller không cập nhật MK và tên ĐN
        gv.setTenDN(txt_MaGV.getText());
        gv.setMatKhau(txt_MaGV.getText()); 
        
        gv.setCccd(txt_CCCD.getText());
        gv.setSoDT(txt_SDT.getText());
        gv.setEmail(txt_Email.getText());

        //Giới tính
        if(btn_Nam.isSelected()){
           gv.setGioiTinh(true);
        } else if(btn_Nu.isSelected()){
            gv.setGioiTinh(false);
        }
        
        //Kiểm tra xem label HÌnh ảnh có ảnh chưa, OK mới setHinhAnh() cho HS
        try{
            File inputFile1 = new File(lbl_HinhAnh.getText()); //lbl_HinhAnh chứa path
            //Ta lưu bản copy hình ảnh với tên file = mã hs + ".png"
            BufferedImage image = ImageIO.read(inputFile1);
            gv.setHinhAnh(gv.getMaGV()+".png");
        } catch(Exception e){}

        return gv;
    }
    //Hiển thị 1 list GV lê bảng
    public void showTable(ArrayList<GiaoVien> list) {
        demGV = 1;
        for(GiaoVien gv: list) {
            showResult(gv);
        }
    }
    
    //Hiển thị 1 GV lên bảng
    public void showResult(GiaoVien gv){
        //Thêm 1 dòng gồm các thuộc tính sau vào bảng
        String gioiTinh = "";
        if(gv.isGioiTinh()){
            gioiTinh = "Nam";
        } else {
            gioiTinh = "Nữ";
        }

        
        model.addRow(new Object[]{
            demGV++, gv.getMaGV(), gv.getHoTen(), gv.getNamSinh(), gioiTinh, gv.getNamCT()
        });
    }
    
    public void LayDuLieuTuBang() {    
        
        tbl_GiaoVien.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if(tbl_GiaoVien.getSelectedRow()>= 0) {
                    txt_MaGV.setText(tbl_GiaoVien.getValueAt(tbl_GiaoVien.getSelectedRow(),1) + "");
                    
                    
//                    GiaoVien gv = new GiaoVien();
        
                    for(GiaoVien gv: listGV){
                        if(gv.getMaGV().equals(txt_MaGV.getText())){
//                            gv = s;
                            
                            
                            txt_HoTen.setText(gv.getHoTen());
                            txt_NamSinh.setText(gv.getNamSinh()+"");

                            String gioiTinh = "";
                            if(gv.isGioiTinh()){
                                btn_Nam.setSelected(true);
                            } else {
                                btn_Nu.setSelected(true);
                            }
                            txt_CCCD.setText(gioiTinh);
                            txt_DanToc.setText(gv.getDanToc());
                            txt_NamCT.setText(gv.getNamCT()+"");
                            txt_DiaChi.setText(gv.getDiaChi());
                            txt_TenDN.setText(gv.getMaGV());
                            txt_CCCD.setText(gv.getCccd());
                            txt_SDT.setText(gv.getSoDT());
                            txt_Email.setText(gv.getEmail());
                            //Ban đầu hiển thị ảnh biểu tượng rõng để xóa ảnh HS đã chọn lần trước
                            lbl_HinhAnh.setText("src\\Teacher_Img\\" + gv.getMaGV()+".png");
                            setImageLabel("src\\images\\user.128.png");
                            setImageLabel("src\\Teacher_Img\\" + gv.getMaGV()+".png");
                            
                            break;
                        }
                    }

//                    lamMoi();
                    btn_CapNhat.setEnabled(true);//ẩn hiện
                    btn_Xoa.setEnabled(true);//ẩn hiện
                    lbl_ValidCCCD.setText(null);
                    lbl_ValidDanToc.setText(null);
                    lbl_ValidDiaChi.setText(null);
                    lbl_ValidEmail.setText(null);
                    lbl_ValidHoTen.setText(null);
                    lbl_ValidNamCT.setText(null);
                    lbl_ValidNamSinh.setText(null);
                    lbl_ValidSDT.setText(null);
                }
            }
            
        });

    }
    //Hàm xem mã HS trên form là mã cho HS mới?
    public boolean kiemTraTonTaiGV(){
        for(NguoiDung gv: listGV){
            if(gv.getMaND().equals(txt_MaGV.getText())){
                return  true;
            }
        }
        return false;
    }
    //Hàm kiểm tra điều kiện để ẩn hiện cập nhật/thêm
    public boolean checkBtn(){
        if(lbl_ValidCCCD.getText()==null&&lbl_ValidDanToc.getText()==null&&lbl_ValidDiaChi.getText()==null
                &&lbl_ValidEmail.getText()==null&&lbl_ValidHoTen.getText()==null&&lbl_ValidNamCT.getText()==null
                &&lbl_ValidNamSinh.getText()==null&&lbl_ValidSDT.getText()==null){
            return true;
        }
            return false;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_Group = new javax.swing.ButtonGroup();
        JD_ThongBaoXoa = new javax.swing.JDialog();
        jLabel8 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btn_HuyXoa = new javax.swing.JButton();
        btn_DongYXoa = new javax.swing.JButton();
        JD_ThongBaoCapNhat = new javax.swing.JDialog();
        jLabel16 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        btn_HuyCapNhat = new javax.swing.JButton();
        btn_DongYCapNhat = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_MaGV = new javax.swing.JTextField();
        txt_HoTen = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_GiaoVien = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        btn_Nam = new javax.swing.JRadioButton();
        btn_Nu = new javax.swing.JRadioButton();
        txt_DanToc = new javax.swing.JTextField();
        txt_NamCT = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_TenDN = new javax.swing.JTextField();
        txt_CCCD = new javax.swing.JTextField();
        txt_SDT = new javax.swing.JTextField();
        txt_Email = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txt_NamSinh = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txt_DiaChi = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        btn_Them = new javax.swing.JButton();
        btn_CapNhat = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        btn_TimKiem = new javax.swing.JButton();
        txt_TimKiem = new javax.swing.JTextField();
        cbb_TimKiem = new javax.swing.JComboBox<>();
        lbl_HinhAnh = new javax.swing.JLabel();
        btnDong = new javax.swing.JButton();
        btn_LamMoi = new javax.swing.JButton();
        lbl_ValidHoTen = new javax.swing.JLabel();
        lbl_ValidNamSinh = new javax.swing.JLabel();
        lbl_ValidDanToc = new javax.swing.JLabel();
        lbl_ValidNamCT = new javax.swing.JLabel();
        lbl_ValidDiaChi = new javax.swing.JLabel();
        lbl_ValidCCCD = new javax.swing.JLabel();
        lbl_ValidSDT = new javax.swing.JLabel();
        lbl_ValidEmail = new javax.swing.JLabel();

        JD_ThongBaoXoa.setMinimumSize(new java.awt.Dimension(400, 250));
        JD_ThongBaoXoa.setModal(true);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 153));
        jLabel8.setText("Bạn Có Muốn Xóa Không?");

        btn_HuyXoa.setText("Hủy");
        btn_HuyXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HuyXoaActionPerformed(evt);
            }
        });

        btn_DongYXoa.setText("Đồng ý");
        btn_DongYXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DongYXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JD_ThongBaoXoaLayout = new javax.swing.GroupLayout(JD_ThongBaoXoa.getContentPane());
        JD_ThongBaoXoa.getContentPane().setLayout(JD_ThongBaoXoaLayout);
        JD_ThongBaoXoaLayout.setHorizontalGroup(
            JD_ThongBaoXoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JD_ThongBaoXoaLayout.createSequentialGroup()
                .addGroup(JD_ThongBaoXoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JD_ThongBaoXoaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2))
                    .addGroup(JD_ThongBaoXoaLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 81, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JD_ThongBaoXoaLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(btn_HuyXoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_DongYXoa)
                .addGap(65, 65, 65))
        );
        JD_ThongBaoXoaLayout.setVerticalGroup(
            JD_ThongBaoXoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JD_ThongBaoXoaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                .addGroup(JD_ThongBaoXoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_DongYXoa)
                    .addComponent(btn_HuyXoa))
                .addGap(17, 17, 17))
        );

        JD_ThongBaoCapNhat.setMinimumSize(new java.awt.Dimension(400, 250));
        JD_ThongBaoCapNhat.setModal(true);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 153, 153));
        jLabel16.setText("Bạn Có Muốn Cập Nhật Không?");

        btn_HuyCapNhat.setText("Hủy");
        btn_HuyCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HuyCapNhatActionPerformed(evt);
            }
        });

        btn_DongYCapNhat.setText("Đồng ý");
        btn_DongYCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DongYCapNhatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JD_ThongBaoCapNhatLayout = new javax.swing.GroupLayout(JD_ThongBaoCapNhat.getContentPane());
        JD_ThongBaoCapNhat.getContentPane().setLayout(JD_ThongBaoCapNhatLayout);
        JD_ThongBaoCapNhatLayout.setHorizontalGroup(
            JD_ThongBaoCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JD_ThongBaoCapNhatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator3)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JD_ThongBaoCapNhatLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(btn_HuyCapNhat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
                .addComponent(btn_DongYCapNhat)
                .addGap(65, 65, 65))
            .addGroup(JD_ThongBaoCapNhatLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JD_ThongBaoCapNhatLayout.setVerticalGroup(
            JD_ThongBaoCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JD_ThongBaoCapNhatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                .addGroup(JD_ThongBaoCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_DongYCapNhat)
                    .addComponent(btn_HuyCapNhat))
                .addGap(17, 17, 17))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Quản Lý Giáo Viên");

        jLabel2.setText("Mã giáo viên");

        jLabel3.setText("Họ và tên");

        jLabel4.setText("Năm sinh");

        jLabel5.setText("Giới tính");

        jLabel6.setText("Dân tộc");

        txt_HoTen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_HoTenKeyReleased(evt);
            }
        });

        tbl_GiaoVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã giáo viên", "Họ tên", "Năm sinh", "Giới tính", "Năm công tác"
            }
        ));
        jScrollPane2.setViewportView(tbl_GiaoVien);

        jLabel11.setText("Ảnh giáo viên");

        btn_Group.add(btn_Nam);
        btn_Nam.setText("Nam");

        btn_Group.add(btn_Nu);
        btn_Nu.setText("Nữ");
        btn_Nu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NuActionPerformed(evt);
            }
        });

        txt_DanToc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_DanTocActionPerformed(evt);
            }
        });
        txt_DanToc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_DanTocKeyReleased(evt);
            }
        });

        txt_NamCT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_NamCTKeyReleased(evt);
            }
        });

        jLabel7.setText("Năm công tác");

        txt_CCCD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_CCCDKeyReleased(evt);
            }
        });

        txt_SDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_SDTActionPerformed(evt);
            }
        });
        txt_SDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_SDTKeyReleased(evt);
            }
        });

        txt_Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_EmailActionPerformed(evt);
            }
        });
        txt_Email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_EmailKeyReleased(evt);
            }
        });

        jLabel9.setText("Tên đăng nhập");

        jLabel10.setText("Mật khẩu: ban đầu khởi tạo giống tên đăng nhập");

        txt_NamSinh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_NamSinhKeyReleased(evt);
            }
        });

        jLabel12.setText("CCCD");

        jLabel13.setText("Số điện thoại");

        jLabel14.setText("Email");

        txt_DiaChi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_DiaChiKeyReleased(evt);
            }
        });

        jLabel15.setText("Địa chỉ");

        btn_Them.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\OneDrive - Hanoi University of Science and Technology\\Desktop\\PJ Java\\BTL_Nhom08_1\\src\\images\\Add.24.png")); // NOI18N
        btn_Them.setText("Thêm");
        btn_Them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemActionPerformed(evt);
            }
        });

        btn_CapNhat.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\OneDrive - Hanoi University of Science and Technology\\Desktop\\PJ Java\\BTL_Nhom08_1\\src\\images\\update.24.png")); // NOI18N
        btn_CapNhat.setText("Cập nhật");
        btn_CapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhatActionPerformed(evt);
            }
        });

        btn_Xoa.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\OneDrive - Hanoi University of Science and Technology\\Desktop\\PJ Java\\BTL_Nhom08_1\\src\\images\\delete.24.png")); // NOI18N
        btn_Xoa.setText("Xóa");
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });

        btn_TimKiem.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\OneDrive - Hanoi University of Science and Technology\\Desktop\\PJ Java\\BTL_Nhom08_1\\src\\images\\Search.24.png")); // NOI18N
        btn_TimKiem.setText("Tìm kiếm");
        btn_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemActionPerformed(evt);
            }
        });

        cbb_TimKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã giáo viên", "Họ tên" }));
        cbb_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_TimKiemActionPerformed(evt);
            }
        });

        lbl_HinhAnh.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\OneDrive - Hanoi University of Science and Technology\\Desktop\\PJ Java\\BTL_Nhom08_1\\src\\images\\user.128.png")); // NOI18N
        lbl_HinhAnh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl_HinhAnh.setPreferredSize(new java.awt.Dimension(54, 20));
        lbl_HinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_HinhAnhMouseClicked(evt);
            }
        });

        btnDong.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\OneDrive - Hanoi University of Science and Technology\\Desktop\\PJ Java\\BTL_Nhom08_1\\src\\images\\Close.24.png")); // NOI18N
        btnDong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDongActionPerformed(evt);
            }
        });

        btn_LamMoi.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\OneDrive - Hanoi University of Science and Technology\\Desktop\\PJ Java\\BTL_Nhom08_1\\src\\images\\refresh.24.png")); // NOI18N
        btn_LamMoi.setText("Làm mới");
        btn_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiActionPerformed(evt);
            }
        });

        lbl_ValidHoTen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_ValidHoTen.setForeground(new java.awt.Color(204, 0, 0));
        lbl_ValidHoTen.setText("(*)");

        lbl_ValidNamSinh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_ValidNamSinh.setForeground(new java.awt.Color(204, 0, 0));
        lbl_ValidNamSinh.setText("(*)");

        lbl_ValidDanToc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_ValidDanToc.setForeground(new java.awt.Color(204, 0, 0));
        lbl_ValidDanToc.setText("(*)");

        lbl_ValidNamCT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_ValidNamCT.setForeground(new java.awt.Color(204, 0, 0));
        lbl_ValidNamCT.setText("(*)");

        lbl_ValidDiaChi.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_ValidDiaChi.setForeground(new java.awt.Color(204, 0, 0));
        lbl_ValidDiaChi.setText("(*)");

        lbl_ValidCCCD.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_ValidCCCD.setForeground(new java.awt.Color(204, 0, 0));
        lbl_ValidCCCD.setText("(*)");

        lbl_ValidSDT.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_ValidSDT.setForeground(new java.awt.Color(204, 0, 0));
        lbl_ValidSDT.setText("(*)");

        lbl_ValidEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_ValidEmail.setForeground(new java.awt.Color(204, 0, 0));
        lbl_ValidEmail.setText("(*)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(447, 447, 447)
                            .addComponent(jLabel1)
                            .addGap(353, 353, 353)
                            .addComponent(btnDong))
                        .addGroup(layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 987, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGap(80, 80, 80)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(lbl_HinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(btn_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(btn_Nu, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel10)
                                                .addComponent(jLabel9))
                                            .addGap(46, 46, 46))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(6, 6, 6)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(txt_MaGV, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addGroup(layout.createSequentialGroup()
                                                                    .addGap(9, 9, 9)
                                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(layout.createSequentialGroup()
                                                                            .addComponent(txt_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                            .addComponent(jLabel7))
                                                                        .addComponent(lbl_ValidHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGroup(layout.createSequentialGroup()
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                    .addComponent(txt_NamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(lbl_ValidDanToc, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(lbl_ValidNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(layout.createSequentialGroup()
                                                                    .addComponent(txt_DanToc, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                        .addGroup(layout.createSequentialGroup()
                                                            .addComponent(jLabel13)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(txt_SDT, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                                                .addComponent(lbl_ValidSDT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                                    .addGap(14, 14, 14)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(txt_NamCT, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                                                                .addComponent(lbl_ValidNamCT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(txt_DiaChi)
                                                                .addComponent(lbl_ValidDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(txt_CCCD)
                                                                .addComponent(lbl_ValidCCCD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                            .addComponent(txt_Email, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(lbl_ValidEmail, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addComponent(txt_TenDN, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(67, 67, 67)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btn_TimKiem)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(cbb_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btn_LamMoi)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(btn_Xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(btn_CapNhat)
                                            .addGap(18, 18, 18)
                                            .addComponent(btn_Them)))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(109, 109, 109)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(btnDong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txt_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3)
                                            .addComponent(txt_NamCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lbl_ValidHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lbl_ValidNamCT, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_NamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel15)
                                    .addComponent(txt_DiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addComponent(lbl_ValidNamSinh, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl_ValidDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_DanToc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel12)
                            .addComponent(txt_CCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_ValidCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_ValidDanToc, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(txt_SDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbl_ValidSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbl_ValidEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(btn_Nam)
                                    .addComponent(btn_Nu))
                                .addGap(17, 17, 17)
                                .addComponent(lbl_HinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txt_MaGV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txt_TenDN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(9, 9, 9)
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_TimKiem)
                            .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbb_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_Them)
                            .addComponent(btn_CapNhat)
                            .addComponent(btn_Xoa)
                            .addComponent(btn_LamMoi))
                        .addGap(69, 69, 69)))
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDongActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnDongActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        JD_ThongBaoXoa.setLocationRelativeTo(null);
        JD_ThongBaoXoa.setVisible(true);
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void btn_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemActionPerformed
        System.out.println(cbb_TimKiem.getSelectedIndex());
        //index=0 --> Tìm Kiếm theo mã HS
        //index=1 --> tìm kiếm theo tên HS
        ArrayList<GiaoVien> listTKGV = new ArrayList<>();
        
        if(cbb_TimKiem.getSelectedIndex()==0){
            for(GiaoVien gv: listGV){
                if(gv.getMaGV().equals(txt_TimKiem.getText())){
                    listTKGV.add(gv);
                    break;
                }
                    
            }
        } else if(cbb_TimKiem.getSelectedIndex()==1){
            for(GiaoVien gv: listGV){
                if(gv.getHoTen().equalsIgnoreCase(txt_TimKiem.getText())){
                    listTKGV.add(gv);
                }
            }
        }  
        
        //Xóa dữ liệu có hiện tại trên JTable
        model.setRowCount(0);
        showTable(listTKGV);
        JOptionPane.showMessageDialog(rootPane, "Tìm thấy " + listTKGV.size() + " kết quả!");
    }//GEN-LAST:event_btn_TimKiemActionPerformed

    private void btn_ThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemActionPerformed
        GiaoVien gv = layGiaoVien();
        if(new Controller_GiaoVien().addGiaoVien(gv)) {
            luuHinhAnh(gv);
            lamMoi();
            JOptionPane.showMessageDialog(rootPane, "Thêm thành công!");
        } else {
            JOptionPane.showMessageDialog(rootPane, "Dữ liệu trống hoặc không đúng định dạng, vui lòng kiểm tra lại!");
        }
    }//GEN-LAST:event_btn_ThemActionPerformed

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        lamMoi();
    }//GEN-LAST:event_btn_LamMoiActionPerformed
// Hàm này chỉ hiển thị ảnh đã chọn lên label Hình ảnh và lấy đường dẫn ảnh vừa chọn
    private void lbl_HinhAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_HinhAnhMouseClicked
        try {
            JFileChooser jfc = new JFileChooser("C:\\Users\\Admin\\OneDrive - Hanoi University of Science and Technology\\Desktop\\Ảnh BTL OOAD\\gv");
            jfc.showOpenDialog(null);
            File file = jfc.getSelectedFile();
//            System.out.println(file.getPath());
            Image img = ImageIO.read(file);
            lbl_HinhAnh.setText(file.getPath());
            int width = lbl_HinhAnh.getWidth();
            int height = lbl_HinhAnh.getHeight();
            lbl_HinhAnh.setIcon(new ImageIcon(img.getScaledInstance(width, height, 0)));
//            jButton4.setIcon(new javax.swing.ImageIcon);
        } catch (IOException ex) {
            System.out.println("Error: " + ex.toString());
        }
    }//GEN-LAST:event_lbl_HinhAnhMouseClicked

    private void btn_CapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CapNhatActionPerformed
        JD_ThongBaoCapNhat.setLocationRelativeTo(null);
        JD_ThongBaoCapNhat.setVisible(true);
    }//GEN-LAST:event_btn_CapNhatActionPerformed

    private void btn_HuyXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HuyXoaActionPerformed
        JD_ThongBaoXoa.dispose();
    }//GEN-LAST:event_btn_HuyXoaActionPerformed

    private void btn_DongYXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DongYXoaActionPerformed
        //Lấy HS khi click chọn trong table
        GiaoVien gv = layGiaoVien();   
            
            if(new Controller_GiaoVien().deleteGiaoVien(gv)) {//Cập nhật HS vào CSDL
                //Xóa ảnh
                try{
                   System.out.println(gv.getHinhAnh());
                   Path imagesPath = Paths.get("src\\Teacher_Img\\" +gv.getMaGV()+".png");
                   Files.delete(imagesPath);
               } catch(Exception e){
                   e.printStackTrace();
               }
                lamMoi();
                JD_ThongBaoXoa.dispose();
                JOptionPane.showMessageDialog(rootPane, "Xóa thành công!"); 
     
            }   
        JD_ThongBaoXoa.dispose();
    }//GEN-LAST:event_btn_DongYXoaActionPerformed

    private void btn_HuyCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HuyCapNhatActionPerformed
        JD_ThongBaoCapNhat.dispose();
    }//GEN-LAST:event_btn_HuyCapNhatActionPerformed

    private void btn_DongYCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DongYCapNhatActionPerformed
        GiaoVien gv = layGiaoVien();     
            
            if(new Controller_GiaoVien().updateGiaoVien(gv)) {
                luuHinhAnh(gv);
                lamMoi();
                JD_ThongBaoCapNhat.dispose();
                JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công!");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Dữ liệu trống hoặc không đúng định dạng, vui lòng kiểm tra lại!");
            }   

    }//GEN-LAST:event_btn_DongYCapNhatActionPerformed

    private void btn_NuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_NuActionPerformed

    private void cbb_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_TimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbb_TimKiemActionPerformed

    private void txt_SDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_SDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_SDTActionPerformed

    private void txt_EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_EmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_EmailActionPerformed

    private void txt_DanTocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_DanTocActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_DanTocActionPerformed

    private void txt_HoTenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_HoTenKeyReleased
        if(Validation.isEmpty(txt_HoTen)){
            lbl_ValidHoTen.setText("Không được trống!");
        }
        else {
                lbl_ValidHoTen.setText(null);
        }
        
        if(kiemTraTonTaiGV()){
            if(checkBtn()){
                btn_CapNhat.setEnabled(true);
                btn_Xoa.setEnabled(true);
            } else {
                btn_CapNhat.setEnabled(false);
                btn_Xoa.setEnabled(false);
            }
        } else{
            if(checkBtn()){
                btn_Them.setEnabled(true);
            } else {
                btn_Them.setEnabled(false);
            }
        }
    }//GEN-LAST:event_txt_HoTenKeyReleased

    private void txt_NamSinhKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_NamSinhKeyReleased
        if(Validation.isEmpty(txt_NamSinh)){
            lbl_ValidNamSinh.setText("Không được trống!");
        }
        else {
           if(Validation.isYear(txt_NamSinh)){
               lbl_ValidNamSinh.setText(null);
           } else{
               lbl_ValidNamSinh.setText("Không đúng định dạng!");
           }
        }
//        System.out.println(setOK());
        if(kiemTraTonTaiGV()){
            if(checkBtn()){
                btn_CapNhat.setEnabled(true);
                btn_Xoa.setEnabled(true);
            } else {
                btn_CapNhat.setEnabled(false);
                btn_Xoa.setEnabled(false);
            }
        } else{
            if(checkBtn()){
                btn_Them.setEnabled(true);
            } else {
                btn_Them.setEnabled(false);
            }
        }
    }//GEN-LAST:event_txt_NamSinhKeyReleased

    private void txt_NamCTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_NamCTKeyReleased
        if(Validation.isEmpty(txt_NamCT)){
            lbl_ValidNamCT.setText("Không được trống!");
        }
        else {
           if(Validation.isYear(txt_NamCT)){
               lbl_ValidNamCT.setText(null);
           } else{
               lbl_ValidNamCT.setText("Không đúng định dạng!");
           }
        }
//        System.out.println(setOK());
        if(kiemTraTonTaiGV()){
            if(checkBtn()){
                btn_CapNhat.setEnabled(true);
                btn_Xoa.setEnabled(true);
            } else {
                btn_CapNhat.setEnabled(false);
                btn_Xoa.setEnabled(false);
            }
        } else{
            if(checkBtn()){
                btn_Them.setEnabled(true);
            } else {
                btn_Them.setEnabled(false);
            }
        }
    }//GEN-LAST:event_txt_NamCTKeyReleased

    private void txt_DanTocKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_DanTocKeyReleased
        if(Validation.isEmpty(txt_DanToc)){
            lbl_ValidDanToc.setText("Không được trống!");
        }
        else {
                lbl_ValidDanToc.setText(null);
        }
//        System.out.println(setOK());
        if(kiemTraTonTaiGV()){
            if(checkBtn()){
                btn_CapNhat.setEnabled(true);
                btn_Xoa.setEnabled(true);
            } else {
                btn_CapNhat.setEnabled(false);
                btn_Xoa.setEnabled(false);
            }
        } else{
            if(checkBtn()){
                btn_Them.setEnabled(true);
            } else {
                btn_Them.setEnabled(false);
            }
        }
    }//GEN-LAST:event_txt_DanTocKeyReleased

    private void txt_SDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_SDTKeyReleased
        if(Validation.isEmpty(txt_SDT)){
            lbl_ValidSDT.setText("Không được trống!");
        }
        else {
           if(Validation.isPhoneNumber(txt_SDT)){
               lbl_ValidSDT.setText(null);
           } else{
               lbl_ValidSDT.setText("Không đúng định dạng!");
           }
        }
//        System.out.println(setOK());
        if(kiemTraTonTaiGV()){
            if(checkBtn()){
                btn_CapNhat.setEnabled(true);
                btn_Xoa.setEnabled(true);
            } else {
                btn_CapNhat.setEnabled(false);
                btn_Xoa.setEnabled(false);
            }
        } else{
            if(checkBtn()){
                btn_Them.setEnabled(true);
            } else {
                btn_Them.setEnabled(false);
            }
        }
    }//GEN-LAST:event_txt_SDTKeyReleased

    private void txt_DiaChiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_DiaChiKeyReleased
        if(Validation.isEmpty(txt_DiaChi)){
            lbl_ValidDiaChi.setText("Không được trống!");
        }
        else {
                lbl_ValidDiaChi.setText(null);
        }
//        System.out.println(setOK());
        if(kiemTraTonTaiGV()){
            if(checkBtn()){
                btn_CapNhat.setEnabled(true);
                btn_Xoa.setEnabled(true);
            } else {
                btn_CapNhat.setEnabled(false);
                btn_Xoa.setEnabled(false);
            }
        } else{
            if(checkBtn()){
                btn_Them.setEnabled(true);
            } else {
                btn_Them.setEnabled(false);
            }
        }
    }//GEN-LAST:event_txt_DiaChiKeyReleased

    private void txt_CCCDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_CCCDKeyReleased
        if(Validation.isEmpty(txt_CCCD)){
            lbl_ValidCCCD.setText("Không được trống!");
        }
        else {
           if(Validation.isCCCD(txt_CCCD)){
               lbl_ValidCCCD.setText(null);
           } else{
               lbl_ValidCCCD.setText("Không đúng định dạng!");
           }
        }
//        System.out.println(setOK());
        if(kiemTraTonTaiGV()){
            if(checkBtn()){
                btn_CapNhat.setEnabled(true);
                btn_Xoa.setEnabled(true);
            } else {
                btn_CapNhat.setEnabled(false);
                btn_Xoa.setEnabled(false);
            }
        } else{
            if(checkBtn()){
                btn_Them.setEnabled(true);
            } else {
                btn_Them.setEnabled(false);
            }
        }
    }//GEN-LAST:event_txt_CCCDKeyReleased

    private void txt_EmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_EmailKeyReleased
        if(Validation.isEmpty(txt_Email)){
            lbl_ValidEmail.setText("Không được trống!");
        }
        else {
           if(Validation.isEmail(txt_Email)){
               lbl_ValidEmail.setText(null);
           } else{
               lbl_ValidEmail.setText("Không đúng định dạng!");
           }
        }
//        System.out.println(setOK());
        if(kiemTraTonTaiGV()){
            if(checkBtn()){
                btn_CapNhat.setEnabled(true);
                btn_Xoa.setEnabled(true);
            } else {
                btn_CapNhat.setEnabled(false);
                btn_Xoa.setEnabled(false);
            }
        } else{
            if(checkBtn()){
                btn_Them.setEnabled(true);
            } else {
                btn_Them.setEnabled(false);
            }
        }
    }//GEN-LAST:event_txt_EmailKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JF_QLGiaoVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JF_QLGiaoVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JF_QLGiaoVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JF_QLGiaoVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JF_QLGiaoVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog JD_ThongBaoCapNhat;
    private javax.swing.JDialog JD_ThongBaoXoa;
    private javax.swing.JButton btnDong;
    private javax.swing.JButton btn_CapNhat;
    private javax.swing.JButton btn_DongYCapNhat;
    private javax.swing.JButton btn_DongYXoa;
    private javax.swing.ButtonGroup btn_Group;
    private javax.swing.JButton btn_HuyCapNhat;
    private javax.swing.JButton btn_HuyXoa;
    private javax.swing.JButton btn_LamMoi;
    private javax.swing.JRadioButton btn_Nam;
    private javax.swing.JRadioButton btn_Nu;
    private javax.swing.JButton btn_Them;
    private javax.swing.JButton btn_TimKiem;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.JComboBox<String> cbb_TimKiem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbl_HinhAnh;
    private javax.swing.JLabel lbl_ValidCCCD;
    private javax.swing.JLabel lbl_ValidDanToc;
    private javax.swing.JLabel lbl_ValidDiaChi;
    private javax.swing.JLabel lbl_ValidEmail;
    private javax.swing.JLabel lbl_ValidHoTen;
    private javax.swing.JLabel lbl_ValidNamCT;
    private javax.swing.JLabel lbl_ValidNamSinh;
    private javax.swing.JLabel lbl_ValidSDT;
    private javax.swing.JTable tbl_GiaoVien;
    private javax.swing.JTextField txt_CCCD;
    private javax.swing.JTextField txt_DanToc;
    private javax.swing.JTextField txt_DiaChi;
    private javax.swing.JTextField txt_Email;
    private javax.swing.JTextField txt_HoTen;
    private javax.swing.JTextField txt_MaGV;
    private javax.swing.JTextField txt_NamCT;
    private javax.swing.JTextField txt_NamSinh;
    private javax.swing.JTextField txt_SDT;
    private javax.swing.JTextField txt_TenDN;
    private javax.swing.JTextField txt_TimKiem;
    // End of variables declaration//GEN-END:variables
}
