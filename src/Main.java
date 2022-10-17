public class Main {
    public static void main(String[] args) {

        Customer currentCustomer = new Customer();
        boolean menuLoop = true;
        while (menuLoop) {
            System.out.println("""
                Welcome to Grit-mart.
                Please make a selection.
                1. Store
                2. View inventory
                3. Bank #WORK IN PROGRESS
                9. Manager
                0. Quit""");
            int menuChoice = UserInput.getInt();
            switch (menuChoice) {
                case 1 -> Store.main(currentCustomer);
                case 2 -> {
                    currentCustomer.printInventory();
                    System.out.println("Your wallet contains: " + currentCustomer.getCash());
                }
                case 3 -> Bank.main(); // Work in Progress

                case 9 -> Manager.main();
                case 0 -> {
                    menuLoop = false;
                    System.out.println("Bye!");
                }
                default -> System.out.println("Please choose a valid number.");
            }
        }
    }
}