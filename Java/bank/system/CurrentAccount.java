package bank.system;

public final class CurrentAccount extends BankAccount {
    private final double overdraftLimit = 10000.0;
    CurrentAccount(
            String customerName,
            long accountNo,
            long customerId,
            int branchCode,
            String IFSCCode
    ) {
        super(customerName, accountNo, customerId, branchCode, IFSCCode);
        super.setType(BankAccountType.CURRENT_ACCOUNT);
    }

    @Override
    protected void displayDetails() {
        super.displayDetails();
        System.out.printf("%-20s : %s%n", "Account Type", super.getType());
        System.out.printf("%-20s : %.2f%n", "Branch Code", overdraftLimit);
        System.out.println("==============================================");
    }
}
