// https://github.com/auth0/java-jwt/tree/ee7332b023719a9007be0caf5ef7608840fc4946/lib/src/main/java/com/auth0/jwt/algorithms/ECDSAAlgorithm.java#L159-L206
public class TempClass {
    void validateSignatureStructure(byte[] joseSignature, ECPublicKey publicKey) throws SignatureException {
        // check signature length, moved this check from JOSEToDER method
        if (joseSignature.length != ecNumberSize * 2) {
            throw new SignatureException("Invalid JOSE signature format.");
        }

        if (isAllZeros(joseSignature)) {
            throw new SignatureException("Invalid signature format.");
        }

        // get R
        byte[] rBytes = new byte[ecNumberSize];
        System.arraycopy(joseSignature, 0, rBytes, 0, ecNumberSize);
        if (isAllZeros(rBytes)) {
            throw new SignatureException("Invalid signature format.");
        }

        // get S
        byte[] sBytes = new byte[ecNumberSize];
        System.arraycopy(joseSignature, ecNumberSize, sBytes, 0, ecNumberSize);
        if (isAllZeros(sBytes)) {
            throw new SignatureException("Invalid signature format.");
        }

        //moved this check from JOSEToDER method
        int rPadding = countPadding(joseSignature, 0, ecNumberSize);
        int sPadding = countPadding(joseSignature, ecNumberSize, joseSignature.length);
        int rLength = ecNumberSize - rPadding;
        int sLength = ecNumberSize - sPadding;

        int length = 2 + rLength + 2 + sLength;
        if (length > 255) {
            throw new SignatureException("Invalid JOSE signature format.");
        }

        BigInteger order = publicKey.getParams().getOrder();
        BigInteger r = new BigInteger(1, rBytes);
        BigInteger s = new BigInteger(1, sBytes);

        // R and S must be less than N
        if (order.compareTo(r) < 1) {
            throw new SignatureException("Invalid signature format.");
        }

        if (order.compareTo(s) < 1) {
            throw new SignatureException("Invalid signature format.");
        }
    }

}