// https://github.com/jenkinsci/jenkins/tree/235d237f32835ccc1064c95c701f331d7dece7a3/core/src/main/java/hudson/util/StreamCopyThread.java#L55-L78
public class TempClass {
    @Override
    public void run() {
        try {
            try {
                byte[] buf = new byte[8192];
                int len;
                while ((len = in.read(buf)) >= 0)
                    out.write(buf, 0, len);
            } finally {
                // it doesn't make sense not to close InputStream that's already EOF-ed,
                // so there's no 'closeIn' flag.
                in.close();
                if (closeOut) {
                    // This mode is not currently used in core.
                    out.close();
                } else {
                    // Leaving the stream open, but we want to make sure any final output is sent to the master.
                    out.flush();
                }
            }
        } catch (IOException e) {
            // TODO: what to do?
        }
    }

}