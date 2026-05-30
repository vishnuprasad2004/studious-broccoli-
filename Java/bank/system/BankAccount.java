package bank.system;


sealed class BankAccount permits CurrentAccount, SavingsAccount {
    protected static final String BANK_NAME = "HDFC Bank";
    private String customerName;
    private long accountNo;
    private long customerId;
    private int branchCode;
    private String IFSCCode;
    private double balance;



    private BankAccountType type;

    BankAccount(String customerName, long accountNo, long customerId, int branchCode, String IFSCCode) {
        this.accountNo = accountNo;
        this.branchCode = branchCode;
        this.customerId = customerId;
        this.IFSCCode = IFSCCode;
        this.customerName = customerName;
    }

    public BankAccountType getType() {
        return type;
    }

    //
    protected void displayDetails() {
        System.out.println("==============================================");
        System.out.printf("%-20s : %s%n", "Bank Name", BANK_NAME);
        System.out.printf("%-20s : %s%n", "Customer Name", customerName);
        System.out.printf("%-20s : %d%n", "Customer ID", customerId);
        System.out.printf("%-20s : %d%n", "Account Number", accountNo);
        System.out.printf("%-20s : %d%n", "Branch Code", branchCode);
        System.out.printf("%-20s : %s%n", "IFSC Code", IFSCCode);
//        System.out.printf("%-20s : ₹%.2f%n", "Balance", balance);
    }

    public void setType(BankAccountType type) {
        this.type = type;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setAccountNo(long accountNo) {
        this.accountNo = accountNo;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public void setBranchCode(int branchCode) {
        this.branchCode = branchCode;
    }

    public void setIFSCCode(String IFSCCode) {
        this.IFSCCode = IFSCCode;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public long getAccountNo() {
        return accountNo;
    }

    public long getCustomerId() {
        return customerId;
    }

    public int getBranchCode() {
        return branchCode;
    }

    public String getIFSCCode() {
        return IFSCCode;
    }

    public double getBalance() {
        return balance;
    }





}
