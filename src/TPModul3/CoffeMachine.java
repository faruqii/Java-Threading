package TPModul3;

public class CoffeMachine implements Runnable {

    private boolean waitingForPickup = false;
    private static Object lock = new Object();
    private static int coffeeNumber = 1;
    
    
    @Override
    public void run() {
        makeCoffee();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int getCoffeeNumber() {
        return coffeeNumber;
    }


    public void makeCoffee() {
        synchronized (lock) {
            if (this.waitingForPickup == true) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                this.waitingForPickup = true;
                lock.notifyAll();
                System.out.println("Coffee Machine:  Making Coffee Number " + coffeeNumber);
                coffeeNumber++;
                System.out.println("Coffee Machine:  Telling the waiter to pick up the coffee");

            }
        }
    
    }

    public boolean isWaitingForPickup() {
        return waitingForPickup;
    }

    public void setWaitingForPickup(boolean waitingForPickup) {
        this.waitingForPickup = waitingForPickup;
    }

    public static Object getLock() {
        return lock;
    }
    
}
