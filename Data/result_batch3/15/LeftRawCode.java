// https://github.com/karatelabs/karate/tree/ace1051b5e40410227f46709708513a5b366d0a9/karate-core/src/main/java/com/intuit/karate/template/KarateServerDialect.java#L45-L59
public class TempClass {
    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        Set<IProcessor> ps = new HashSet();
        ps.add(new KaScriptAttrProcessor(dialectPrefix, config));
        ps.add(new KaScriptElemProcessor(dialectPrefix));
        ps.add(new KaSetElemProcessor(dialectPrefix));
        ps.add(new KaLinkAttrProcessor(dialectPrefix, config));
        ps.add(new KaHxMethodAttrProcessor(dialectPrefix, "get", config));
        ps.add(new KaHxMethodAttrProcessor(dialectPrefix, "post", config));
        ps.add(new KaHxMethodAttrProcessor(dialectPrefix, "put", config));
        ps.add(new KaHxMethodAttrProcessor(dialectPrefix, "patch", config));
        ps.add(new KaHxMethodAttrProcessor(dialectPrefix, "delete", config));
        ps.add(new KaHxValsAttrProcessor(dialectPrefix));
        return ps;
    }

}