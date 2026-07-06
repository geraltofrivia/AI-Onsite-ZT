// https://github.com/YunaiV/yudao-cloud/tree/38e73c2b10b868431cd8e1238fc61f031571247c/yudao-framework/yudao-spring-boot-starter-excel/src/main/java/cn/iocoder/yudao/framework/excel/core/handler/ColumnWidthMatchStyleStrategy.java#L54-L76
public class TempClass {
    @SuppressWarnings("EnhancedSwitchMigration")
    private Integer dataLength(List<WriteCellData<?>> cellDataList, Cell cell, Boolean isHead) {
        if (isHead) {
            return cell.getStringCellValue().getBytes().length;
        }
        WriteCellData<?> cellData = cellDataList.get(0);
        CellDataTypeEnum type = cellData.getType();
        if (type == null) {
            return -1;
        }
        switch (type) {
            case STRING:
                return cellData.getStringValue().getBytes().length;
            case BOOLEAN:
                return cellData.getBooleanValue().toString().getBytes().length;
            case NUMBER:
                return cellData.getNumberValue().toString().getBytes().length;
            case DATE:
                return cellData.getDateValue().toString().getBytes().length;
            default:
                return -1;
        }
    }

}