/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import ViewComponent.Dslh_Panel;
import ViewComponent.Panel_NienKhoa;
import ViewComponent.PcgvPanel;

import javax.swing.*;

/**
 *
 * @author My laptop
 */
public class QldtFrame extends JFrame{
    private  JTabbedPane jTabbedPane = new JTabbedPane();

    public QldtFrame() {
         setSize(850, 600);
        jTabbedPane.add("Quản lý niên khóa", (new Panel_NienKhoa()).getjSplitPane());
        jTabbedPane.add("Quản lý danh sách lớp học", (new Dslh_Panel()).getjSplitPane());
        jTabbedPane.add("Phân công giáo viên", (new PcgvPanel()).getjSplitPane());
        add(jTabbedPane);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setTitle("Quản lý đào tạo");
        setVisible(true);
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
        var login = new QldtFrame();
    }

}
