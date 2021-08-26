
public abstract class Customer {
    private int accountNo;
    private String name;
    double balance;
    int loan;       // it is zero by default

    Customer(int accountNo, String name, double balance) {
        this.accountNo = accountNo;
        this.name = name;
        if(balance<0)
            this.balance = 0;
        else
            this.balance = balance;
    }

    String[] PrintAccountInfo(){ //returning an array of info
        return new String[]{name,String.valueOf(accountNo),String.valueOf(balance),String.valueOf(loan)};
    }
}
