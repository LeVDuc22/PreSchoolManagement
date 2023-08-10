package ViewComponent;

import Models.Contants;
import Ultity.RegExpInputVerifier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class Dslh_Panel {
    private JLabel NienKhoaLabel = new JLabel("Năm học");

    String[] fruits = {"Apple", "Banana", "Orange", "Mango", "Pineapple"};
    JComboBox<String> list = new JComboBox<>(fruits);
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

    public Dslh_Panel() {
        Flag.put(1, false);
        Flag.put(2, false);
        Flag.put(3, false);
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
        String[] columnNames = {"Tên lớp học", "Sĩ số lớp", "Học Phí","Niên khóa"};
        String[][] data = {{"Lớp A","40","10000000","2012-2013"}};
        var model = new DefaultTableModel(data,columnNames);
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
    public  void addEventListenner(){
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
                    CapNhatBtn.setEnabled(true);
                    XoaBtn.setEnabled(true);
                } else {
                    ThemButton.setEnabled(false);
                    CapNhatBtn.setEnabled(false);
                    XoaBtn.setEnabled(false);
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
                    CapNhatBtn.setEnabled(true);
                    XoaBtn.setEnabled(true);
                } else {
                    ThemButton.setEnabled(false);
                    CapNhatBtn.setEnabled(false);
                    XoaBtn.setEnabled(false);
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
                    CapNhatBtn.setEnabled(true);
                    XoaBtn.setEnabled(true);
                } else {
                    ThemButton.setEnabled(false);
                    CapNhatBtn.setEnabled(false);
                    XoaBtn.setEnabled(false);
                }
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
