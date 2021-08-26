public class Natwest extends Customer implements BankOperations {

    Natwest(int accountNo, String name, double balance) {
        super(accountNo, name, balance);
    }

    public String deposit(int amount){
        balance=balance+amount-(amount*0.05);
        return "TRANSACTION SUCCESSFUL! \nYou deposited $"+amount+" to your account!";
    }
    public String withdraw(int amount){
        if (balance<amount){
            return "The operation cannot happen! \nYour withdrawal amount is higher than your balance!";
        }
        balance = balance - amount - 2;
        if (balance<=0){
            balance=0;
            return "TRANSACTION SUCCESSFUL! \nYou're bankrupted! Find a job!";
        }else
            return "TRANSACTION SUCCESSFUL! \nYou withdrew $"+amount+" from your account!";
    }
    public String getLoan(int amount){

        if (amount>1000){
            try {
                throw new MyException("You are not allowed to borrow more than 1000 dollars\n");
            } catch (MyException e) {
                return e.getMessage();
            }
        }else{
            loan=loan+amount;
            balance=balance+amount;
            return "TRANSACTION SUCCESSFUL! \n You borrowed $"+amount+" to your account!";
        }
    }
}
