// https://github.com/Konloch/bytecode-viewer/tree/903ac6a3fd19f1a3047789a91fb35e48d60459fb/src/main/java/the/bytecode/club/bytecodeviewer/decompilers/impl/ProcyonDecompiler.java#L232-L251
public class TempClass {
    public DecompilerSettings getDecompilerSettings()
    {
        DecompilerSettings settings = new DecompilerSettings();
        settings.setAlwaysGenerateExceptionVariableForCatchBlocks(BytecodeViewer.viewer.alwaysGenerateExceptionVars.isSelected());
        settings.setExcludeNestedTypes(BytecodeViewer.viewer.excludeNestedTypes.isSelected());
        settings.setShowDebugLineNumbers(BytecodeViewer.viewer.showDebugLineNumbers.isSelected());
        settings.setIncludeLineNumbersInBytecode(BytecodeViewer.viewer.includeLineNumbersInBytecode.isSelected());
        settings.setIncludeErrorDiagnostics(BytecodeViewer.viewer.includeErrorDiagnostics.isSelected());
        settings.setShowSyntheticMembers(BytecodeViewer.viewer.showSyntheticMembers.isSelected());
        settings.setSimplifyMemberReferences(BytecodeViewer.viewer.simplifyMemberReferences.isSelected());
        settings.setMergeVariables(BytecodeViewer.viewer.mergeVariables.isSelected());
        settings.setForceExplicitTypeArguments(BytecodeViewer.viewer.forceExplicitTypeArguments.isSelected());
        settings.setForceExplicitImports(BytecodeViewer.viewer.forceExplicitImports.isSelected());
        settings.setFlattenSwitchBlocks(BytecodeViewer.viewer.flattenSwitchBlocks.isSelected());
        settings.setRetainPointlessSwitches(BytecodeViewer.viewer.retainPointlessSwitches.isSelected());
        settings.setRetainRedundantCasts(BytecodeViewer.viewer.retainRedunantCasts.isSelected());
        settings.setUnicodeOutputEnabled(BytecodeViewer.viewer.unicodeOutputEnabled.isSelected());
        settings.setJavaFormattingOptions(JavaFormattingOptions.createDefault());
        return settings;
    }

}