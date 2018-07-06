import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** 
* ClassName: DepositUI
* Description: The UI of deposit function 
* @author ShenJianan
*  
*/
public class DepositUI extends JFrame implements ActionListener {
    ButtonGroup chooseEntrance = new ButtonGroup();
    int type = 1;
    JRadioButton clearButton = new JRadioButton("Clear"), unclearButton = new JRadioButton("Uncleared");
    JPanel accountNumPanel = new JPanel(), amountPanel = new JPanel(), typePanel = new JPanel(), buttonPanel = new JPanel();
    JLabel accountNumMessage = new JLabel("Input your account number:"), amountMessage = new JLabel("Input deposit amount:");
    JButton depositButton = new JButton("Deposit"), returnButton = new JButton("Return");
    JTextField accountNumField = new JTextField(6), depositField = new JTextField(6);

    public DepositUI() {

        this.setLayout(new GridLayout(0, 1));
        this.setTitle("Deposit Funds");

        chooseEntrance.add(clearButton);
        chooseEntrance.add(unclearButton);
        clearButton.addActionListener(new ClearListener());
        unclearButton.addActionListener(new UnclearListener());
        clearButton.setSelected(true);

        typePanel.add(clearButton);
        typePanel.add(unclearButton);
        accountNumPanel.add(accountNumMessage);
        accountNumPanel.add(accountNumField);
        amountPanel.add(amountMessage);
        amountPanel.add(depositField);
        buttonPanel.add(depositButton);
        buttonPanel.add(returnButton);

        depositButton.setActionCommand("deposit");
        returnButton.setActionCommand("return");
        depositButton.addActionListener(this);
        returnButton.addActionListener(this);

        this.add(accountNumPanel);
        this.add(amountPanel);
        this.add(typePanel);
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
    * @param e action event
    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent) 
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("deposit")) {
            int accountNum = Integer.parseInt(accountNumField.getText());
            int amount = Integer.parseInt(depositField.getText());
            BankControl.deposit(accountNum, amount, type);
//           if(BankControl.deposit(accountNum, amount, type)){
//               JOptionPane.showMessageDialog(null, "Deposit successfully!", "Successful", JOptionPane.PLAIN_MESSAGE);
//           }else{
//               JOptionPane.showMessageDialog(null, "Account doesn't exist or PIN error!", "Warning", JOptionPane.WARNING_MESSAGE);
//           }
        }
        if (e.getActionCommand().equals("return")) {
            new BankUI();
            this.dispose();
        }
    }

    class ClearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            type = 1;
        }
    }

    class UnclearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            type = 2;
        }
    }
}
