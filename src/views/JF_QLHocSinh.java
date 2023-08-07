/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;


import controller.Controller_NguoiDung;
import java.io.IOException;
import javax.swing.JFileChooser;
import controller.Controller_HocSinh;
import controller.Validation;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import models.BienToanCuc;
import models.HocSinh;
import models.NguoiDung;

/**
 *
 * @author Admin
 */
public class JF_QLHocSinh extends javax.swing.JFrame {
    //Tạo listHS để chứa toàn bộ HS từ CSDL để update Bảng trong Phần mềm
    private ArrayList<HocSinh> listHS;
    //Khai báo đối tượng để sử dụng bảng
    DefaultTableModel model;
    //Biến đếm STT HS
    private int demHS;


    public JF_QLHocSinh() {
        initComponents();
//        setSize(1100,650);
        this.setLocationRelativeTo(null);
        
        txt_MaHS.setEnabled(false);
        //Tải Data HS từ CSDL vào listHS
        listHS = new Controller_HocSinh().getListHocSinh();
        //Sử dụng bảng để hiển thị
        model = (DefaultTableModel) tbl_HocSinh.getModel();
        //Lấy dữ luêụ từ bảng khi click dòng trong bảng
        LayDuLieuTuBang();

        lamMoi();
    }
    
    //Tạo tự tăng cho mã HS: Kiểm tra nếu đã có mã này trong list --> tăng 1 đơn vị, đến khi k có trong list --> thoát ra
    public void taoMaHS(){
        int id = 1;
        boolean state;
        while(true){
            state = false;
            for(HocSinh ph: listHS){
                if(ph.getMaHS().equals("hs"+id)){
                    state = true;
                    break;
                }
            }
            if(!state) break;
            ++id;
        }
        txt_MaHS.setText("hs"+ id);
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
  
    //Tạo hàm làm mới: làm mới biểu mẫu,Biến toàn cục, listHS, listPH --> sử dụng cuối cùng khi  bấm Thêm HS, Cập nhật, Làm mới, ...
    public void lamMoi(){
        //làm mới validation
        btn_ThemHocSinh.setEnabled(false);// ẩn hiện
        btn_CapNhat.setEnabled(false);// ẩn hiện
        btn_Xoa.setEnabled(false);// ẩn hiện
        lbl_ValidDanToc.setText("(*)");
        txt_DanToc.setBackground(Color.white);
        lbl_ValidHoTen.setText("(*)");
        txt_HoTen.setBackground(Color.white);
        lbl_ValidNgaySinh.setText("(*)(dd/mm/yyyy)");
        txt_NgaySinh.setBackground(Color.white);
        //làm mới textfield, reset button, combobox, label hình ảnh
        txt_MaHS.setText("");
        txt_HoTen.setText("");
        txt_NgaySinh.setText("");
        btn_Nam.setSelected(true);
        txt_DanToc.setText("");
        lbl_HinhAnh.setText("");
        txt_TimKiem.setText("");
        cbb_TimKiem.setSelectedIndex(0);
        setImageLabel("src\\images\\user.128.png");
        //Xóa hết dữ liệu đệm của biến toàn cục
        BienToanCuc.resetBTC();    
        //Load lại list HS từ CSDL --> Tải dữ liệu mới nhất HS từ CSDL
        listHS = new Controller_HocSinh().getListHocSinh();
        model.setRowCount(0);//Xóa hết các dòng trong bảng
        showTable(listHS);
        taoMaHS();
    }
    
    //Hàm lưu bản sao ảnh và setHinhAnh()
    public void luuHinhAnh(HocSinh hs){
        try{
            File inputFile = new File(lbl_HinhAnh.getText()); //lbl_HinhAnh chứa path
            //Ta lưu bản copy hình ảnh với tên file = mã hs + ".png"
            BufferedImage image = ImageIO.read(inputFile);
            if(!new File("src\\Student_Img").exists()) {
                new File("src\\Student_Img").mkdir();
            }
            File outFile = new File("src\\Student_Img" + "\\" + hs.getMaHS() + ".png");
            ImageIO.write(image, "png", outFile);

        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Tạo hàm trả về đôi tượng với các thông tin điền lên biểu mẫu
    public HocSinh getHocSinh(){
        HocSinh hs = new HocSinh();
        
        // Lấy dữ liệu từ biểu mẫu gán cho đối tượng HS
        hs.setMaHS(txt_MaHS.getText());
        hs.setHoTen(txt_HoTen.getText());
        hs.setDanToc(txt_DanToc.getText());

        try {
            //lấy ngày sinh           
            hs.setNgaySinh(new SimpleDateFormat("dd/MM/yyyy").parse(txt_NgaySinh.getText()));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        
        //Giới tính
        //set giới tính
        if(btn_Nam.isSelected()){
           hs.setGioiTinh(true);
        } else if(btn_Nu.isSelected()){
            hs.setGioiTinh(false);
        }
        
        //Kiểm tra xem label HÌnh ảnh có ảnh chưa, OK mới setHinhAnh() cho HS

        try{
            File inputFile1 = new File(lbl_HinhAnh.getText()); //lbl_HinhAnh chứa path
            BufferedImage image = ImageIO.read(inputFile1);
            hs.setHinhAnh(hs.getMaHS()+".png");
        } catch(Exception e){}
        
        
        return hs;
    }
    
    //Hiển thị một list Học sinh lên bảng
    public void showTable(ArrayList<HocSinh> list) {
        demHS = 1;
        for(HocSinh s: list) {
            showResult(s);
        }
    }
    
//Mỗi một lần thêm mới ta lại gọi lại hàm hiển thị
    public void showResult(HocSinh s){
        //Thêm 1 dòng gồm các thuộc tính sau vào bảng
        String gioiTinh = "";
        if(s.isGioiTinh()){
            gioiTinh = "Nam";
        } else {
            gioiTinh = "Nữ";
        }

        String date = new SimpleDateFormat("dd/MM/yyyy").format(s.getNgaySinh()).toString();
        
        model.addRow(new Object[]{
            demHS++, s.getMaHS(), s.getHoTen(), gioiTinh, date, s.getDanToc(), s.getHinhAnh(), s.getMaPH()
        });
    }    
    
    //Lấy dữ liệu từ bảng điền vào Biểu mẫu
    public void LayDuLieuTuBang() {    
        
        tbl_HocSinh.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if(tbl_HocSinh.getSelectedRow()>= 0) {
                    txt_MaHS.setText(tbl_HocSinh.getValueAt(tbl_HocSinh.getSelectedRow(),1) + "");
                    txt_HoTen.setText(tbl_HocSinh.getValueAt(tbl_HocSinh.getSelectedRow(),2) + "");
                    //Giới tính
                    if((tbl_HocSinh.getValueAt(tbl_HocSinh.getSelectedRow(),3)+"").equals("Nam")) {
                        btn_Nam.setSelected(true);
                    } else if((tbl_HocSinh.getValueAt(tbl_HocSinh.getSelectedRow(),3)+"").equals("Nữ")){
                        btn_Nu.setSelected(true);
                    }
   
                    txt_NgaySinh.setText(tbl_HocSinh.getValueAt(tbl_HocSinh.getSelectedRow(),4)+"");
                    txt_DanToc.setText(tbl_HocSinh.getValueAt(tbl_HocSinh.getSelectedRow(),5) + "");
                    
                    lbl_HinhAnh.setText("src\\Student_Img\\" + tbl_HocSinh.getValueAt(tbl_HocSinh.getSelectedRow(),1) + ".png");
                    //Ban đầu hiển thị ảnh biểu tượng rõng để xóa ảnh HS đã chọn lần trước
                    setImageLabel("src\\images\\user.128.png");
                    setImageLabel("src\\Student_Img\\" + tbl_HocSinh.getValueAt(tbl_HocSinh.getSelectedRow(),1) + ".png" );
                    
                    BienToanCuc btc = new BienToanCuc();
                    btc.maND=tbl_HocSinh.getValueAt(tbl_HocSinh.getSelectedRow(),7)+"";
                    for(NguoiDung ph: btc.listPH){
                        if(ph.getMaND().equals(btc.maND)){
                            //CHỉ cần gán như vậy thôi, trong hàm tạo form thông tin PH sẽ tự lấy phuhuynh ra để load data;
                            btc.phuhuynh = ph;
                            btc.luaChonPH = 1;
                            break;
                        }
                        
                    }
                    btn_CapNhat.setEnabled(true);//ẩn hiện
                    btn_Xoa.setEnabled(true);//ẩn hiện
                    lbl_ValidDanToc.setText(null);
                    lbl_ValidNgaySinh.setText(null);
                    lbl_ValidHoTen.setText(null);
                }
            }
            
        });
        
    }
    //Hàm xem mã HS trên form là mã cho HS mới?
    public boolean kiemTraTonTaiHS(){
        for(HocSinh s: listHS){
            if(s.getMaHS().equals(txt_MaHS.getText())){
                return  true;
            }
        }
        return false;
    }
    //Hàm kiểm tra điều kiện để ẩn hiện cập nhật/thêm
    public boolean checkValidation(){
        if(lbl_ValidDanToc.getText()==null&&lbl_ValidHoTen.getText()==null&&lbl_ValidNgaySinh.getText()==null){
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
        jSeparator3 = new javax.swing.JSeparator();
        btn_HuyCapNhat = new javax.swing.JButton();
        btn_DongYCapNhat = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_HoTen = new javax.swing.JTextField();
        txt_DanToc = new javax.swing.JTextField();
        btn_ThemHocSinh = new javax.swing.JButton();
        btn_Xoa = new javax.swing.JButton();
        btn_LamMoi = new javax.swing.JButton();
        btn_CapNhat = new javax.swing.JButton();
        btn_TimKiem = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_HocSinh = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        btn_Nam = new javax.swing.JRadioButton();
        btn_Nu = new javax.swing.JRadioButton();
        btn_ThongTinPH = new javax.swing.JButton();
        txt_TimKiem = new javax.swing.JTextField();
        cbb_TimKiem = new javax.swing.JComboBox<>();
        lbl_HinhAnh = new javax.swing.JLabel();
        btn_Dong = new javax.swing.JButton();
        txt_NgaySinh = new javax.swing.JTextField();
        txt_MaHS = new javax.swing.JTextField();
        lbl_ValidNgaySinh = new javax.swing.JLabel();
        lbl_ValidHoTen = new javax.swing.JLabel();
        lbl_ValidDanToc = new javax.swing.JLabel();

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
            .addGroup(JD_ThongBaoXoaLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(btn_HuyXoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_DongYXoa)
                .addGap(64, 64, 64))
        );
        JD_ThongBaoXoaLayout.setVerticalGroup(
            JD_ThongBaoXoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JD_ThongBaoXoaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addGroup(JD_ThongBaoXoaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_HuyXoa)
                    .addComponent(btn_DongYXoa))
                .addGap(25, 25, 25))
        );

