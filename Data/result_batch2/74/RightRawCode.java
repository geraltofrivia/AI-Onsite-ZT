// https://github.com/risesoft-y9/Digital-Infrastructure/tree/ed8caa40c8bcd856fea4b328df5d7e4c562403da/y9-digitalbase-module/y9-module-sso/risenet-y9boot-webapp-sso-jpa/src/main/java/y9/util/common/AESUtil.java#L24-L39
public class TempClass {
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
        IvParameterSpec iv = new IvParameterSpec(IV.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes());

        return Base64.encodeBase64String(encrypted);// 此处使用BAES64做转码功能，同时能起到2次
    }

    // 解密
    public static String decrypt(String sSrc) {
        byte[] encrypted1 = Base64.decodeBase64(sSrc);// 先用bAES64解密

        byte[] raw = KEY.getBytes(StandardCharsets.US_ASCII);

        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

}