import java.util.Arrays;
import java.util.Random;

class Bank {

    static void main(Customer currentCustomer) {


        final String bankAccountFile = "GritBank.dat";
        BankAccount[] currentBank = FileHandler.readBankFile(bankAccountFile);
        BankAccount currentAccount = null;



        System.out.println("""
                Welcome to Grit Banking Services!
                How may we be of assistance?
                1. Login
                2. Create Account
                0. Quit
                """);
        boolean menuLoop = true;
        while (menuLoop) {
            int menuChoice = UserInput.getInt();
            switch (menuChoice) {
                case 1 -> {
                    if(currentAccount == null){
                        System.out.println("Please create an account first");
                    }else {
                        for(int i = 0; i < currentBank.length; i++){
                            if (currentBank[i].getAccountNr() == currentCustomer.getBankAccount()){
                                currentAccount = currentBank[i];
                            }
                            else {
                                System.out.println("Customer not found");
                            }
                        }
                        System.out.println("Please enter password");
                        String password = UserInput.getString();
                        System.out.println("The password is: " + currentAccount.login(password));
                    }
                }

                case 2 -> {
                    System.out.println("Please enter your name: ");
                    String name = UserInput.getName();
                    System.out.println("Please enter your surname: ");
                    String surname = UserInput.getName();
                    System.out.println("Please enter a new PIN: ");
                    String pin = UserInput.getString();
                    currentAccount = new BankAccount(name, surname, pin);
                    currentCustomer.setBankAccount(currentAccount.accountNr);
                    BankAccount[] tempBank = Arrays.copyOf(currentBank, currentBank.length + 1);
                    tempBank[tempBank.length] = currentAccount;
                    currentBank = tempBank;


                    System.out.println("Account created");
                }
                case 0 -> {
                    menuLoop = false;
                    System.out.println("Bye!");
                }
                default -> System.out.println("Please choose a valid number.");
            }
        }
    }

    static class BankAccount {
        private final String name;
        private final String surname;
        final private long accountNr;
        Security security;
        private boolean locked = true;
        private float balance;

        BankAccount(String name, String surname, String pin) {
            this.name = name;
            this.surname = surname;
            Random rand = new Random();
            StringBuilder tempAccountNr = new StringBuilder();
            for (int i = 0; i < 7; i++) {
                int j = rand.nextInt(10);
                tempAccountNr.append(j);
            }
            this.accountNr = Long.parseLong(tempAccountNr.toString());
            this.security = new Security(pin);
        }

        boolean login(String password) {
            byte[] providedPassword = Authentication.hashPw(Authentication.stringToByteArray(password), security.getSalt());
            return Authentication.comparePassword(providedPassword, security.getPin());
        }

        private long getAccountNr() {
            return accountNr;
        }
        void deposit(float amountToDeposit){
            balance += amountToDeposit;
        }
        void withdraw(float amountToWithdraw){
            balance -= amountToWithdraw;
        }

        private float getBalance() {
            return balance;
        }

        private void setBalance(float balance) {
            this.balance = balance;
        }

        public String getName() {
            return name;
        }

        public String getSurname() {
            return surname;
        }

        public boolean isLocked() {
            return locked;
        }

        public void setLocked(boolean locked) {
            this.locked = locked;
        }

        class Security {
            private final byte[] pin;
            private final byte[] salt;


            Security(String password) {
                this.salt = Authentication.generateSalt();
                this.pin = Authentication.hashPw(Authentication.stringToByteArray(password), this.salt);
            }

            byte[] getSalt() {
                return salt;
            }

            byte[] getPin() {
                return pin;
            }
        }
    }
}

