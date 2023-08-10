/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewComponent;

import Controller.NienKhoaController;
import Models.Contants;
import Models.NienKhoa;
import Ultity.RegExpInputVerifier;
import View.Login;
import ViewModels.DeleteModel;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseMotionListener;

/**
 *
 * @author My laptop
 */
public class Panel_NienKhoa {
    public ArrayList<NienKhoa> data;
    public JTextField NamHoc = new JTextField();
    private JTextField NgayBatDau = new JTextField();
    private JTextField NgayKetThuc = new JTextField();
    private JLabel NamHocLabel = new JLabel("Năm học");
    private JLabel StartDateLabel = new JLabel("Ngày bắt đầu");
    private JLabel DueDateLabel = new JLabel("Ngày kết thúc");
    private Button XoaBtn = new Button("Xóa");
    private Button CapNhatBtn = new Button("Cập nhật");
    private Button LamMoiBtn = new Button("Làm mới");
    public  Button ThemButton = new Button("Thêm");
    private JLabel emtpy = new JLabel();
    private JTable table ;
    private JPanel nienKhoaPanel = new JPanel();
    private JPanel leftPanel = new JPanel();
    private JLabel nienKhoaError = new JLabel();
    private JLabel startDateError = new JLabel();
    private JLabel dueDateError = new JLabel();
    private JSplitPane jSplitPane = new JSplitPane();
    private JPanel rightPanel = new JPanel();
    private JTextField searchField = new JTextField();
    private JButton searchNBtn = new JButton("Tìm kiếm");
    private HashMap<Integer, Boolean> Flag = new HashMap<>();
    private NienKhoa nienKhoa = new NienKhoa();
    public JSplitPane getjSplitPane() {
        return jSplitPane;
    }
    public int RowSeleted;
    public void setjSplitPane(JSplitPane jSplitPane) {
        this.jSplitPane = jSplitPane;
    }

    public void setNienKhoaPanel(JPanel nienKhoaPanel) {
        this.nienKhoaPanel = nienKhoaPanel;
    }

