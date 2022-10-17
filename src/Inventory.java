import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

class Inventory implements Serializable {
    @Serial
    private static final long serialVersionUID = 42L;
    private Product[] currentInventory = new Product[0];
    int getLength(){
        return currentInventory.length;
    }
    private Product[] getCurrentInventory() {
        return currentInventory;
    }
    void appendCurrentInventory(Inventory inventory) {
        Product[] tempInventory = new Product[currentInventory.length + inventory.getLength()];
        System.arraycopy(currentInventory,0,tempInventory,0,currentInventory.length);
        System.arraycopy(inventory.getCurrentInventory(),0,tempInventory,currentInventory.length,inventory.getLength());
        currentInventory = tempInventory;
    }

    float totalWorth(){
        float worth = 0;
        for(Product product:currentInventory){
            worth += product.getPrice() * product.getItems();
        }
        return worth;
    }


    void addToCart(Inventory cart, int product, int amount){
        if(currentInventory[product].enoughItems(amount)) {
            cart.addProduct(currentInventory[product].getName(), currentInventory[product].getPrice(), amount);

        }
    }

    void addProduct(String name, float price, int items) {
        Product[] tempInventory = Arrays.copyOf(currentInventory, currentInventory.length + 1);
        tempInventory[currentInventory.length] = new Product(name, price, items);
        this.currentInventory = tempInventory;
    }

    void printInventory() {
        System.out.printf(String.format("#|%-15s|%-15s|%-15s|%-15s%n","Name","Price","Amount","Rebated"));
        for (int i = 0; i < currentInventory.length; i++) {
            if(currentInventory[i] != null) {
                System.out.println(String.format("%d|%-15s|%-15s|%-15s|%-15s",i,currentInventory[i].getName(), currentInventory[i].getPrice() , currentInventory[i].getItems() , ((currentInventory[i] instanceof RebatedProduct) ? "Yes" : "No")));
            }
        }
    }

    void rebateProduct(int index, float rebateRate) {
        if (currentInventory[index] instanceof RebatedProduct) {
            ((RebatedProduct) currentInventory[index]).setRebateRate(rebateRate);
        } else {
            currentInventory[index] = new RebatedProduct(currentInventory[index].getName(), currentInventory[index].getPrice(), currentInventory[index].getItems(), rebateRate);
        }
        System.out.println(currentInventory[index].getClass());
    }

    void removeRebate(int index) {
        if (currentInventory[index] instanceof RebatedProduct) {
            currentInventory[index] = new Product(currentInventory[index].getName(), currentInventory[index].getPrice(), currentInventory[index].getItems());
        } else {
            System.out.println("Product is not rebated");
        }
    }

    void removeItem(int product) {
        Product[] tempInventory = new Product[currentInventory.length-1];
        System.arraycopy(currentInventory,0,tempInventory,0,product);
        System.arraycopy(currentInventory,product+1,tempInventory,product,currentInventory.length - product - 1);
        this.currentInventory = tempInventory;

    }
    void modifyProduct(int index){
        boolean modify = true;
        while (modify){
            System.out.println("""
                        1. Set name
                        2. Set price
                        3. Set stock
                        0. Quit""");
            int choice = UserInput.getInt();
            switch (choice){

                case 1 -> {
                    System.out.println("Please enter new name: ");
                    currentInventory[index].setName(UserInput.getString());
                }
                case 2-> {
                    System.out.println("Please enter new price");
                    currentInventory[index].setPrice(UserInput.getFloat());
                }
                case 3-> {
                    System.out.println("Please enter new stock");
                    currentInventory[index].setItems(UserInput.getInt());
                }
                case 0 -> modify = false;
                default -> System.out.println("Please choose a valid number.");
            }

        }
    }


    private static class Product implements Serializable {
        @Serial
        private static final long serialVersionUID = 42L;
        private String name;
        private float price;
        private int items;

        Product(String name, float price, int items) {
            this.name = name;
            this.price = price;
            this.items = items;
        }

        private String getName() {
            return name;
        }

        private void setName(String name) {
            if (name.isBlank()) {
                System.out.println("Can't set name to blank.");
            } else {
                this.name = name;
            }
        }

        float getPrice() {
            return price;
        }

        private void setPrice(float price) {
            if (price <= 0) {
                System.out.println("Can't set price to zero or negative");
            } else {
                this.price = price;
            }
        }

        private int getItems() {
            return this.items;
        }

        private void setItems(int items) {
            if (items < 0) {
                System.out.println("Can't set items to negative");
            } else {
                this.items = items;
            }
        }

       private boolean enoughItems(int items) {
            if (this.items >= items && this.items > 0) {
                this.items -= items;
                return true;
            } else {
                System.out.println("Not enough items.");
                return false;
            }
        }
    }

    private static class RebatedProduct extends Product {
        @Serial
        private static final long serialVersionUID = 42L;
        private float rebateRate;

        private RebatedProduct(String name, float price, int items, float rebateRate) {
            super(name, price, items);
            this.rebateRate = rebateRate;
        }

        private void setRebateRate(float rebateRate) {
            this.rebateRate = rebateRate;
        }

        float getPrice() {
            return super.getPrice() * rebateRate;
        }
    }
}
