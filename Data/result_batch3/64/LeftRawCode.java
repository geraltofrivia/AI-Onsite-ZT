// https://github.com/abel533/Mapper/tree/7990b9c48bbcebf590f09fc245daba0c396f7b57/core/src/main/java/tk/mybatis/mapper/entity/EntityColumn.java#L177-L190
public class TempClass {
    @Override
    public int hashCode() {
        int result = table != null ? table.hashCode() : 0;
        result = 31 * result + (property != null ? property.hashCode() : 0);
        result = 31 * result + (column != null ? column.hashCode() : 0);
        result = 31 * result + (javaType != null ? javaType.hashCode() : 0);
        result = 31 * result + (jdbcType != null ? jdbcType.hashCode() : 0);
        result = 31 * result + (typeHandler != null ? typeHandler.hashCode() : 0);
        result = 31 * result + (id ? 1 : 0);
        result = 31 * result + (identity ? 1 : 0);
        result = 31 * result + (generator != null ? generator.hashCode() : 0);
        result = 31 * result + (orderBy != null ? orderBy.hashCode() : 0);
        return result;
    }

}