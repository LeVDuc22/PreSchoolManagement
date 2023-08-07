/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewComponent;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author My laptop
 */
public class Pane_NienKhoa {
    private JPanel nienKhoaPanel;
    public  JTextField NamHoc = new JTextField();
    public  Button ThemButton = new Button("Them");

    public Pane_NienKhoa() {
       nienKhoaPanel = new JPanel();
       nienKhoaPanel.add(NamHoc);
       nienKhoaPanel.add(ThemButton);
    }
    public  void addEventListenner(){
         ThemButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                NamHoc.setText("Hello");
            }
        });
    }

    public JPanel getNienKhoaPanel() {
        return nienKhoaPanel;
    }
    
    
    
}
