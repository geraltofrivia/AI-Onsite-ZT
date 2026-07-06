// https://github.com/JZ-Darkal/AndroidHttpCapture/tree/e0aa4f6fcda60adf4f1c77d2ecadc6c383ba1a86/app/src/main/java/cn/darkal/networkdiagnosis/Utils/FileUtil.java#L365-L390
public class TempClass {
    public static void copyFile(String src, String dst) {
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(src);
            out = new FileOutputStream(dst);
            byte[] b = new byte[1024 * 5];
            int len = 0;
            while ((len = in.read(b)) > 0) {
                out.write(b, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}