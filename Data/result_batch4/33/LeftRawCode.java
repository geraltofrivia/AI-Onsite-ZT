// https://github.com/microsoft/typespec/tree/2bccd0226a134214538acb2eb6af04a5626858d5/packages/http-client-java/generator/http-client-generator-test/src/main/java/tsptest/armresourceprovider/implementation/ChildExtensionResourceInterfacesImpl.java#L127-L150
public class TempClass {
    public void deleteById(String id) {
        String resourceUri = ResourceManagerUtils.getValueFromIdByParameterName(id,
            "/{resourceUri}/providers/TspTest.ArmResourceProvider/topLevelArmResources/{topLevelArmResourceName}/childExtensionResources/{childExtensionResourceName}",
            "resourceUri");
        if (resourceUri == null) {
            throw LOGGER.logExceptionAsError(new IllegalArgumentException(
                String.format("The resource ID '%s' is not valid. Missing path segment 'resourceUri'.", id)));
        }
        String topLevelArmResourceName = ResourceManagerUtils.getValueFromIdByParameterName(id,
            "/{resourceUri}/providers/TspTest.ArmResourceProvider/topLevelArmResources/{topLevelArmResourceName}/childExtensionResources/{childExtensionResourceName}",
            "topLevelArmResourceName");
        if (topLevelArmResourceName == null) {
            throw LOGGER.logExceptionAsError(new IllegalArgumentException(
                String.format("The resource ID '%s' is not valid. Missing path segment 'topLevelArmResources'.", id)));
        }
        String childExtensionResourceName = ResourceManagerUtils.getValueFromIdByParameterName(id,
            "/{resourceUri}/providers/TspTest.ArmResourceProvider/topLevelArmResources/{topLevelArmResourceName}/childExtensionResources/{childExtensionResourceName}",
            "childExtensionResourceName");
        if (childExtensionResourceName == null) {
            throw LOGGER.logExceptionAsError(new IllegalArgumentException(String
                .format("The resource ID '%s' is not valid. Missing path segment 'childExtensionResources'.", id)));
        }
        this.delete(resourceUri, topLevelArmResourceName, childExtensionResourceName, Context.NONE);
    }

}