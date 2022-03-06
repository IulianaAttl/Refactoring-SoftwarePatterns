package bankApp;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class AdminUser extends Menu {
   
    private JFrame f1;
    private JFrame f;
    
    public AdminUser() {
        
    }
    
    
    
    public AdminUser(JFrame f1, JFrame f) {
        this.f1 = f1;
        this.f = f;
    }



    public void loginAdminUser() {
       boolean loop = true, loop2 = true;
       boolean cont = false;
       while (loop) {
           Object adminUsername = JOptionPane.showInputDialog(f, "Enter Administrator Username:");

           if (!adminUsername.equals("admin"))// search admin list for admin with matching admin username
           {
               int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect Username. Try again?",JOptionPane.YES_NO_OPTION);
               if (reply == JOptionPane.YES_OPTION) {
                   loop = true;
               } else if (reply == JOptionPane.NO_OPTION) {
                   f1.dispose();
                   loop = false;
                   loop2 = false;
                   menuStart();
               }
           } else {
               loop = false;
           }
       }

       while (loop2) {
           Object adminPassword = JOptionPane.showInputDialog(f, "Enter Administrator Password;");

           if (!adminPassword.equals("admin11"))// search admin list for admin with matching admin password
           {
               int reply = JOptionPane.showConfirmDialog(null, null, "Incorrect Password. Try again?", JOptionPane.YES_NO_OPTION);
               if (reply == JOptionPane.YES_OPTION) {

               } else if (reply == JOptionPane.NO_OPTION) {
                   f1.dispose();
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
           admin();
       }
   }



    public JFrame getF1() {
        return f1;
    }



    public void setF1(JFrame f1) {
        this.f1 = f1;
    }



    public JFrame getF() {
        return f;
    }



    public void setF(JFrame f) {
        this.f = f;
    }
    
    
}
