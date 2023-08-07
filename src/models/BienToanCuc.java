/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.Controller_NguoiDung;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class BienToanCuc {
    //Phụ huynh
    public static NguoiDung phuhuynh;
    public static String maND;
    public static int luaChonPH;
    public static ArrayList<NguoiDung> listPH = new Controller_NguoiDung().getListNguoiDung();

    public BienToanCuc() {
    }

//    public static NguoiDung getPhuhuynh() {
//        return phuhuynh;
//    }
//
//    public static void setPhuhuynh(NguoiDung phuhuynh) {
//        BienToanCuc.phuhuynh = phuhuynh;
//    }
//
//    public static String getMaND() {
//        return maND;
//    }
//
//    public static void setMaND(String maND) {
//        BienToanCuc.maND = maND;
//    }
//     public static int getLuaChonPH() {
//        return luaChonPH;
//    }
//
//    public static void setLuaChonPH(int luaChonPH) {
//        BienToanCuc.luaChonPH = luaChonPH;
//    }
//
//    public static ArrayList<NguoiDung> getListPH() {
//        return listPH;
//    }
//
//    public static void setListPH(ArrayList<NguoiDung> listPH) {
//        BienToanCuc.listPH = listPH;
//    }
//    
//    public static void addPHForListPH(NguoiDung ph){
//        listPH.add(ph);
//    }
    
    public static void resetBTC(){
        phuhuynh=null;
        maND="";
        luaChonPH=0;
        //tải lại dữ liệu mới nhất lên listPH
        listPH = new Controller_NguoiDung().getListNguoiDung();
    }
}
