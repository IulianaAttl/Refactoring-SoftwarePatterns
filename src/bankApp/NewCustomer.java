package bankApp;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class NewCustomer extends Menu{
    
    private JFrame f1;
    private String password;
    private String surname;
    private String firstName;
    private String dob;
    private String customerID;
    private ArrayList<Customer> customerList = new ArrayList<Customer>();
    private JFrame f;
    private String pps;

    public NewCustomer() {
        
    }

    public NewCustomer(JFrame f1, String password, String surname, String firstName, String dob, String customerID,
            ArrayList<Customer> customerList, JFrame f, String pps) {
        this.f1 = f1;
        this.password = password;
        this.surname = surname;
        this.firstName = firstName;
        this.dob = dob;
        this.customerID = customerID;
        this.customerList = customerList;
        this.f = f;
        this.pps = pps;
    }

    public void createNewCustomer() {
        f1.dispose();
        boolean loop = true;

        while (loop) {
            password = JOptionPane.showInputDialog(f, "Enter 7 character Password;");
            if (password.length() != 7)// Making sure password is 7 characters
            {
                JOptionPane.showMessageDialog(null, null, "Password must be 7 charatcers long", JOptionPane.OK_OPTION);
            } else {
                loop = false;
            }
        }

        ArrayList<CustomerAccount> accounts = new ArrayList<CustomerAccount>();
        Customer customer = new Customer(pps, surname, firstName, dob, customerID, password, accounts);

        customerList.add(customer);

        JOptionPane.showMessageDialog(f,"CustomerID = " + customerID + "\n Password = " + password, "Customer created.", JOptionPane.INFORMATION_MESSAGE);
    }
}
