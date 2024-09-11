package Service;

import DAO.AccountDao;
import Model.Account;

public class AccountService {
    private AccountDao accountDao;

    public AccountService() {
        accountDao = new AccountDao();
    }

    public Account addAccount(Account account) {
        if (account.getUsername().isEmpty() ||
                account.getPassword().length() < 4 ||
                accountDao.getAccountByUsername(account.getUsername()) != null) {
            return null;
        }
        return accountDao.insertAccount(account);
    }

    public Account getAccountByLoginCredentials(Account account) {
        return accountDao.getAccountByLoginCredentials(account);
    }
}
