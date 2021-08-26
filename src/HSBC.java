public class HSBC extends Customer implements BankOperations{

    public HSBC(int accountNo, String name, double balance) {
        super(accountNo, name, balance);
    }

    public String deposit(int amount){
        balance=balance+amount-(amount*0.02);
        return "TRANSACTION SUCCESSFUL! \nYou deposited $"+amount+" to your account!";
    }
    public String withdraw(int amount){
        if (balance<amount){
            return "The operation cannot happen! \nYour withdrawal amount is higher than your balance!";
        }
        balance=balance-amount-3;
        if (balance<=0){
            balance=0;
            return "TRANSACTION SUCCESSFUL! \nYou're bankrupted";
        }else
            return "TRANSACTION SUCCESSFUL! \nYou withdrew $"+amount+" from your account!";
    }
    public String getLoan(int amount){
        if (amount>2000){
            try {
                throw new MyException("You are not allowed to borrow more than 2000 dollars");
            } catch (MyException e) {
                return e.getMessage();
            }
        }else{
            loan=loan+amount;
            balance=balance+amount;
            return "TRANSACTION SUCCESSFUL! \nYou borrowed $"+amount+" to your account!";
        }
    }


}
