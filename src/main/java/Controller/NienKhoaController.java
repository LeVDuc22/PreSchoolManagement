package Controller;

import Models.NguoiDung;
import Models.NienKhoa;
import Service.DbContextService;
import ViewModels.DeleteModel;

import java.util.ArrayList;
import java.util.List;

public class NienKhoaController {
    private static final String tableName = "NienKhoa";
    public static final String prefix ="NK";
    public static boolean AddNienKhoa(NienKhoa nienKhoa){
        try {
            DbContextService service = new DbContextService();
            var value = service.insert(tableName,nienKhoa);
            service.closeDbConext();
            return value != -1;
        } catch (Exception e) {
            return false;
        }
    }
    public static String setMa(){
        try {
            DbContextService service = new DbContextService();
            var value = service.getCount(tableName);
            service.closeDbConext();
            return prefix + value;
        } catch (Exception e) {
            return prefix;
        }
    }
    public static ArrayList<NienKhoa> GetAll(){
        try {
            DbContextService service = new DbContextService();
            var value = service.GetAll(tableName);
            var result = new ArrayList<NienKhoa>();
            while (value.next()){
                result.add(new NienKhoa( value.getString(1), value.getString(4),value.getString(2).split(" ")[0],
                        value.getString(3).split(" ")[0]));
            }
            return result;
        } catch (Exception e) {
           return  new ArrayList<NienKhoa>();
        }
    }
    public static boolean Update(NienKhoa nienKhoa){
        try {
            DbContextService service = new DbContextService();
            var value = service.update(tableName,nienKhoa);
            service.closeDbConext();
            return value != -1;
        } catch (Exception e) {
            return false;
        }
    }
    public static boolean Delete(DeleteModel deleteModel){
        try {
            DbContextService service = new DbContextService();
            var value = service.delete(tableName,deleteModel);
            service.closeDbConext();
            return value != -1;
        } catch (Exception e) {
            return false;
        }
    }
}
