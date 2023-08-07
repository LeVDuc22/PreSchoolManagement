/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;


import controller.ConnectDB_PhuHuynh;
import views.ThongTinPhuHuynh;
import java.io.IOException;
import javax.swing.JFileChooser;
import controller.ConnectDB_HocSinh;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import models.BienToanCuc_QLHS;
import models.HocSinh;
import models.NguoiDung;

/**
 *
 * @author Admin
 */
public class QL_HocSinh extends javax.swing.JFrame {
    //Tạo listHS để chứa toàn bộ HS từ CSDL để update Bảng trong Phần mềm
    private ArrayList<HocSinh> listHS;
    //Khai báo đối tượng để sử dụng bảng
    DefaultTableModel model;
    //Biến đếm STT HS
    private int demHS;


    public QL_HocSinh() {
        initComponents();
        setSize(1044,580);
        this.setLocationRelativeTo(null);
        
        txt_MaHS.setEnabled(false);
        //Tải Data HS từ CSDL vào listHS
        listHS = new ConnectDB_HocSinh().getListStudent();
        //Ban đầu mặc định luôn chọn giới tính Nam
        btn_Nam.setSelected(true);
        //Sử dụng bảng để hiển thị
        model = (DefaultTableModel) tbl_Hocsinh.getModel();
        //Lấy dữ luêụ từ bảng khi click dòng trong bảng
        LayDuLieuTuBang();
        //Mỗi lần mở form lên thì hiển thị Danh sách HS từ CSDL lên
        showTable(listHS);
        //Ban đầu hiển thị ảnh biểu tượng rỗng
        setImageLabel("src\\images\\addPhoto64.png");
        
        //Mã HS tự tăng
        taoMaHS();

    }
    
    //Tạo tự tăng cho mã HS
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
        txt_MaHS.setText("");
        txt_HoTen.setText("");
        txt_NgaySinh.setText("");
        btn_Nam.setSelected(true);
        txt_DanToc.setText("");
        lbl_HinhAnh.setText("");
        txt_TimKiem.setText("");
        cbb_TimKiem.setSelectedIndex(0);
        setImageLabel("src\\images\\addPhoto64.png");

        BienToanCuc_QLHS.resetBTC();
        //hiển thị lại mã HS tự tăng
        //Mã HS tự tăng
        taoMaHS();
        
