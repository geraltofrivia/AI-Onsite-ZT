// https://github.com/qunarcorp/bistoury/tree/b83b87032c3a394df31300a4fe3a1123cf6d7917/bistoury-proxy/src/main/java/qunar/tc/bistoury/proxy/communicate/ui/linuxcommand/StandardCommand.java#L216-L232
public class TempClass {
        @Override
        public Options createOptions() {
            Options options = new Options();
            options.addOption(Option.builder().longOpt("max-unchanged-stats").hasArg().argName("N").build());
            options.addOption(Option.builder().longOpt("pid").build());
            options.addOption(Option.builder().longOpt("retry").build());
            options.addOption(Option.builder().longOpt("help").build());
            options.addOption(Option.builder().longOpt("version").build());
            options.addOption("c", DEFAULT_MSG);
            options.addOption("f", DEFAULT_MSG);
            options.addOption("F", DEFAULT_MSG);
            options.addOption("n", DEFAULT_MSG);
            options.addOption("q", DEFAULT_MSG);
            options.addOption("s", DEFAULT_MSG);
            options.addOption("v", DEFAULT_MSG);
            return options;
        }

}