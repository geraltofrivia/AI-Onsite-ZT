// https://github.com/checkstyle/checkstyle/tree/bd9a465994b5a1bfdb27153ce7bbe9b2f2baf8f2/src/main/java/com/puppycrawl/tools/checkstyle/checks/UncommentedMainCheck.java#L255-L271
public class TempClass {
    private static boolean checkParams(DetailAST method) {
        boolean checkPassed = false;
        final DetailAST params = method.findFirstToken(TokenTypes.PARAMETERS);

        if (params.getChildCount() == 1) {
            final DetailAST parameterType = params.getFirstChild().findFirstToken(TokenTypes.TYPE);
            final boolean isArrayDeclaration =
                parameterType.findFirstToken(TokenTypes.ARRAY_DECLARATOR) != null;
            final Optional<DetailAST> varargs = Optional.ofNullable(
                params.getFirstChild().findFirstToken(TokenTypes.ELLIPSIS));

            if (isArrayDeclaration || varargs.isPresent()) {
                checkPassed = isStringType(parameterType.getFirstChild());
            }
        }
        return checkPassed;
    }

}