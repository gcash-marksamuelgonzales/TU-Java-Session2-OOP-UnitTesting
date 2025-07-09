package classes;

import java.util.List;

public class Maintenance extends Account{

    public Maintenance(Integer accountId, String accountName, Double accountBalance, String accountType) {
        super(accountId, accountName, accountBalance, accountType);
    }

    public Maintenance(Integer accountId, Double accountBalance){
        super(accountId, accountBalance);
    }

    public Maintenance(){

    }

    public double deposit(Double balance, Double input){
        if(input >= 100){
            return balance + input;
        } else{
            return 0.00;
        }
    }

    public double withdraw(Double balance, Double input){
        if(balance > input){
            return balance - input;
        } else{
            return 0.00;
        }
    }

    public boolean validateWithdrawal(Double balance, Double input){
        if(input > balance){
            return true;
        } else{
            return false;
        }
    }

    public boolean validateDeposit(Double input){
        if(input < 100){
            return true;
        } else{
            return false;
        }
    }

    public double computeInterest(Double balance){
        return balance * 0.05;
    }


    public String validateAccountType(String input){
        if(input.equalsIgnoreCase("savings") || input.equalsIgnoreCase("checking")){
            return input.toUpperCase();
        } else{
            System.out.println("Invalid input. Unaccepted value for Account Type. Kindly retry..");
            return "";
        }
    }

    public Account validateAccountRegistration(List<Account> accountList, Account account){

        // Validate if account already exists
        if(accountList.contains(account.getAccountName())){
            System.out.println("User already exists! Registration will be cancelled..");
            account.clear();
        }

        // Validate initial deposit
        if(account.getAccountBalance() < 2500){
            System.out.println("Initial deposit should be 2,500 or more..");
            account.clear();
        }

        return account;
    }

    public Integer validateAccountId(Integer input, List<Account> accountList){
        boolean isExisting = accountList
                .stream()
                .anyMatch(account -> account.getAccountId().equals(input));
        return isExisting ? input : 0;
    }

}
