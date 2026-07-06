// https://github.com/qunarcorp/bistoury/tree/b83b87032c3a394df31300a4fe3a1123cf6d7917/bistoury-decompiler-fernflower/src/main/java/qunar/tc/decompiler/struct/gen/generics/GenericMain.java#L202-L232
public class TempClass {
    private static void appendTypeArguments(GenericType type, StringBuilder buffer) {
        if (!type.getArguments().isEmpty()) {
            buffer.append('<');

            for (int i = 0; i < type.getArguments().size(); i++) {
                if (i > 0) {
                    buffer.append(", ");
                }

                int wildcard = type.getWildcards().get(i);
                switch (wildcard) {
                    case GenericType.WILDCARD_UNBOUND:
                        buffer.append('?');
                        break;
                    case GenericType.WILDCARD_EXTENDS:
                        buffer.append("? extends ");
                        break;
                    case GenericType.WILDCARD_SUPER:
                        buffer.append("? super ");
                        break;
                }

                GenericType genPar = type.getArguments().get(i);
                if (genPar != null) {
                    buffer.append(getGenericCastTypeName(genPar));
                }
            }

            buffer.append(">");
        }
    }

}