class Store {
    static void main(Customer currentCustomer) {
        Inventory cart = new Inventory();
        final String storeFile = "GritMart.dat";
        Inventory currentStore = FileHandler.readInventoryFile(storeFile);
        boolean menuLoop = true;
        while (menuLoop) {
            System.out.println(("==========Store=========="));
            currentStore.printInventory();
            System.out.println("""
                    1. Put an item in your cart
                    2. View cart
                    3. Checkout
                    0. Quit""");
            int menuChoice = UserInput.getInt();
            switch (menuChoice) {
                case 1 -> {
                    System.out.println("Which item?");
                    int product;
                    do {
                        System.out.println("Please enter product");
                        product = UserInput.getInt();
                    } while (product > currentStore.getLength());
                    System.out.println("Please enter amount");
                    int amount = UserInput.getInt();
                    currentStore.addToCart(cart, product, amount);
                }
                case 2 -> {
                    System.out.println(("==========Cart==========="));
                    cart.printInventory();
                }
                case 3 -> {
                    System.out.println("The total is: " + cart.totalWorth());
                    System.out.println("Accept?");
                    if (UserInput.getYesOrNo()) {
                        if (cart.totalWorth() > currentCustomer.getCash()) {
                            System.out.println("Insufficient funds");
                        } else {
                            FileHandler.writeInventoryFile(currentStore, storeFile);
                            currentCustomer.spendMoney(cart.totalWorth());
                            currentCustomer.appendInventory(cart);
                            cart = new Inventory();
                        }
                    }
                }


                case 0 -> {
                    FileHandler.writeInventoryFile(currentStore, storeFile);
                    currentCustomer.printInventory();
                    System.out.println("You've spent " + currentCustomer.getCostOfPurchases() + " on a total of " + currentCustomer.getAmountOfPurchases());
                    menuLoop = false;
                    System.out.println("Bye!");
                }

            }
        }

    }
}