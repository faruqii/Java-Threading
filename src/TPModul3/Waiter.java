package TPModul3;

public class Waiter implements Runnable {

    private int orderQty;
    private int customerID;
    private int coffeePrice = 25000;

    public Waiter(int customerID, int orderQty) {
        this.customerID = customerID;
        this.orderQty = orderQty;
    }


    @Override
    public void run() {
        // call getCoffee method and pending it to 5000 ms
        getCoffee();
        
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
        synchronized(CoffeMachine.getLock()) {
            // display order delivered coffee 
            System.out.println("Waiter:  Coffee Delivered");
            // if orderQty is greater than 1, then waiter inform machine to make another coffee
            if (CoffeMachine.getCoffeeNumber() == orderQty) {
                this.orderInfo();
                System.exit(0);
            } else {
                System.out.println("Waiter:  Telling the machine to make another coffee");
                CoffeMachine.getLock().notifyAll();
            }
            
        }
        
    }
    

}

