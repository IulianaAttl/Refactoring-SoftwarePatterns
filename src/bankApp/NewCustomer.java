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



    public JFrame getF1() {
        return f1;
    }



    public void setF1(JFrame f1) {
        this.f1 = f1;
    }



    public String getPassword() {
        return password;
    }



    public void setPassword(String password) {
        this.password = password;
    }



    public String getSurname() {
        return surname;
    }



    public void setSurname(String surname) {
        this.surname = surname;
    }



    public String getFirstName() {
        return firstName;
    }



    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }



    public String getDob() {
        return dob;
    }



    public void setDob(String dob) {
        this.dob = dob;
    }



    public String getCustomerID() {
        return customerID;
    }



    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }



    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }



    public void setCustomerList(ArrayList<Customer> customerList) {
        this.customerList = customerList;
    }



    public JFrame getF() {
        return f;
    }



    public void setF(JFrame f) {
        this.f = f;
    }



    public String getPps() {
        return pps;
    }



    public void setPps(String pps) {
        this.pps = pps;
    }
    
    
}
