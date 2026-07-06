// https://github.com/graphql-java/graphql-java/tree/23f078da4f181751f972d6ce071093fa8e661df7/src/main/java/graphql/execution/ResultPath.java#L324-L344
public class TempClass {
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ResultPath self = this;
        ResultPath that = (ResultPath) o;
        while (self.segment != null && that.segment != null) {
            if (!Objects.equals(self.segment, that.segment)) {
                return false;
            }
            self = self.parent;
            that = that.parent;
        }

        return self.isRootPath() && that.isRootPath();
    }

}