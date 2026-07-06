// https://github.com/qunarcorp/bistoury/tree/b83b87032c3a394df31300a4fe3a1123cf6d7917/bistoury-remoting/src/main/java/qunar/tc/bistoury/remoting/util/LocalHost.java#L104-L114
public class TempClass {
    public static String getHostName() {
        if (hostName != null)
            return hostName;
        String id = "LocalHost";
        try {
            id = InetAddress.getLocalHost().getHostName();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return hostName = id;
    }

}