import java.io.*;

class FileHandler {
    static void writeInventoryFile(Inventory store, String storeFile) {
        try (FileOutputStream fos = new FileOutputStream(storeFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(store);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static Inventory readInventoryFile(String storeFile) {
        if (new File(storeFile).exists()) {
            try (FileInputStream fis = new FileInputStream(storeFile);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                return (Inventory) ois.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return new Inventory();

    }

    static void writeBankFile(Bank.BankAccount[] bankAccounts, String bankAccountFile) {
        try (FileOutputStream fos = new FileOutputStream(bankAccountFile);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(bankAccounts);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    static Bank.BankAccount[] readBankFile(String bankAccountFile) {
        if (new File(bankAccountFile).exists()) {
            try (FileInputStream fis = new FileInputStream(bankAccountFile);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {
                return (Bank.BankAccount[]) ois.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        }
        return new Bank.BankAccount[0];

    }

}
