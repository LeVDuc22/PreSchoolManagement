/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Models.NguoiDung;
import ViewComponent.MenuItem;
import ViewModels.MenuEnum;
import ViewModels.MenuItemEnum;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.JComponent;

/**
 *
 * @author My laptop
 */
public class MainFrame extends javax.swing.JFrame {

    private NguoiDung _user;
    private HashMap<MenuEnum, MenuItem> menus = new HashMap<>();
    private JMenuBar menuBar = new JMenuBar();

    public MainFrame(NguoiDung user) {
        setSize(900, 650);
        _user = user;
        var menuQltk = new MenuItem(new JMenu("Tai Khoan"));
        menuQltk.addJMenuItem(MenuItemEnum.DOIMATKHAU, new JMenuItem("Doi Mat Khau"));
        menuQltk.addJMenuItem(MenuItemEnum.DANGXUAT, new JMenuItem("Dang xuat"));
        menuQltk.addItemToMenu();
        var menuQldt = new MenuItem(new JMenu("Quan Ly Chuc Nang"));
        menuQldt.addJMenuItem(MenuItemEnum.QLDT, new JMenuItem("Quan ly dao tao"));
       
        menuQldt.addItemToMenu();
        menus.put(MenuEnum.QLCN, menuQldt);
        menus.put(MenuEnum.QLTK, menuQltk);
        menuBar.add(menus.get(MenuEnum.QLCN).jMenu);
        menuBar.add(menus.get(MenuEnum.QLTK).jMenu);
        setJMenuBar(menuBar);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setTitle("Trang Chu");
        setVisible(true);
        addEventListenners();

    }

    private void addEventListenners() {
        menus.get(MenuEnum.QLTK).getMenuItems().get(MenuItemEnum.DANGXUAT).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login();
            }
        });
        menus.get(MenuEnum.QLTK).getMenuItems().get(MenuItemEnum.DOIMATKHAU).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangePasswordFrame(_user);
            }
        });
        menus.get(MenuEnum.QLCN).getMenuItems().get(MenuItemEnum.QLDT).addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              new QldtFrame();
            }
        });
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
        var main = new MainFrame(new NguoiDung());
    }
}
