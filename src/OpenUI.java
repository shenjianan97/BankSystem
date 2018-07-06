import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Calendar;

/** 
* ClassName: OpenUI
* Description: The UI of open function
* @author ShenJianan
*  
*/
public class OpenUI extends JFrame implements ActionListener {
    ButtonGroup chooseEntrance;
    int type = 1;
    int y, mo, d;
    JRadioButton typeSaverButton, typeJuniorButton, typeCurrentButton;
    JPanel namePanel, addressPanel, birthPanel, accountTypePanel, contentPane, buttonPanel;
    JButton openButton, returnButton;
    JLabel welcomeMessage, nameMessage, addressMessage, birthMessage, typeMessage;
    JTextField nameField, addressField;
    String name, address;

    public OpenUI() {
        this.setLayout(new GridLayout(0, 1));
        this.setTitle("Open An Account");

        typeSaverButton = new JRadioButton("Saver");
        typeJuniorButton = new JRadioButton("Junior");
        typeCurrentButton = new JRadioButton("Current");

        chooseEntrance = new ButtonGroup();
        chooseEntrance.add(typeSaverButton);
        chooseEntrance.add(typeJuniorButton);
        chooseEntrance.add(typeCurrentButton);
        typeSaverButton.addActionListener(new SaverListener());
        typeJuniorButton.addActionListener(new JuniorListener());
        typeCurrentButton.addActionListener(new CurrentListener());
        typeSaverButton.setSelected(true);

        welcomeMessage = new JLabel("Welcome to open an account", JLabel.CENTER);
        welcomeMessage.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
        nameMessage = new JLabel("Input your name:");
        addressMessage = new JLabel("Input your address:");
        birthMessage = new JLabel("Select your birthday:");
        typeMessage = new JLabel("Choose the account type:");

        namePanel = new JPanel();
        addressPanel = new JPanel();
        birthPanel = new JPanel();
        accountTypePanel = new JPanel();
        buttonPanel = new JPanel();


        nameField = new JTextField(6);
        addressField = new JTextField(6);

        openButton = new JButton("Open");
        returnButton = new JButton("Return");
        openButton.addActionListener(this);
        returnButton.addActionListener(this);
        openButton.setActionCommand("open");
        returnButton.setActionCommand("return");

        contentPane = new JPanel();
        contentPane.setLayout(new FlowLayout());
        final JComboBox year = new JComboBox();
        year.setModel(new DefaultComboBoxModel(getModel(1950, 2020)));
        contentPane.add(year);
        final JComboBox month = new JComboBox();
        month.setModel(new DefaultComboBoxModel(getModel(1, 12)));
        contentPane.add(month);
        final JComboBox day = new JComboBox();
        contentPane.add(day);
        year.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                setDay(year, month, day);
            }
        });
        month.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                setDay(year, month, day);
            }
        });
        setDay(year, month, day);
        day.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                d = Integer.parseInt((String) day.getSelectedItem());
            }
        });

        buttonPanel.add(openButton);
        buttonPanel.add(returnButton);
        namePanel.add(nameMessage);
        namePanel.add(nameField);
        addressPanel.add(addressMessage);
        addressPanel.add(addressField);
        birthPanel.add(birthMessage);
        birthPanel.add(contentPane);
        accountTypePanel.add(typeMessage);
        accountTypePanel.add(typeSaverButton);
        accountTypePanel.add(typeJuniorButton);
        accountTypePanel.add(typeCurrentButton);

        this.add(welcomeMessage);
        this.add(namePanel);
        this.add(addressPanel);
        this.add(birthPanel);
        this.add(accountTypePanel);
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

    /*
    * Title: actionPerformed
    * @param e 
    * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent) 
    */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("open")) {
            int i = JOptionPane.showConfirmDialog(null, "Is the person qualified?", "Qualification check", JOptionPane.YES_NO_OPTION);
            if(i == 1){
                JOptionPane.showMessageDialog(null, "You are not qualified!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String name = nameField.getText();
            String address = addressField.getText();
            if (type == 1) {
                System.out.println("saver");
                String birth = y + "-" + mo + "-" + d;
                int age = 0;
                try {
                    age = BankControl.getAge(BankControl.parse(birth));
                } catch (Exception except) {
                    except.printStackTrace();
                }
                if (age <= 0) {
                    JOptionPane.showMessageDialog(null, "Please choose the right age!", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    int accountNum = BankControl.generateNumber();
                    int PIN = BankControl.generatePIN();
                    String input = JOptionPane.showInputDialog(null, "Please save more than 50 dollars to open an account");
                    int amount = Integer.parseInt(input);
                    if (amount <= 50) {
                        JOptionPane.showMessageDialog(null, "The money you save is not enough to open an account!", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if (BankControl.openAccount(accountNum, PIN, type, name, address, amount)) {
                        JOptionPane.showMessageDialog(null, "Open account successfully!", "Successful", JOptionPane.PLAIN_MESSAGE);
                        JOptionPane.showMessageDialog(null, "Your accountNum is: " + accountNum, "Successful", JOptionPane.PLAIN_MESSAGE);
                        JOptionPane.showMessageDialog(null, "Your PIN is: " + PIN, "Successful", JOptionPane.PLAIN_MESSAGE);
                        new BankUI();
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Open account error!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } else if (type == 2) {
                System.out.println("junior");
                String birth = y + "-" + mo + "-" + d;
                int age = 0;
                try {
                    age = BankControl.getAge(BankControl.parse(birth));
                } catch (Exception except) {
                    except.printStackTrace();
                }
                if (age >= 16) {
                    JOptionPane.showMessageDialog(null, "You are not under age 16!", "Warning", JOptionPane.WARNING_MESSAGE);
                } else if (age <= 0) {
                    JOptionPane.showMessageDialog(null, "Please choose the right age!", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    int accountNum = BankControl.generateNumber();
                    int PIN = BankControl.generatePIN();
                    String input = JOptionPane.showInputDialog(null, "Please save more than 50 dollars to open an account");
                    int amount = Integer.parseInt(input);
                    if (amount <= 50) {
                        JOptionPane.showMessageDialog(null, "The money you save is not enough to open an account!", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if (BankControl.openAccount(accountNum, PIN, type, name, address, amount)) {
                        JOptionPane.showMessageDialog(null, "Open account successfully!", "Successful", JOptionPane.PLAIN_MESSAGE);
                        JOptionPane.showMessageDialog(null, "Your accountNum is: " + accountNum, "Successful", JOptionPane.PLAIN_MESSAGE);
                        JOptionPane.showMessageDialog(null, "Your PIN is: " + PIN, "Successful", JOptionPane.PLAIN_MESSAGE);
                        new BankUI();
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Open account error!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } else if (type == 3) {
                System.out.println("current");
                String birth = y + "-" + mo + "-" + d;
                int age = 0;
                try {
                    age = BankControl.getAge(BankControl.parse(birth));
                } catch (Exception except) {
                    except.printStackTrace();
                }
                if (age <= 0) {
                    JOptionPane.showMessageDialog(null, "Please choose the right age!", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    int accountNum = BankControl.generateNumber();
                    int PIN = BankControl.generatePIN();
                    String input = JOptionPane.showInputDialog(null, "Please save more than 50 dollars to open an account");
                    int amount = Integer.parseInt(input);
                    if (amount <= 50) {
                        JOptionPane.showMessageDialog(null, "The money you save is not enough to open an account!", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    if (BankControl.openAccount(accountNum, PIN, type, name, address, amount)) {
                        JOptionPane.showMessageDialog(null, "Open account successfully!", "Successful", JOptionPane.PLAIN_MESSAGE);
                        JOptionPane.showMessageDialog(null, "Your accountNum is: " + accountNum, "Successful", JOptionPane.PLAIN_MESSAGE);
                        JOptionPane.showMessageDialog(null, "Your PIN is: " + PIN, "Successful", JOptionPane.PLAIN_MESSAGE);
                        new BankUI();
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Open account error!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
            //this.dispose();
        }
        if (e.getActionCommand().equals("return")) {
            new BankUI();
            this.dispose();
        }
    }

    private void setDay(JComboBox year, JComboBox month, JComboBox day) {
        y = Integer.parseInt((String) year.getSelectedItem());
        mo = Integer.parseInt((String) month.getSelectedItem());
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, y);
        c.set(Calendar.MONTH, mo - 1);
        int days = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        day.setModel(new DefaultComboBoxModel(getModel(1, days)));
    }

    /**
     * get String array [start, end]
     */
    private String[] getModel(int start, int end) {
        String[] m = new String[end - start + 1];
        for (int i = 0; i < m.length; i++) {
            m[i] = String.valueOf(i + start);
        }
        return m;
    }

    class SaverListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            type = 1;
        }
    }

    class JuniorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            type = 2;
        }
    }

    class CurrentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            type = 3;
        }
    }
}