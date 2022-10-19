import java.io.Serial;
import java.io.Serializable;

class Customer implements Serializable {
    @Serial
    private static final long serialVersionUID = 42L;
    private float cash = 500;
    private double bankAccount;
    private Inventory inventory = new Inventory();
    private int amountOfPurchases = 0;
    private float costOfPurchases = 0;

    void appendInventory(Inventory inventory){
        setAmountOfPurchases(inventory.getLength());
        this.inventory.appendCurrentInventory(inventory);
    }
    void printInventory(){
        inventory.printInventory();
    }

    float getCash(){
        return cash;
    }
    void spendMoney(float cost){
        cash -= cost;
        setCostOfPurchases(cost);
    }

    int getAmountOfPurchases() {
        return amountOfPurchases;
    }

    private void setAmountOfPurchases(int amountOfPurchases) {
        this.amountOfPurchases += amountOfPurchases;
    }

    float getCostOfPurchases() {
        return costOfPurchases;
    }

    private void setCostOfPurchases(float costOfPurchases) {
        this.costOfPurchases += costOfPurchases;
    }
    double getBankAccount(){
        return bankAccount;
    }
    void setBankAccount(double bankAccount ){
        this.bankAccount = bankAccount;
    }
}
