package TPModul3;

public class Waiter implements Runnable {

    private final int orderQty;
    private final int customerID;
    static int coffeePrice = 25000;

    public Waiter(int customerID, int orderQty) {
        this.customerID = customerID;
        this.orderQty = orderQty;
    }



    @Override
    public void run() {
        // call getCoffee method and pending it to 5000 ms
        while (true) {
            getCoffee();
            try {
                Thread.sleep(15000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void orderInfo() {
        System.out.println("==========================================================");
        System.out.println("Customer ID: " + this.customerID);
        System.out.println("Numbeer of Coffee: " + this.orderQty);
        System.out.println("Total Price: " + this.orderQty * coffeePrice);
        System.out.println("==========================================================");
    }
    // create synchronized method getCoffee

    public void getCoffee() {
        synchronized(CoffeeMachine.getLock()) {

            System.out.println("Waiter: Coffee is ready to deliver");
            CoffeeMachine coffeeMachine = new CoffeeMachine();
            coffeeMachine.setWaitingForPickup(false);

            // check if value of getCoffeeNumber form CoffeeMachine class is equal to orderQty
            // if same, call method orderInfo() to print order info and exit the program
            if (CoffeeMachine.getCoffeeNumber() == this.orderQty + 1) {
                orderInfo();
                System.exit(0);
            }
            CoffeeMachine.getLock().notifyAll();
            System.out.println("Waiter: Tell the coffee machine to make another coffee\n");

        }
    }
}
