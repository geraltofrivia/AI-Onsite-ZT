// https://github.com/halo-dev/halo/tree/395399f078c39a7cd1b4b29509ab95d7ece0a296/application/src/main/java/run/halo/app/theme/dialect/HaloProcessorDialect.java#L35-L46
public class TempClass {
    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        final Set<IProcessor> processors = new HashSet<IProcessor>();
        // add more processors
        processors.add(new GlobalHeadInjectionProcessor(dialectPrefix));
        processors.add(new TemplateFooterElementTagProcessor(dialectPrefix));
        processors.add(new EvaluationContextEnhancer());
        processors.add(new CommentElementTagProcessor(dialectPrefix));
        processors.add(new CommentEnabledVariableProcessor());
        processors.add(new InjectionExcluderProcessor());
        return processors;
    }

}