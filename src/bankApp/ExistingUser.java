package bankApp;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ExistingUser extends Menu{
    
    public ExistingUser() {
        
    }


    public void loginExistingUser(JFrame f1, JFrame f, ArrayList<Customer> customerList) {
        boolean loop = true, loop2 = true;
        boolean cont = false;
        boolean found = false;
        Customer customer = null;
        while (loop) {
            Object customerID = JOptionPane.showInputDialog(f, "Enter Customer ID:");

            for (Customer aCustomer : customerList) {
                if (aCustomer.getCustomerID().equals(customerID))// search customer list for matching customer ID
                {
                    found = true;
                    customer = aCustomer;
                }
            }

            if (found == false) {
                int reply = JOptionPane.showConfirmDialog(null, null, "User not found. Try again?", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    loop = true;
                } else if (reply == JOptionPane.NO_OPTION) {
                    f.dispose();
                    loop = false;
                    loop2 = false;
                    menuStart();
                }
            } else {
                loop = false;
            }
        }

        while (loop2) {
            Object customerPassword = JOptionPane.showInputDialog(f, "Enter Customer Password;");

            if (!customer.getPassword().equals(customerPassword))// check if custoemr password is correct
            {
                int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect password. Try again?", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {

                } else if (reply == JOptionPane.NO_OPTION) {
                    f.dispose();
                    loop2 = false;
                    menuStart();
                }
            } else {
                loop2 = false;
                cont = true;
            }
        }

        if (cont) {
            loop = false;
            customer();
        }
    }
    
    public void withdraw(CustomerAccount acc, JFrame f) {
        boolean loop = true;
        boolean on = true;
        double withdraw = 0;

        if (acc instanceof CustomerCurrentAccount) {
            int count = 3;
            int checkPin = ((CustomerCurrentAccount) acc).getAtm().getPin();
            loop = true;

            while (loop) {
                if (count == 0) {
                    JOptionPane.showMessageDialog(f,
                            "Pin entered incorrectly 3 times. ATM card locked.", "Pin",
                            JOptionPane.INFORMATION_MESSAGE);
                    ((CustomerCurrentAccount) acc).getAtm().setValid(false);
                    customer();
                    loop = false;
                    on = false;
                }

                String Pin = JOptionPane.showInputDialog(f, "Enter 4 digit PIN;");
                int i = Integer.parseInt(Pin);

                if (on) {
                    if (checkPin == i) {
                        loop = false;
                        JOptionPane.showMessageDialog(f, "Pin entry successful", "Pin",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        count--;
                        JOptionPane.showMessageDialog(f,
                                "Incorrect pin. " + count + " attempts remaining.", "Pin",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }

        if (on == true) {
            String balanceTest = JOptionPane.showInputDialog(f,
                    "Enter amount you wish to withdraw (max 500):");
         // the isNumeric method tests to see if the string entered was numeric.
            if (isNumeric(balanceTest)) {
                withdraw = Double.parseDouble(balanceTest);
                loop = false;
            } else {
                JOptionPane.showMessageDialog(f, "You must enter a numerical value!", "Oops!",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            if (withdraw > 500) {
                JOptionPane.showMessageDialog(f, "500 is the maximum you can withdraw at a time.",
                        "Oops!", JOptionPane.INFORMATION_MESSAGE);
                withdraw = 0;
            }

            if (withdraw > acc.getBalance()) {
                JOptionPane.showMessageDialog(f, "Insufficient funds.", "Oops!",
                        JOptionPane.INFORMATION_MESSAGE);
                withdraw = 0;
            }

            String euro = "\u20ac";
            acc.setBalance(acc.getBalance() - withdraw);
            // recording transaction:
            // String date = new
            // SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            Date date = new Date();
            String date2 = date.toString();
            String type = "Withdraw";
            double amount = withdraw;

            AccountTransaction transaction = new AccountTransaction(date2, type, amount);
            acc.getTransactionList().add(transaction);

            JOptionPane.showMessageDialog(f, withdraw + euro + " withdrawn.", "Withdraw",
                    JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance() + euro, "Withdraw",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    
    public void lodge(CustomerAccount acc, JFrame f) {
        boolean loop = true;
        boolean on = true;
        double balance = 0;

        if (acc instanceof CustomerCurrentAccount) {
            int count = 3;
            int checkPin = ((CustomerCurrentAccount) acc).getAtm().getPin();
            loop = true;

            while (loop) {
                if (count == 0) {
                    JOptionPane.showMessageDialog(f,"Pin entered incorrectly 3 times. ATM card locked.", "Pin",JOptionPane.INFORMATION_MESSAGE);
                    ((CustomerCurrentAccount) acc).getAtm().setValid(false);
                    customer();
                    loop = false;
                    on = false;
                }

                String Pin = JOptionPane.showInputDialog(f, "Enter 4 digit PIN;");
                int i = Integer.parseInt(Pin);

                if (on) {
                    if (checkPin == i) {
                        loop = false;
                        JOptionPane.showMessageDialog(f, "Pin entry successful", "Pin", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        count--;
                        JOptionPane.showMessageDialog(f, "Incorrect pin. " + count + " attempts remaining.", "Pin",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }

        if (on == true) {
            String balanceTest = JOptionPane.showInputDialog(f, "Enter amount you wish to lodge:");
         // the isNumeric method tests to see if the string entered was numeric.
            if (isNumeric(balanceTest)) {
                balance = Double.parseDouble(balanceTest);
                loop = false;
            } else {
                JOptionPane.showMessageDialog(f, "You must enter a numerical value!", "Oops!",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            String euro = "\u20ac";
            acc.setBalance(acc.getBalance() + balance);
            // String date = new
            // SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            Date date = new Date();
            String date2 = date.toString();
            String type = "Lodgement";
            double amount = balance;

            AccountTransaction transaction = new AccountTransaction(date2, type, amount);
            acc.getTransactionList().add(transaction);

            JOptionPane.showMessageDialog(f, balance + euro + " added do you account!", "Lodgement",
                    JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(f, "New balance = " + acc.getBalance() + euro,
                    "Lodgement", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
}
