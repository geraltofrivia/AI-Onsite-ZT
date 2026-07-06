// https://github.com/karatelabs/karate/tree/ace1051b5e40410227f46709708513a5b366d0a9/karate-core/src/main/java/com/intuit/karate/shell/StopListenerThread.java#L61-L76
public class TempClass {
    @Override
    public void run() {
        logger.info("starting thread: {}", getName());
        Socket accept;
        try {
            accept = socket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(accept.getInputStream()));
            reader.readLine();
            logger.info("shutting down thread: {}", getName());
            stoppable.stop();
            accept.close();
            socket.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }   

}