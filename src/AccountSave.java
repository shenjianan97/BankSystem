import java.util.ArrayList;

/** 
* ClassName: AccountSave
* Description: A public class which has array lists of different account types to make them accessible to all classes in the package
* @author ShenJianan
*  
*/
public class AccountSave {
    public static ArrayList<SaverAccount> saverList = new ArrayList<SaverAccount>();
    public static ArrayList<JuniorAccount> juniorList = new ArrayList<JuniorAccount>();
    public static ArrayList<CurrentAccount> currentList = new ArrayList<CurrentAccount>();
}