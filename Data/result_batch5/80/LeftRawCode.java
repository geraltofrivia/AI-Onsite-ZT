// https://github.com/graphql-java/graphql-java/tree/23f078da4f181751f972d6ce071093fa8e661df7/src/main/java/graphql/language/AstPrinter.java#L459-L473
public class TempClass {
    private void type(StringBuilder out, Type<?> type) {
        if (type instanceof NonNullType) {
            NonNullType inner = (NonNullType) type;
            type(out, inner.getType());
            out.append('!');
        } else if (type instanceof ListType) {
            ListType inner = (ListType) type;
            out.append('[');
            type(out, inner.getType());
            out.append(']');
        } else {
            TypeName inner = (TypeName) type;
            out.append(inner.getName());
        }
    }

}