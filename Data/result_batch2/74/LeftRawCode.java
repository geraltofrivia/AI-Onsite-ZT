// https://github.com/nining377/dolby_beta/tree/258ab9b95671ecfb13a03636842890d9ca36fe75/app/src/main/java/com/raincat/dolby_beta/utils/NeteaseAES.java#L14-L34
public class TempClass {
    public static String Encrypt(String sSrc, String sKey) throws Exception {
        if (sKey == null) {
            System.out.print("Key为空null");
            return null;
        }
        // 判断Key是否为16位
        if (sKey.length() != 16) {
            System.out.print("Key长度不是16位");
            return null;
        }
        byte[] raw = sKey.getBytes();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        // AES CBC 加密
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
        // 偏移量
        IvParameterSpec iv = new IvParameterSpec("0102030405060708".getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes());

        return NeteaseBase64.encode(encrypted);// 此处使用BASE64做转码功能，同时能起到2次加密的作用。
    }

}