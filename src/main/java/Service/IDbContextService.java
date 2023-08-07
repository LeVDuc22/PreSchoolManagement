/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import ViewModels.FilterModel;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author My laptop
 */
public interface IDbContextService {
    int insert(String tableName, Object object);
    int update(String tableName, Object object);
    int delete(String tableName, String whereClause);
    ResultSet GetDataByModel(String tableName, Object object);
    boolean filterMultiOneObject(String tableName, List<FilterModel> filterModel);
}