    public Panel_NienKhoa() {
        data = NienKhoaController.GetAll();
        Flag.put(1, false);
        Flag.put(2, false);
        Flag.put(3, false);
        leftPanel.setBounds(0,0,400,600);
        leftPanel.setLayout(new BorderLayout());
        nienKhoaPanel.setLayout(new BoxLayout(nienKhoaPanel, BoxLayout.LINE_AXIS));
        NamHocLabel.setBounds(15,25,90,35);
        NamHoc.setBounds(120,25,252,35);
        nienKhoaError.setBounds(120, 65, 252, 20);
        StartDateLabel.setBounds(15,90,90,35);
        NgayBatDau.setBounds(120,90,252,35);
        startDateError.setBounds(120, 130, 252, 20);
        DueDateLabel.setBounds(15,155,90,35);
        NgayKetThuc.setBounds(120,155,252,35);
        dueDateError.setBounds(120,190,252,20);
        ThemButton.setBounds(15,250,60,35);
        CapNhatBtn.setBounds(112,250,60,35);
        XoaBtn.setBounds(209,250,60,35);
        LamMoiBtn.setBounds(304,250,60,35);
        String[] columnNames = {"Năm học", "Ngày bắt đầu", "Ngày kết thúc"};
        var model = new DefaultTableModel();
        model.addColumn("Mã");
        model.addColumn("Niên khóa");
        model.addColumn("Ngày bắt đầu");
        model.addColumn("Ngày kết thúc");
        addData(model,data);
        table = new JTable(model);
        var scrollPane = new JScrollPane(table);
        nienKhoaError.setForeground(Color.RED);
        startDateError.setForeground(Color.RED);
        dueDateError.setForeground(Color.RED);
        leftPanel.add(NamHocLabel);
        leftPanel.add(NamHoc);
        leftPanel.add(StartDateLabel);
        leftPanel.add(NgayBatDau);
        leftPanel.add(DueDateLabel);
        leftPanel.add(NgayKetThuc);
        leftPanel.add(ThemButton);
        leftPanel.add(CapNhatBtn);
        leftPanel.add(XoaBtn);
        leftPanel.add(LamMoiBtn);
        leftPanel.add(nienKhoaError);
        leftPanel.add(startDateError);
        leftPanel.add(dueDateError);
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

    private void addData(DefaultTableModel model, ArrayList<NienKhoa> data) {
        for (var item : data){
            var ngayBatDau = reverseTime(item.getNgayBatDau());
            var ngayKetThuc = reverseTime(item.getNgayKetThuc());
            model.addRow(new Object[]{item.getMa(),item.getNamHoc(),ngayBatDau,ngayKetThuc});
        }
    }
    private String reverseTime(String time){
        StringTokenizer tokenizer = new StringTokenizer(time, "-");
        String year = tokenizer.nextToken();
        String month = tokenizer.nextToken();
        String day = tokenizer.nextToken();
        return day + "-" + month + "-" + year;
    }

    public  void addEventListenner(){

        LamMoiBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NamHoc.setText("");
                NgayBatDau.setText("");
                NgayKetThuc.setText("");
                nienKhoaError.setText("");
                startDateError.setText("");
                dueDateError.setText("");
                XoaBtn.setEnabled(false);
                ThemButton.setEnabled(false);
                CapNhatBtn.setEnabled(false);
                nienKhoa.setMa("");
            }
        });
        XoaBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    var result = NienKhoaController.Delete(new DeleteModel("Ma", nienKhoa.getMa()));
                    if (result) {
                        JOptionPane.showMessageDialog(null, "Xóa niên khóa thành công!");
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.removeRow(RowSeleted);
                        NamHoc.setText("");
                        NgayBatDau.setText("");
                        NgayKetThuc.setText("");
                        XoaBtn.setEnabled(false);
                        CapNhatBtn.setEnabled(false);
                    }

                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });
        CapNhatBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    nienKhoa.setNamHoc(NamHoc.getText());
                    nienKhoa.setNgayBatDau(NgayBatDau.getText());
                    nienKhoa.setNgayKetThuc(NgayKetThuc.getText());
                    var result = NienKhoaController.Update(nienKhoa);
                    if(result){
                        JOptionPane.showMessageDialog(null, "Cập nhật niên khóa thành công!");
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        model.setValueAt(nienKhoa.getNamHoc(), RowSeleted, 1);
                        model.setValueAt(nienKhoa.getNgayBatDau(), RowSeleted, 2);
                        model.setValueAt(nienKhoa.getNgayKetThuc(), RowSeleted, 3);
                        NamHoc.setText("");
                        NgayBatDau.setText("");
                        NgayKetThuc.setText("");
                        XoaBtn.setEnabled(false);
                        CapNhatBtn.setEnabled(false);
                    }
                }
                catch (Exception ex){
                    System.out.println(ex);
                }

            }


        });
         ThemButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    var nienkhoa = new NienKhoa(NamHoc.getText().toString(),NgayBatDau.getText().toString(),NgayKetThuc.getText().toString());
                    nienkhoa.setMa(NienKhoaController.setMa());
                    var result = NienKhoaController.AddNienKhoa(nienkhoa);
                    if(result){
                        JOptionPane.showMessageDialog(null, "Thêm mới niên khóa thành công!");
                        NamHoc.setText("");
                        NgayBatDau.setText("");
                        NgayKetThuc.setText("");
                    }
                }
                catch (Exception ex){
                    System.out.println(ex);
                }

            }
        });
        NamHoc.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(!NamHoc.getText().isEmpty()){
                    var validate = new RegExpInputVerifier(Contants.NienKhoaRegex);
                    if(!validate.verify(NamHoc.getText())){
                        nienKhoaError.setText("Năm học có định dạng: dddd-YYYY");
                        Flag.put(1,false);
                    }
                    else{
                        Flag.put(1,true);
                        nienKhoaError.setText("");
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
        NgayBatDau.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(!NgayBatDau.getText().isEmpty()){
                    var validate = new RegExpInputVerifier(Contants.DateRegex);
                    if(!validate.verify(NgayBatDau.getText())){
                        startDateError.setText("Ngày bắt đầu có định dạng: dd-mm-yyyy");
                        Flag.put(2,false);
                    }
                    else{
                        Flag.put(2,true);
                        startDateError.setText("");
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
        NgayKetThuc.addKeyListener(new KeyAdapter(){

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if(!NgayKetThuc.getText().isEmpty()){
                    var validate = new RegExpInputVerifier(Contants.DateRegex);
                    if(!validate.verify(NgayKetThuc.getText())){
                        dueDateError.setText("Ngày kết thúc có định dạng: dd-mm-yyyy");
                        Flag.put(3,false);
                    }
                    else{
                        Flag.put(3,true);
                        dueDateError.setText("");
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
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Get the row index of the clicked row
                int row = table.rowAtPoint(e.getPoint());
                nienKhoa.setMa(table.getValueAt(row, 0).toString().trim());
                NamHoc.setText(table.getValueAt(row, 1).toString().trim());
                NgayBatDau.setText(table.getValueAt(row, 2).toString().trim());
                NgayKetThuc.setText(table.getValueAt(row, 2).toString().trim());
                // Get the data of the clicked row
                if(!nienKhoa.getMa().isEmpty()){
                    CapNhatBtn.setEnabled(true);
                    XoaBtn.setEnabled(true);
                }
                RowSeleted = row;
            }
        });

    }

    public JPanel getNienKhoaPanel() {
        return nienKhoaPanel;
    }
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
//
        var login = new Panel_NienKhoa();
    }
    
    
}
