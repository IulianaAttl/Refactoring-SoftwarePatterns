package bankApp;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.text.MaskFormatter;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Menu extends JFrame {

    private ArrayList<Customer> customerList = new ArrayList<Customer>();
    private int menuPosition = 0;
    private String pps, firstName, surname, dob, customerID,password;
    private Customer customerChosen = null;
    private CustomerAccount acc = new CustomerAccount();
    private JFrame f, f1;
    private JLabel firstNameLabel, surnameLabel, ppsLabel, dobLabel, customerIDLabel, passwordLabel;
    private JTextField firstNameTextField, surnameTextField, ppsTextField, dobTextField, customerIDTextField, passwordTextField;
    private Container content;
    private JPanel panel2;
    private JButton addCustomer;

    public static void main(String[] args) {
        Menu driver = new Menu();
        driver.menuStart();
    }

    public void menuStart() {
        /*
         * The menuStart method asks the user if they are a new customer, an existing
         * customer or an admin. It will then start the create customer process if they 
         * are a new customer, or will ask them to log in if they are an existing
         * customer or admin.
         */

        f = new JFrame("User Type");
        f.setSize(400, 300);
        f.setLocation(200, 200);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        JPanel userTypePanel = new JPanel();
        final ButtonGroup userType = new ButtonGroup();
        JRadioButton radioButton;
        userTypePanel.add(radioButton = new JRadioButton("Existing Customer"));
        radioButton.setActionCommand("Customer");
        userType.add(radioButton);

        userTypePanel.add(radioButton = new JRadioButton("Administrator"));
        radioButton.setActionCommand("Administrator");
        userType.add(radioButton);

        userTypePanel.add(radioButton = new JRadioButton("New Customer"));
        radioButton.setActionCommand("New Customer");
        userType.add(radioButton);

        JPanel continuePanel = new JPanel();
        JButton continueButton = new JButton("Continue");
        continuePanel.add(continueButton);

        Container content = f.getContentPane();
        content.setLayout(new GridLayout(2, 1));
        content.add(userTypePanel);
        content.add(continuePanel);

        continueButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String user = userType.getSelection().getActionCommand();

                // if user selects NEW CUSTOMER--------------------------------------------------------------------------------------
                if (user.equals("New Customer")) {
                    f.dispose();
                    f1 = new JFrame("Create New Customer");
                    f1.setSize(400, 300);
                    f1.setLocation(200, 200);
                    f1.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent we) {
                            System.exit(0);
                        }
                    });

                    Container content = f1.getContentPane();
                    content.setLayout(new BorderLayout());

                    firstNameLabel = new JLabel("First Name:", SwingConstants.RIGHT);
                    surnameLabel = new JLabel("Surname:", SwingConstants.RIGHT);
                    ppsLabel = new JLabel("PPS Number:", SwingConstants.RIGHT);
                    dobLabel = new JLabel("Date of birth", SwingConstants.RIGHT);
                    firstNameTextField = new JTextField(20);
                    surnameTextField = new JTextField(20);
                    ppsTextField = new JTextField(20);
                    dobTextField = new JTextField(20);
                    JPanel panel = new JPanel(new GridLayout(6, 2));
                    panel.add(firstNameLabel);
                    panel.add(firstNameTextField);
                    panel.add(surnameLabel);
                    panel.add(surnameTextField);
                    panel.add(ppsLabel);
                    panel.add(ppsTextField);
                    panel.add(dobLabel);
                    panel.add(dobTextField);

                    panel2 = new JPanel();
                    addCustomer = new JButton("Add");

                    addCustomer.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            pps = ppsTextField.getText();
                            firstName = firstNameTextField.getText();
                            surname = surnameTextField.getText();
                            dob = dobTextField.getText();
                            password = "";
                            customerID = "ID" + pps;

                            addCustomer.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent e) {
                                    
                                    NewCustomer nc = new NewCustomer(f1, password, surname, firstName, dob, customerID, customerList, f, pps);
                                    nc.createNewCustomer();
                                    menuStart();
                                    f.dispose();
                                }
                            });
                        }

                    });

                    JButton cancel = new JButton("Cancel");
                    cancel.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            f1.dispose();
                            menuStart();
                        }
                    });

                    panel2.add(addCustomer);
                    panel2.add(cancel);

                    content.add(panel, BorderLayout.CENTER);
                    content.add(panel2, BorderLayout.SOUTH);

                    f1.setVisible(true);
                }

                // ------------------------------------------------------------------------------------------------------------------
                // if user select ADMIN----------------------------------------------------------------------------------------------

                if (user.equals("Administrator")) {
                   AdminUser au = new AdminUser();
                   au.loginAdminUser(f1, f);
                }

                // ----------------------------------------------------------------------------------------------------------------
                // if user selects CUSTOMER
                // ----------------------------------------------------------------------------------------

                if (user.equals("Customer")) {
                    ExistingUser eu = new ExistingUser();
                    eu.loginExistingUser(f1, f, customerList);
                }
                 
                // ----------------------------------------------------------------------------------------------------------------------
            }
        });

        f.setVisible(true);
    }

    public void admin() {
        dispose();

        f = new JFrame("Administrator Menu");
        f.setSize(400, 400);
        f.setLocation(200, 200);

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        f.setVisible(true);

        JPanel deleteCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton deleteCustomer = new JButton("Delete Customer");
        deleteCustomer.setPreferredSize(new Dimension(250, 20));
        deleteCustomerPanel.add(deleteCustomer);

        JPanel deleteAccountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton deleteAccount = new JButton("Delete Account");
        deleteAccount.setPreferredSize(new Dimension(250, 20));
        deleteAccountPanel.add(deleteAccount);

        JPanel bankChargesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton bankChargesButton = new JButton("Apply Bank Charges");
        bankChargesButton.setPreferredSize(new Dimension(250, 20));
        bankChargesPanel.add(bankChargesButton);

        JPanel interestPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton interestButton = new JButton("Apply Interest");
        interestPanel.add(interestButton);
        interestButton.setPreferredSize(new Dimension(250, 20));

        JPanel editCustomerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton editCustomerButton = new JButton("Edit existing Customer");
        editCustomerPanel.add(editCustomerButton);
        editCustomerButton.setPreferredSize(new Dimension(250, 20));

        JPanel navigatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton navigateButton = new JButton("Navigate Customer Collection");
        navigatePanel.add(navigateButton);
        navigateButton.setPreferredSize(new Dimension(250, 20));

        JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton summaryButton = new JButton("Display Summary Of All Accounts");
        summaryPanel.add(summaryButton);
        summaryButton.setPreferredSize(new Dimension(250, 20));

        JPanel accountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton accountButton = new JButton("Add an Account to a Customer");
        accountPanel.add(accountButton);
        accountButton.setPreferredSize(new Dimension(250, 20));

        JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton returnButton = new JButton("Exit Admin Menu");
        returnPanel.add(returnButton);

        JLabel label1 = new JLabel("Please select an option");

        content = f.getContentPane();
        content.setLayout(new GridLayout(9, 1));
        content.add(label1);
        content.add(accountPanel);
        content.add(bankChargesPanel);
        content.add(interestPanel);
        content.add(editCustomerPanel);
        content.add(navigatePanel);
        content.add(summaryPanel);
        content.add(deleteCustomerPanel);
        // content.add(deleteAccountPanel);
        content.add(returnPanel);

        bankChargesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                boolean loop = true;
                boolean found = false;

                if (customerList.isEmpty()) {
                    JOptionPane.showMessageDialog(f, "There are no customers yet!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
                    f.dispose();
                    admin();
                } else {
                    while (loop) {
                        Object customerID = JOptionPane.showInputDialog(f, "Customer ID of Customer You Wish to Apply Charges to:");

                        for (Customer aCustomer : customerList) {
                            if (aCustomer.getCustomerID().equals(customerID)) {
                                found = true;
                                customerChosen = aCustomer;
                                loop = false;
                            }
                        }

                        if (found == false) {
                            int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
                            if (reply == JOptionPane.YES_OPTION) {
                                loop = true;
                            } else if (reply == JOptionPane.NO_OPTION) {
                                f.dispose();
                                loop = false;
                                admin();
                            }
                        } else {
                            f.dispose();
                            f = new JFrame("Administrator Menu");
                            f.setSize(400, 300);
                            f.setLocation(200, 200);
                            f.addWindowListener(new WindowAdapter() {
                                public void windowClosing(WindowEvent we) {
                                    System.exit(0);
                                }
                            });

                            f.setVisible(true);

                            JComboBox<String> box = new JComboBox<String>();
                            for (int i = 0; i < customerChosen.getAccounts().size(); i++) {
                                box.addItem(customerChosen.getAccounts().get(i).getNumber());
                            }

                            box.getSelectedItem();

                            JPanel boxPanel = new JPanel();
                            boxPanel.add(box);

                            JPanel buttonPanel = new JPanel();
                            JButton continueButton = new JButton("Apply Charge");
                            JButton returnButton = new JButton("Return");
                            buttonPanel.add(continueButton);
                            buttonPanel.add(returnButton);
                            Container content = f.getContentPane();
                            content.setLayout(new GridLayout(2, 1));

                            content.add(boxPanel);
                            content.add(buttonPanel);

                            if (customerChosen.getAccounts().isEmpty()) {
                                JOptionPane.showMessageDialog(f,"This customer has no accounts! \n The admin must add acounts to this customer.", "Oops!", JOptionPane.INFORMATION_MESSAGE);
                                f.dispose();
                                admin();
                            } else {
                                for (int i = 0; i < customerChosen.getAccounts().size(); i++) {
                                    if (customerChosen.getAccounts().get(i).getNumber() == box.getSelectedItem()) {
                                        acc = customerChosen.getAccounts().get(i);
                                    }
                                }

                                continueButton.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent ae) {
                                        String euro = "\u20ac";

                                        if (acc instanceof CustomerDepositAccount) {
                                            JOptionPane.showMessageDialog(f, "25" + euro + " deposit account fee aplied.", "", JOptionPane.INFORMATION_MESSAGE);
                                            acc.setBalance(acc.getBalance() - 25);
                                            JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance(),"Success!", JOptionPane.INFORMATION_MESSAGE);
                                        }

                                        if (acc instanceof CustomerCurrentAccount) {
                                            JOptionPane.showMessageDialog(f, "15" + euro + " current account fee aplied.", "", JOptionPane.INFORMATION_MESSAGE);
                                            acc.setBalance(acc.getBalance() - 25);
                                            JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance(), "Success!", JOptionPane.INFORMATION_MESSAGE);
                                        }
                                        f.dispose();
                                        admin();
                                    }
                                });

                                returnButton.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent ae) {
                                        f.dispose();
                                        menuStart();
                                    }
                                });
                            }
                        }
                    }
                }
            }
        });

        interestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                boolean loop = true;
                boolean found = false;

                if (customerList.isEmpty()) {
                    JOptionPane.showMessageDialog(f, "There are no customers yet!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
                    f.dispose();
                    admin();

                } else {
                    while (loop) {
                        Object customerID = JOptionPane.showInputDialog(f,"Customer ID of Customer You Wish to Apply Interest to:");

                        for (Customer aCustomer : customerList) {
                            if (aCustomer.getCustomerID().equals(customerID)) {
                                found = true;
                                customerChosen = aCustomer;
                                loop = false;
                            }
                        }

                        if (found == false) {
                            int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);

                            if (reply == JOptionPane.YES_OPTION) {
                                loop = true;
                            } else if (reply == JOptionPane.NO_OPTION) {
                                f.dispose();
                                loop = false;
                                admin();
                            }
                        } else {
                            f.dispose();
                            f = new JFrame("Administrator Menu");
                            f.setSize(400, 300);
                            f.setLocation(200, 200);
                            f.addWindowListener(new WindowAdapter() {
                                public void windowClosing(WindowEvent we) {
                                    System.exit(0);
                                }
                            });

                            f.setVisible(true);

                            JComboBox<String> box = new JComboBox<String>();
                            for (int i = 0; i < customerChosen.getAccounts().size(); i++) {
                                box.addItem(customerChosen.getAccounts().get(i).getNumber());
                            }

                            box.getSelectedItem();

                            JPanel boxPanel = new JPanel();
                            JLabel label = new JLabel("Select an account to apply interest to:");
                            boxPanel.add(label);
                            boxPanel.add(box);
                            JPanel buttonPanel = new JPanel();
                            JButton continueButton = new JButton("Apply Interest");
                            JButton returnButton = new JButton("Return");
                            buttonPanel.add(continueButton);
                            buttonPanel.add(returnButton);
                            Container content = f.getContentPane();
                            content.setLayout(new GridLayout(2, 1));

                            content.add(boxPanel);
                            content.add(buttonPanel);

                            if (customerChosen.getAccounts().isEmpty()) {
                                JOptionPane.showMessageDialog(f,"This customer has no accounts! \n The admin must add acounts to this customer.","Oops!", JOptionPane.INFORMATION_MESSAGE);
                                f.dispose();
                                admin();
                            } else {
                                for (int i = 0; i < customerChosen.getAccounts().size(); i++) {
                                    if (customerChosen.getAccounts().get(i).getNumber() == box.getSelectedItem()) {
                                        acc = customerChosen.getAccounts().get(i);
                                    }
                                }

                                continueButton.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent ae) {
                                        String euro = "\u20ac";
                                        double interest = 0;
                                        boolean loop = true;

                                        while (loop) {
                                            String interestString = JOptionPane.showInputDialog(f,"Enter interest percentage you wish to apply: \n NOTE: Please enter a numerical value. (with no percentage sign) \n E.g: If you wish to apply 8% interest, enter '8'");
                                            // the isNumeric method tests to see if the string entered was numeric.
                                            if (isNumeric(interestString)) {
                                                interest = Double.parseDouble(interestString);
                                                loop = false;
                                                acc.setBalance(acc.getBalance() + (acc.getBalance() * (interest / 100)));

                                                JOptionPane.showMessageDialog(f,interest + "% interest applied. \n new balance = "  + acc.getBalance() + euro,  "Success!", JOptionPane.INFORMATION_MESSAGE);
                                            }

                                            else {
                                                JOptionPane.showMessageDialog(f, "You must enter a numerical value!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
                                            }
                                        }
                                        f.dispose();
                                        admin();
                                    }
                                });

                                returnButton.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent ae) {
                                        f.dispose();
                                        menuStart();
                                    }
                                });

                            }
                        }
                    }
                }
            }
        });

        editCustomerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                boolean loop = true;
                boolean found = false;

                if (customerList.isEmpty()) {
                    JOptionPane.showMessageDialog(f, "There are no customers yet!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
                    f.dispose();
                    admin();
                } else {
                    while (loop) {
                        Object customerID = JOptionPane.showInputDialog(f, "Enter Customer ID:");
                        for (Customer aCustomer : customerList) {
                            if (aCustomer.getCustomerID().equals(customerID)) {
                                found = true;
                                customerChosen = aCustomer;
                            }
                        }

                        if (found == false) {
                            int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
                            if (reply == JOptionPane.YES_OPTION) {
                                loop = true;
                            } else if (reply == JOptionPane.NO_OPTION) {
                                f.dispose();
                                loop = false;
                                admin();
                            }
                        } else {
                            loop = false;
                        }
                    }

                    f.dispose();

                    f.dispose();
                    f = new JFrame("Administrator Menu");
                    f.setSize(400, 300);
                    f.setLocation(200, 200);
                    f.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent we) {
                            System.exit(0);
                        }
                    });

                    firstNameLabel = new JLabel("First Name:", SwingConstants.LEFT);
                    surnameLabel = new JLabel("Surname:", SwingConstants.LEFT);
                    ppsLabel = new JLabel("PPS Number:", SwingConstants.LEFT);
                    dobLabel = new JLabel("Date of birth", SwingConstants.LEFT);
                    customerIDLabel = new JLabel("CustomerID:", SwingConstants.LEFT);
                    passwordLabel = new JLabel("Password:", SwingConstants.LEFT);
                    firstNameTextField = new JTextField(20);
                    surnameTextField = new JTextField(20);
                    ppsTextField = new JTextField(20);
                    dobTextField = new JTextField(20);
                    customerIDTextField = new JTextField(20);
                    passwordTextField = new JTextField(20);

                    JPanel textPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

                    JPanel cancelPanel = new JPanel();

                    textPanel.add(firstNameLabel);
                    textPanel.add(firstNameTextField);
                    textPanel.add(surnameLabel);
                    textPanel.add(surnameTextField);
                    textPanel.add(ppsLabel);
                    textPanel.add(ppsTextField);
                    textPanel.add(dobLabel);
                    textPanel.add(dobTextField);
                    textPanel.add(customerIDLabel);
                    textPanel.add(customerIDTextField);
                    textPanel.add(passwordLabel);
                    textPanel.add(passwordTextField);

                    firstNameTextField.setText(customerChosen.getFirstName());
                    surnameTextField.setText(customerChosen.getSurname());
                    ppsTextField.setText(customerChosen.getPPS());
                    dobTextField.setText(customerChosen.getDOB());
                    customerIDTextField.setText(customerChosen.getCustomerID());
                    passwordTextField.setText(customerChosen.getPassword());

                    // JLabel label1 = new JLabel("Edit customer details below. The save");

                    JButton saveButton = new JButton("Save");
                    JButton cancelButton = new JButton("Exit");

                    cancelPanel.add(cancelButton, BorderLayout.SOUTH);
                    cancelPanel.add(saveButton, BorderLayout.SOUTH);
                    // content.removeAll();
                    Container content = f.getContentPane();
                    content.setLayout(new GridLayout(2, 1));
                    content.add(textPanel, BorderLayout.NORTH);
                    content.add(cancelPanel, BorderLayout.SOUTH);

                    f.setContentPane(content);
                    f.setSize(340, 350);
                    f.setLocation(200, 200);
                    f.setVisible(true);
                    f.setResizable(false);

                    saveButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                            customerChosen.setFirstName(firstNameTextField.getText());
                            customerChosen.setSurname(surnameTextField.getText());
                            customerChosen.setPPS(ppsTextField.getText());
                            customerChosen.setDOB(dobTextField.getText());
                            customerChosen.setCustomerID(customerIDTextField.getText());
                            customerChosen.setPassword(passwordTextField.getText());

                            JOptionPane.showMessageDialog(null, "Changes Saved.");
                        }
                    });

                    cancelButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                            f.dispose();
                            admin();
                        }
                    });
                }
            }
        });

        summaryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                f.dispose();

                f = new JFrame("Summary of Transactions");
                f.setSize(400, 700);
                f.setLocation(200, 200);
                f.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent we) {
                        System.exit(0);
                    }
                });
                f.setVisible(true);

                JLabel label1 = new JLabel("Summary of all transactions: ");

                JPanel returnPanel = new JPanel();
                JButton returnButton = new JButton("Return");
                returnPanel.add(returnButton);

                JPanel textPanel = new JPanel();

                textPanel.setLayout(new BorderLayout());
                JTextArea textArea = new JTextArea(40, 20);
                textArea.setEditable(false);
                textPanel.add(label1, BorderLayout.NORTH);
                textPanel.add(textArea, BorderLayout.CENTER);
                textPanel.add(returnButton, BorderLayout.SOUTH);

                JScrollPane scrollPane = new JScrollPane(textArea);
                textPanel.add(scrollPane);

                for (int a = 0; a < customerList.size(); a++)// For each customer, for each account, it displays each
                                                             // transaction.
                {
                    for (int b = 0; b < customerList.get(a).getAccounts().size(); b++) {
                        acc = customerList.get(a).getAccounts().get(b);
                        for (int c = 0; c < customerList.get(a).getAccounts().get(b).getTransactionList().size(); c++) {
                            textArea.append(acc.getTransactionList().get(c).toString());
                            // Int total = acc.getTransactionList().get(c).getAmount(); //I was going to use
                            // this to keep a running total but I couldnt get it working.
                        }
                    }
                }

                textPanel.add(textArea);
                content.removeAll();

                Container content = f.getContentPane();
                content.setLayout(new GridLayout(1, 1));
                // content.add(label1);
                content.add(textPanel);
                // content.add(returnPanel);

                returnButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        f.dispose();
                        admin();
                    }
                });
            }
        });

        navigateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                f.dispose();

                if (customerList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "There are currently no customers to display. ");
                    admin();
                } else {
                    JButton first, previous, next, last, cancel;
                    JPanel gridPanel, buttonPanel, cancelPanel;

                    Container content = getContentPane();

                    content.setLayout(new BorderLayout());

                    buttonPanel = new JPanel();
                    gridPanel = new JPanel(new GridLayout(8, 2));
                    cancelPanel = new JPanel();

                    firstNameLabel = new JLabel("First Name:", SwingConstants.LEFT);
                    surnameLabel = new JLabel("Surname:", SwingConstants.LEFT);
                    ppsLabel = new JLabel("PPS Number:", SwingConstants.LEFT);
                    dobLabel = new JLabel("Date of birth", SwingConstants.LEFT);
                    customerIDLabel = new JLabel("CustomerID:", SwingConstants.LEFT);
                    passwordLabel = new JLabel("Password:", SwingConstants.LEFT);
                    firstNameTextField = new JTextField(20);
                    surnameTextField = new JTextField(20);
                    ppsTextField = new JTextField(20);
                    dobTextField = new JTextField(20);
                    customerIDTextField = new JTextField(20);
                    passwordTextField = new JTextField(20);

                    first = new JButton("First");
                    previous = new JButton("Previous");
                    next = new JButton("Next");
                    last = new JButton("Last");
                    cancel = new JButton("Cancel");

                    firstNameTextField.setText(customerList.get(0).getFirstName());
                    surnameTextField.setText(customerList.get(0).getSurname());
                    ppsTextField.setText(customerList.get(0).getPPS());
                    dobTextField.setText(customerList.get(0).getDOB());
                    customerIDTextField.setText(customerList.get(0).getCustomerID());
                    passwordTextField.setText(customerList.get(0).getPassword());

                    firstNameTextField.setEditable(false);
                    surnameTextField.setEditable(false);
                    ppsTextField.setEditable(false);
                    dobTextField.setEditable(false);
                    customerIDTextField.setEditable(false);
                    passwordTextField.setEditable(false);

                    gridPanel.add(firstNameLabel);
                    gridPanel.add(firstNameTextField);
                    gridPanel.add(surnameLabel);
                    gridPanel.add(surnameTextField);
                    gridPanel.add(ppsLabel);
                    gridPanel.add(ppsTextField);
                    gridPanel.add(dobLabel);
                    gridPanel.add(dobTextField);
                    gridPanel.add(customerIDLabel);
                    gridPanel.add(customerIDTextField);
                    gridPanel.add(passwordLabel);
                    gridPanel.add(passwordTextField);

                    buttonPanel.add(first);
                    buttonPanel.add(previous);
                    buttonPanel.add(next);
                    buttonPanel.add(last);

                    cancelPanel.add(cancel);

                    content.add(gridPanel, BorderLayout.NORTH);
                    content.add(buttonPanel, BorderLayout.CENTER);
                    content.add(cancelPanel, BorderLayout.AFTER_LAST_LINE);
                    first.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                            menuPosition = 0;
                            navigateMenuSetTextFields();
                        }
                    });

                    previous.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                            if (menuPosition >= 1) {
                                menuPosition = menuPosition - 1;
                                navigateMenuSetTextFields();
                            }
                        }
                    });

                    next.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {

                            if (menuPosition != customerList.size() - 1) {
                                menuPosition = menuPosition + 1;
                                navigateMenuSetTextFields();
                            }
                        }
                    });

                    last.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                            menuPosition = customerList.size() - 1;
                            navigateMenuSetTextFields();
                        }
                    });

                    cancel.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                            dispose();
                            admin();
                        }
                    });
                    setContentPane(content);
                    setSize(400, 300);
                    setVisible(true);
                }
            }
        });

        accountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                f.dispose();

                if (customerList.isEmpty()) {
                    JOptionPane.showMessageDialog(f, "There are no customers yet!", "Oops!", JOptionPane.INFORMATION_MESSAGE);
                    f.dispose();
                    admin();
                } else {
                    boolean loop = true;

                    boolean found = false;

                    while (loop) {
                        Object customerID = JOptionPane.showInputDialog(f,
                                "Customer ID of Customer You Wish to Add an Account to:");
                        for (Customer aCustomer : customerList) {
                            if (aCustomer.getCustomerID().equals(customerID)) {
                                found = true;
                                customerChosen = aCustomer;
                            }
                        }

                        if (found == false) {
                            int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
                            if (reply == JOptionPane.YES_OPTION) {
                                loop = true;
                            } else if (reply == JOptionPane.NO_OPTION) {
                                f.dispose();
                                loop = false;

                                admin();
                            }
                        } else {
                            loop = false;
                            // a combo box in an dialog box that asks the admin what type of account they
                            // wish to create (deposit/current)
                            String[] choices = { "Current Account", "Deposit Account" };
                            String account = (String) JOptionPane.showInputDialog(null, "Please choose account type", "Account Type", JOptionPane.QUESTION_MESSAGE, null, choices, choices[1]);

                            if (account.equals("Current Account")) {
                                // create current account
                                boolean valid = true;
                                double balance = 0;
                                String number = String.valueOf("C" + (customerList.indexOf(customerChosen) + 1) * 10  + (customerChosen.getAccounts().size() + 1));// this simple algorithm generates the
                                                                               // account number
                                ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();
                                int randomPIN = (int) (Math.random() * 9000) + 1000;
                                String pin = String.valueOf(randomPIN);
                                ATMCard atm = new ATMCard(randomPIN, valid);

                                CustomerCurrentAccount current = new CustomerCurrentAccount(atm, number, balance,  transactionList);

                                customerChosen.getAccounts().add(current);
                                JOptionPane.showMessageDialog(f, "Account number = " + number + "\n PIN = " + pin, "Account created.", JOptionPane.INFORMATION_MESSAGE);

                                f.dispose();
                                admin();
                            }

                            if (account.equals("Deposit Account")) {
                                // create deposit account

                                double balance = 0, interest = 0;
                                String number = String.valueOf("D" + (customerList.indexOf(customerChosen) + 1) * 10 + (customerChosen.getAccounts().size() + 1));// this simple algorithm generates the
                                                                               // account number
                                ArrayList<AccountTransaction> transactionList = new ArrayList<AccountTransaction>();

                                CustomerDepositAccount deposit = new CustomerDepositAccount(interest, number, balance,  transactionList);

                                customerChosen.getAccounts().add(deposit);
                                JOptionPane.showMessageDialog(f, "Account number = " + number, "Account created.", JOptionPane.INFORMATION_MESSAGE);

                                f.dispose();
                                admin();
                            }
                        }
                    }
                }
            }
        });

        deleteCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                boolean found = true;

                if (customerList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "There are currently no customers to display. ");
                    dispose(); 
                    admin();
                } else {
                    Object customerID = JOptionPane.showInputDialog(f, "Customer ID of Customer You Wish to Delete:");

                    for (Customer aCustomer : customerList) {
                        if (aCustomer.getCustomerID().equals(customerID)) {
                            found = true;
                            customerChosen = aCustomer;
                        }
                    }

                    if (found == false) {
                        int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
                        if (reply == JOptionPane.YES_OPTION) {
                        } else if (reply == JOptionPane.NO_OPTION) {
                            f.dispose();
                            admin();
                        }
                    } else {
                        if (customerChosen.getAccounts().size() > 0) {
                            JOptionPane.showMessageDialog(f, "This customer has accounts. \n You must delete a customer's accounts before deleting a customer ","Oops!", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            customerList.remove(customerChosen);
                            JOptionPane.showMessageDialog(f, "Customer Deleted ", "Success.",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                }
            }
        });

        deleteAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                boolean found = true;
                Object customerID = JOptionPane.showInputDialog(f, "Customer ID of Customer from which you wish to delete an account");

                for (Customer aCustomer : customerList) {
                    if (aCustomer.getCustomerID().equals(customerID)) {
                        found = true;
                        customerChosen = aCustomer;
                    }
                }

                if (found == false) {
                    int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?",JOptionPane.YES_NO_OPTION);

                    if (reply == JOptionPane.YES_OPTION) {
                    } else if (reply == JOptionPane.NO_OPTION) {
                        f.dispose();
                        admin();
                    }
                } else {
                    // Here I would make the user select a an account to delete from a combo box. If
                    // the account had a balance of 0 then it would be deleted. (I do not have time
                    // to do this)
                }
            }
        });

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                f.dispose();
                menuStart();
            }
        });
    }
    
    //method to set the text fields to ever customer in customer list
    public void navigateMenuSetTextFields() {
        firstNameTextField.setText(customerList.get(menuPosition).getFirstName());
        surnameTextField.setText(customerList.get(menuPosition).getSurname());
        ppsTextField.setText(customerList.get(menuPosition).getPPS());
        dobTextField.setText(customerList.get(menuPosition).getDOB());
        customerIDTextField.setText(customerList.get(menuPosition).getCustomerID());
        passwordTextField.setText(customerList.get(menuPosition).getPassword());
    }

    public void customer() {
        f = new JFrame("Customer Menu");
        f.setSize(400, 300);
        f.setLocation(200, 200);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        f.setVisible(true);

        if (customerChosen.getAccounts().size() == 0) {
            JOptionPane.showMessageDialog(f,"This customer does not have any accounts yet. \n An admin must create an account for this customer \n for them to be able to use customer functionality. ", "Oops!", JOptionPane.INFORMATION_MESSAGE);
            f.dispose();
            menuStart();
        } else {
            JPanel buttonPanel = new JPanel();
            JPanel boxPanel = new JPanel();
            JPanel labelPanel = new JPanel();

            JLabel label = new JLabel("Select Account:");
            labelPanel.add(label);

            JButton returnButton = new JButton("Return");
            buttonPanel.add(returnButton);
            JButton continueButton = new JButton("Continue");
            buttonPanel.add(continueButton);

            JComboBox<String> box = new JComboBox<String>();

            for (int i = 0; i < customerChosen.getAccounts().size(); i++) {
                box.addItem(customerChosen.getAccounts().get(i).getNumber());
            }

            for (int i = 0; i < customerChosen.getAccounts().size(); i++) {
                if (customerChosen.getAccounts().get(i).getNumber() == box.getSelectedItem()) {
                    acc = customerChosen.getAccounts().get(i);
                }
            }

            boxPanel.add(box);
            content = f.getContentPane();
            content.setLayout(new GridLayout(3, 1));
            content.add(labelPanel);
            content.add(boxPanel);
            content.add(buttonPanel);

            returnButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    f.dispose();
                    menuStart();
                }
            });

            continueButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    f.dispose();

                    f = new JFrame("Customer Menu");
                    f.setSize(400, 300);
                    f.setLocation(200, 200);
                    f.addWindowListener(new WindowAdapter() {
                        public void windowClosing(WindowEvent we) {
                            System.exit(0);
                        }
                    });

                    f.setVisible(true);

                    JPanel statementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    JButton statementButton = new JButton("Display Bank Statement");
                    statementButton.setPreferredSize(new Dimension(250, 20));

                    statementPanel.add(statementButton);

                    JPanel lodgementPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    JButton lodgementButton = new JButton("Lodge money into account");
                    lodgementPanel.add(lodgementButton);
                    lodgementButton.setPreferredSize(new Dimension(250, 20));

                    JPanel withdrawalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
                    JButton withdrawButton = new JButton("Withdraw money from account");
                    withdrawalPanel.add(withdrawButton);
                    withdrawButton.setPreferredSize(new Dimension(250, 20));

                    JPanel returnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                    JButton returnButton = new JButton("Exit Customer Menu");
                    returnPanel.add(returnButton);

                    JLabel label1 = new JLabel("Please select an option");

                    content = f.getContentPane();
                    content.setLayout(new GridLayout(5, 1));
                    content.add(label1);
                    content.add(statementPanel);
                    content.add(lodgementPanel);
                    content.add(withdrawalPanel);
                    content.add(returnPanel);

                    statementButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                            f.dispose();
                            f = new JFrame("Customer Menu");
                            f.setSize(400, 600);
                            f.setLocation(200, 200);
                            f.addWindowListener(new WindowAdapter() {
                                public void windowClosing(WindowEvent we) {
                                    System.exit(0);
                                }
                            });

                            f.setVisible(true);

                            JLabel label1 = new JLabel("Summary of account transactions: ");

                            JPanel returnPanel = new JPanel();
                            JButton returnButton = new JButton("Return");
                            returnPanel.add(returnButton);

                            JPanel textPanel = new JPanel();

                            textPanel.setLayout(new BorderLayout());
                            JTextArea textArea = new JTextArea(40, 20);
                            textArea.setEditable(false);
                            textPanel.add(label1, BorderLayout.NORTH);
                            textPanel.add(textArea, BorderLayout.CENTER);
                            textPanel.add(returnButton, BorderLayout.SOUTH);

                            JScrollPane scrollPane = new JScrollPane(textArea);
                            textPanel.add(scrollPane);

                            for (int i = 0; i < acc.getTransactionList().size(); i++) {
                                textArea.append(acc.getTransactionList().get(i).toString());
                            }

                            textPanel.add(textArea);
                            content.removeAll();

                            Container content = f.getContentPane();
                            content.setLayout(new GridLayout(1, 1));
                            // content.add(label1);
                            content.add(textPanel);
                            // content.add(returnPanel);

                            returnButton.addActionListener(new ActionListener() {
                                public void actionPerformed(ActionEvent ae) {
                                    f.dispose();
                                    customer();
                                }
                            });
                        }
                    });

                    lodgementButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                            ExistingUser eu = new ExistingUser();
                            eu.lodge(acc, f);
                        }
                    });

                    withdrawButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                            ExistingUser eu = new ExistingUser();
                            eu.withdraw(acc, f);
                        }
                    });

                    returnButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent ae) {
                            f.dispose();
                            menuStart();
                        }
                    });
                }
            });
        }
    }

    public static boolean isNumeric(String str) // a method that tests if a string is numeric
    {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}