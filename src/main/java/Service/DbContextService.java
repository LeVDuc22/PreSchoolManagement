/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DbContext.DbContextFactory;
import ViewModels.FilterModel;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author My laptop
 */
public class DbContextService implements IDbContextService {

    private final Connection _connection;
    private final HashMap<Integer, String> foreginKey = new HashMap<>();

    public DbContextService() throws SQLException {
        _connection = DbContextFactory.CreateConnect();
        foreginKey.put(1, "MaNienKhoa");
        foreginKey.put(2, "MaLopHoc");
        foreginKey.put(3, "MaNguoiDung");
        foreginKey.put(4, "MaHocSinh");
        foreginKey.put(5, "MaGiaoVien");
    }

    public void closeDbConext() throws SQLException {
        _connection.close();
    }

    @Override
    public int insert(String tableName, Object object) {
        return 0;
    }

    @Override
    public int update(String tableName, Object object) {
        try {
            var stringBuilder = new StringBuilder();
            stringBuilder.append("update ").append(tableName).append(" set ");
            var excuteString = "";
            Field[] fields = object.getClass().getDeclaredFields();
            var fieldPK = object.getClass().getDeclaredField("Ma");
            if(fieldPK != null){
                excuteString = GenerateUpdateQueries(stringBuilder, object, fields, fieldPK);  
                PreparedStatement statement = _connection.prepareStatement(excuteString);
                return statement.executeUpdate();
            }
            return -1;
        } catch (Exception ex) {
            System.out.println(ex);
            return -1;
        }

    }

    @Override
    public int delete(String tableName, String whereClause) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ResultSet GetDataByModel(String tableName, Object object) {
        try {
            var stringBuilder = new StringBuilder();
            stringBuilder.append("select * from ").append(tableName).append(" where ");
            var queriesString = "";
            Field[] fields = object.getClass().getDeclaredFields();
            var fieldResult = new ArrayList<Field>(Arrays.asList(fields));
            var fieldPK = new ArrayList<Field>();
            var fieldFK = new ArrayList<Field>();
            for (Field field : fields) {
                if (field.getName().equals("Ma")) {
                    fieldPK.add(field);
                }
                if (foreginKey.containsKey(field.getName())) {
                    fieldFK.add(field);
                }
            }
            if (!fieldPK.isEmpty()) {
                queriesString = GenerateQueryString(stringBuilder, fieldPK, object);
            } else {
                if (!fieldFK.isEmpty()) {
                    queriesString = GenerateQueryString(stringBuilder, fieldFK, object);
                } else {
                    queriesString = GenerateQueryString(stringBuilder, fieldResult, object);
                }
            }
            var statement = _connection.createStatement();
            var value = statement.executeQuery(queriesString);
            return value;
//          }
        } catch (Exception e) {
            System.err.println(e.toString());
            return (ResultSet) new Object();
        }

    }

    private String GenerateQueryString(StringBuilder stringBuilder, ArrayList<Field> arrayList, Object object) throws IllegalArgumentException, IllegalAccessException {
        for (Field field : arrayList) {
            field.setAccessible(true);
            var properties = field.getName();
            var value = field.get(object).toString();
            stringBuilder.append(properties + "= '" + value).append("' and ");
        }
        return stringBuilder.append("1=1").toString();
    }

    public boolean filterMultiOneObject(String tableName, List<FilterModel> filterModel) {
        try {
            var stringBuilder = new StringBuilder();
            stringBuilder.append("select * from ").append(tableName).append(" where ");
            var queriesString = GenerateFilterString(stringBuilder, filterModel);
            var statement = _connection.createStatement();
            var value = statement.executeQuery(queriesString);
            return value.next();
        } catch (Exception e) {
            System.err.println(e.toString());
            return false;
        }
    }

    private String GenerateFilterString(StringBuilder stringBuilder, List<FilterModel> filters) throws IllegalArgumentException, IllegalAccessException {
        for (FilterModel filter : filters) {
            stringBuilder.append(filter.getColumnName() + "= '" + filter.getValueFilter()).append("' and ");
        }
        return stringBuilder.append("1=1").toString();
    }

    private String GenerateUpdateQueries(StringBuilder stringBuilder, Object object, Field[] fields, Field fieldPK) throws IllegalArgumentException, IllegalAccessException {
        for (Field field : fields) {
             field.setAccessible(true);
            var properties = field.getName();
            var value = field.get(object).toString();
            stringBuilder.append(properties + "= '" + value).append("' , ");
        }
        var result = stringBuilder.toString().lastIndexOf((','));
        var stringResult = stringBuilder.toString().substring(0,result -1);
        fieldPK.setAccessible(true);
        var properties = fieldPK.getName();
        var value = fieldPK.get(object).toString();
        return new StringBuilder(stringResult).append(" where "+ properties + "= '" + value +"'").toString();
    }

}
