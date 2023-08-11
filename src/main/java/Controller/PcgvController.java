package Controller;

import DbContext.DbContextFactory;
import Service.DbContextService;
import ViewModels.GiaoVienViewModel;

import java.util.ArrayList;

public class PcgvController {
   public static ArrayList<GiaoVienViewModel> GetAll(String MaNienKhoa){
      try{
         var _connection = DbContextFactory.CreateConnect();
         var queriesString = "select GiaoVien.Ma,HoTen,ChiTietGiaoVien.MaLopHoc from GiaoVien\n" +
                 "join NguoiDung on NguoiDung.Ma = GiaoVien.MaNguoiDung\n" +
                 "left join ChiTietGiaoVien on ChiTietGiaoVien.MaGiaoVien = GiaoVien.Ma\n" +
                 "where ChiTietGiaoVien.MaLopHoc In (SELECT LopHoc.Ma\n" +
                 "FROM LopHoc WHERE LopHoc.MaNienKhoa = '" + MaNienKhoa + "') or ChiTietGiaoVien.MaLopHoc is null";
         var statement = _connection.createStatement();
         var result = statement.executeQuery(queriesString);
         var giaoVienView = new ArrayList<GiaoVienViewModel>();
         while (result.next()){
            if(result.getObject("MaLopHoc") == null){
               giaoVienView.add(new GiaoVienViewModel(result.getString(1),result.getString(2),""));
            }
            else {
               giaoVienView.add(new GiaoVienViewModel(result.getString(1),result.getString(2),result.getString(3)));
            }
         }
         return giaoVienView;
      }
      catch (Exception ex){
         return  null;
      }
   }
   public static boolean CheckNienKhoaExit(String MaNienKhoa){
      try{
         var _connection = DbContextFactory.CreateConnect();
         var queriesString = "select COUNT(*) from ChiTietGiaoVien\n" +
                 "join LopHoc on ChiTietGiaoVien.MaLopHoc = LopHoc.Ma\n" +
                 "where LopHoc.MaNienKhoa = " + MaNienKhoa;
         var statement = _connection.createStatement();
         var result = statement.executeQuery(queriesString);
         if(result.next() && result.getDouble(1) > 0){
            return true;
         }
        return false;

      }
      catch (Exception ex){
         return  false;
      }
   }
}
