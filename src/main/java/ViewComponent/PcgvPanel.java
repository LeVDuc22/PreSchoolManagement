package ViewComponent;

import Models.NamHoc;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PcgvPanel {
    private JLabel NienKhoaLabel = new JLabel("Năm học");

    String[] fruits = {"Apple", "Banana", "Orange", "Mango", "Pineapple"};
    JComboBox<String> listNienKhoa = new JComboBox<>(fruits);
    private JLabel TenLopHocLabel = new JLabel("Lớp học");
    JComboBox<String> listLopHoc = new JComboBox<>(fruits);
    private JTable tableLopHoc ;
    private JTable tableGiaoVien;
    private JPanel leftPanel = new JPanel();
    private JPanel rightPanel = new JPanel();

    private JSplitPane jSplitPane = new JSplitPane();
    private  String IdGiaoVien ="";
    private Button XoaBtn = new Button("Xóa");
    public  Button ThemButton = new Button("Thêm");
    private JLabel emtpy = new JLabel();
    private JTextField searchField = new JTextField();
    private JButton searchNBtn = new JButton("Tìm kiếm");
    public PcgvPanel() {
        leftPanel.setBounds(0,0,400,600);
        leftPanel.setLayout(new BorderLayout());
        NienKhoaLabel.setBounds(15,25,90,35);
        listNienKhoa.setBounds(120,25,252,35);
        TenLopHocLabel.setBounds(15,70,90,35);
        listLopHoc.setBounds(120,70,252,35);
        ThemButton.setBounds(80,435,80,35);
        XoaBtn.setBounds(240,435,80,35);
        String[] columnNames = {"Mã giáo viên","Giáo viên"};
        String[][] data = {{"Lớp A","Lê Văn A"}};
        var modelLopHoc = new DefaultTableModel(data,columnNames);
        tableLopHoc = new JTable(modelLopHoc);
        var scrollPaneLeft = new JScrollPane(tableLopHoc);
        scrollPaneLeft.setBounds(15,120,345,300);
        leftPanel.add(NienKhoaLabel);
        leftPanel.add(listNienKhoa);
        leftPanel.add(TenLopHocLabel);
        leftPanel.add(listLopHoc);
        leftPanel.add(ThemButton);
        leftPanel.add(XoaBtn);
        leftPanel.add(scrollPaneLeft);

        leftPanel.add(emtpy);
        rightPanel.setLayout(new BorderLayout());
        String[] columnGVNames = {"Mã giáo viên","Giáo viên","Lớp phụ trách"};
        var modelGv = new DefaultTableModel(data,columnGVNames);
        tableGiaoVien = new JTable(modelGv);
        searchNBtn.setBounds(300,25,100,35);
        searchField.setBounds(15,25,250,35);
        var scrollPaneRight = new JScrollPane(tableGiaoVien);
        scrollPaneRight.setBounds(15,75,385,400);
        rightPanel.add(searchField);
        rightPanel.add(searchNBtn);
        rightPanel.add(scrollPaneRight);
        rightPanel.add(emtpy);
        jSplitPane.setResizeWeight(0.5);
        jSplitPane.setLeftComponent(leftPanel);
        jSplitPane.setRightComponent(rightPanel);
    }

    public JSplitPane getjSplitPane() {
        return jSplitPane;
    }

    public void setjSplitPane(JSplitPane jSplitPane) {
        this.jSplitPane = jSplitPane;
    }
}
