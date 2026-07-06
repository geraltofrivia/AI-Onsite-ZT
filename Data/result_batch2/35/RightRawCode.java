// https://github.com/alibaba/yugong/tree/94a5888688415510a20bd72d246cbf93eb1d43f0/src/main/java/com/taobao/yugong/common/db/sql/SqlTemplate.java#L181-L196
public class TempClass {
        sql.append("insert into ").append(makeFullName(schemaName, tableName)).append("(");
        String[] allColumns = buildAllColumns(pkNames, columnNames);
        int size = allColumns.length;
        for (int i = 0; i < size; i++) {
            sql.append(getColumnName(allColumns[i])).append(splitCommea(size, i));
        }

        sql.append(") values (");
        makeColumnQuestions(sql, allColumns);
        sql.append(")");
        return sql.toString().intern();
    }

    public String getUpdateSql(String schemaName, String tableName, String[] pkNames, String[] columnNames) {
        StringBuilder sql = new StringBuilder();
        sql.append("update ").append(makeFullName(schemaName, tableName)).append(" set ");

}