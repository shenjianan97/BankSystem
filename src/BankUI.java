import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** 
* ClassName: BankUI
* Description: Main UI of the bank system
* @author ShenJianan
*  
*/
public class BankUI extends JFrame implements ActionListener {
    JButton openAccountButton, depositFundsButton, clearFundsButton, withdrawFundsButton, suspendAccountButton, closeAccountButton;

    public BankUI() {
        new Thread() {
            @Override
            public void run() {
                BankControl.init();
                System.out.println("init complete");
            }
        }.start();


        this.setTitle("Banking System");
        openAccountButton = new JButton("Open An Account");
        depositFundsButton = new JButton("Deposit Funds");
        clearFundsButton = new JButton("Clear Funds");
        withdrawFundsButton = new JButton("Withdraw Funds");
        suspendAccountButton = new JButton("Suspend An Account");
        closeAccountButton = new JButton("Close An Account");

        openAccountButton.setActionCommand("open");
        depositFundsButton.setActionCommand("deposit");
        clearFundsButton.setActionCommand("clear");
        withdrawFundsButton.setActionCommand("withdraw");
        suspendAccountButton.setActionCommand("suspend");
        closeAccountButton.setActionCommand("close");

        openAccountButton.addActionListener(this);
        depositFundsButton.addActionListener(this);
        clearFundsButton.addActionListener(this);
        withdrawFundsButton.addActionListener(this);
        suspendAccountButton.addActionListener(this);
        closeAccountButton.addActionListener(this);

        this.setLayout(new GridLayout(0, 3));
        this.add(new JLabel(""));
        this.add(openAccountButton);
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(depositFundsButton);
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(clearFundsButton);
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(withdrawFundsButton);
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(suspendAccountButton);
        this.add(new JLabel(""));
        this.add(new JLabel(""));
        this.add(closeAccountButton);
        this.add(new JLabel(""));
        this.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension size = this.getSize();
        int wei = (screenSize.width - size.width) / 2;
        int hei = (screenSize.height - size.height) / 2;
        this.setLocation(wei, hei);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /*
    * Title: actionPerformed
    * @param e 
    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent) 
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("open")) {
            //System.out.println("you press open");
            new OpenUI();
            this.dispose();
        }
        if (e.getActionCommand().equals("deposit")) {
            //System.out.println("you press deposit");
            new DepositUI();
            this.dispose();
        }
        if (e.getActionCommand().equals("clear")) {
            //System.out.println("you press clear");
            new ClearUI();
            this.dispose();
        }
        if (e.getActionCommand().equals("withdraw")) {
            //System.out.println("you press withdraw");
            new WithdrawUI();
            this.dispose();
        }
        if (e.getActionCommand().equals("suspend")) {
            //System.out.println("you press suspend");
            new SuspendUI();
            this.dispose();
        }
        if (e.getActionCommand().equals("close")) {
            //System.out.println("you press close");
            new CloseUI();
            this.dispose();
        }
    }

    /** 
    * Title: main 
    * Description: the main function
    * @param args
    */
    public static void main(String[] args) {
        new BankUI();
    }
}
