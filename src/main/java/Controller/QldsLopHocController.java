package Controller;

import Models.LopHoc;
import Models.NienKhoa;
import Service.DbContextService;
import ViewModels.DeleteModel;
import ViewModels.FilterModel;

import java.util.ArrayList;

public class QldsLopHocController {
    public static final String prefix ="LH";
    private static final String tableName = "LopHoc";
    public static ArrayList<LopHoc> getLopHocByNienKhoaId(FilterModel filterModel){
        try {
            DbContextService service = new DbContextService();
            var filterList = new ArrayList<FilterModel>();
            filterList.add(filterModel);
            var value = service.filterModel(tableName, filterList);
            var result = new ArrayList<LopHoc>();
            while (value.next()){
                result.add(new LopHoc( value.getString(1), value.getString(2),value.getInt(3),
                        value.getFloat(4), value.getString(5)));
            }
            service.closeDbConext();
            return result;
        } catch (Exception e) {
            return null;
        }
    }
    public static ArrayList<LopHoc> GetAll(){
        try {
            DbContextService service = new DbContextService();
            var value = service.GetAll(tableName);
            var result = new ArrayList<LopHoc>();
            while (value.next()){
                result.add(new LopHoc( value.getString(1), value.getString(2),value.getInt(3),
                        value.getFloat(4), value.getString(5)));
            }
            service.closeDbConext();
            return result;
        } catch (Exception e) {
            return  new ArrayList<LopHoc>();
        }
    }
    public static boolean AddLopHoc(LopHoc lopHoc){
        try {
            DbContextService service = new DbContextService();
            var value = service.insert(tableName,lopHoc);
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
            return prefix +(int)value;
        } catch (Exception e) {
            return prefix;
        }
    }
    public static boolean Update(LopHoc lopHoc){
        try {
            DbContextService service = new DbContextService();
            var value = service.update(tableName,lopHoc);
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