        //Tải dữ liệu mới nhất HS từ CSDL
        listHS = new ConnectDB_HocSinh().getListStudent();
        model.setRowCount(0);
        showTable(listHS);
    }
    
    //Tạo hàm trả về đôi tượng với các thông tin điền lên biểu mẫu
    public HocSinh layHocSinh(){
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
        
        //Ảnh HS: Chọn ảnh từ Local và lưu bản coppy vào thư mục ảnh
        //labelText như biến trung gian lưu filepath hình ảnh
        //Lưu ảnh của học sinh khi nhấn thêm học sinh
        try{
            //Kiểm tra xem HS này có file ảnh không: Nếu có --> đọc được --> setHinhAnh
            File inputFile = new File("src\\Student_Img" + "\\" + hs.getMaHS() + ".png");
            BufferedImage image = ImageIO.read(inputFile);

            //Nếu hs k có ảnh --> khi đọc file --> xảy ra ngoại lệ và không thực hiện setHinhanh --> OK;
            hs.setHinhAnh(txt_MaHS.getText() + ".png");
        } catch(IOException e){
            System.out.println("Error: "+e);
          }
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
        
        tbl_Hocsinh.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent lse) {
                if(tbl_Hocsinh.getSelectedRow()>= 0) {
                    txt_MaHS.setText(tbl_Hocsinh.getValueAt(tbl_Hocsinh.getSelectedRow(),1) + "");
                    txt_HoTen.setText(tbl_Hocsinh.getValueAt(tbl_Hocsinh.getSelectedRow(),2) + "");
                    //Giới tính
                    if((tbl_Hocsinh.getValueAt(tbl_Hocsinh.getSelectedRow(),3)+"").equals("Nam")) {
                        btn_Nam.setSelected(true);
                    } else if((tbl_Hocsinh.getValueAt(tbl_Hocsinh.getSelectedRow(),3)+"").equals("Nữ")){
                        btn_Nu.setSelected(true);
                    }
   
                    txt_NgaySinh.setText(tbl_Hocsinh.getValueAt(tbl_Hocsinh.getSelectedRow(),4)+"");
                    txt_DanToc.setText(tbl_Hocsinh.getValueAt(tbl_Hocsinh.getSelectedRow(),5) + "");
                    
                    //Ban đầu hiển thị ảnh biểu tượng rõng để xóa ảnh HS đã chọn lần trước
                    setImageLabel("src\\images\\addPhoto64.png");
                    setImageLabel("src\\Student_Img\\" + tbl_Hocsinh.getValueAt(tbl_Hocsinh.getSelectedRow(),1) + ".png" );
                    
                    BienToanCuc_QLHS btc = new BienToanCuc_QLHS();
                    btc.maND=tbl_Hocsinh.getValueAt(tbl_Hocsinh.getSelectedRow(),7)+"";
                    for(NguoiDung ph: btc.listPH){
                        if(ph.getMaND().equals(btc.maND)){
                            //CHỉ cần gán như vậy thôi, trong hàm tạo form thông tin PH sẽ tự lấy phuhuynh ra để load data;
                            btc.phuhuynh = ph;
                            btc.luaChonPH = 1;
                            break;
                        }
                        
                    }
                    
                }
            }
            
        });
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_Group = new javax.swing.ButtonGroup();
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
        tbl_Hocsinh = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        btn_Nam = new javax.swing.JRadioButton();
        btn_Nu = new javax.swing.JRadioButton();
        btn_ThongTinPH = new javax.swing.JButton();
        txt_TimKiem = new javax.swing.JTextField();
        cbb_TimKiem = new javax.swing.JComboBox<>();
        lbl_HinhAnh = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        txt_NgaySinh = new javax.swing.JTextField();
        txt_MaHS = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Quản Lý Học Sinh");

        jLabel2.setText("Mã học sinh");

        jLabel3.setText("Họ và tên");

        jLabel4.setText("Ngày sinh");

        jLabel5.setText("Giới tính");

        jLabel6.setText("Dân tộc");

        txt_HoTen.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_HoTenFocusLost(evt);
            }
        });

        txt_DanToc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_DanTocFocusLost(evt);
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

        btn_CapNhat.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\OneDrive - Hanoi University of Science and Technology\\Desktop\\PJ Java\\BTL_Nhom08\\src\\main\\java\\images\\Edit-validated.24.png")); // NOI18N
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

        tbl_Hocsinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã học sinh", "Họ tên", "Giới tính", "Ngày sinh", "Dân tộc", "Hình ảnh", "Mã Phụ huynh"
            }
        ));
        jScrollPane2.setViewportView(tbl_Hocsinh);

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

        cbb_TimKiem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã học sinh", "Họ tên", " " }));
        cbb_TimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbb_TimKiemActionPerformed(evt);
            }
        });

        lbl_HinhAnh.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\OneDrive - Hanoi University of Science and Technology\\Desktop\\PJ Java\\BTL_test\\src\\images\\addphoto128.png")); // NOI18N
        lbl_HinhAnh.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lbl_HinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_HinhAnhMouseClicked(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Admin\\OneDrive - Hanoi University of Science and Technology\\Desktop\\PJ Java\\BTL_Nhom08_1\\src\\images\\Close24.png")); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txt_NgaySinh.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_NgaySinhFocusLost(evt);
            }
        });

        jLabel7.setText("(dd/mm/yyyy)");

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
                                .addComponent(lbl_HinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_CapNhat, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btn_LamMoi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_ThemHocSinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_Xoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                .addComponent(jLabel7)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbb_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(379, 379, 379)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jScrollPane2))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(txt_MaHS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(txt_HoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_NgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(btn_Nam)
                                    .addComponent(btn_Nu))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txt_DanToc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_TimKiem)
                                .addComponent(cbb_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txt_TimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_ThongTinPH)
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_ThemHocSinh)
                                .addGap(18, 18, 18)
                                .addComponent(btn_CapNhat)
                                .addGap(18, 18, 18)
                                .addComponent(btn_Xoa)
                                .addGap(18, 18, 18)
                                .addComponent(btn_LamMoi))
                            .addComponent(lbl_HinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ThongTinPHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThongTinPHActionPerformed
        new ThongTinPhuHuynh().setVisible(true);       
    
    }//GEN-LAST:event_btn_ThongTinPHActionPerformed

    private void btn_LamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LamMoiActionPerformed
        lamMoi();
    }//GEN-LAST:event_btn_LamMoiActionPerformed

    private void btn_XoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_XoaActionPerformed
        //Lấy HS khi click chọn trong table
        BienToanCuc_QLHS.hocsinh=layHocSinh();
        
        new JD_ThongBaoXoa(this, rootPaneCheckingEnabled).setVisible(true);
        
        if(BienToanCuc_QLHS.luaChonXoa){      
            
            if(new ConnectDB_HocSinh().deleteHocSinh(BienToanCuc_QLHS.hocsinh)) {//Cập nhật HS vào CSDL

                //Trường hợp 1 PH nhiều HS--> Khi xóa 1 HS thì ta chưa thẻ xóa luôn PH này được
                new ConnectDB_PhuHuynh().deletePhuHuynh(BienToanCuc_QLHS.maND);                
                
                lamMoi();

                JOptionPane.showMessageDialog(rootPane, "Xóa thành công!");                
            }   
            
        }
    }//GEN-LAST:event_btn_XoaActionPerformed

    private void btn_ThemHocSinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ThemHocSinhActionPerformed


        HocSinh hs = layHocSinh();
        
        BienToanCuc_QLHS btc = new BienToanCuc_QLHS(); 
        
        //Lấy mã Phụ huynh cho HS
        if(btc.luaChonPH == 1) { //TH tạo PH mới
            hs.setMaPH(btc.phuhuynh.getMaND());
            //Bắt bộc thêm PH trươc để có Khóa ngoại cho HS
            ConnectDB_PhuHuynh cn_PH = new ConnectDB_PhuHuynh();
            //Thêm PH và listPH trong btc để tiện sử dụng. VD chọn HS trong bảng thì cũng phải có thông tin PH để load lên form ThongTinPH
//            btc.addPHForListPH(btc.phuhuynh);
            btc.listPH.add(btc.phuhuynh);
            cn_PH.themPhuHuynh(btc.phuhuynh);
        } else if(btc.luaChonPH == 2) {
            hs.setMaPH(btc.maND);
        }
        //Kiểm tra buton group
        if(!(btn_Nam.isSelected()||btn_Nu.isSelected())){
            JOptionPane.showMessageDialog(rootPane, "Giới tính không được để trống!");
        }
        
        //Thêm học sinh vào CSDL
        
        if(new ConnectDB_HocSinh().addStudent(hs)) {
            JOptionPane.showMessageDialog(rootPane, "Thêm thành công!");
            //Thêm student s vào danh sách để load lên bảng
            listHS.add(hs);

            lamMoi();
        } //else {
