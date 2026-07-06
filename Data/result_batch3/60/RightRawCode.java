// https://github.com/Luohuayu/CatServer/tree/1c92118fcca69ffac97a48c8e1f6e1bb861b41d1/src/main/java/org/bukkit/StructureType.java#L205-L215
public class TempClass {
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof StructureType)) {
            return false;
        }
        StructureType that = (StructureType) other;
        return this.name.equals(that.name) && this.mapCursor == that.mapCursor;
    }

}