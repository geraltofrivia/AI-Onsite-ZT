// https://github.com/gephi/gephi/tree/dbc674485e70d7fbd74ef8f8489c1b7ba3dab50f/modules/FiltersPlugin/src/main/java/org/gephi/filters/plugin/partition/PartitionBuilder.java#L334-L348
public class TempClass {
        @Override
        public FilterProperty[] getProperties() {
            if (filterProperties == null) {
                filterProperties = new FilterProperty[0];
                try {
                    filterProperties = new FilterProperty[] {
                        FilterProperty.createProperty(this, Column.class, "column"),
                        FilterProperty.createProperty(this, Set.class, "parts"),
                        FilterProperty.createProperty(this, Boolean.class, "flattenList"),};
                } catch (Exception ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
            return filterProperties;
        }

}