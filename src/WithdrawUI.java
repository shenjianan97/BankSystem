import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** 
* ClassName: WithdrawUI
* Description: Realize the UI of withdraw function
* @author ShenJianan
*  
*/
public class WithdrawUI extends JFrame implements ActionListener {
    JPanel accountNumPanel = new JPanel(), PINPanel = new JPanel(), withdrawPanel = new JPanel(), buttonPanel = new JPanel();
    JLabel accountNumMessage = new JLabel("Input account number:"), PINMessage = new JLabel("Input PIN:"), withdrawMessage = new JLabel("Input withdraw amount:");
    JTextField accountNumField = new JTextField(6), PINField = new JTextField(6), withdrawField = new JTextField(6);
    JButton withdrawButton = new JButton("Withdraw"), returnButton = new JButton("Return");

    /** 
    * <p>Title: </p> 
    * <p>Description: Initiate the withdraw UI and add action listeners</p>  
    */
    public WithdrawUI() {
        this.setLayout(new GridLayout(0, 1));
        this.setTitle("Withdraw Funds");

        returnButton.setActionCommand("return");
        withdrawButton.setActionCommand("withdraw");
        returnButton.addActionListener(this);
        withdrawButton.addActionListener(this);

        accountNumPanel.add(accountNumMessage);
        accountNumPanel.add(accountNumField);

        PINPanel.add(PINMessage);
        PINPanel.add(PINField);

        withdrawPanel.add(withdrawMessage);
        withdrawPanel.add(withdrawField);

        buttonPanel.add(withdrawButton);
        buttonPanel.add(returnButton);

        this.add(accountNumPanel);
        this.add(PINPanel);
        this.add(withdrawPanel);
        this.add(buttonPanel);

        this.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension size = this.getSize();
        int wei = (screenSize.width - size.width) / 2;
        int hei = (screenSize.height - size.height) / 2;
        this.setLocation(wei, hei);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
    * Title: actionPerformed
    * @param e 
    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent) 
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("withdraw")) {
            //System.out.println("withdraw xxxxxx");
            int accountNum = Integer.parseInt(accountNumField.getText());
            int PIN = Integer.parseInt(PINField.getText());
            int amount = Integer.parseInt(withdrawField.getText());
            BankControl.withdraw(accountNum, PIN, amount);
//            if(BankControl.withdraw(accountNum, PIN, amount)){
//                JOptionPane.showMessageDialog(null, "Withdraw Complete.", "Successful", JOptionPane.PLAIN_MESSAGE);
//            }else{
//                JOptionPane.showMessageDialog(null, "Account doesn't exist or PIN error!", "Warning", JOptionPane.WARNING_MESSAGE);
//            }
            new BankUI();
            this.dispose();
        }
        if (e.getActionCommand().equals("return")) {
            new BankUI();
            this.dispose();
        }
    }
}