//            JOptionPane.showMessageDialog(rootPane, "Dữ liệu trống hoặc không đúng định dạng, vui lòng kiểm tra lại!");
//        }     
        
    }//GEN-LAST:event_btn_ThemHocSinhActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed
// Hàm này chỉ hiển thị ảnh đã chọn lên label Hình ảnh và lấy đường dẫn ảnh vừa chọn
    private void lbl_HinhAnhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_HinhAnhMouseClicked
        try {
            JFileChooser jfc = new JFileChooser("C:\\Users\\Admin\\OneDrive - Hanoi University of Science and Technology\\Desktop");
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

        
        BienToanCuc_QLHS.hocsinh = layHocSinh();

        new JD_ThongBaoCapNhat(this, rootPaneCheckingEnabled).setVisible(true);

        
        if(BienToanCuc_QLHS.luaChonCapNhat){      
            
            if(new ConnectDB_HocSinh().updateHocSinh(BienToanCuc_QLHS.hocsinh)&&new ConnectDB_PhuHuynh().updatePhuHuynh(BienToanCuc_QLHS.phuhuynh)) {//Cập nhật HS vào CSDL
                
                lamMoi();
                
                JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công!");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Dữ liệu trống hoặc không đúng định dạng, vui lòng kiểm tra lại!");
            }   
            
        }
    }//GEN-LAST:event_btn_CapNhatActionPerformed

    private void btn_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_TimKiemActionPerformed
            
        //index=0 --> Tìm Kiếm theo mã HS
        //index=1 --> tìm kiếm theo tên HS
        ArrayList<HocSinh> listTKHS = new ArrayList<>();
        
        if(BienToanCuc_QLHS.luaChonTimKiem==0){
            for(HocSinh hs: listHS){
                if(hs.getMaHS().equals(txt_TimKiem.getText())){
                    listTKHS.add(hs);
                    break;
                }
                    
            }
        } else if(BienToanCuc_QLHS.luaChonTimKiem==1){
            for(HocSinh hs: listHS){
                if(hs.getHoTen().equalsIgnoreCase(txt_TimKiem.getText())){
                    listTKHS.add(hs);
                }
            }
        }  
        
        //Xóa dữ liệu có hiện tại trên JTable
        model.setRowCount(0);
        JOptionPane.showMessageDialog(rootPane, "Tìm thấy " + listTKHS.size() + " kết quả!");  
        showTable(listTKHS);
    }//GEN-LAST:event_btn_TimKiemActionPerformed

    private void cbb_TimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbb_TimKiemActionPerformed
        BienToanCuc_QLHS.luaChonTimKiem = cbb_TimKiem.getSelectedIndex();
    }//GEN-LAST:event_cbb_TimKiemActionPerformed
    //Bắt lỗi khi điền ngày sinh trong TextField
    private void txt_NgaySinhFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_NgaySinhFocusLost
