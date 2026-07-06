// https://github.com/microsoft/typespec/tree/2bccd0226a134214538acb2eb6af04a5626858d5/packages/http-client-java/generator/http-client-generator-core/src/main/java/com/microsoft/typespec/http/client/generator/core/model/javamodel/JavaImportComparer.java#L18-L50
public class TempClass {
    public final int compare(String lhsImport, String rhsImport) {
        int result;

        if (Objects.equals(lhsImport, rhsImport)) {
            result = 0;
        } else if (lhsImport == null) {
            result = -1;
        } else if (rhsImport == null) {
            result = 1;
        } else {
            result = 0;

            String[] lhsImportParts = getImportParts(lhsImport);
            String[] rhsImportParts = getImportParts(rhsImport);

            int minimumImportPartCount = Math.min(lhsImportParts.length, rhsImportParts.length);
            for (int i = 0; i < minimumImportPartCount; ++i) {
                int caseInsensitiveCompareTo = lhsImportParts[i].compareToIgnoreCase(rhsImportParts[i]);

                if (caseInsensitiveCompareTo != 0) {
                    boolean isLastLhsPart = isLastPart(i, lhsImportParts);
                    boolean isLastRhsPart = isLastPart(i, rhsImportParts);
                    if (isLastLhsPart != isLastRhsPart) {
                        return isLastLhsPart ? -1 : 1;
                    } else {
                        return caseInsensitiveCompareTo;
                    }
                }
            }
        }

        return result;
    }

}