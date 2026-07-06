// https://github.com/j-easy/easy-rules/tree/d4450831533e47a5c0cb361edb80dad43db228a4/easy-rules-core/src/main/java/org/jeasy/rules/core/BasicRule.java#L133-L148
public class TempClass {
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        BasicRule basicRule = (BasicRule) o;

        if (priority != basicRule.priority)
            return false;
        if (!name.equals(basicRule.name))
            return false;
        return Objects.equals(description, basicRule.description);

    }

}