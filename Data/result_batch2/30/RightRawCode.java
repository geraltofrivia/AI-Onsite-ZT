// https://github.com/chillzhuang/blade-tool/tree/89bd158b54c7511a8908f2a34039f0197eacfd5c/blade-starter-report/src/main/java/com/bstek/ureport/console/html/HtmlPreviewServletAction.java#L338-L349
public class TempClass {
	private String buildExceptionMessage(Throwable throwable) {
		Throwable root = buildRootException(throwable);
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		root.printStackTrace(pw);
		String trace = sw.getBuffer().toString();
		trace = trace.replaceAll("\n", "<br>");
		pw.close();
		return trace;
	}

	public void setExportManager(ExportManager exportManager) {

}