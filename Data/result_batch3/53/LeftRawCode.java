// https://github.com/Col-E/Recaf/tree/391ceb6307c3dd052947a1a158baa0e3e270ea71/recaf-ui/src/main/java/software/coley/recaf/ui/pane/editing/AbstractDecompilePane.java#L270-L316
public class TempClass {
	public void decompile() {
		Workspace workspace = path.getValueOfType(Workspace.class);
		JvmClassInfo classInfo = path.getValue().asJvmClass();

		// Schedule decompilation task, update the editor's text asynchronously on the JavaFX UI thread when complete.
		decompileInProgress.setValue(true);
		editor.setMouseTransparent(true);
		decompilerManager.decompile(decompiler.getValue(), workspace, classInfo)
				.completeOnTimeout(timeoutResult(), config.getTimeoutSeconds().getValue(), TimeUnit.SECONDS)
				.whenCompleteAsync((result, throwable) -> {
					editor.setMouseTransparent(false);
					decompileInProgress.setValue(false);

					// Handle uncaught exceptions
					if (throwable != null) {
						String trace = StringUtil.traceToString(throwable);
						editor.setText("/*\nUncaught exception when decompiling:\n" + trace + "\n*/");
						return;
					}

					// Handle decompilation result
					String text = result.getText();
					if (Objects.equals(text, editor.getText()))
						return; // Skip if existing text is the same
					DecompileResult.ResultType resultType = result.getType();
					decompileOutputErrored.setValue(resultType == DecompileResult.ResultType.FAILURE);
					switch (resultType) {
						case SUCCESS -> editor.setText(text);
						case SKIPPED -> editor.setText(text == null ? "// Decompilation skipped" : text);
						case FAILURE -> {
							Throwable exception = result.getException();
							if (exception != null) {
								String trace = StringUtil.traceToString(exception);
								editor.setText("/*\nDecompile failed:\n" + trace + "\n*/");
							} else {
								editor.setText("/*\nDecompile failed, but no trace was attached.\n*/");
							}
						}
					}

					// Schedule AST parsing for context action support.
					contextActionSupport.scheduleAstParse();

					// Prevent undo from reverting to empty state.
					editor.getCodeArea().getUndoManager().forgetHistory();
				}, FxThreadUtil.executor());
	}

}