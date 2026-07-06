// https://github.com/oshi/oshi/tree/35badc331ff64b262629f552d8b81034a08ab432/oshi-core/src/main/java/oshi/driver/unix/NetStat.java#L56-L79
public class TempClass {
    public static List<IPConnection> queryNetstat() {
        List<IPConnection> connections = new ArrayList<>();
        List<String> activeConns = ExecutingCommand.runNative("netstat -n");
        for (String s : activeConns) {
            String[] split = null;
            if (s.startsWith("tcp") || s.startsWith("udp")) {
                split = ParseUtil.whitespaces.split(s);
                if (split.length >= 5) {
                    String state = (split.length == 6) ? split[5] : null;
                    // Substitution if required
                    if ("SYN_RCVD".equals(state)) {
                        state = "SYN_RECV";
                    }
                    String type = split[0];
                    Pair<byte[], Integer> local = parseIP(split[3]);
                    Pair<byte[], Integer> foreign = parseIP(split[4]);
                    connections.add(new IPConnection(type, local.getA(), local.getB(), foreign.getA(), foreign.getB(),
                            state == null ? TcpState.NONE : TcpState.valueOf(state),
                            ParseUtil.parseIntOrDefault(split[2], 0), ParseUtil.parseIntOrDefault(split[1], 0), -1));
                }
            }
        }
        return connections;
    }

}