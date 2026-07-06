// https://github.com/f-droid/fdroidclient/tree/83f87606b0558083b06691ac391d41c0f52bdbb8/app/src/androidTest/java/org/fdroid/fdroid/Netstat.java#L120-L224
public class TempClass {
    public static List<Connection> getConnections() {

        final ArrayList<Connection> net = new ArrayList<>();

        // read from /proc/net/tcp the list of currently opened socket connections
        try {
            BufferedReader in = new BufferedReader(new FileReader("/proc/net/tcp"));
            String line;
            while ((line = in.readLine()) != null) { // NOPMD
                Matcher matcher = NET_PATTERN.matcher(line);
                if (matcher.find()) {
                    final Connection c = new Connection();
                    c.setProtocol(Connection.TCP_CONNECTION);
                    net.add(c);
                    final String localPortHexa = matcher.group(2);
                    final String remoteAddressHexa = matcher.group(3);
                    final String remotePortHexa = matcher.group(4);
                    final String statusHexa = matcher.group(5);
                    //final String uid = matcher.group(6);
                    //final String inode = matcher.group(7);
                    c.setLocalPort(getInt16(localPortHexa));
                    c.setRemoteAddress(getAddress(remoteAddressHexa));
                    c.setRemotePort(getInt16(remotePortHexa));
                    try {
                        c.setStatus(STATES[Integer.parseInt(statusHexa, 16) - 1]);
                    } catch (Exception ex) {
                        c.setStatus(STATES[11]); // unknown
                    }
                    c.setPID(-1); // unknown
                    c.setPName("UNKNOWN");
                }
            }
            in.close();
        } catch (Throwable t) { // NOPMD
            // ignored
        }

        // read from /proc/net/udp the list of currently opened socket connections
        try {
            BufferedReader in = new BufferedReader(new FileReader("/proc/net/udp"));
            String line;
            while ((line = in.readLine()) != null) { // NOPMD
                Matcher matcher = NET_PATTERN.matcher(line);
                if (matcher.find()) {
                    final Connection c = new Connection();
                    c.setProtocol(Connection.UDP_CONNECTION);
                    net.add(c);
                    final String localPortHexa = matcher.group(2);
                    final String remoteAddressHexa = matcher.group(3);
                    final String remotePortHexa = matcher.group(4);
                    final String statusHexa = matcher.group(5);
                    //final String uid = matcher.group(6);
                    //final String inode = matcher.group(7);
                    c.setLocalPort(getInt16(localPortHexa));
                    c.setRemoteAddress(getAddress(remoteAddressHexa));
                    c.setRemotePort(getInt16(remotePortHexa));
                    try {
                        c.setStatus(STATES[Integer.parseInt(statusHexa, 16) - 1]);
                    } catch (Exception ex) {
                        c.setStatus(STATES[11]); // unknown
                    }
                    c.setPID(-1); // unknown
                    c.setPName("UNKNOWN");
                }
            }
            in.close();
        } catch (Throwable t) { // NOPMD
            // ignored
        }

        // read from /proc/net/raw the list of currently opened socket connections
        try {
            BufferedReader in = new BufferedReader(new FileReader("/proc/net/raw"));
            String line;
            while ((line = in.readLine()) != null) { // NOPMD
                Matcher matcher = NET_PATTERN.matcher(line);
                if (matcher.find()) {
                    final Connection c = new Connection();
                    c.setProtocol(Connection.RAW_CONNECTION);
                    net.add(c);
                    //final String localAddressHexa = matcher.group(1);
                    final String localPortHexa = matcher.group(2);
                    final String remoteAddressHexa = matcher.group(3);
                    final String remotePortHexa = matcher.group(4);
                    final String statusHexa = matcher.group(5);
                    //final String uid = matcher.group(6);
                    //final String inode = matcher.group(7);
                    c.setLocalPort(getInt16(localPortHexa));
                    c.setRemoteAddress(getAddress(remoteAddressHexa));
                    c.setRemotePort(getInt16(remotePortHexa));
                    try {
                        c.setStatus(STATES[Integer.parseInt(statusHexa, 16) - 1]);
                    } catch (Exception ex) {
                        c.setStatus(STATES[11]); // unknown
                    }
                    c.setPID(-1); // unknown
                    c.setPName("UNKNOWN");
                }
            }
            in.close();
        } catch (Throwable t) { // NOPMD
            // ignored
        }
        return net;
    }

}