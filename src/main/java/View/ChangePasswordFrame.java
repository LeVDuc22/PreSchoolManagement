/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.AccountController;
import Models.NguoiDung;
import Ultity.RegExpInputVerifier;
import ViewModels.Account;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.prefs.Preferences;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author My laptop
 */
public class ChangePasswordFrame extends javax.swing.JFrame {

    private NguoiDung _user;
    private JPasswordField oldPassword;
    private JPasswordField newPassword;
    private JPasswordField confirmPassword;
    private JLabel oldPasswordLabel;
    private JLabel newPasswordLabel;
    private Button btnChange;
    private JLabel errorLabel;
    private JLabel confirmPasswordLabel;
    private HashMap<Integer, Boolean> Flag = new HashMap<>();

    public ChangePasswordFrame(NguoiDung user) {
        setSize(550, 350);
        _user = user;
        Flag.put(1, false);
        Flag.put(2, false);
        Flag.put(3, false);
        oldPassword = new JPasswordField();
        newPassword = new JPasswordField();
        confirmPassword = new JPasswordField();
        oldPasswordLabel = new JLabel("Current password");
        newPasswordLabel = new JLabel("New password");
        confirmPasswordLabel = new JLabel("Confirm password ");
        btnChange = new Button("Change password");
        btnChange.setEnabled(false);
        errorLabel = new JLabel();
        oldPasswordLabel.setPreferredSize(new Dimension(120, 35));
        newPasswordLabel.setPreferredSize(new Dimension(120, 35));
        confirmPasswordLabel.setPreferredSize(new Dimension(90, 35));
        oldPassword.setPreferredSize(new Dimension(220, 35));
        newPassword.setPreferredSize(new Dimension(220, 35));
        confirmPassword.setPreferredSize(new Dimension(220, 35));
        btnChange.setPreferredSize(new Dimension(220, 35));
        btnChange.setBackground(new Color(66, 245, 114));
        setLayout(new GridBagLayout());
        Insets textInsets = new Insets(10, 10, 5, 5);
        var buttonInsets = new Insets(10, 10, 10, 5);
        var errorInsets = new Insets(0, 5, 0, 0);
        var input = new GridBagConstraints();
        input.anchor = GridBagConstraints.CENTER;
        input.gridwidth = 2;
        input.gridy = 0;
        input.gridheight = 1;
        errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        errorLabel.setForeground(Color.RED);
        add(errorLabel, input);
        input.gridwidth = 1;
        input.gridheight = 1;
        input.gridy = 1;
        input.gridx = 0;
        input.insets = errorInsets;
        input.anchor = GridBagConstraints.EAST;
        add(oldPasswordLabel, input);
        input.anchor = GridBagConstraints.CENTER;
        input.insets = textInsets;
        input.gridx = 1;
        input.gridy = 1;
        add(oldPassword, input);
        input.gridx = 0;
        input.gridy = 2;
        input.insets = errorInsets;
        input.anchor = GridBagConstraints.EAST;
        add(newPasswordLabel, input);
        input.gridx = 1;
        input.gridy = 2;
        input.insets = textInsets;
        input.anchor = GridBagConstraints.CENTER;
        add(newPassword, input);
        input.anchor = GridBagConstraints.WEST;
        input.gridx = 0;
        input.gridy = 3;
        input.insets = errorInsets;
        add(confirmPasswordLabel, input);
        input.gridx = 1;
        input.gridy = 3;
        input.insets = textInsets;
        input.anchor = GridBagConstraints.CENTER;
        add(confirmPassword, input);
        input.insets = buttonInsets;
        input.anchor = GridBagConstraints.CENTER;
        input.gridx = 1;
        input.gridy = 4;
        add(btnChange, input);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Change the password");
        setVisible(true);
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (!confirmPassword.getText().isEmpty()) {
                    if (!newPassword.getText().equals(confirmPassword.getText())) {
                        Flag.put(3, false);
                        errorLabel.setText("Confirmation password does not match");
                    }

                } else {
                    Flag.put(3, true);
                    errorLabel.setText("");
                }
                if (!Flag.containsValue(false)) {
                    btnChange.setEnabled(true);
                } else {
                    btnChange.setEnabled(false);
                }

            }
        });
        addEventListenners();
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
        new ChangePasswordFrame(new NguoiDung());
    }

    private void addEventListenners() {
        oldPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (oldPassword.getText().isEmpty()) {
                    errorLabel.setText("Please enter your current password");
                    Flag.put(1, false);
                } else if (oldPassword.getText().compareTo(_user.getMatKhau()) != 0) {
                    Flag.put(1, false);
                    errorLabel.setText("Incorrect old password entered");
                } else {
                    errorLabel.setText("");
                    Flag.put(1, true);
                }
                if (!Flag.containsValue(false)) {
                    btnChange.setEnabled(true);
                } else {
                    btnChange.setEnabled(false);
                }
            }
        });
        newPassword.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                if (newPassword.getText().isEmpty()) {
                    errorLabel.setText("Please enter your new password");
                    Flag.put(2, false);
                } else {
                    errorLabel.setText("");
                    Flag.put(2, true);
                }
            }
        });
        confirmPassword.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (confirmPassword.getText().isEmpty()) {
                    errorLabel.setText("Please enter your confirm password");
                    Flag.put(3, false);
                } else if (!newPassword.getText().equals(confirmPassword.getText())) {
                    Flag.put(3, false);
                    errorLabel.setText("Confirmation password does not match");
                } else {
                    errorLabel.setText("");
                    Flag.put(3, true);
                }
                if (!Flag.containsValue(false)) {
                    btnChange.setEnabled(true);
                } else {
                    btnChange.setEnabled(false);
                }
            }
        });
        btnChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _user.setMatKhau(newPassword.getText());
                int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to change password?");
                if (option == JOptionPane.YES_OPTION) {
                    var result = AccountController.ChangePassword(_user);
                    if (result) {
                        JOptionPane.showMessageDialog(null, "Change password successfully!");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Change password failed!");
                    }
                }

            }
        });
    }
}
