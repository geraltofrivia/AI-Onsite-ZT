// https://github.com/hneemann/Digital/tree/0fc1087e8aef407dffda4846e2b5f1311e9180e2/src/main/java/de/neemann/digital/core/extern/ProcessStarter.java#L72-L86
public class TempClass {
        @Override
        public void run() {
            try {
                try {
                    byte[] buffer = new byte[4096];
                    int len;
                    while ((len = in.read(buffer)) > 0)
                        baos.write(buffer, 0, len);
                } finally {
                    in.close();
                }
            } catch (IOException e) {
                // do nothing, simply end the thread
            }
        }

}