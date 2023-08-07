/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Admin
 */
public class Validation {
    //Hàm tạo Focus Adapter: check xem khi nào focus và nhả focus để ktra txtField
//    public void createFocusLost(JTextField textField){
//        textField.addFocusListener(new java.awt.event.FocusAdapter() {
//            public void focusLost(java.awt.event.FocusEvent evt) {
//                txt_HoTenFocusLost(evt);
//            }
//        });
//    }
    
    //Kiểm tra trống không?
    public static boolean isEmpty(JTextField textField){
        String txtValue = textField.getText().trim();
        if(txtValue.length()==0){
//            lbl.setText(msg);
            textField.setBackground(Color.yellow);
//            textField.requestFocus();
            return true;
        }
        textField.setBackground(Color.white);
        return false;
    }
    //Kiểm tra định dạng email
    public static boolean isEmail(JTextField textField){
        String txtValue = textField.getText().trim();
        //^là bắt đầu, $ là kết thúc; \\w{3,} -> tói thiểu là 3 kí tự, tối đa bao nhiêu cũng được
        //[a-zA-Z]--> bắt đầu bằng chữ cái; \\w bao gồm cả ký tự và số
        //{1,2} lặp 1-2 lần cái group trước đó (()->là group)
        String strPtn = "^(\\w{1,})@(\\w{2,})(.\\w{2,6}){1,2}$";//Regular Expression in java
        if(!txtValue.matches(strPtn)){
//            lbl.setText(msg);
            textField.setBackground(Color.yellow);
            textField.requestFocus();
            return false;
        }
        textField.setBackground(Color.white);
        return true;
    }
    //Kiểm tra có phải CCCD? 13 số
    public static boolean isCCCD(JTextField textField){
        String txtValue = textField.getText().trim();
        //Chú ý: \\d là 1 số từ 0-9 -> \\d{1,} -> ít nhất là 1 số và k giới hạn
        //(\\d){13} -> 13 số
        String strPtn = "^(\\d){13}$";//Regular Expression in java
        if(!txtValue.matches(strPtn)){
//            lbl.setText(msg);
            textField.setBackground(Color.yellow);
            textField.requestFocus();
            return false;
        }
        textField.setBackground(Color.white);
        return true;
    }
    //Kiểm tra định dạng ngày
    public static boolean isDate(JTextField textField){
        String txtValue = textField.getText().trim();
        //Chú ý: \\d là 1 số từ 0-9 -> \\d{1,} -> ít nhất là 1 số và k giới hạn
        //(\\d){13} -> 13 số
        String strPtn = "^((0?[1-9]|[12][0-9]|3[01]|[0-9])/(0?[1-9]|1[012]|[0-9])/(19|2[0-9])[0-9]{2})$";
        if(!txtValue.matches(strPtn)){
//            lbl.setText(msg);
            textField.setBackground(Color.yellow);
            textField.requestFocus();
            return false;
        }
        textField.setBackground(Color.white);
        return true;
    }
    //Kiểm tra định dạng năm
    public static boolean isYear(JTextField textField){
        String txtValue = textField.getText().trim();
        String strPtn = "^(([12][0-9][0-9][0-9]))$";
        if(!txtValue.matches(strPtn)){
            textField.setBackground(Color.yellow);
            textField.requestFocus();
            return false;
        }
        textField.setBackground(Color.white);
        return true;
    }
    //Kiểm tra định dạng SDT
    public static boolean isPhoneNumber(JTextField textField){
        String txtValue = textField.getText().trim();

        String strPtn = "^0(\\d){9}$";
        if(!txtValue.matches(strPtn)){
//            lbl.setText(msg);
            textField.setBackground(Color.yellow);
            textField.requestFocus();
            return false;
        }
        textField.setBackground(Color.white);
        return true;
    }
    //Kiểm tra định dạng mã giáo viên hoặc phụ huynh
    public static boolean isID(JTextField textField, String key){
        String txtValue = textField.getText().trim();

        String strPtn = "^" + key + "(\\d){1,}$";
        if(!txtValue.matches(strPtn)){
            textField.setBackground(Color.yellow);
            textField.requestFocus();
            return false;
        }
        textField.setBackground(Color.white);
        return true;
    }
}
