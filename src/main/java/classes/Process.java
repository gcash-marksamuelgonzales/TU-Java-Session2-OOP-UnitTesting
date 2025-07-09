package classes;

import java.util.List;

public interface Process {
    double computeInterest(Double balance);
    double withdraw(Double balance, Double input);
    boolean validateWithdrawal(Double balance, Double input);
    double deposit(Double balance, Double input);
    boolean validateDeposit(Double input);
    String validateAccountType(String input);
    Account validateAccountRegistration(List<Account> accountList, Account account);
    Integer validateAccountId(Integer input, List<Account> accountList);
}
