/*
* Aryan Majeed Karim
* am15489@auis.edu.krd
* References:
* https://stackoverflow.com/questions/38349445/how-to-delete-all-components-in-a-jpanel-dynamically/38350395
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends JFrame implements ActionListener {

    static class MyButtons extends JButton{ //a self-made buttons class to manage buttons easily
                                            //extends JButton to change color inside its constructor

        static MyButtons natwestBtn;                  //the home menu buttons
        static MyButtons barclaysBtn;
        static MyButtons HSBCBtn;

        static MyButtons createUserBtn;
        static MyButtons backToHomeBtn;

        static MyButtons deposit;                     //the operations buttons
        static MyButtons withdraw;
        static MyButtons loan;
        static MyButtons showInfo;

        static MyButtons backToOperationsMenu;        //the show information button

        static MyButtons exitBtn;

        MyButtons(String text){                        //CONSTRUCTOR for MyButtons
            super(text);
            if(text.compareTo("Exit")==0)
                setBackground(new Color(255, 157, 118));
            else if(text.compareTo("back")==0)
                setBackground(new Color(189, 207, 136));
            else
                setBackground(new Color(217, 244, 137));

        }
    }//end of the class


    private static JPanel home= new JPanel();    //our one and only Panel

    private JLabel bankChosen = new JLabel();    //I made this to be (class instance) for tracking which bank the user is using

    private JTextField nameTF;                    //the form TFs and MyButtons
    private JTextField accountNumTF;
    private JTextField balanceTF;

    private static ArrayList<Natwest> natwestUsers = new ArrayList<>();
    private static ArrayList<Barclays> barclaysUsers = new ArrayList<>();
    private static ArrayList<HSBC> HSBCUsers = new ArrayList<>();

    public static void main(String[] args) {
        new Main();
        home.setBackground(new Color(237, 254, 173));
    }

     private Main(){
        setAlwaysOnTop(true);
        setLocation(300,100);
        setSize(500,500);
        setResizable(false);
        setTitle("Electronic Bank");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        homeMenu();
    }
    private void homeMenu(){
        home.setLayout(null);
        MyButtons.natwestBtn = new MyButtons("Natwest");
        MyButtons.barclaysBtn = new MyButtons("Barclays");
        MyButtons.HSBCBtn = new MyButtons("HSBC");
        MyButtons.exitBtn  = new MyButtons("Exit");

        MyButtons.natwestBtn.addActionListener(this);
        MyButtons.barclaysBtn.addActionListener(this);
        MyButtons.HSBCBtn.addActionListener(this);
        MyButtons.exitBtn.addActionListener(this);

        MyButtons.natwestBtn.setBounds(100,100,200,30);
        MyButtons.barclaysBtn.setBounds(100,132,200,30);
        MyButtons.HSBCBtn.setBounds(100,164,200,30);
        MyButtons.exitBtn.setBounds(100,220,100,30);


        home.add(MyButtons.natwestBtn);
        home.add(MyButtons.barclaysBtn);
        home.add(MyButtons.HSBCBtn);
        home.add(MyButtons.exitBtn);
        add(home);
        setVisible(true);
    }
    private void fillForm() {
        home.removeAll();

        home.setLayout(null);
        JLabel name = new JLabel("Name:");
        nameTF = new JTextField();
        JLabel accountNum = new JLabel("Acc. Number:");
        accountNumTF = new JTextField();
        JLabel balance = new JLabel("Balance:");
        balanceTF = new JTextField();
        MyButtons.createUserBtn= new MyButtons("create");
        MyButtons.createUserBtn.addActionListener(this);
        MyButtons.backToHomeBtn = new MyButtons("back");
        MyButtons.backToHomeBtn.addActionListener(this);

        bankChosen.setBounds(20,10,200,20);
        name.setBounds(60,100,100,30);
        nameTF.setBounds(200,100,100,30);
        accountNum.setBounds(60,132,100,30);
        accountNumTF.setBounds(200,132,100,30);
        balance.setBounds(60,164,100,30);
        balanceTF.setBounds(200,164,100,30);
        MyButtons.createUserBtn.setBounds(200,220,100,30);
        MyButtons.backToHomeBtn.setBounds(50,220,100,30);

        home.add(bankChosen);
        home.add(name);
        home.add(nameTF);
        home.add(accountNum);
        home.add(accountNumTF);
        home.add(balance);
        home.add(balanceTF);
        home.add(MyButtons.createUserBtn);
        home.add(MyButtons.backToHomeBtn);
        add(home);

        home.revalidate();
        home.repaint();
    }



    private void bankOperations() {
        home.removeAll();

        home.setLayout(null);

        MyButtons.deposit = new MyButtons("Deposit");
        MyButtons.withdraw = new MyButtons("Withdraw");
        MyButtons.loan = new MyButtons("Loan");
        MyButtons.showInfo = new MyButtons("Show Information");
        MyButtons.backToHomeBtn = new MyButtons("back");

        bankChosen.setBounds(10,10,206,20);
        MyButtons.deposit.setBounds(100,100,206,30);
        MyButtons.withdraw.setBounds(100,132,206,30);
        MyButtons.loan.setBounds(100,164,206,30);
        MyButtons.showInfo.setBounds(100,196,206,30);
        MyButtons.backToHomeBtn.setBounds(100,234,100,30);

        MyButtons.showInfo.addActionListener(this);
        MyButtons.backToHomeBtn.addActionListener(this);
        MyButtons.deposit.addActionListener(this);
        MyButtons.withdraw.addActionListener(this);
        MyButtons.loan.addActionListener(this);

        addExitButton();    //because i used same exit button in two different methods

        home.add(bankChosen);
        home.add(MyButtons.deposit);
        home.add(MyButtons.withdraw);
        home.add(MyButtons.loan);
        home.add(MyButtons.showInfo);
        home.add(MyButtons.backToHomeBtn);
        add(home);
        home.setVisible(true);
        home.revalidate();
        home.repaint();
    }

    private void addExitButton() {
        MyButtons.exitBtn  = new MyButtons("Exit");
        MyButtons.exitBtn.setBounds(206,234,100,30);
        MyButtons.exitBtn.addActionListener(this);
        home.add(MyButtons.exitBtn);
    }

    private void showInfo(String[] results){
        home.removeAll();

        home.setLayout(null);
        JLabel name = new JLabel("Name: "+results[0]);
        JLabel accountNum = new JLabel("Account Number: "+results[1]);
        JLabel balance = new JLabel("Balance: "+results[2]);
        JLabel loan = new JLabel("Loan: "+results[3]);
        MyButtons.backToOperationsMenu = new MyButtons("back");
        MyButtons.backToOperationsMenu.addActionListener(this);

        name.setBounds(110,70,200,30);
        accountNum.setBounds(110,100,200,30);
        balance.setBounds(110,130,200,30);
        loan.setBounds(110,160,200,30);
        MyButtons.backToOperationsMenu.setBounds(100,234,100,30);

        addExitButton();

        home.add(name);
        home.add(accountNum);
        home.add(balance);
        home.add(loan);
        home.add(MyButtons.backToOperationsMenu);
        add(home);
        setVisible(true);

        home.revalidate();
        home.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==MyButtons.natwestBtn){
            bankChosen.setText("Welcome to Natwest Bank");
            fillForm();
        }
        if (e.getSource()==MyButtons.barclaysBtn){
            bankChosen.setText("Welcome to Barclays Bank");
            fillForm();
        }
        if (e.getSource()==MyButtons.HSBCBtn){
            bankChosen.setText("Welcome to HSBC Bank");
            fillForm();
        }

        if (e.getSource()== MyButtons.createUserBtn){
            if(nameTF.getText().isEmpty() || accountNumTF.getText().isEmpty() || balanceTF.getText().isEmpty() ){
                JOptionPane.showMessageDialog(home,"Please fill the boxes!!");
            }else{

                Pattern p = Pattern.compile("[^a-z]", Pattern.CASE_INSENSITIVE);//creates a pattern containing only numbers and special chars
                Matcher m = p.matcher(nameTF.getText());//matches the name to the pattern

                if (m.find()){              //m.find() returns TRUE if it finds any numbers or special characters
                    JOptionPane.showMessageDialog(home,"Name cannot contain numbers or special characters! ");
                    return;
                }
                int accountNum;
                double balance;
                try {
                    accountNum= Integer.parseInt(accountNumTF.getText());
                    balance= Double.parseDouble(balanceTF.getText());
                }catch (NumberFormatException ex ){
                    JOptionPane.showMessageDialog(home, "You cannot use letters or special characters or huge numbers in Account Number or Balance field." +
                                                                 "\n                               NOTE:Balance can accept decimal numbers!! ");
                    return;
                }
                if(balance <0 || accountNum <0 ){
                    JOptionPane.showMessageDialog(home,"Balance or Account Number cannot be smaller than zero. ");
                    return;
                }
                switch (bankChosen.getText()) {             //creating accounts for users
                    case "Welcome to HSBC Bank": {
                        HSBCUsers.add(new HSBC(accountNum,nameTF.getText(),balance));
                        bankOperations();
                        break;
                    }
                    case "Welcome to Barclays Bank": {
                        barclaysUsers.add(new Barclays(accountNum,nameTF.getText(),balance));
                        bankOperations();
                        break;
                    }
                    case "Welcome to Natwest Bank": {
                        natwestUsers.add(new Natwest(accountNum,nameTF.getText(),balance));
                        bankOperations();
                        break;
                    }
                }
            }
        }
        if(e.getSource()==MyButtons.backToHomeBtn){       //resetting MyButtons for next usages
            bankChosen.setText("");
            nameTF.setText("");
            accountNumTF.setText("");
            balanceTF.setText("");
            home.removeAll();
            homeMenu();
            home.revalidate();
            home.repaint();
        }
        if( e.getSource()==MyButtons.deposit || e.getSource()==MyButtons.withdraw || e.getSource()==MyButtons.loan || e.getSource()==MyButtons.showInfo){
            String option="";
            if (e.getSource()==MyButtons.deposit){
                option="deposit";
            }
            if (e.getSource()==MyButtons.withdraw){
                option="withdraw";
            }
            if (e.getSource()==MyButtons.loan){
                option="borrow";
                if (bankChosen.getText().equalsIgnoreCase("Welcome to Barclays Bank")){
                    if (barclaysUsers.get(barclaysUsers.size()-1).balance<500){
                        JOptionPane.showMessageDialog(home, "Sorry! You're not eligible! \nYour balance is lower than $500");
                        return;
                    }
                }
                if (bankChosen.getText().equalsIgnoreCase("Welcome to Natwest Bank")){
                    if (natwestUsers.get(natwestUsers.size()-1).balance<100){
                        JOptionPane.showMessageDialog(home, "Sorry! You're not eligible! \nYour balance is lower than $100");
                        return;
                    }
                }
            }
            int amount=0;
            if(!(e.getSource()==MyButtons.showInfo)){
                try{
                    String numberEntered = JOptionPane.showInputDialog(home,"Please enter the amount you want to "+option);
                    if (numberEntered==null){           //this if statement will be executed when the user presses cancel
                        return;
                    }
                    amount = Integer.parseInt(numberEntered);
                }
                catch (NumberFormatException ex){       //this will be executed if the user enters an invalid input
                    JOptionPane.showMessageDialog(home, "You cannot use letters or special characters or huge numbers in the amount Input Dialog");
                    return;
                }
                if (amount<=0){
                    JOptionPane.showMessageDialog(home, "The number you entered is zero or less than zero!!");
                    return;
                }
            }
            switch (bankChosen.getText()) {                                   //All bank operations are handled in this switch
                case "Welcome to HSBC Bank":{                                 //While doing each action im getting a string back for confirmation
                    if(e.getSource()==MyButtons.deposit){                     //the string will be sent to show message dialog method
                        JOptionPane.showMessageDialog(home,HSBCUsers.get(HSBCUsers.size()-1).deposit(amount));
                        return;
                    }
                    if (e.getSource()==MyButtons.withdraw){
                        JOptionPane.showMessageDialog(home,HSBCUsers.get(HSBCUsers.size()-1).withdraw(amount));
                        return;
                    }
                    if(e.getSource()==MyButtons.loan){
                        JOptionPane.showMessageDialog(home,HSBCUsers.get(HSBCUsers.size()-1).getLoan(amount));
                        return;
                    }
                    if(e.getSource()==MyButtons.showInfo){
                        showInfo(HSBCUsers.get(HSBCUsers.size()-1).PrintAccountInfo());     //recreates the Panel again
                        return;
                    }
                }
                case "Welcome to Barclays Bank":{
                    if(e.getSource()==MyButtons.deposit){
                        JOptionPane.showMessageDialog(home,barclaysUsers.get(barclaysUsers.size()-1).deposit(amount));
                        return;
                    }
                    if (e.getSource()==MyButtons.withdraw){
                        JOptionPane.showMessageDialog(home,barclaysUsers.get(barclaysUsers.size()-1).withdraw(amount));
                        return;
                    }
                    if(e.getSource()==MyButtons.loan){
                        JOptionPane.showMessageDialog(home,barclaysUsers.get(barclaysUsers.size()-1).getLoan(amount));
                        return;
                    }
                    if (e.getSource()==MyButtons.showInfo){
                        showInfo(barclaysUsers.get(barclaysUsers.size()-1).PrintAccountInfo());
                        return;
                    }
                }
                case "Welcome to Natwest Bank":{
                    if(e.getSource()==MyButtons.deposit){
                        JOptionPane.showMessageDialog(home,natwestUsers.get(natwestUsers.size()-1).deposit(amount));
                        return;
                    }
                    if (e.getSource()==MyButtons.withdraw){
                        JOptionPane.showMessageDialog(home,natwestUsers.get(natwestUsers.size()-1).withdraw(amount));
                        return;
                    }
                    if(e.getSource()==MyButtons.loan){
                        JOptionPane.showMessageDialog(home,natwestUsers.get(natwestUsers.size()-1).getLoan(amount));
                        return;
                    }
                    if(e.getSource()==MyButtons.showInfo){
                        showInfo(natwestUsers.get(natwestUsers.size()-1).PrintAccountInfo());
                        return;
                    }
                }
            }
        }
        if (e.getSource()==MyButtons.backToOperationsMenu){ //creates bankoperations components again
            bankOperations();
        }
        if (e.getSource()==MyButtons.exitBtn){              // exit button
            System.exit(0);
        }
    }
}
