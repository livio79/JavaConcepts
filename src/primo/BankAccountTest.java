package primo;

public class BankAccountTest{
	public static void main(String[] args) {
		BankAccount account = new BankAccount(200.0);
		account.deposit(400);
		account.withdraw(-100);
		account.addInterest(10);
		System.out.println(account.getBalance());
	}
}

class BankAccount {
	
		double balance;
		
		BankAccount(double balance){
			this.balance = balance;
		}
		
		public void withdraw(double amount) {
			if(amount <= balance && amount>=0)
				balance -= amount;
			else
				System.out.println("Nicht mehr als " + balance);
		}
		
		/**
		 @param amount  the amount to deposit
		 */
		
		public void deposit(double amount) {
			if(amount>=0)
			balance += amount;
		}
		
		public void addInterest(double rate) {
			balance += (balance/100 * rate);
		}
		
		public double getBalance() {
			return balance;
		}
		
		
}


