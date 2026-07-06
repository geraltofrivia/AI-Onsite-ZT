// https://github.com/microsoft/typespec/tree/2bccd0226a134214538acb2eb6af04a5626858d5/packages/http-client-java/generator/http-client-generator-mgmt/src/main/java/com/microsoft/typespec/http/client/generator/mgmt/model/clientmodel/fluentmodel/ResourceOperation.java#L93-L126
public class TempClass {
    protected List<ModelProperty> getProperties() {
        List<ModelProperty> properties = new ArrayList<>();

        List<String> commonPropertyNames = Arrays.asList(ResourceTypeName.FIELD_LOCATION, ResourceTypeName.FIELD_TAGS);

        if (this.isBodyParameterSameAsFluentModel()) {
            for (String commonPropertyName : commonPropertyNames) {
                if (resourceModel.hasProperty(commonPropertyName)) {
                    FluentModelProperty property = resourceModel.getProperty(commonPropertyName);
                    properties.add(property.getModelProperty());
                }
            }
            for (FluentModelProperty property : resourceModel.getProperties()) {
                if (!commonPropertyNames.contains(property.getName())) {
                    properties.add(property.getModelProperty());
                }
            }
        } else {
            Map<String, ModelProperty> propertyMap = this.getRequestBodyModelPropertiesMap();
            for (String commonPropertyName : commonPropertyNames) {
                if (propertyMap.containsKey(commonPropertyName)) {
                    ModelProperty property = propertyMap.get(commonPropertyName);
                    properties.add(property);
                }
            }
            for (ModelProperty property : this.getRequestBodyModelProperties()) {
                if (!commonPropertyNames.contains(property.getName())) {
                    properties.add(property);
                }
            }
        }

        return properties.stream().filter(p -> !p.isReadOnly() && !p.isConstant()).collect(Collectors.toList());
    }

}