        JD_ThongBaoCapNhat.setMinimumSize(new java.awt.Dimension(400, 250));
        JD_ThongBaoCapNhat.setModal(true);

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

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 153, 153));
        jLabel16.setText("Bạn Có Muốn Cập Nhật Không?");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, Short.MAX_VALUE)
                .addGroup(JD_ThongBaoCapNhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_DongYCapNhat)
                    .addComponent(btn_HuyCapNhat))
                .addGap(17, 17, 17))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Quản Lý Học Sinh");

        jLabel2.setText("Mã học sinh");

        jLabel3.setText("Họ và tên");

        jLabel4.setText("Ngày sinh");

        jLabel5.setText("Giới tính");

        jLabel6.setText("Dân tộc");

        txt_HoTen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_HoTenKeyReleased(evt);
            }
        });

        txt_DanToc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_DanTocKeyReleased(evt);
            }
        });

        btn_ThemHocSinh.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\OneDrive - Hanoi University of Science and Technology\\Desktop\\PJ Java\\BTL_Nhom08\\src\\main\\java\\images\\Add.png")); // NOI18N
        btn_ThemHocSinh.setText("Thêm");
        btn_ThemHocSinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThemHocSinhActionPerformed(evt);
            }
        });

        btn_Xoa.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\OneDrive - Hanoi University of Science and Technology\\Desktop\\PJ Java\\BTL_Nhom08\\src\\main\\java\\images\\Delete.png")); // NOI18N
        btn_Xoa.setText("Xóa");
        btn_Xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_XoaActionPerformed(evt);
            }
        });

        btn_LamMoi.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\OneDrive - Hanoi University of Science and Technology\\Desktop\\PJ Java\\BTL_Nhom08\\src\\main\\java\\images\\Refresh.png")); // NOI18N
        btn_LamMoi.setText("Làm mới");
        btn_LamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LamMoiActionPerformed(evt);
            }
        });

        btn_CapNhat.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\OneDrive - Hanoi University of Science and Technology\\Desktop\\PJ Java\\BTL_Nhom08_1\\src\\images\\update.24.png")); // NOI18N
        btn_CapNhat.setText("Cập nhật");
        btn_CapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CapNhatActionPerformed(evt);
            }
        });

        btn_TimKiem.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\OneDrive - Hanoi University of Science and Technology\\Desktop\\PJ Java\\BTL_Nhom08\\src\\main\\java\\images\\Search.png")); // NOI18N
        btn_TimKiem.setText("Tìm kiếm");
        btn_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_TimKiemActionPerformed(evt);
            }
        });

        tbl_HocSinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã học sinh", "Họ tên", "Giới tính", "Ngày sinh", "Dân tộc", "Hình ảnh", "Mã Phụ huynh"
            }
        ));
        jScrollPane2.setViewportView(tbl_HocSinh);
        if (tbl_HocSinh.getColumnModel().getColumnCount() > 0) {
            tbl_HocSinh.getColumnModel().getColumn(6).setHeaderValue("Hình ảnh");
        }

        jLabel11.setText("Ảnh học sinh");

        btn_Group.add(btn_Nam);
        btn_Nam.setText("Nam");

        btn_Group.add(btn_Nu);
        btn_Nu.setText("Nữ");

        btn_ThongTinPH.setText("Thông tin phụ huynh");
        btn_ThongTinPH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ThongTinPHActionPerformed(evt);
            }
        });

        cbb_TimKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã học sinh", "Họ tên" }));

        lbl_HinhAnh.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\OneDrive - Hanoi University of Science and Technology\\Desktop\\PJ Java\\BTL_test\\src\\images\\addphoto128.png")); // NOI18N
        lbl_HinhAnh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl_HinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_HinhAnhMouseClicked(evt);
            }
        });

        btn_Dong.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\OneDrive - Hanoi University of Science and Technology\\Desktop\\PJ Java\\BTL_Nhom08_1\\src\\images\\Close.24.png")); // NOI18N
        btn_Dong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DongActionPerformed(evt);
            }
        });

        txt_NgaySinh.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_NgaySinhKeyReleased(evt);
            }
        });

        lbl_ValidNgaySinh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_ValidNgaySinh.setForeground(new java.awt.Color(204, 0, 0));
        lbl_ValidNgaySinh.setText("(*)(dd/mm/yyyy)");

        lbl_ValidHoTen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_ValidHoTen.setForeground(new java.awt.Color(204, 0, 51));
        lbl_ValidHoTen.setText("(*)");

        lbl_ValidDanToc.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbl_ValidDanToc.setForeground(new java.awt.Color(204, 0, 0));
        lbl_ValidDanToc.setText("(*)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lbl_HinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btn_CapNhat, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btn_LamMoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn_ThemHocSinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btn_Xoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(btn_ThongTinPH, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(btn_Nam, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btn_Nu, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txt_NgaySinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                                    .addComponent(txt_HoTen, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_DanToc, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_MaHS, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(lbl_ValidHoTen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbl_ValidNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                                    .addComponent(lbl_ValidDanToc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_TimKiem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbb_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(379, 379, 379)
                        .addComponent(btn_Dong)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btn_Dong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_MaHS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txt_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_ValidHoTen))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_NgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_ValidNgaySinh))
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(btn_Nam)
                            .addComponent(btn_Nu))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txt_DanToc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_ValidDanToc))
                        .addGap(6, 6, 6)
                        .addComponent(btn_ThongTinPH)
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btn_ThemHocSinh)
                                .addGap(18, 18, 18)
                                .addComponent(btn_CapNhat)
                                .addGap(18, 18, 18)
                                .addComponent(btn_Xoa)
                                .addGap(18, 18, 18)
                                .addComponent(btn_LamMoi))
                            .addComponent(lbl_HinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbb_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ThongTinPHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThongTinPHActionPerformed
        new JF_ThongTinPhuHuynh().setVisible(true);
    }//GEN-LAST:event_btn_ThongTinPHActionPerformed

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        lamMoi();
    }//GEN-LAST:event_btn_LamMoiActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed

        JD_ThongBaoXoa.setLocationRelativeTo(null);
        JD_ThongBaoXoa.setVisible(true);
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void btn_ThemHocSinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemHocSinhActionPerformed


        HocSinh hs = getHocSinh();
        
        //Lấy mã Phụ huynh cho HS
        if(BienToanCuc.luaChonPH == 1) { //TH tạo PH mới
            hs.setMaPH(BienToanCuc.phuhuynh.getMaND());
            //Bắt bộc thêm PH trươc để có Khóa ngoại cho HS
            Controller_NguoiDung cn_PH = new Controller_NguoiDung();
            //Thêm PH và listPH trong btc để tiện sử dụng. VD chọn HS trong bảng thì cũng phải có thông tin PH để load lên form ThongTinPH
//            btc.addPHForListPH(btc.phuhuynh);
            BienToanCuc.listPH.add(BienToanCuc.phuhuynh);
            cn_PH.addNguoiDung(BienToanCuc.phuhuynh);
        } else if(BienToanCuc.luaChonPH == 2) {
            hs.setMaPH(BienToanCuc.maND);
        } 
        
        
        //Thêm học sinh vào CSDL
        
        if(new Controller_HocSinh().addHocSinh(hs)) {
            luuHinhAnh(hs);
            lamMoi();
            JOptionPane.showMessageDialog(rootPane, "Thêm thành công!");
        } else {
            JOptionPane.showMessageDialog(rootPane, "Xem lại thông tin Phụ huynh!");//thông báo cũ: Dữ liệu trống hoặc không đúng định dạng, vui lòng kiểm tra lại!
        }     
        
    }//GEN-LAST:event_btn_ThemHocSinhActionPerformed

    private void btn_DongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DongActionPerformed
        this.dispose();
    }//GEN-LAST:event_btn_DongActionPerformed
