package TPModul3;

public class Waiter implements Runnable {

    private int orderQty;
    private int customerID;
    private static int coffeePrice = 25000;
    CoffeMachine coffeMachine;


    public Waiter(int orderQty, int customerID) {
        this.orderQty = orderQty;
        this.customerID = customerID;
    }


    @Override
    public void run() {
        // TODO Auto-generated method stub
        getCoffee(coffeMachine);
        // pending for 15000 ms
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public void orderInfo() {
        System.out.println("Customer " + customerID + " ordered " + orderQty + " coffees" + " for " + (orderQty * coffeePrice) + " Rupiah");
    }

    public void getCoffee(CoffeMachine lock) {
        // show Displays the coffee order delivered
        orderInfo();
        // change value of coffeeReady to false
        coffeMachine.coffeReady = false;
        // Check whether the value of the method
        // getCoffeeNumber belongs to CoffeeMachine class equal to
        // orderQty. If the same, then display order and exit information program
        if (CoffeMachine.getCoffeeNumber() == orderQty) {
            System.out.println("Coffee " + orderQty + " is ready");
            System.exit(0);
        }
        // call CoffeeMachine.lock.notifyAll() to inform can make next coffee
        CoffeMachine.lock.notifyAll();


    
    }

}

