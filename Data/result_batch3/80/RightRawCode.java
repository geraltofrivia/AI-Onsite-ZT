// https://github.com/alibaba/yugong/tree/94a5888688415510a20bd72d246cbf93eb1d43f0/src/main/java/com/taobao/yugong/common/db/meta/TableMetaGenerator.java#L280-L342
public class TempClass {
            public Object doInConnection(Connection conn) throws SQLException, DataAccessException {
                DatabaseMetaData metaData = conn.getMetaData();
                ResultSet rs;
                // 查询所有字段
                rs = metaData.getColumns(table.getSchema(), table.getSchema(), table.getName(), null);
                List<ColumnMeta> columnList = new ArrayList<ColumnMeta>();

                while (rs.next()) {
                    String catlog = rs.getString(1);
                    String schema = rs.getString(2);
                    String name = rs.getString(3);
                    if ((table.getSchema() == null || LikeUtil.isMatch(table.getSchema(), catlog) || LikeUtil.isMatch(table.getSchema(),
                        schema))
                        && LikeUtil.isMatch(table.getName(), name)) {
                        String columnName = rs.getString(4); // COLUMN_NAME
                        int columnType = rs.getInt(5);
                        String typeName = rs.getString(6);
                        columnType = convertSqlType(columnType, typeName);
                        ColumnMeta col = new ColumnMeta(columnName, columnType);
                        columnList.add(col);
                    }
                }
                rs.close();

                // 查询主键信息
                rs = metaData.getPrimaryKeys(table.getSchema(), table.getSchema(), table.getName());
                List<String> primaryKeys = new ArrayList<String>();
                while (rs.next()) {
                    String catlog = rs.getString(1);
                    String schema = rs.getString(2);
                    String name = rs.getString(3);
                    if ((table.getSchema() == null || StringUtils.equalsIgnoreCase(catlog, table.getSchema()) || StringUtils.equalsIgnoreCase(schema,
                        table.getSchema()))
                        && StringUtils.equalsIgnoreCase(name, table.getName())) {
                        primaryKeys.add(rs.getString(4));
                    }
                }
                rs.close();

                Set<ColumnMeta> columns = new HashSet<ColumnMeta>();
                Set<ColumnMeta> pks = new HashSet<ColumnMeta>();
                for (ColumnMeta columnMeta : columnList) {
                    if (primaryKeys.contains(columnMeta.getName())) {
                        pks.add(columnMeta);
                    } else {
                        columns.add(columnMeta);
                    }
                }

                table.getColumns().addAll(columns);
                table.getPrimaryKeys().addAll(pks);
                return null;
            }

        });

    }

    /**
     * 获取DRDS下表的拆分字段, 返回格式为 id,name
     */
    public static String getShardKeyByDRDS(final DataSource dataSource, final String schemaName, final String tableName) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

}