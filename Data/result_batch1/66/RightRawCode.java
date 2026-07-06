// https://github.com/alibaba/COLA/tree/d948f204d58fe7bd30a3ac919308a1311b76fec5/cola-components/cola-component-ruleengine/src/main/java/com/alibaba/cola/ruleengine/core/AbstractRule.java#L129-L144
public class TempClass {
    @Override
    public boolean equals(final Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        AbstractRule basicRule = (AbstractRule) o;

        if (priority != basicRule.priority)
            return false;
        if (!name.equals(basicRule.name))
            return false;
        return Objects.equals(description, basicRule.description);

    }

}