// https://github.com/risesoft-y9/Digital-Infrastructure/tree/ed8caa40c8bcd856fea4b328df5d7e4c562403da/y9-digitalbase-common/risenet-y9boot-common-sqlddl/src/main/java/net/risesoft/y9/sqlddl/DbMetaDataUtil.java#L224-L248
public class TempClass {
            return "mysql";
        } else if (databaseName.indexOf(SqlConstants.DBTYPE_ORACLE) > -1) {
            return "oracle";
        } else if (databaseName.indexOf(SqlConstants.DBTYPE_DM) > -1) {
            return "dm";
        } else if (databaseName.indexOf(SqlConstants.DBTYPE_MICROSOFT) > -1) {
            return "mssql";
        } else if (databaseName.indexOf(SqlConstants.DBTYPE_KINGBASE) > -1) {
            return "kingbase";
        }
        return "";
    }

    /**
     * 获取数据库方言
     * 
     * @param connection 数据库的连接
     * @return String 数据库方言
     */
    public static String getDatabaseDialectNameByConnection(Connection connection) {
        String databaseName = "";
        try {
            databaseName = getDatabaseProductNameByConnection(connection).toLowerCase();
        } catch (SQLException e) {
            e.printStackTrace();

}