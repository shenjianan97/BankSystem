/** 
* ClassName: JuniorAccount
* Description: A type of bank account
* @author ShenJianan
*  
*/
public class JuniorAccount extends Account {
    public JuniorAccount(int accountNum, int PIN, String name, String address, double balance, double unclearFunds, boolean isSuspended, boolean isActive, boolean noticeNeeded) {
        super(accountNum, PIN, name, address, 0.0, balance, unclearFunds, isSuspended, isActive, noticeNeeded);
    }

    @Override
    public boolean saveAccount() {
        int type = 2;
        String output = type + " " + accountNum + " " + PIN + " " + name + " " + address + " " + overdraftLimit + " " + balance + " " + unclearFunds + " " + isSuspended + " " + isActive + " " + noticeNeeded;
        FileFunction.chaseWrite("BankAccount.txt", output);
        return false;
    }
}