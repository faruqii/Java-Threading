package TPModul3;

public class CoffeeMachine implements Runnable {

    private boolean waitingForPickup = false;
    private static final Object lock = new Object();
    private static int coffeeNumber = 1;

    @Override
    public void run() {
        while (true) {
            makeCoffee();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setWaitingForPickup(boolean waitingForPickup) {
        this.waitingForPickup = waitingForPickup;
    }

    public static int getCoffeeNumber() {
        return coffeeNumber;
    }

    public void makeCoffee() {
        synchronized(CoffeeMachine.lock) {
            // check if waitingForPickup is true so call method lock.wait() to lock for thread
            // so process not to continue before waiter deliver coffee
            if (this.waitingForPickup) {
                try {
                    System.out.println("Coffee Machine: Waiting for the Waiter to deliver the coffee");
                    CoffeeMachine.lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            // to inform another thread that another coffee is ready to deliver
            waitingForPickup = true;
            System.out.println("Coffee Machine:  Making Coffee Number " + coffeeNumber++);
            CoffeeMachine.lock.notifyAll();
            System.out.println("Coffee Machine: Telling the waiter to get the coffee\n");
        }
    }

    public static Object getLock() {
        return lock;
    }
}
