package classes;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MaintenanceTest {
    private Maintenance maintenance;

    @BeforeEach
    void setup(){
        maintenance = new Maintenance();
    }

    @Test
    @DisplayName("Test Case - Compute for Interest")
    void computeInterest() {
        // Variables
        double balance = 5000;
        double expectedResults = 250;
        assertEquals(expectedResults, maintenance.computeInterest(balance));
    }

    @Test
    @DisplayName("Test Case - Deposit")
    void deposit() {
        double balance = 5000;
        double depositAmount = 500;
        double expectedResults = 5500;
        assertEquals(expectedResults, maintenance.deposit(balance,depositAmount));
    }

    @Test
    @DisplayName("Test Case - Withdraw")
    void withdraw() {
        double balance = 5000;
        double withdrawAmount = 1000;
        double expectedResults = 4000;
        assertEquals(expectedResults, maintenance.withdraw(balance,withdrawAmount));
    }

    @Test
    @DisplayName("Test Case - Account Type Validation")
    void validateAccountType() {
        String accountType = "savings";
        String expectedResults = "SAVINGS";

        String result = maintenance.validateAccountType(accountType);

        assertEquals(expectedResults, result);
    }

    @Test
    @DisplayName("Test Case - Register Account Validation")
    void validateAccountRegistration() {
        String inputName = "";
        Double inputDeposit = 0.00;
        Account inputAccount = new Maintenance();
        inputAccount.setAccountName(inputName);
        inputAccount.setAccountBalance(inputDeposit);

        List<Account> accountList = new ArrayList<>();
        Account expectedAccount = new Maintenance();
        expectedAccount.setAccountName("Mark Samuel");
        expectedAccount.setAccountBalance(2500.00);
        accountList.add(expectedAccount);

        Account expectedResult = maintenance.validateAccountRegistration(accountList,inputAccount);

        assertNotNull(expectedResult);
    }

    @Test
    @DisplayName("Test Case - Validate Account Id")
    void validateAccountId() {
        Integer inputAccountId = 1;
        Integer expectedResults = 1;

        List<Account> accountList = new ArrayList<>();
        Account account = new Maintenance();
        account.setAccountId(expectedResults);
        accountList.add(account);

        Integer isExisting = maintenance.validateAccountId(inputAccountId, accountList);

        assertEquals(expectedResults,isExisting);
    }

    @Test
    @DisplayName("Test Case - Validation of Withdrawal")
    void validateWithdrawal() {
        Double balance = 5000.00;
        Double input = 500.00;

        boolean isValid = maintenance.validateWithdrawal(balance, input);
        assertFalse(isValid);
    }

    @Test
    @DisplayName("Test Case - Validation of Deposit")
    void validateDeposit() {
        Double input = 1000.00;

        boolean isValid = maintenance.validateDeposit(input);
        assertFalse(isValid);
    }
}