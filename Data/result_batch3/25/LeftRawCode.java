// https://github.com/abel533/Mapper/tree/7990b9c48bbcebf590f09fc245daba0c396f7b57/core/src/main/java/tk/mybatis/mapper/code/IdentityDialect.java#L49-L73
public class TempClass {
    public static IdentityDialect getDatabaseDialect(String database) {
        IdentityDialect returnValue = null;
        if ("DB2".equalsIgnoreCase(database)) {
            returnValue = DB2;
        } else if ("MySQL".equalsIgnoreCase(database)) {
            returnValue = MYSQL;
        } else if ("SqlServer".equalsIgnoreCase(database)) {
            returnValue = SQLSERVER;
        } else if ("Cloudscape".equalsIgnoreCase(database)) {
            returnValue = CLOUDSCAPE;
        } else if ("Derby".equalsIgnoreCase(database)) {
            returnValue = DERBY;
        } else if ("HSQLDB".equalsIgnoreCase(database)) {
            returnValue = HSQLDB;
        } else if ("SYBASE".equalsIgnoreCase(database)) {
            returnValue = SYBASE;
        } else if ("DB2_MF".equalsIgnoreCase(database)) {
            returnValue = DB2_MF;
        } else if ("Informix".equalsIgnoreCase(database)) {
            returnValue = INFORMIX;
        } else if ("".equals(database)) {
            return DEFAULT;
        }
        return returnValue;
    }

}