//        if(this.isVisible()){
//            String str = txt_NgaySinh.getText();
//            String dinhDang = "^((0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/(19|2[0-9])[0-9]{2})$";
//
//            if(str.length()>0){
//                if(! str.matches(dinhDang)){
//                    JOptionPane.showMessageDialog(null, "Sai định dạng ngày!");
//                    txt_NgaySinh.requestFocus();
//                }
//            } else {
//                JOptionPane.showMessageDialog(null, "Ngày sinh không được để trống!");
//                txt_NgaySinh.requestFocus();
//            }
//        }
    }//GEN-LAST:event_txt_NgaySinhFocusLost

    private void txt_HoTenFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_HoTenFocusLost
//        if(this.isVisible()){
//            String str = txt_HoTen.getText().trim();
//            if(!(str.length()>0)){
//                JOptionPane.showMessageDialog(null, "Họ tên không được để trống!");
//                txt_HoTen.requestFocus();
//            }
//        }
    }//GEN-LAST:event_txt_HoTenFocusLost

    private void txt_DanTocFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_DanTocFocusLost
//        if(this.isVisible()){
//            String str = txt_DanToc.getText();
//            if(!(str.length()>0)){
//                JOptionPane.showMessageDialog(null, "Dân tộc không được để trống!");
//                txt_DanToc.requestFocus();
//            }
//        }
    }//GEN-LAST:event_txt_DanTocFocusLost

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
            java.util.logging.Logger.getLogger(QL_HocSinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QL_HocSinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QL_HocSinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QL_HocSinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QL_HocSinh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_CapNhat;
    private javax.swing.ButtonGroup btn_Group;
    private javax.swing.JButton btn_LamMoi;
    private javax.swing.JRadioButton btn_Nam;
    private javax.swing.JRadioButton btn_Nu;
    private javax.swing.JButton btn_ThemHocSinh;
    private javax.swing.JButton btn_ThongTinPH;
    private javax.swing.JButton btn_TimKiem;
    private javax.swing.JButton btn_Xoa;
    private javax.swing.JComboBox<String> cbb_TimKiem;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbl_HinhAnh;
    private javax.swing.JTable tbl_Hocsinh;
    private javax.swing.JTextField txt_DanToc;
    private javax.swing.JTextField txt_HoTen;
    private javax.swing.JTextField txt_MaHS;
    private javax.swing.JTextField txt_NgaySinh;
    private javax.swing.JTextField txt_TimKiem;
    // End of variables declaration//GEN-END:variables
}
