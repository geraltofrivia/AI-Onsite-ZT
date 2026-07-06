// https://github.com/TEAMMATES/teammates/tree/5e8a38d446d8113daab924becc36206a0a86a134/src/it/java/teammates/it/storage/sqlapi/AccountsDbIT.java#L51-L62
public class TempClass {
    @Test
    public void testCreateAccount() throws Exception {
        ______TS("Create account, does not exists, succeeds");

        Account account = new Account("google-id", "name", "email@teammates.com");

        accountsDb.createAccount(account);
        HibernateUtil.flushSession();

        Account actualAccount = accountsDb.getAccount(account.getId());
        verifyEquals(account, actualAccount);
    }

}