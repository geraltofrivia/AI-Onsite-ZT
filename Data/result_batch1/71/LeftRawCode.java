// https://github.com/Col-E/Recaf/tree/391ceb6307c3dd052947a1a158baa0e3e270ea71/recaf-core/src/main/java/software/coley/recaf/services/decompile/procyon/ProcyonConfig.java#L78-L103
public class TempClass {
	@Nonnull
	public DecompilerSettings toSettings() {
		DecompilerSettings decompilerSettings = new DecompilerSettings();
		decompilerSettings.setIncludeLineNumbersInBytecode(includeLineNumbersInBytecode.getValue());
		decompilerSettings.setShowSyntheticMembers(showSyntheticMembers.getValue());
		decompilerSettings.setAlwaysGenerateExceptionVariableForCatchBlocks(alwaysGenerateExceptionVariableForCatchBlocks.getValue());
		decompilerSettings.setForceFullyQualifiedReferences(forceFullyQualifiedReferences.getValue());
		decompilerSettings.setForceExplicitImports(forceExplicitImports.getValue());
		decompilerSettings.setForceExplicitTypeArguments(forceExplicitTypeArguments.getValue());
		decompilerSettings.setFlattenSwitchBlocks(flattenSwitchBlocks.getValue());
		decompilerSettings.setExcludeNestedTypes(excludeNestedTypes.getValue());
		decompilerSettings.setRetainRedundantCasts(retainRedundantCasts.getValue());
		decompilerSettings.setRetainPointlessSwitches(retainPointlessSwitches.getValue());
		decompilerSettings.setUnicodeOutputEnabled(isUnicodeOutputEnabled.getValue());
		decompilerSettings.setIncludeErrorDiagnostics(includeErrorDiagnostics.getValue());
		decompilerSettings.setMergeVariables(mergeVariables.getValue());
		decompilerSettings.setDisableForEachTransforms(disableForEachTransforms.getValue());
		decompilerSettings.setShowDebugLineNumbers(showDebugLineNumbers.getValue());
		decompilerSettings.setSimplifyMemberReferences(simplifyMemberReferences.getValue());
		decompilerSettings.setPreviewFeaturesEnabled(arePreviewFeaturesEnabled.getValue());
		decompilerSettings.setTextBlockLineMinimum(textBlockLineMinimum.getValue());
		decompilerSettings.setForcedCompilerTarget(forcedCompilerTarget.getValue());
		decompilerSettings.setBytecodeOutputOptions(bytecodeOutputOptions.getValue());
		decompilerSettings.setLanguage(languageTarget.getValue());
		return decompilerSettings;
	}

}