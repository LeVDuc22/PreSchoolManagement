/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import java.awt.AWTEventMulticaster;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author My laptop
 */
public class QldtFrame extends JFrame{

    private JTabbedPane tabbedPane = new JTabbedPane();

    public QldtFrame() {
         setSize(800, 600);
        tabbedPane.add("Quản lý niên khóa", new JPanel());
        tabbedPane.add("Quản lý danh sách lớp học", new JPanel());
        tabbedPane.add("Phân công giáo viên", new JPanel());
        tabbedPane.add("Phân công học sinh", new JPanel());
        add(tabbedPane);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setTitle("Quản lý đào tạo");
        setVisible(true);
    }

}
