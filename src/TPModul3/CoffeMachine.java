package TPModul3;

public class CoffeMachine implements Runnable {

    boolean coffeReady = false;
    static Object lock = new Object();
    private static int coffeeNumber = 1;
    
    
    @Override
    public void run() {
        // call makeCoffee method and pending it to 5000 ms
        makeCoffee();
        while(coffeReady) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                System.out.println("InterruptedException");
            }
            System.out.println("Coffee " + coffeeNumber + " is ready");
            coffeeNumber++;
            coffeReady = false;
        }
        
    }

    public static int getCoffeeNumber() {
        return coffeeNumber;
    }


    public void makeCoffee() {
        synchronized (lock) {
            if (coffeReady == true) {
                try {
                    CoffeMachine.lock.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                System.out.println("Coffee is ready");
                coffeReady = true;
                CoffeMachine.lock.notify();
            }
        }
    
    }

    public void setCoffeeNumber(int i) {
    }
}

    
