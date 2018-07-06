/** 
* ClassName: CurrentAccount
* Description: A type of bank account 
* @author ShenJianan
*  
*/
public class CurrentAccount extends Account {
    public CurrentAccount(int accountNum, int PIN, String name, String address, double overdraftLimit, double balance, double unclearFunds, boolean isSuspended, boolean isActive, boolean noticeNeeded) {
        super(accountNum, PIN, name, address, overdraftLimit, balance, unclearFunds, isSuspended, isActive, noticeNeeded);
    }

    @Override
    public boolean saveAccount() {
        int type = 3;
        String output = type + " " + accountNum + " " + PIN + " " + name + " " + address + " " + overdraftLimit + " " + balance + " " + unclearFunds + " " + isSuspended + " " + isActive + " " + noticeNeeded;
        FileFunction.chaseWrite("BankAccount.txt", output);
        return false;
    }
}