// https://github.com/RipMeApp/ripme/tree/531f235154024e17e367560ddaf0232401bd537b/src/main/java/com/rarchives/ripme/ui/UpdateUtils.java#L222-L250
public class TempClass {
    public static String createSha256(Path file) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            try (InputStream fis = Files.newInputStream(file)) {
                int n = 0;
                byte[] buffer = new byte[8192];
                while (n != -1) {
                    n = fis.read(buffer);
                    if (n > 0) {
                        digest.update(buffer, 0, n);
                    }
                }
            }
            byte[] hash = digest.digest();
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append("0123456789ABCDEF".charAt((b & 0xF0) >> 4));
                sb.append("0123456789ABCDEF".charAt((b & 0x0F)));
            }
            // As patch.py writes the hash in lowercase this must return the has in
            // lowercase
            return sb.toString().toLowerCase();
        } catch (FileNotFoundException e) {
            logger.error("Could not find file: " + file);
        } catch (NoSuchAlgorithmException | IOException e) {
            logger.error("Got error getting file hash " + e.getMessage());
        }
        return null;
    }

}