/** 
* ClassName: Account
* Description: The super class of different bank accounts
* @author ShenJianan
*  
*/
public abstract class Account {
    protected int accountNum;
    protected int PIN;
    protected double overdraftLimit;
    protected boolean isSuspended;
    protected boolean isActive;
    protected boolean noticeNeeded;
    protected double balance = 0;
    protected double unclearFunds = 0;
    protected String name;
    protected String address;

    public Account(int accountNum, int PIN, String name, String address, double overdraftLimit, double balance, double unclearFunds, boolean isSuspended, boolean isActive, boolean noticeNeeded) {
        this.name = name;
        this.address = address;
        this.accountNum = accountNum;
        this.PIN = PIN;
        this.overdraftLimit = overdraftLimit;
        this.isSuspended = isSuspended;
        this.isActive = isActive;
        this.noticeNeeded = noticeNeeded;
        this.balance = balance;
        this.unclearFunds = unclearFunds;
    }

    public int getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(int accountNum) {
        this.accountNum = accountNum;
    }

    public int getPIN() {
        return PIN;
    }

    public void setPIN(int PIN) {
        this.PIN = PIN;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    public void setSuspended(boolean suspended) {
        isSuspended = suspended;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isNoticeNeeded() {
        return noticeNeeded;
    }

    public void setNoticeNeeded(boolean noticeNeeded) {
        this.noticeNeeded = noticeNeeded;
    }

    public double getBalance() {
        return balance;
    }

    public void addBalance(double balance) {
        this.balance += balance;
    }

    public void clearUnclearFunds() {
        double amount = unclearFunds;
        unclearFunds = 0;
        addBalance(amount);
    }

    public void addUnclearFunds(double unclearFunds) {
        this.unclearFunds += unclearFunds;
    }

    public double getUnclearFunds() {
        return unclearFunds;
    }

    public abstract boolean saveAccount();
}
