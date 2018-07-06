import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/** 
* ClassName: CloseUI
* Description: The UI of close function 
* @author ShenJianan
*  
*/
public class CloseUI extends JFrame implements ActionListener {
    JPanel accountNumPanel = new JPanel(), PINPanel = new JPanel(), buttonPanel = new JPanel();
    JLabel accountNumMessage = new JLabel("Input account number:"), PINMessage = new JLabel("Input PIN:");
    JTextField accountNumField = new JTextField(6), PINField = new JTextField(6);
    JButton returnButton = new JButton("Return"), closeButton = new JButton("Close");

    public CloseUI() {
        this.setLayout(new GridLayout(0, 1));
        this.setTitle("Close An Account");

        closeButton.setActionCommand("close");
        returnButton.setActionCommand("return");
        closeButton.addActionListener(this);
        returnButton.addActionListener(this);

        accountNumPanel.add(accountNumMessage);
        accountNumPanel.add(accountNumField);

        PINPanel.add(PINMessage);
        PINPanel.add(PINField);

        buttonPanel.add(closeButton);
        buttonPanel.add(returnButton);

        this.add(accountNumPanel);
        this.add(PINPanel);
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
        if (e.getActionCommand() == "close") {
            int accountNum = Integer.parseInt(accountNumField.getText());
            int PIN = Integer.parseInt(PINField.getText());
            if (BankControl.close(accountNum, PIN)) {
                JOptionPane.showMessageDialog(null, "Close Complete.", "Successful", JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Account doesn't exist or PIN error!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
            new BankUI();
            this.dispose();
        }
    }
}
