package ViewModels;

public class DeleteModel {
    private String ColumnName;
    private String Value;

    public DeleteModel(String columnName, String value) {
        ColumnName = columnName;
        Value = value;
    }

    public String getColumnName() {
        return ColumnName;
    }

    public void setColumnName(String columnName) {
        ColumnName = columnName;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
