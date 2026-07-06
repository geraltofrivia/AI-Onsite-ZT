// https://github.com/testcontainers/testcontainers-java/tree/768a2af266bf20be4800281053c83d4d1f345942/modules/rabbitmq/src/test/java/org/testcontainers/containers/RabbitMQContainerTest.java#L283-L311
public class TempClass {
    private SSLContext createSslContext(
        String keystoreFile,
        String keystorePassword,
        String truststoreFile,
        String truststorePassword
    )
        throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, KeyManagementException {
        ClassLoader classLoader = getClass().getClassLoader();

        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(
            Files.newInputStream(new File(classLoader.getResource(keystoreFile).getFile()).toPath()),
            keystorePassword.toCharArray()
        );
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(ks, "password".toCharArray());

        KeyStore trustStore = KeyStore.getInstance("PKCS12");
        trustStore.load(
            Files.newInputStream(new File(classLoader.getResource(truststoreFile).getFile()).toPath()),
            truststorePassword.toCharArray()
        );
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(trustStore);

        SSLContext c = SSLContext.getInstance("TLSv1.2");
        c.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        return c;
    }

}