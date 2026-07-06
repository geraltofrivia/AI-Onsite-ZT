// https://github.com/knowm/XChange/tree/784a88e954fe0db135766be75124d95c1d2b0efe/xchange-bitcoinde/src/main/java/org/knowm/xchange/bitcoinde/service/BitcoindeDigest.java#L64-L78
public class TempClass {
  private String getMD5(String original) {
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    md.update(original.getBytes());
    byte[] digest = md.digest();
    StringBuffer sb = new StringBuffer();
    for (byte b : digest) {
      sb.append(String.format("%02x", b & 0xff));
    }
    return sb.toString();
  }

}