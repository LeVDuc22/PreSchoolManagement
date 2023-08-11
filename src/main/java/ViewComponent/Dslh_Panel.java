package ViewComponent;

import Controller.NienKhoaController;
import Controller.QldsLopHocController;
import Models.Contants;
import Models.LopHoc;
import Models.NienKhoa;
import Ultity.RegExpInputVerifier;
import ViewModels.DeleteModel;
import ViewModels.FilterModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Dslh_Panel {
    private JLabel NienKhoaLabel = new JLabel("Năm học");
    private ArrayList<NienKhoa> lisNienKhoa = new ArrayList<>();
    private  HashMap<String,String> comboxList = new HashMap<>();
    String[] fruits = {"Apple", "Banana", "Orange", "Mango", "Pineapple"};
    JComboBox<String> list;
    public JTextField TenLopHoc = new JTextField();
    private JTextField SiSoLop = new JTextField();
    private JTextField HocPhi = new JTextField();
    private JLabel TenLopHocLabel = new JLabel("Tên lớp học");
    private JLabel SiSoLopLabel = new JLabel("Sĩ số");
    private JLabel HocPhiLabel = new JLabel("Học phí");
    private Button XoaBtn = new Button("Xóa");
    private Button CapNhatBtn = new Button("Cập nhật");
    private Button LamMoiBtn = new Button("Làm mới");
    public  Button ThemButton = new Button("Thêm");
    private JLabel emtpy = new JLabel();
    private JTable table ;
    private JPanel nienKhoaPanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JLabel TenLopHocError = new JLabel();
    private JLabel SiSoError = new JLabel();
    private JLabel HocPhiError = new JLabel();
    private JSplitPane jSplitPane = new JSplitPane();
    private JPanel rightPanel = new JPanel();
    private JTextField searchField = new JTextField();
    private JButton searchNBtn = new JButton("Tìm kiếm");
    private HashMap<Integer, Boolean> Flag = new HashMap<>();
    private String Id;
    private String MaNienKhoa;
    private ArrayList<LopHoc> lophocData = new ArrayList<>();
    public int RowSeleted;
    private LopHoc lopHoc = new LopHoc();
    public Dslh_Panel() {
        Flag.put(1, false);
        Flag.put(2, false);
        Flag.put(3, false);
        lisNienKhoa = NienKhoaController.GetAll();
        if(!lisNienKhoa.isEmpty()){
            MaNienKhoa = lisNienKhoa.get(0).getMa();
            lophocData = QldsLopHocController.getLopHocByNienKhoaId(new FilterModel("MaNienKhoa",MaNienKhoa));
        }

        String[] nienKhoas = new String[lisNienKhoa.size()];
        int i=0;
        for (var item: lisNienKhoa
             ) {
            comboxList.put(item.getNamHoc().trim(),item.getMa());
            nienKhoas[i] = item.getNamHoc();
            i++;
        }
        list = new JComboBox<>(nienKhoas);
        leftPanel.setBounds(0,0,400,600);
        leftPanel.setLayout(new BorderLayout());
        NienKhoaLabel.setBounds(15,25,90,35);
        list.setBounds(120,25,252,35);
        TenLopHocLabel.setBounds(15,85,90,35);
        TenLopHoc.setBounds(120,85,252,35);
        TenLopHocError.setBounds(120,125,252,20);
        SiSoLopLabel.setBounds(15,150,90,35);
        SiSoLop.setBounds(120,150,75,35);
        HocPhiLabel.setBounds(220,150,70,35);
        HocPhi.setBounds(295,150,75,35);
        SiSoError.setBounds(120,190,252,20);
        HocPhiError.setBounds(120,215,252,20);
        ThemButton.setBounds(15,250,60,35);
        CapNhatBtn.setBounds(112,250,60,35);
        XoaBtn.setBounds(209,250,60,35);
        LamMoiBtn.setBounds(304,250,60,35);
        var model = new DefaultTableModel();
        model.addColumn("Mã");
        model.addColumn("Tên lớp");
        model.addColumn("Sĩ số");
        model.addColumn("Học Phí");
        model.addColumn("Mã Niên khóa");
        addData(model,lophocData);
//        String[] columnNames = {"Tên lớp học", "Sĩ số lớp", "Học Phí","Niên khóa"};
//        String[][] data = {{"Lớp A","40","10000000","2012-2013"}};
//        var model = new DefaultTableModel(data,columnNames);
        table = new JTable(model);
        var scrollPane = new JScrollPane(table);
        TenLopHocError.setForeground(Color.RED);
        SiSoError.setForeground(Color.RED);
        HocPhiError.setForeground(Color.RED);
        leftPanel.add(NienKhoaLabel);
        leftPanel.add(list);
        leftPanel.add(TenLopHocLabel);
        leftPanel.add(TenLopHoc);
        leftPanel.add(SiSoLopLabel);
        leftPanel.add(SiSoLop);
        leftPanel.add(HocPhiLabel);
        leftPanel.add(HocPhi);
        leftPanel.add(ThemButton);
        leftPanel.add(CapNhatBtn);
        leftPanel.add(XoaBtn);
        leftPanel.add(LamMoiBtn);
        leftPanel.add(TenLopHocError);
        leftPanel.add(SiSoError);
        leftPanel.add(HocPhiError);
        leftPanel.add(emtpy);
        rightPanel.setLayout(new BorderLayout());
        searchNBtn.setBounds(300,25,100,35);
        searchField.setBounds(15,25,250,35);
        rightPanel.add(searchField);
        scrollPane.setBounds(15,75,385,400);
        rightPanel.add(searchNBtn);
        rightPanel.add(scrollPane);
        rightPanel.add(emtpy);
        jSplitPane.setResizeWeight(0.5);
        jSplitPane.setLeftComponent(leftPanel);
        jSplitPane.setRightComponent(rightPanel);
        ThemButton.setEnabled(false);
        XoaBtn.setEnabled(false);
        CapNhatBtn.setEnabled(false);
        addEventListenner();
    }
    private void addData(DefaultTableModel model, ArrayList<LopHoc> data) {
        for (var item : data){

            model.addRow(new Object[]{item.getMa(),item.getTenLopHoc(),item.getSiSoLop(),item.getHocPhi(),item.getMaNienKhoa()});
        }
    }
    private void refreshData(DefaultTableModel model, ArrayList<LopHoc> data) {
        model.setRowCount(0);
        for (var item : data){

            model.addRow(new Object[]{item.getMa(),item.getTenLopHoc(),item.getSiSoLop(),item.getHocPhi(),item.getMaNienKhoa()});
        }
    }
    public  void addEventListenner(){
        CapNhatBtn.addActionListener(new ActionListener() {
                                         @Override
                                         public void actionPerformed(ActionEvent e) {
                                             try {
                                                 lopHoc.setTenLopHoc(TenLopHoc.getText().trim());
                                                 lopHoc.setSiSoLop(Integer.parseInt(SiSoLop.getText().trim()));
                                                 lopHoc.setHocPhi(Float.parseFloat(HocPhi.getText().trim()));
                                                 var result = QldsLopHocController.Update(lopHoc);
                                                 if (result) {
                                                     JOptionPane.showMessageDialog(null, "Cập nhật niên khóa thành công!");
                                                     DefaultTableModel model = (DefaultTableModel) table.getModel();
                                                     model.setValueAt(lopHoc.getTenLopHoc(), RowSeleted, 1);
                                                     model.setValueAt(lopHoc.getSiSoLop(), RowSeleted, 2);
                                                     model.setValueAt(lopHoc.getHocPhi(), RowSeleted, 3);
                                                     TenLopHoc.setText("");
                                                     HocPhi.setText("");
                                                     SiSoLop.setText("");
                                                     RowSeleted = -1;
                                                     XoaBtn.setEnabled(false);
                                                     CapNhatBtn.setEnabled(false);
                                                 }
                                             } catch (Exception ex) {
                                                 System.out.println(ex);
                                             }

                                         }
        });
        XoaBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    var result = QldsLopHocController.Delete(new DeleteModel("Ma",lopHoc.getMa()));
                    if (result) {
                        JOptionPane.showMessageDialog(null, "Xóa Lớp học thành công!");
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.removeRow(RowSeleted);
                        RowSeleted = -1;
                        TenLopHoc.setText("");
                        TenLopHoc.setText("");
                        TenLopHoc.setText("");
                        XoaBtn.setEnabled(false);
                        CapNhatBtn.setEnabled(false);
                    }

                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        ThemButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    var ma = QldsLopHocController.setMa();
                    var lopHoc = new LopHoc(ma,TenLopHoc.getText().toString(),Integer.parseInt(SiSoLop.getText()),Float.parseFloat(HocPhi.getText()),MaNienKhoa);
                    var result = QldsLopHocController.AddLopHoc(lopHoc);
                    if(result){
                        JOptionPane.showMessageDialog(null, "Thêm mới niên khóa thành công!");
                        TenLopHoc.setText("");
                        SiSoLop.setText("");
                        HocPhi.setText("");
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.addRow(new Object[]{lopHoc.getMa(),lopHoc.getTenLopHoc(),lopHoc.getSiSoLop(),lopHoc.getHocPhi()});
                    }
                }
                catch (Exception ex){
                    System.out.println(ex);
                }

            }
        });
        TenLopHoc.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(!TenLopHoc.getText().isEmpty()){
                    var validate = new RegExpInputVerifier(Contants.TenLopHocRegex);
                    if(!validate.verify(TenLopHoc.getText())){
                        TenLopHocError.setText("Tên lớp học chỉ gồm chữ và số, nằm trong khoảng (3,50)");
                        Flag.put(1,false);
                    }
                    else{
                        Flag.put(1,true);
                        TenLopHocError.setText("");
                    }

                }
                else {
                    Flag.put(1,false);
                }
                if (!Flag.containsValue(false)) {
                    ThemButton.setEnabled(true);

                } else {
                    ThemButton.setEnabled(false);

                }
            }
        });
        LamMoiBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TenLopHoc.setText("");
                SiSoError.setText("");
                SiSoLop.setText("");
                TenLopHocError.setText("");
                HocPhi.setText("");
                HocPhiError.setText("");
                Id="";
                ThemButton.setEnabled(false);
                XoaBtn.setEnabled(false);
                CapNhatBtn.setEnabled(false);
            }
        });
        SiSoLop.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(!SiSoLop.getText().isEmpty()){
                    var validate = new RegExpInputVerifier(Contants.SiSoLopRegex);
                    if(!validate.verify(SiSoLop.getText())){
                        SiSoError.setText("Lớp học có số lượng 10 đến 50 HS");
                        Flag.put(2,false);
                    }
                    else{
                        Flag.put(2,true);
                        SiSoError.setText("");
                    }

                }
                else {
                    Flag.put(2,false);
                }
                if (!Flag.containsValue(false)) {
                    ThemButton.setEnabled(true);

                } else {
                    ThemButton.setEnabled(false);

                }
            }
        });
        HocPhi.addKeyListener(new KeyAdapter(){

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(!HocPhi.getText().isEmpty()){
                    var validate = new RegExpInputVerifier(Contants.HocPhiRegex);
                    if(!validate.verify(HocPhi.getText())){
                        HocPhiError.setText("Học phí là 1 số nguyên dương hoặc số thực dương");
                        Flag.put(3,false);
                    }
                    else{
                        Flag.put(3,true);
                        HocPhiError.setText("");
                    }

                }
                else {
                    Flag.put(3,false);
                }
                if (!Flag.containsValue(false)) {
                    ThemButton.setEnabled(true);

                } else {
                    ThemButton.setEnabled(false);

                }
            }
        });
        list.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Get the selected item
                String selectedItem = (String) e.getItem();

                MaNienKhoa = comboxList.get(selectedItem).trim();
                FilterModel filterModel = new FilterModel("MaNienKhoa",MaNienKhoa);
                lophocData = QldsLopHocController.getLopHocByNienKhoaId(filterModel);
                refreshData((DefaultTableModel) table.getModel(),lophocData);
            }
        });
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                int row = table.rowAtPoint(e.getPoint());
                lopHoc.setMa(table.getValueAt(row, 0).toString().trim());
                TenLopHoc.setText(table.getValueAt(row, 1).toString().trim());
                SiSoLop.setText(table.getValueAt(row, 2).toString().trim());
                HocPhi.setText(table.getValueAt(row, 2).toString().trim());

                if(!lopHoc.getMa().isEmpty()){
                    CapNhatBtn.setEnabled(true);
                    XoaBtn.setEnabled(true);
                    ThemButton.setEnabled(false);
                }
                RowSeleted = row;
            }
        });
    }


    public JSplitPane getjSplitPane() {
        return jSplitPane;
    }

    public void setjSplitPane(JSplitPane jSplitPane) {
        this.jSplitPane = jSplitPane;
    }
}
