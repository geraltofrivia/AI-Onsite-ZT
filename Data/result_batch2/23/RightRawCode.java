// https://github.com/open-keychain/open-keychain/tree/8d0bd1f8537fb3a795fda8161f730a313ef7b01c/OpenKeychain/src/main/java/org/sufficientlysecure/keychain/securitytoken/operations/SecurityTokenPsoSignTokenOp.java#L115-L163
public class TempClass {
    private byte[] encodeSignature(byte[] signature, KeyFormat keyFormat) throws IOException {
        // Make sure the signature we received is actually the expected number of bytes long!
        if (keyFormat instanceof RsaKeyFormat) {
            // no encoding necessary
            int modulusLength = ((RsaKeyFormat) keyFormat).modulusLength();
            if (signature.length != (modulusLength / 8)) {
                throw new IOException("Bad signature length! Expected " + (modulusLength / 8) +
                        " bytes, got " + signature.length);
            }

            return signature;
        } else if (keyFormat instanceof EcKeyFormat) {
            EcKeyFormat ecKeyFormat = (EcKeyFormat) keyFormat;
            if (ecKeyFormat.isEdDsa()) {
                return signature;
            }

            // "plain" encoding, see https://github.com/open-keychain/open-keychain/issues/2108
            if (signature.length % 2 != 0) {
                throw new IOException("Bad signature length!");
            }
            byte[] br = new byte[signature.length / 2];
            byte[] bs = new byte[signature.length / 2];
            for (int i = 0; i < br.length; ++i) {
                br[i] = signature[i];
                bs[i] = signature[br.length + i];
            }
            if (br[0] == 0x00 && (br[1] & 0x80) == 0) {
                br = Arrays.copyOfRange(br, 1, br.length);
            }
            if (bs[0] == 0x00 && (bs[1] & 0x80) == 0) {
                bs = Arrays.copyOfRange(bs, 1, bs.length);
            }
            // prepend a zero if the MPI value (i.e. high bit of first byte) is negative
            if (br[0] < 0) {
                br = Arrays.prepend(br, (byte) 0);
            }
            if (bs[0] < 0) {
                bs = Arrays.prepend(bs, (byte) 0);
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ASN1OutputStream out = ASN1OutputStream.create(baos);
            out.writeObject(new DERSequence(new ASN1Encodable[]{new ASN1Integer(br), new ASN1Integer(bs)}));
            out.flush();
            return baos.toByteArray();
        } else {
            throw new IOException("Not supported key format!");
        }
    }

}