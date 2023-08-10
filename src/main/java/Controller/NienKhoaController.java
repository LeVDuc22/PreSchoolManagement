package Controller;

import Models.NienKhoa;
import Service.DbContextService;

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
}
