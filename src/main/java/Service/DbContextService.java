/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DbContext.DbContextFactory;
import ViewModels.DeleteModel;
import ViewModels.FilterModel;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

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
        try {
            var stringBuilder = new StringBuilder();
            stringBuilder.append("insert into ").append(tableName +" (");
            var excuteString = "";
            Field[] fields = object.getClass().getDeclaredFields();
            var fieldResult = new ArrayList<Field>(Arrays.asList(fields));
            excuteString = GenerateInsertQuery(stringBuilder,fieldResult,object);

            PreparedStatement statement = _connection.prepareStatement(excuteString);
            return statement.executeUpdate();
        }
        catch (Exception ex){
            return  -1;
        }
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
    public int delete(String tableName, DeleteModel deleteModel) {
        try{
            var stringBuilder = new StringBuilder();
            stringBuilder.append("delete  from ").append(tableName).append(" where ");
            stringBuilder.append(deleteModel.getColumnName() + "= '" + deleteModel.getValue()).append("'");
            PreparedStatement statement = _connection.prepareStatement(stringBuilder.toString());
            return statement.executeUpdate();
        }
        catch (Exception ex){
            return  -1;
        }

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
    public ResultSet filterModel(String tableName, List<FilterModel> filterModel) {
        try {
            var stringBuilder = new StringBuilder();
            stringBuilder.append("select * from ").append(tableName).append(" where ");
            var queriesString = GenerateFilterString(stringBuilder, filterModel);
            var statement = _connection.createStatement();
            return statement.executeQuery(queriesString);
        } catch (Exception e) {
            System.err.println(e.toString());
            return null;
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

    @Override
    public ResultSet GetAll(String tableName) {
        try {
            var stringBuilder = new StringBuilder();
            stringBuilder.append("select * from ").append(tableName);
            var statement = _connection.createStatement();
            return statement.executeQuery(stringBuilder.toString());
        }
        catch (Exception ex) {
            return null;
        }
    }

    @Override
    public double getCount(String tableName) {
        try {
            var stringBuilder = new StringBuilder();
            stringBuilder.append("SELECT COUNT(*) FROM ").append(tableName);
            var statement = _connection.createStatement();
             var value = statement.executeQuery(stringBuilder.toString());
            while (value.next()) {
                return value.getDouble(1);
            }
            return  -1;
        }
        catch (Exception ex) {
            return -1;
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
    private String GenerateInsertQuery(StringBuilder stringBuilder, ArrayList<Field> arrayList, Object object) throws IllegalArgumentException, IllegalAccessException {
        String[] properties = new String[arrayList.size()];

        LinkedHashMap<String,String> values = new LinkedHashMap<>();
        int i =0;
        for (Field field : arrayList) {
            field.setAccessible(true);
            properties[i] = field.getName();
            values.put(field.get(object).toString(),field.getType().toString());
            i++;
        }
        for (i=0; i< properties.length; i++){
            if(i != properties.length - 1){
                stringBuilder.append(properties[i] +" ,");
            }
            else {
                stringBuilder.append(properties[i] + ")");
            }
        }
        stringBuilder.append(" values (");
        for(Map.Entry<String, String> entry : values.entrySet()){
            if(entry.getValue().contains("String")){
                stringBuilder.append("\'").append( entry.getKey()).append("\'").append(" ,");
            }
            else {
                stringBuilder.append(entry.getKey()+ ", ");
            }
        }
        var result = stringBuilder.toString().lastIndexOf((','));
        var stringResult = stringBuilder.toString().substring(0,result);

        return new StringBuilder(stringResult).append(")").toString();
    }
}
