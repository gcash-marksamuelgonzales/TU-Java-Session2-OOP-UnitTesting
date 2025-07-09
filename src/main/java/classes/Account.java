package classes;


public abstract class Account implements Process {
    private Integer accountId;
    private String accountName;
    private Double accountBalance;
    private String accountType;

    public Account(Integer accountId, String accountName, Double accountBalance, String accountType){
        this.accountId = accountId;
        this.accountName = accountName;
        this.accountBalance = accountBalance;
        this.accountType = accountType;
    }

    public Account(Integer accountId, Double accountBalance){
        this.accountId = accountId;
        this.accountBalance = accountBalance;
    }

    public Account(){

    }

    public void clear(){
        this.accountId = 0;
        this.accountName = null;
        this.accountBalance = 0.00;
        this.accountType = null;
    }

    // Getter & Setter
    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

}
