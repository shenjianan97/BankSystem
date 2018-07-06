import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** 
* ClassName: SuspendUI
* Description: The UI of suspend function
* @author ShenJianan
*  
*/
public class SuspendUI extends JFrame implements ActionListener {
    int type = 2;
    ButtonGroup chooseEntrance = new ButtonGroup();
    JPanel accountNumPanel = new JPanel(), PINPanel = new JPanel(), buttonPanel = new JPanel(), suspendStatePanel = new JPanel();
    JLabel accountNumMessage = new JLabel("Input account number:"), PINMessage = new JLabel("Input PIN:"), suspendMessage = new JLabel("Choose suspend state:");
    JTextField accountNumField = new JTextField(6), PINField = new JTextField(6);
    JButton returnButton = new JButton("Return"), suspendButton = new JButton("Set");
    JRadioButton trueButton = new JRadioButton("True"), falseButton = new JRadioButton("False");

    public SuspendUI() {
        this.setLayout(new GridLayout(0, 1));
        this.setTitle("Suspend An Account");

        chooseEntrance.add(trueButton);
        chooseEntrance.add(falseButton);
        chooseEntrance.add(trueButton);
        trueButton.setSelected(true);

        trueButton.addActionListener(new TrueListener());
        falseButton.addActionListener(new FalseListener());

        suspendButton.setActionCommand("suspend");
        returnButton.setActionCommand("return");
        suspendButton.addActionListener(this);
        returnButton.addActionListener(this);

        accountNumPanel.add(accountNumMessage);
        accountNumPanel.add(accountNumField);

        PINPanel.add(PINMessage);
        PINPanel.add(PINField);

        buttonPanel.add(suspendButton);
        buttonPanel.add(returnButton);

        suspendStatePanel.add(suspendMessage);
        suspendStatePanel.add(trueButton);
        suspendStatePanel.add(falseButton);

        this.add(accountNumPanel);
        this.add(PINPanel);
        this.add(suspendStatePanel);
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
        if (e.getActionCommand() == "return") {
            new BankUI();
            this.dispose();
        }
        if (e.getActionCommand() == "suspend") {
            int accountNum = Integer.parseInt(accountNumField.getText());
            int PIN = Integer.parseInt(PINField.getText());
            BankControl.suspend(accountNum, PIN, type);
//            if(BankControl.suspend(accountNum, PIN, type)) {
//                JOptionPane.showMessageDialog(null, "Set suspend state Complete.", "Successful", JOptionPane.PLAIN_MESSAGE);
//            }else{
//                JOptionPane.showMessageDialog(null, "Account doesn't exist or PIN error!", "Warning", JOptionPane.WARNING_MESSAGE);
//            }
            new BankUI();
            this.dispose();
        }
    }

    class TrueListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            type = 2;
        }
    }

    class FalseListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            type = 1;
        }
    }
}
