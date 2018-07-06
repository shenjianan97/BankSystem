/** 
* ClassName: SaverAccount
* Description: A type of bank account
* @author ShenJianan
*  
*/
public class SaverAccount extends Account {
    protected String noticeDate;
    protected double noticeAmount;

    public SaverAccount(int accountNum, int PIN, String name, String address, double overdraftLimit, double balance, double unclearFunds, boolean isSuspended, boolean isActive, boolean noticeNeeded, String noticeDate) {
        super(accountNum, PIN, name, address, overdraftLimit, balance, unclearFunds, isSuspended, isActive, noticeNeeded);
        this.noticeDate = noticeDate;
    }

    @Override
    public boolean saveAccount() {
        int type = 1;
        String output = type + " " + accountNum + " " + PIN + " " + name + " " + address + " " + overdraftLimit + " " + balance + " " + unclearFunds + " " + isSuspended + " " + isActive + " " + noticeNeeded + " " + noticeDate;
        FileFunction.chaseWrite("BankAccount.txt", output);
        return false;
    }

    public String getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(String noticeDate) {
        this.noticeDate = noticeDate;
    }
}