// Hàm này chỉ hiển thị ảnh đã chọn lên label Hình ảnh và lấy đường dẫn ảnh vừa chọn
    private void lbl_HinhAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_HinhAnhMouseClicked
        try {
            JFileChooser jfc = new JFileChooser("C:\\Users\\Admin\\OneDrive - Hanoi University of Science and Technology\\Desktop\\Ảnh BTL OOAD\\hs");
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

    private void btn_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemActionPerformed

        ArrayList<HocSinh> listTKHS = new ArrayList<>();
        
        if(cbb_TimKiem.getSelectedIndex()==0){
            for(HocSinh hs: listHS){
                if(hs.getMaHS().equals(txt_TimKiem.getText())){
                    listTKHS.add(hs);
                    break;
                }
                    
            }
        } else if(cbb_TimKiem.getSelectedIndex()==1){
            for(HocSinh hs: listHS){
                if(hs.getHoTen().equalsIgnoreCase(txt_TimKiem.getText())){
                    listTKHS.add(hs);
                }
            }
        }  
        
        //Xóa dữ liệu có hiện tại trên JTable
        model.setRowCount(0);
        showTable(listTKHS);
        JOptionPane.showMessageDialog(rootPane, "Tìm thấy " + listTKHS.size() + " kết quả!");  
        
    }//GEN-LAST:event_btn_TimKiemActionPerformed

    private void btn_HuyXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HuyXoaActionPerformed
        JD_ThongBaoXoa.dispose();
    }//GEN-LAST:event_btn_HuyXoaActionPerformed

    private void btn_DongYXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DongYXoaActionPerformed
        //Lấy HS khi click chọn trong table
        HocSinh hs = getHocSinh(); //Chú ý hàm lấy học sinh không gán mã PH cho hs     
            
        if(new Controller_HocSinh().deleteHocSinh(hs)) {//Cập nhật HS vào CSDL

            //Trường hợp 1 PH nhiều HS--> Khi xóa 1 HS thì ta chưa thẻ xóa luôn PH này được
            new Controller_NguoiDung().deleteNguoiDung(BienToanCuc.maND);                
            //Xóa ảnh
            try{
               System.out.println(hs.getHinhAnh());
               Path imagesPath = Paths.get("src\\Student_Img\\" +hs.getMaHS()+".png");
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

//        BienToanCuc.hocsinh = getHocSinh();
        HocSinh hs = getHocSinh();
            if(new Controller_HocSinh().updateHocSinh(hs)&&new Controller_NguoiDung().updateNguoiDung(BienToanCuc.phuhuynh)) {//Cập nhật HS vào CSDL
                luuHinhAnh(hs);
                lamMoi();
                JD_ThongBaoCapNhat.dispose();
                JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công!");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Dữ liệu trống hoặc không đúng định dạng, vui lòng kiểm tra lại!");
            }
    }//GEN-LAST:event_btn_DongYCapNhatActionPerformed

    private void txt_HoTenKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_HoTenKeyReleased
        if(Validation.isEmpty(txt_HoTen)){
            lbl_ValidHoTen.setText("Không được trống!");
        }
        else {
                lbl_ValidHoTen.setText(null);
        }
        
        if(kiemTraTonTaiHS()){
            if(checkValidation()){
                btn_CapNhat.setEnabled(true);
                btn_Xoa.setEnabled(true);
            } else {
                btn_CapNhat.setEnabled(false);
                btn_Xoa.setEnabled(false);
            }
        } else{
            if(checkValidation()){
                btn_ThemHocSinh.setEnabled(true);
            } else {
                btn_ThemHocSinh.setEnabled(false);
            }
        }
        
    }//GEN-LAST:event_txt_HoTenKeyReleased

    private void txt_NgaySinhKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_NgaySinhKeyReleased
        if(Validation.isEmpty(txt_NgaySinh)){
            lbl_ValidNgaySinh.setText("Không được trống!");
            return;
        }
        else {
            if(Validation.isDate(txt_NgaySinh)){
            lbl_ValidNgaySinh.setText(null);
            } else{
                lbl_ValidNgaySinh.setText("Không đúng định dạng: dd/mm/yyyy");
            }
        }
        if(kiemTraTonTaiHS()){
            if(checkValidation()){
                btn_CapNhat.setEnabled(true);
                btn_Xoa.setEnabled(true);
            } else {
                btn_CapNhat.setEnabled(false);
                btn_Xoa.setEnabled(false);
            }
        } else{
            if(checkValidation()){
                btn_ThemHocSinh.setEnabled(true);
            } else {
                btn_ThemHocSinh.setEnabled(false);
            }
        }
    }//GEN-LAST:event_txt_NgaySinhKeyReleased

    private void txt_DanTocKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_DanTocKeyReleased
        if(Validation.isEmpty(txt_DanToc)){
            lbl_ValidDanToc.setText("Không được trống!");
        }
        else {
                lbl_ValidDanToc.setText(null);
        }
        if(kiemTraTonTaiHS()){
            if(checkValidation()){
                btn_CapNhat.setEnabled(true);
                btn_Xoa.setEnabled(true);
            } else {
                btn_CapNhat.setEnabled(false);
                btn_Xoa.setEnabled(false);
            }
        } else{
            if(checkValidation()){
                btn_ThemHocSinh.setEnabled(true);
            } else {
                btn_ThemHocSinh.setEnabled(false);
            }
        }
    }//GEN-LAST:event_txt_DanTocKeyReleased
  
    @SuppressWarnings("unchecked")
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
            java.util.logging.Logger.getLogger(JF_QLHocSinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JF_QLHocSinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JF_QLHocSinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JF_QLHocSinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JF_QLHocSinh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog JD_ThongBaoCapNhat;
    private javax.swing.JDialog JD_ThongBaoXoa;
    private javax.swing.JButton btn_CapNhat;
    private javax.swing.JButton btn_Dong;
    private javax.swing.JButton btn_DongYCapNhat;
    private javax.swing.JButton btn_DongYXoa;
    private javax.swing.ButtonGroup btn_Group;
    private javax.swing.JButton btn_HuyCapNhat;
    private javax.swing.JButton btn_HuyXoa;
    private javax.swing.JButton btn_LamMoi;
    private javax.swing.JRadioButton btn_Nam;
    private javax.swing.JRadioButton btn_Nu;
    private javax.swing.JButton btn_ThemHocSinh;
    private javax.swing.JButton btn_ThongTinPH;
    private javax.swing.JButton btn_TimKiem;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.JComboBox<String> cbb_TimKiem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel lbl_HinhAnh;
    private javax.swing.JLabel lbl_ValidDanToc;
    private javax.swing.JLabel lbl_ValidHoTen;
    private javax.swing.JLabel lbl_ValidNgaySinh;
    private javax.swing.JTable tbl_HocSinh;
    private javax.swing.JTextField txt_DanToc;
    private javax.swing.JTextField txt_HoTen;
    private javax.swing.JTextField txt_MaHS;
    private javax.swing.JTextField txt_NgaySinh;
    private javax.swing.JTextField txt_TimKiem;
    // End of variables declaration//GEN-END:variables
}
