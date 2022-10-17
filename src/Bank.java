import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

class Bank {

    static void main() {
        final String bankAccountFile = "GritBank.dat";
        BankAccount[] currentBank = FileHandler.readBankFile(bankAccountFile);
//        BankAccount currentAccount = new BankAccount("test","surname", pin);
        BankAccount currentAccount;


        System.out.println("""
                Welcome to Grit Banking Services!
                How may we be of assistance?
                1. Login
                2. Create Account
                3. print some stuff
                0. Quit
                """);
        boolean menuLoop = true;
        while (menuLoop) {
            int menuChoice = UserInput.getInt();
            switch (menuChoice) {
                case 1 -> System.out.println("To be implemented");
                case 2 -> {
                    System.out.println("Please enter your name: ");
                    String name = UserInput.getName();
                    System.out.println("Please enter your surname: ");
                    String surname = UserInput.getName();
                    System.out.println("Please enter a new PIN: ");
                    int pin = UserInput.getInt();
                    currentAccount = new BankAccount(name, surname,pin);
                    System.out.println("your accntnr is: " + currentAccount.getAccountNr());
                    System.out.println("your balance is: " + currentAccount.getBalance());
                    System.out.println("your balance is: " + currentAccount.getSurname());
                    System.out.println("your balance is: " + currentAccount.getName());
                }
                case 3 -> {

                }
                case 0 -> {
                    menuLoop = false;
                    System.out.println("Bye!");
                }
                default -> System.out.println("Please choose a valid number.");
            }
        }


    }
/*
    void appendCurrentAccount(){
        BankAccount[] tempBank= Arrays.copyOf(currentBank, currentBank.length + 1);
        tempBank[tempBank.length] = currentAccount;
        currentBank = tempBank;
    }

 */

    static class BankAccount {
        private final String name;
        private final String surname;
        final private long accountNr;
        private boolean locked = true;
        private float balance;
        Security security;

        class Security{
            private final byte[] pin;
            private final byte[] salt;


            Security(byte[] pin, byte[] salt) {
                this.pin = pin;
                this.salt = salt;
            }
        }

        BankAccount(String name, String surname, int pin) {
            this.name = name;
            this.surname = surname;
            Random rand = new Random();
            StringBuilder tempAccountNr = new StringBuilder();
            for (int i = 0; i < 7 ; i++) {
                int j = rand.nextInt(10);
                tempAccountNr.append(j);
            }
            this.accountNr = Long.parseLong(tempAccountNr.toString());
            BigInteger bigInt = BigInteger.valueOf(pin);
            byte[] salt = new byte[16];
            new SecureRandom().nextBytes(salt);
            this.security = new Security(bigInt.toByteArray(), salt );
            System.out.println(Arrays.toString(security.pin) + " and salt is " + Arrays.toString(security.salt));
        }

        private long getAccountNr() {
            return accountNr;
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
    }
}

