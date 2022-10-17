class Manager {
    static void main() {
        String storeFile = "GritMart.dat";
        Inventory currentStore = FileHandler.readInventoryFile(storeFile);
        boolean menuLoop = true;
        while (menuLoop) {
            System.out.println("""
                    Please make a selection
                    1. Print inventory
                    2. Add product
                    3. Set or change rebate rate
                    4. Remove rebate
                    5. Remove product
                    6. Modify product
                    9. Quit without saving
                    0. Save and quit""");
            int menuChoice = UserInput.getInt();
            switch (menuChoice) {
                case 1 -> currentStore.printInventory();
                case 2 -> {
                    currentStore.printInventory();
                    String name = UserInput.getName();
                    System.out.println("Please enter price");
                    float price = UserInput.getFloat();
                    System.out.println("Please enter stock");
                    int items = UserInput.getInt();
                    currentStore.addProduct(name, price, items);
                }
                case 3 -> {
                    currentStore.printInventory();
                    int product;
                    do {
                    System.out.println("Please enter product");
                        product = UserInput.getInt();
                    } while (product > currentStore.getLength());
                    System.out.println("Please enter rebate rate");
                    float rebateRate = UserInput.getFloat();
                    currentStore.rebateProduct(product, rebateRate);
                }
                case 4 -> {
                    currentStore.printInventory();
                    int product;
                    do {
                        System.out.println("Please enter product");
                        product = UserInput.getInt();
                    } while (product > currentStore.getLength());
                    currentStore.removeRebate(product);
                }
                case 5 -> {
                    currentStore.printInventory();
                    int product;
                    do {
                        System.out.println("Please enter product");
                        product = UserInput.getInt();
                    } while (product > currentStore.getLength());
                    currentStore.removeItem(product);
                }
                case 6-> {
                    currentStore.printInventory();
                    int product;
                    do {
                        System.out.println("Please enter product");
                        product = UserInput.getInt();
                    } while (product > currentStore.getLength());
                    currentStore.modifyProduct(product);
                }

                case 9 ->{
                    menuLoop = false;
                    System.out.println("Bye!");
                }

                case 0 -> {
                    FileHandler.writeInventoryFile(currentStore,storeFile);
                    menuLoop = false;
                    System.out.println("Bye!");
                }
                default -> System.out.println("Please choose a valid number.");
            }
        }
    }
}


