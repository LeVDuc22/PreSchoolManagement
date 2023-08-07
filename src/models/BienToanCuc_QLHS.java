/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import controller.ConnectDB_PhuHuynh;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class BienToanCuc_QLHS {
    //Phụ huynh
    public static NguoiDung phuhuynh;
    public static String maND;
    public static int luaChonPH;
    public static ArrayList<NguoiDung> listPH = new ConnectDB_PhuHuynh().selectQuery();
    
    //Hoc sinh
    public static HocSinh hocsinh;
    public static boolean luaChonCapNhat;
    public static boolean luaChonXoa;
    public static int luaChonTimKiem;
   

    public BienToanCuc_QLHS() {
    }

    public static NguoiDung getPhuhuynh() {
        return phuhuynh;
    }

    public static void setPhuhuynh(NguoiDung phuhuynh) {
        BienToanCuc_QLHS.phuhuynh = phuhuynh;
    }

    public static String getMaND() {
        return maND;
    }

    public static void setMaND(String maND) {
        BienToanCuc_QLHS.maND = maND;
    }
     public static int getLuaChonPH() {
        return luaChonPH;
    }

    public static void setLuaChonPH(int luaChonPH) {
        BienToanCuc_QLHS.luaChonPH = luaChonPH;
    }

    public static ArrayList<NguoiDung> getListPH() {
        return listPH;
    }

    public static void setListPH(ArrayList<NguoiDung> listPH) {
        BienToanCuc_QLHS.listPH = listPH;
    }
    
    public static void addPHForListPH(NguoiDung ph){
        listPH.add(ph);
    }
    
    public static void resetBTC(){
        phuhuynh=null;
        maND="";
        luaChonPH=0;
        //tải lại dữ liệu mới nhất lên listPH
        listPH = new ConnectDB_PhuHuynh().selectQuery();
        
        hocsinh=null;
        luaChonCapNhat=false;
        luaChonXoa=false;
        luaChonTimKiem = 0;
        
    }
}
