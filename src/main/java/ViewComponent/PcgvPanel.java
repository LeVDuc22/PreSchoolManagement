package ViewComponent;

import Controller.NienKhoaController;
import Controller.PcgvController;
import Controller.QldsLopHocController;
import Models.LopHoc;
import Models.NienKhoa;
import ViewModels.FilterModel;
import ViewModels.GiaoVienViewModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;

public class PcgvPanel {
    private JLabel NienKhoaLabel = new JLabel("Năm học");


    JComboBox<String> listNienKhoaCb;
    private JLabel TenLopHocLabel = new JLabel("Lớp học");
    JComboBox<String> listLopHocCb;
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
    private ArrayList<NienKhoa> lisNienKhoa = new ArrayList<>();
    private ArrayList<LopHoc> listLopHoc = new ArrayList<>();
    private String MaNienKhoa;
    private String MaLopHoc;
    private HashMap<String,String> comboxListNk = new HashMap<>();
    private HashMap<String,String> comboxListLh = new HashMap<>();
    private ArrayList<GiaoVienViewModel> giaoVienData = new ArrayList<>();
    public PcgvPanel() {

        lisNienKhoa = NienKhoaController.GetAll();
        if(!lisNienKhoa.isEmpty()){
            MaNienKhoa = lisNienKhoa.get(0).getMa();
            listLopHoc = QldsLopHocController.getLopHocByNienKhoaId(new FilterModel("MaNienKhoa",MaNienKhoa));
        }
        giaoVienData = PcgvController.GetAll(MaNienKhoa);
        String[] nienKhoas = new String[lisNienKhoa.size()];
        int i=0;
        for (var item: lisNienKhoa
        ) {
            comboxListNk.put(item.getNamHoc().trim(),item.getMa());
            nienKhoas[i] = item.getNamHoc();
            i++;
        }

        if(!listLopHoc.isEmpty()){
            String[] LopHoc = new String[listLopHoc.size()];
            i=0;
            for (var item:listLopHoc
            ) {
                comboxListLh.put(item.getTenLopHoc().trim(),item.getMa());
                LopHoc[i] = item.getTenLopHoc();
                i++;
            }
            listLopHocCb = new JComboBox<>(LopHoc);
        }
        else{
            listLopHocCb = new JComboBox<>();
        }

        listNienKhoaCb = new JComboBox<>(nienKhoas);
        leftPanel.setBounds(0,0,400,600);
        leftPanel.setLayout(new BorderLayout());
        NienKhoaLabel.setBounds(15,25,90,35);
        listNienKhoaCb.setBounds(120,25,252,35);
        TenLopHocLabel.setBounds(15,70,90,35);
        listLopHocCb.setBounds(120,70,252,35);
        ThemButton.setBounds(80,435,80,35);
        XoaBtn.setBounds(240,435,80,35);
        String[] columnNames = {"Mã giáo viên","Giáo viên"};
        String[][] data = {{"Lớp A","Lê Văn A"}};
        var modelLopHoc = new DefaultTableModel(data,columnNames);
        tableLopHoc = new JTable(modelLopHoc);
        var scrollPaneLeft = new JScrollPane(tableLopHoc);
        scrollPaneLeft.setBounds(15,120,345,300);
        leftPanel.add(NienKhoaLabel);
        leftPanel.add(listNienKhoaCb);
        leftPanel.add(TenLopHocLabel);
        leftPanel.add(listLopHocCb);
        leftPanel.add(ThemButton);
        leftPanel.add(XoaBtn);
        leftPanel.add(scrollPaneLeft);
        leftPanel.add(emtpy);
        rightPanel.setLayout(new BorderLayout());
        var model = new DefaultTableModel();
        model.addColumn("Mã Giáo viên");
        model.addColumn("Tên giáo viên");
        model.addColumn("Lớp phụ trách");
        String[] columnGVNames = {"Mã giáo viên","Giáo viên","Lớp phụ trách"};
        addData(model,giaoVienData);

        tableGiaoVien = new JTable(model);
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
        addEventListenner();
    }
    private void refreshData(DefaultTableModel model, ArrayList<GiaoVienViewModel> data) {
        model.setRowCount(0);
        for (var item : data){

            model.addRow(new Object[]{item.getMa(),item.getHoTen(),item.getLopPhuTrach()});
        }
    }
    private void addData(DefaultTableModel model, ArrayList<GiaoVienViewModel> data) {
        for (var item : data){

            model.addRow(new Object[]{item.getMa(),item.getHoTen(),item.getLopPhuTrach()});
        }
    }
    private void addEventListenner() {
        listNienKhoaCb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Get the selected item
                String selectedItem = (String) e.getItem();

                MaNienKhoa = comboxListNk.get(selectedItem).trim();
                FilterModel filterModel = new FilterModel("MaNienKhoa",MaNienKhoa);
                listLopHoc = QldsLopHocController.getLopHocByNienKhoaId(filterModel);
                DefaultComboBoxModel model = new DefaultComboBoxModel();
                comboxListLh.clear();
                for (var item : listLopHoc){
                    model.addElement(item.getTenLopHoc());
                    comboxListLh.put(item.getTenLopHoc(),item.getMa());
                }
                listLopHocCb.setModel(model);
                if(PcgvController.CheckNienKhoaExit(MaNienKhoa)){
                    giaoVienData = PcgvController.GetAll(MaNienKhoa);
                    refreshData((DefaultTableModel) tableGiaoVien.getModel(),giaoVienData);
                }
                DefaultTableModel modelTest = (DefaultTableModel) tableGiaoVien.getModel();
                modelTest.setRowCount(0);


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
