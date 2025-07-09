package service;

import classes.Account;
import classes.Maintenance;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankingSystemManager {

    public void execute(){
        List<Account> accountList = new ArrayList<>();
        Boolean isComplete = true;
        System.out.println("=== Welcome to MyBank ===");
        System.out.println("What would you like to do?");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Compute Interest");
        System.out.println("5. Display Account");
        System.out.println("6. Exit");
        Integer userInput = 0;
        Integer accountNumber = 1;

        do{
            while(true){
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter choice: ");
                if(scanner.hasNextInt()){
                    userInput = scanner.nextInt();
                    if(userInput >= 1 && userInput <= 6) {
                        break;
                    } else{
                        System.out.println("Invalid input. Kindly retry..");
                    }
                } else{
                    System.out.println("Invalid input. Kindly retry..");
                    scanner.next();
                }
            }

            switch(userInput){
                case 1:
                    Account newAccount = new Maintenance();
                    // Retrieve List Count
                    Integer accountCount = accountList.size();

                    // Account Type
                    Scanner typeScanner = new Scanner(System.in);
                    String accountType = "";
                    while(accountType.equals("")){
                        System.out.println("Enter Account Type(Savings/Checking): ");
                        accountType = newAccount.validateAccountType(typeScanner.nextLine());
                    }

                    // Account Name
                    Scanner nameScanner = new Scanner(System.in);
                    String accountName = "";
                    while(accountName.equals("")){
                        System.out.println("Enter Account Name: ");
                        if(nameScanner.hasNext()){
                            accountName = nameScanner.nextLine();
                            if(!accountName.equals("")){
                                break;
                            }
                        } else{
                            System.out.println("Account name cannot be blank..");
                        }
                    }

                    // Initial Deposit
                    Scanner depositScanner = new Scanner(System.in);
                    Double initialDeposit = 0.00;
                    while(initialDeposit < 2500){
                        System.out.println("Enter Initial Deposit: ");
                        if(depositScanner.hasNextDouble()){
                            initialDeposit = depositScanner.nextDouble();
                            if(initialDeposit < 2500){
                                System.out.println("Initial deposit for new accounts cannot be less than 2500..");
                            } else{
                                break;
                            }
                        }
                    }

                    // Add Account
                    Account account = new Maintenance(accountNumber, accountName, initialDeposit, accountType);
                    account = newAccount.validateAccountRegistration(accountList, account);
                    if(!account.getAccountName().equals(null) || !account.getAccountName().equals("")){
                        accountList.add(account);
                        Integer newCount = accountList.size();

                        if(newCount > accountCount){
                            System.out.println(String.format("User: %s successfully registered!",account.getAccountName()));
                            accountNumber++;
                        } else{
                            System.out.println(String.format("User: %s registration failed..",account.getAccountName()));
                        }

                    }
                    break;

                case 2:
                    // Deposit
                    Account depositMaintenance = new Maintenance();
                    Scanner depositIdScanner = new Scanner(System.in);
                    Integer depositAccountId = 0;
                    while(true){
                        System.out.println("Enter Account Id: ");
                        if(depositIdScanner.hasNextInt()){
                            depositAccountId = depositIdScanner.nextInt();
                            if(depositMaintenance.validateAccountId(depositAccountId, accountList) > 0){
                                break;
                            } else{
                                System.out.println("Account Id does not exist..");
                            }
                        } else{
                            System.out.println("Invalid input. Kindly retry..");
                            depositIdScanner.next();
                        }
                    }

                    Scanner depositAmountScanner = new Scanner(System.in);
                    Double depositAmount = 0.00;
                    while(depositAmount == 0.00){
                        System.out.println("Enter amount for deposit: ");
                        if(depositAmountScanner.hasNextDouble()){
                            depositAmount = depositAmountScanner.nextDouble();
                            if(depositAmount > 0){
                                break;
                            } else{
                                System.out.println("Invalid input. Kindly retry..");
                            }
                        } else{
                            System.out.println("Invalid input. Kindly retry..");
                            depositAmountScanner.next();
                        }
                    }

                    for(Account depositAccount : accountList){
                        Account accountDeposit = new Maintenance(depositAccount.getAccountId(),depositAccount.getAccountBalance());
                        if(depositAccountId == depositAccount.getAccountId()){
                            double newBalance = accountDeposit.deposit(depositAccount.getAccountBalance(),depositAmount);
                            // Validate if deposit is below 100
                            if(accountDeposit.validateDeposit(depositAmount) == false){
                                // Process Deposit
                                if(newBalance > depositAccount.getAccountBalance()){
                                    depositAccount.setAccountBalance(newBalance);
                                    System.out.println(String.format("Deposit Successful! [New Balance: %s]",depositAccount.getAccountBalance()));
                                }
                            }
                        }
                    }

                    break;

                case 3:
                    // Withdraw
                    Account withdrawMaintenance = new Maintenance();
                    Scanner withdrawIdScanner = new Scanner(System.in);
                    Integer withdrawAccountId = 0;
                    while(true){
                        System.out.println("Enter Account Id: ");
                        if(withdrawIdScanner.hasNextInt()){
                            withdrawAccountId = withdrawIdScanner.nextInt();
                            if(withdrawMaintenance.validateAccountId(withdrawAccountId, accountList) > 0){
                                break;
                            } else{
                                System.out.println("Account Id does not exist..");
                            }
                        } else{
                            System.out.println("Invalid input. Kindly retry..");
                            withdrawIdScanner.next();
                        }
                    }

                    Scanner withdrawAmountScanner = new Scanner(System.in);
                    Double withdrawAmount = 0.00;
                    while(withdrawAmount == 0.00){
                        System.out.println("Enter amount for withdrawal: ");
                        if(withdrawAmountScanner.hasNextDouble()){
                            withdrawAmount = withdrawAmountScanner.nextDouble();
                            if(withdrawAmount > 0){
                                break;
                            } else{
                                System.out.println("Invalid input. Kindly retry..");
                            }
                        } else{
                            System.out.println("Invalid input. Kindly retry..");
                            withdrawAmountScanner.next();
                        }
                    }

                    for(Account withdrawAccount : accountList){
                        Account accountWithdraw = new Maintenance(withdrawAccount.getAccountId(), withdrawAccount.getAccountBalance());
                        if(withdrawAccountId == withdrawAccount.getAccountId()){
                            double newBalance = accountWithdraw.withdraw(withdrawAccount.getAccountBalance(),withdrawAmount);
                            // Validate Balance if exceeding from withdrawal amount
                            if(accountWithdraw.validateWithdrawal(withdrawAccount.getAccountBalance(),withdrawAmount) == false){
                                // Process Withdrawal
                                if(newBalance < withdrawAccount.getAccountBalance()){
                                    withdrawAccount.setAccountBalance(newBalance);
                                    System.out.println(String.format("Withdraw Successful! [New Balance: %s]", withdrawAccount.getAccountBalance()));
                                } else{
                                    System.out.println("Withdrawal failed..");
                                }
                            } else{
                                System.out.println("Invalid: Withdrawal amount cannot exceed on current balance..");
                            }
                        }
                    }

                    break;

                case 4:
                    // Compute for Interest
                    Account interestMaintenance = new Maintenance();
                    Scanner interestIdScanner = new Scanner(System.in);
                    Integer interestAccountId = 0;
                    while(true){
                        System.out.println("Enter Account Id: ");
                        if(interestIdScanner.hasNextInt()){
                            interestAccountId = interestIdScanner.nextInt();
                            if(interestMaintenance.validateAccountId(interestAccountId, accountList) > 0){
                                break;
                            } else{
                                System.out.println("Account Id does not exist..");
                            }
                        } else{
                            System.out.println("Invalid input. Kindly retry..");
                            interestIdScanner.next();
                        }
                    }

                    for(Account interestAccount : accountList){
                        Account accountInterest = new Maintenance(interestAccountId, interestAccount.getAccountBalance());
                        if(interestAccountId == interestAccount.getAccountId()){
                            System.out.println(String.format("Computing interest for Account Id: %s ",interestAccount.getAccountId()));
                            double interest = accountInterest.computeInterest(interestAccount.getAccountBalance());
                            if(interest > 0){
                                System.out.println(String.format("Interest earned: %s",interest));
                                double newBalance = interestAccount.getAccountBalance() + interest;
                                if(newBalance > interestAccount.getAccountBalance()){
                                    interestAccount.setAccountBalance(newBalance);
                                    System.out.println(String.format("New Balance: %s",newBalance));
                                }
                            }
                        }
                    }
                    break;

                case 5:
                    // Display Registered Accounts
                    System.out.println("--- Account Information ---");
                    if(accountList.size() > 0){
                        for(Account accounts : accountList){
                            System.out.println(String.format("Account Number: %s",accounts.getAccountId()));
                            System.out.println(String.format("Holder Name: %s", accounts.getAccountName()));
                            String displayAccountType = accounts.getAccountType().toUpperCase();
                            System.out.println(String.format("Account Type: %s", displayAccountType));
                            System.out.println(String.format("Balance: %s", accounts.getAccountBalance()));
                            System.out.println("-----");
                        }
                    } else{
                        System.out.println("No registered accounts");
                        System.out.println("-----");
                    }
                    break;

                default:
                    System.out.println("Thank you!");
                    isComplete = false;
            }

        } while (isComplete);
        System.exit(0);
    }

}
