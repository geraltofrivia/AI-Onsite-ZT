// https://github.com/rubenlagus/TelegramBots/tree/508bc2c1810ec3b7ef7cd086efdf84d78c4a5f49/telegrambots-longpolling/src/test/java/org/telegram/telegrambots/longpolling/TestTelegramBotsLongPollingApplicationIntegration.java#L123-L160
public class TempClass {
    @Test
    public void testStartStop() {
        try {
            List<Update> updateReceived = new ArrayList<>();

            Dispatcher dispatcher = getDispatcher(List.of(getFakeUpdates1()));
            webServer.setDispatcher(dispatcher);

            assertTrue(application.isRunning());

            application.stop();

            assertFalse(application.isRunning());

            application.registerBot("TOKEN",
                    () -> telegramUrl,
                    new DefaultGetUpdatesGenerator(),
                    (LongPollingSingleThreadUpdateConsumer) update -> updateReceived.add(update));

            assertFalse(application.isRunning());

            await().timeout(5, TimeUnit.SECONDS).until(updateReceived::isEmpty);

            application.start();

            assertTrue(application.isRunning());

            await().timeout(10, TimeUnit.SECONDS).until(() -> !updateReceived.isEmpty());

            application.stop();

            assertFalse(application.isRunning());

            assertEquals(2, updateReceived.size());
        } catch (Exception e) {
            fail(e);
        }
    }

}