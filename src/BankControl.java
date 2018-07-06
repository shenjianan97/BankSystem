import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/** 
* ClassName: BankControl
* Description: The realizing of different functions of the bank system
* @author ShenJianan
*  
*/
public class BankControl {
    /** 
    * Title: openAccount
    * Description: The realization of the open account function
    * @param accountNum
    * @param PIN
    * @param type
    * @param name
    * @param address
    * @param amount
    * @return boolean
    */
    public static boolean openAccount(int accountNum, int PIN, int type, String name, String address, int amount) {
        try {
            if (type == 1) {
                SaverAccount account = new SaverAccount(accountNum, PIN, name, address, 500, amount, 0, false, true, true, "00000000");
                account.saveAccount();
                AccountSave.saverList.add(account);
            } else if (type == 2) {
                JuniorAccount account = new JuniorAccount(accountNum, PIN, name, address, amount, 0, false, true, false);
                account.saveAccount();
                AccountSave.juniorList.add(account);
            } else if (type == 3) {
                CurrentAccount account = new CurrentAccount(accountNum, PIN, name, address, 500, amount, 0, false, true, false);
                account.saveAccount();
                AccountSave.currentList.add(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /** 
    * Title: deposit
    * Description: The realization of the deposit function
    * @param accountNum
    * @param amount
    * @param type
    * @return boolean
    */
    public static boolean deposit(int accountNum, int amount, int type) {
        if (type == 1) {
            System.out.println("deposit");
            String input = JOptionPane.showInputDialog(null, "You  have to deposit more than 200 dollars!");
            if(Integer.parseInt(input) < 200){
                JOptionPane.showMessageDialog(null, "Wrong", "Warning", JOptionPane.WARNING_MESSAGE);
                return true;
            }
            for (int i = 0; i < AccountSave.saverList.size(); i++) {
                SaverAccount account = AccountSave.saverList.get(i);
                System.out.println("Suspend state is " + account.isSuspended());
                if (account.getAccountNum() == accountNum) {
                    if (account.isSuspended()) {
                        JOptionPane.showMessageDialog(null, "This account has benn suspended!", "Warning", JOptionPane.WARNING_MESSAGE);
                        return true;
                    }
                    account.addBalance(amount);
                    AccountSave.saverList.set(i, account);
                    BankControl.rewriteFile();
                    JOptionPane.showMessageDialog(null, "Deposit successfully!", "Successful", JOptionPane.PLAIN_MESSAGE);
                    return true;
                }
            }
            for (int i = 0; i < AccountSave.juniorList.size(); i++) {
                JuniorAccount account = AccountSave.juniorList.get(i);
                System.out.println("Suspend state is " + account.isSuspended());
                if (account.getAccountNum() == accountNum) {
                    if (account.isSuspended()) {
                        JOptionPane.showMessageDialog(null, "This account has benn suspended!", "Warning", JOptionPane.WARNING_MESSAGE);
                        return true;
                    }
                    account.addBalance(amount);
                    AccountSave.juniorList.set(i, account);
                    BankControl.rewriteFile();
                    JOptionPane.showMessageDialog(null, "Deposit successfully!", "Successful", JOptionPane.PLAIN_MESSAGE);
                    return true;
                }
            }
            for (int i = 0; i < AccountSave.currentList.size(); i++) {
                CurrentAccount account = AccountSave.currentList.get(i);
                //System.out.println("suspend state: " + account.isSuspended());
                if (account.getAccountNum() == accountNum) {
                    if (account.isSuspended()) {
                        JOptionPane.showMessageDialog(null, "This account has benn suspended!", "Warning", JOptionPane.WARNING_MESSAGE);
                        return true;
                    }
                    account.addBalance(amount);
                    AccountSave.currentList.set(i, account);
                    BankControl.rewriteFile();
                    JOptionPane.showMessageDialog(null, "Deposit successfully!", "Successful", JOptionPane.PLAIN_MESSAGE);
                    return true;
                }
            }
        } else if (type == 2) {
            System.out.println("unclear");
            for (int i = 0; i < AccountSave.saverList.size(); i++) {
                SaverAccount account = AccountSave.saverList.get(i);
                if (account.getAccountNum() == accountNum) {
                    if (account.isSuspended()) {
                        JOptionPane.showMessageDialog(null, "This account has benn suspended!", "Warning", JOptionPane.WARNING_MESSAGE);
                        return true;
                    }
                    account.addUnclearFunds(amount);
                    AccountSave.saverList.set(i, account);
                    BankControl.rewriteFile();
                    JOptionPane.showMessageDialog(null, "Deposit successfully!", "Successful", JOptionPane.PLAIN_MESSAGE);
                    return true;
                }
            }
            for (int i = 0; i < AccountSave.juniorList.size(); i++) {
                JuniorAccount account = AccountSave.juniorList.get(i);
                if (account.getAccountNum() == accountNum) {
                    if (account.isSuspended()) {
                        JOptionPane.showMessageDialog(null, "This account has benn suspended!", "Warning", JOptionPane.WARNING_MESSAGE);
                        return true;
                    }
                    account.addUnclearFunds(amount);
                    AccountSave.juniorList.set(i, account);
                    BankControl.rewriteFile();
                    JOptionPane.showMessageDialog(null, "Deposit successfully!", "Successful", JOptionPane.PLAIN_MESSAGE);
                    return true;
                }
            }
            for (int i = 0; i < AccountSave.currentList.size(); i++) {
                CurrentAccount account = AccountSave.currentList.get(i);
                if (account.getAccountNum() == accountNum) {
                    if (account.isSuspended()) {
                        JOptionPane.showMessageDialog(null, "This account has benn suspended!", "Warning", JOptionPane.WARNING_MESSAGE);
                        return true;
                    }
                    account.addUnclearFunds(amount);
                    AccountSave.currentList.set(i, account);
                    BankControl.rewriteFile();
                    JOptionPane.showMessageDialog(null, "Deposit successfully!", "Successful", JOptionPane.PLAIN_MESSAGE);
                    return true;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Account doesn't exist or PIN error!", "Warning", JOptionPane.WARNING_MESSAGE);
        return false;
    }

    /** 
    * Title: clear
    * Description: The realization of the clear function
    * @param accountNum
    * @param PIN
    * @return boolean
    */
    public static boolean clear(int accountNum, int PIN) {
        for (int i = 0; i < AccountSave.saverList.size(); i++) {
            SaverAccount account = AccountSave.saverList.get(i);
            if (account.getAccountNum() == accountNum) {
                if (account.getPIN() == PIN) {
                    if (account.isSuspended()) {
                        JOptionPane.showMessageDialog(null, "This account has benn suspended!", "Warning", JOptionPane.WARNING_MESSAGE);
                        return true;
                    }
                    if (account.getUnclearFunds() == 0) {
                        JOptionPane.showMessageDialog(null, "You don't have funds to be cleared!", "Warning", JOptionPane.WARNING_MESSAGE);
                        return true;
                    }
                    account.clearUnclearFunds();
                    AccountSave.saverList.set(i, account);
                    BankControl.rewriteFile();
                    return true;
                }
            }
        }
        for (int i = 0; i < AccountSave.juniorList.size(); i++) {
            JuniorAccount account = AccountSave.juniorList.get(i);
            if (account.getAccountNum() == accountNum) {
                if (account.getPIN() == PIN) {
                    if (account.isSuspended()) {
                        JOptionPane.showMessageDialog(null, "This account has benn suspended!", "Warning", JOptionPane.WARNING_MESSAGE);
                        return true;
                    }
                    //System.out.println("aaa");
                    if (account.getUnclearFunds() == 0) {
                        JOptionPane.showMessageDialog(null, "You don't have funds to be cleared!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    account.clearUnclearFunds();
                    AccountSave.juniorList.set(i, account);
                    BankControl.rewriteFile();
                    return true;
                }
            }
        }
        for (int i = 0; i < AccountSave.currentList.size(); i++) {
            CurrentAccount account = AccountSave.currentList.get(i);
            if (account.getAccountNum() == accountNum) {
                if (account.getPIN() == PIN) {
                    if (account.isSuspended()) {
                        JOptionPane.showMessageDialog(null, "This account has benn suspended!", "Warning", JOptionPane.WARNING_MESSAGE);
                        return true;
                    }
                    if (account.getUnclearFunds() == 0) {
                        JOptionPane.showMessageDialog(null, "You don't have funds to be cleared!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    //System.out.println("aaa");
                    account.clearUnclearFunds();
                    AccountSave.currentList.set(i, account);
                    BankControl.rewriteFile();
                    return true;
                }
            }
        }
        return false;
    }

    /** 
    * Title: withdraw
    * Description: The realization of the withdraw function
    * @param accountNum
    * @param PIN
    * @param amount
    * @return boolean
    */
    public static boolean withdraw(int accountNum, int PIN, int amount) {
        for (int i = 0; i < AccountSave.saverList.size(); i++) {
            SaverAccount account = AccountSave.saverList.get(i);
            if (account.getAccountNum() == accountNum) {
                if (account.getPIN() == PIN) {
                    System.out.println("is needed" + account.isNoticeNeeded());
                    if (account.isSuspended()) {
                        JOptionPane.showMessageDialog(null, "This account has benn suspended!", "Warning", JOptionPane.WARNING_MESSAGE);
                        return true;
                    }
                    if (account.getBalance() + account.getOverdraftLimit() - amount <= 0) {
                        JOptionPane.showMessageDialog(null, "You can't overdraw!", "Warning", JOptionPane.WARNING_MESSAGE);
                        return true;
                    }
                    if (account.isNoticeNeeded()) {
                        JOptionPane.showMessageDialog(null, "Please withdraw 7 days later!", "Warning", JOptionPane.WARNING_MESSAGE);
                        account.setNoticeDate(BankControl.generateDateString(7));
                        account.setNoticeNeeded(false);
                        BankControl.rewriteFile();
                        return true;
                    } else if (Integer.parseInt(account.getNoticeDate()) >= Integer.parseInt(BankControl.generateDateString(0))) {
                        JOptionPane.showMessageDialog(null, "Please wait several more days!", "Warning", JOptionPane.WARNING_MESSAGE);
                        return true;
                    } else if (Integer.parseInt(account.getNoticeDate()) <= Integer.parseInt(BankControl.generateDateString(0))) {
                        account.setNoticeNeeded(true);
                    }
                    //System.out.println("aaa");
                    System.out.println("notice date is " + account.getNoticeDate());
                    System.out.println("today is  " + BankControl.generateDateString(0));
                    account.addBalance(-amount);
                    AccountSave.saverList.set(i, account);
                    BankControl.rewriteFile();
                    JOptionPane.showMessageDialog(null, "Withdraw Complete.", "Successful", JOptionPane.PLAIN_MESSAGE);
                    return true;
                }
            }
        }
        for (int i = 0; i < AccountSave.juniorList.size(); i++) {
            JuniorAccount account = AccountSave.juniorList.get(i);
            if (account.getAccountNum() == accountNum) {
                if (account.getPIN() == PIN) {
                    if (account.isSuspended()) {
                        JOptionPane.showMessageDialog(null, "This account has benn suspended!", "Warning", JOptionPane.WARNING_MESSAGE);
                        return true;
                    }
                    if (account.getBalance() + account.getOverdraftLimit() - amount <= 0) {
                        JOptionPane.showMessageDialog(null, "You can't overdraw!", "Warning", JOptionPane.WARNING_MESSAGE);
                        return true;
                    }
                    //System.out.println("aaa");
                    account.addBalance(-amount);
                    AccountSave.juniorList.set(i, account);
                    BankControl.rewriteFile();
                    JOptionPane.showMessageDialog(null, "Withdraw Complete.", "Successful", JOptionPane.PLAIN_MESSAGE);
                    return true;
                }
            }
        }
        for (int i = 0; i < AccountSave.currentList.size(); i++) {
            CurrentAccount account = AccountSave.currentList.get(i);
            if (account.getAccountNum() == accountNum) {
                if (account.getPIN() == PIN) {
                    if (account.isSuspended()) {
                        JOptionPane.showMessageDialog(null, "This account has benn suspended!", "Warning", JOptionPane.WARNING_MESSAGE);
                        return true;
                    }
                    if (account.getBalance() + account.getOverdraftLimit() - amount <= 0) {
                        JOptionPane.showMessageDialog(null, "You can't overdraw!", "Warning", JOptionPane.WARNING_MESSAGE);
                        return true;
                    }
                    //System.out.println("aaa");
                    account.addBalance(-amount);
                    AccountSave.currentList.set(i, account);
                    BankControl.rewriteFile();
                    JOptionPane.showMessageDialog(null, "Withdraw Complete.", "Successful", JOptionPane.PLAIN_MESSAGE);
                    return true;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Account doesn't exist or PIN error!", "Warning", JOptionPane.WARNING_MESSAGE);
        return false;
    }

    /** 
    * Title: close
    * Description: The realization of the close account function
    * @param accountNum
    * @param PIN
    * @return boolean
    */
    public static boolean close(int accountNum, int PIN) {
        for (int i = 0; i < AccountSave.saverList.size(); i++) {
            SaverAccount account = AccountSave.saverList.get(i);
            if (account.getAccountNum() == accountNum) {
                if (account.getPIN() == PIN) {
                    //System.out.println("aaa");
                    AccountSave.saverList.remove(i);
                    BankControl.rewriteFile();
                    return true;
                }
            }
        }
        for (int i = 0; i < AccountSave.juniorList.size(); i++) {
            JuniorAccount account = AccountSave.juniorList.get(i);
            if (account.getAccountNum() == accountNum) {
                if (account.getPIN() == PIN) {
                    //System.out.println("aaa");
                    AccountSave.juniorList.remove(i);
                    BankControl.rewriteFile();
                    return true;
                }
            }
        }
        for (int i = 0; i < AccountSave.currentList.size(); i++) {
            CurrentAccount account = AccountSave.currentList.get(i);
            if (account.getAccountNum() == accountNum) {
                if (account.getPIN() == PIN) {
                    //System.out.println("aaa");
                    AccountSave.currentList.remove(i);
                    BankControl.rewriteFile();
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean suspend(int accountNum, int PIN, int state) {
        boolean isSuspended = true;
        if (state == 1) {
            isSuspended = false;
        } else if (state == 2) {
            isSuspended = true;
        }
        for (int i = 0; i < AccountSave.saverList.size(); i++) {
            SaverAccount account = AccountSave.saverList.get(i);
            if (account.getAccountNum() == accountNum) {
                if (account.getPIN() == PIN) {
                    account.setSuspended(isSuspended);
                    //System.out.println("aaa");
                    AccountSave.saverList.set(i, account);
                    BankControl.rewriteFile();
                    JOptionPane.showMessageDialog(null, "Set suspend state Complete.", "Successful", JOptionPane.PLAIN_MESSAGE);
                    return true;
                }
            }
        }
        for (int i = 0; i < AccountSave.juniorList.size(); i++) {
            JuniorAccount account = AccountSave.juniorList.get(i);
            if (account.getAccountNum() == accountNum) {
                if (account.getPIN() == PIN) {
                    account.setSuspended(isSuspended);
                    //System.out.println("aaa");
                    AccountSave.juniorList.set(i, account);
                    BankControl.rewriteFile();
                    JOptionPane.showMessageDialog(null, "Set suspend state Complete.", "Successful", JOptionPane.PLAIN_MESSAGE);
                    return true;
                }
            }
        }
        for (int i = 0; i < AccountSave.currentList.size(); i++) {
            CurrentAccount account = AccountSave.currentList.get(i);
            if (account.getAccountNum() == accountNum) {
                if (account.getPIN() == PIN) {
                    account.setSuspended(isSuspended);
                    //System.out.println("aaa");
                    AccountSave.currentList.set(i, account);
                    BankControl.rewriteFile();
                    JOptionPane.showMessageDialog(null, "Set suspend state Complete.", "Successful", JOptionPane.PLAIN_MESSAGE);
                    return true;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "Account doesn't exist or PIN error!", "Warning", JOptionPane.WARNING_MESSAGE);
        return false;
    }

    /** 
    * Title: parse
    * Description: convert the birth String to Date
    * @param strDate
    * @return Date
    */
    public static Date parse(String strDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(strDate);
    }

    /** 
    * Title: getAge
    * Description: Calculate out the age
    * @param birthDay
    * @return int
    */
    public static int getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthDay)) {
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH);
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
        cal.setTime(birthDay);

        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;
            } else {
                age--;
            }
        }
        return age;
    }

    /** 
    * Title: generateNumber
    * Description: generate the account number
    * @return int
    */
    public static int generateNumber() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        /**generate random numbers and add to the string
         */
        for (int i = 0; i < 8; i++) {
            str.append(random.nextInt(10));
        }
        //convert the string bank to integer
        int num = Integer.parseInt(str.toString());
        return num;
    }

    /** 
    * Title: generatePIN
    * Description: generate the PIN number
    * @return int
    */
    public static int generatePIN() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        /**generate random numbers and add to the string
         */
        for (int i = 0; i < 4; i++) {
            str.append(random.nextInt(9) + 1);
        }
        int num = Integer.parseInt(str.toString());
        return num;
    }

    /** 
    * Title: init
    * Description: An initilisation function to read bank accounts from file and add to array lists
    * @return boolean
    */
    public static boolean init() {
        AccountSave.saverList = new ArrayList<>();
        AccountSave.juniorList = new ArrayList<>();
        AccountSave.currentList = new ArrayList<>();
        BufferedReader accountReader;
        String readLine;
        String[] splitLine;
        File bankAccount = new File("BankAccount.txt");
        if(bankAccount.exists() == false){
            FileFunction.writeFile("BankAccount.txt", "");
        }
        try {
            accountReader = new BufferedReader(new FileReader(bankAccount));
            while ((readLine = accountReader.readLine()) != null) {
                splitLine = readLine.split(" ");
                //System.out.println(splitLine[0]);
                if (splitLine[0].equals("1")) {
                    SaverAccount account = new SaverAccount(Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]), splitLine[3], splitLine[4], Double.parseDouble(splitLine[5]), Double.parseDouble(splitLine[6]), Double.parseDouble(splitLine[7]), splitLine[8].equals("true"), splitLine[9].equals("true"), splitLine[10].equals("true"), splitLine[11]);
                    AccountSave.saverList.add(account);
                } else if (splitLine[0].equals("2")) {
                    JuniorAccount account = new JuniorAccount(Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]), splitLine[3], splitLine[4], Double.parseDouble(splitLine[6]), Double.parseDouble(splitLine[7]), splitLine[8].equals("true"), splitLine[9].equals("true"), splitLine[10].equals("true"));
                    AccountSave.juniorList.add(account);
                } else if (splitLine[0].equals("3")) {
                    CurrentAccount account = new CurrentAccount(Integer.parseInt(splitLine[1]), Integer.parseInt(splitLine[2]), splitLine[3], splitLine[4], Double.parseDouble(splitLine[5]), Double.parseDouble(splitLine[6]), Double.parseDouble(splitLine[7]), splitLine[8].equals("true"), splitLine[9].equals("true"), splitLine[10].equals("true"));
                    AccountSave.currentList.add(account);
                }
                //System.out.println("suspend state " + splitLine[6]);
            }
            //System.out.println(AccountSave.saverList.get(0).getAccountNum());
            accountReader.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /** 
    * Title: rewriteFile
    * Description: rewrite the bank accounts from array lists to the file
    * @return boolean
    */
    public static boolean rewriteFile() {
        FileFunction.writeFile("BankAccount.txt", "");
        for (SaverAccount account : AccountSave.saverList) {
            account.saveAccount();
        }
        for (JuniorAccount account : AccountSave.juniorList) {
            account.saveAccount();
        }
        for (CurrentAccount account : AccountSave.currentList) {
            account.saveAccount();
        }
        return true;
    }

    /** 
    * Title: generateDateString
    * Description: return the date in string
    * @param days
    * @return String
    */
    public static String generateDateString(int days) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, days);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        String dayStr, monthStr;
        if ((day >= 1) && (day <= 9)) {
            dayStr = "0" + day;
        } else {
            dayStr = "" + day;
        }
        if (month >= 1 && month <= 9) {
            monthStr = "0" + month;
        } else {
            monthStr = "" + day;
        }
        return year + monthStr + dayStr;
    }
}