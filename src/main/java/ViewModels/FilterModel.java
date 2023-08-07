/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModels;

/**
 *
 * @author My laptop
 */
public class FilterModel {
    private String ColumnName;
    private String ValueFilter;

    public FilterModel(String ColumnName, String ValueFilter) {
        this.ColumnName = ColumnName;
        this.ValueFilter = ValueFilter;
    }

    public String getColumnName() {
        return ColumnName;
    }

    public void setColumnName(String ColumnName) {
        this.ColumnName = ColumnName;
    }

    public String getValueFilter() {
        return ValueFilter;
    }

    public void setValueFilter(String ValueFilter) {
        this.ValueFilter = ValueFilter;
    }
    
   

  
    
}
