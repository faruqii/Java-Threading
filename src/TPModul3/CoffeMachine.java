package TPModul3;

public class CoffeMachine implements Runnable {

    private boolean waitingForPickup = false;
    private static Object lock = new Object();
    private int coffeeNumber = 1;
    
    
    @Override
    public void run() {
        // call makeCoffee method and pending it to 5000 ms
        makeCoffee();
        while(this.waitingForPickup) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                System.out.println("InterruptedException");
            }
            System.out.println("Coffee " + coffeeNumber + " is ready");
            coffeeNumber++;
            this.waitingForPickup = false;
        }
        
    }

    public int getCoffeeNumber() {
        return this.coffeeNumber;
    }


    public void makeCoffee() {
        synchronized (lock) {
            if (this.waitingForPickup == true) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                System.out.println("Coffee is ready");
                this.waitingForPickup = true;
                lock.notify();
            }
        }
    
    }
}

    
