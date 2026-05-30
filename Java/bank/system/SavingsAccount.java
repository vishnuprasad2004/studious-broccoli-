package bank.system;

public final class SavingsAccount extends BankAccount {
    private final float interestRate = 2.5f;

    SavingsAccount(
            String customerName,
            long accountNo,
            long customerId,
            int branchCode,
            String IFSCCode
    ) {
        super(customerName, accountNo, customerId, branchCode, IFSCCode);
        super.setType(BankAccountType.SAVINGS_ACCOUNT);
    }

    @Override
    protected void displayDetails() {
        super.displayDetails();
        System.out.printf("%-20s : %s%n", "Account Type", super.getType());
        System.out.printf("%-20s : %f%n", "Interest Rate", interestRate);
        System.out.println("==============================================");
    }
}
