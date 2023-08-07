/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Models.NguoiDung;
import Service.DbContextService;
import ViewModels.Account;
import ViewModels.FilterModel;
import java.util.ArrayList;

/**
 *
 * @author My laptop
 */
public class AccountController {

    private static String tableName = "NguoiDung";

    public static NguoiDung getNguoiDung(Account account) {
        try {
            DbContextService service = new DbContextService();
            var result = service.GetDataByModel(tableName, account);
            var nguoiDung = new ArrayList<NguoiDung>();
            while (result.next()) {
                var value = new NguoiDung(
                        result.getString(1), result.getString(2), result.getString(3),
                        result.getString(4), result.getString(5), result.getDate(6),
                        result.getBoolean(7), result.getString(8), result.getString(9),
                        result.getString(10), result.getString(11), result.getString(12));
                nguoiDung.add(value);
            }
            if (nguoiDung.isEmpty()) {
                return null;
            }
            service.closeDbConext();
            return nguoiDung.get(0);
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean UsernameVerified(Account account) {
        try {
            DbContextService service = new DbContextService();
            var filterList = new ArrayList<FilterModel>();
            filterList.add(new FilterModel("TenDangNhap", account.getTenDangNhap()));
            var value = service.filterMultiOneObject(tableName, filterList);
            service.closeDbConext();
            return value;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean PasswordVerified(Account account) {
        try {
            DbContextService service = new DbContextService();
            var filterList = new ArrayList<FilterModel>();
            filterList.add(new FilterModel("TenDangNhap", account.getTenDangNhap()));
            filterList.add(new FilterModel("MatKhau", account.getMatKhau()));
            var value = service.filterMultiOneObject(tableName, filterList);
            service.closeDbConext();
            return value;
        } catch (Exception e) {
            return false;
        }
    }
    public static boolean ChangePassword(NguoiDung nguoiDung){
         try {
            DbContextService service = new DbContextService();
            var value = service.update(tableName,nguoiDung);
            service.closeDbConext();
            return value != -1;
        } catch (Exception e) {
            return false;
        }
    }
}
