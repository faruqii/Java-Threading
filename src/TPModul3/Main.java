package TPModul3;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        try {
            CoffeMachine machine = new CoffeMachine();
            System.out.println("Enter Customer ID: ");
            int customerID = input.nextInt();
            System.out.println("Enter how much coffe to made: ");
            int coffeMade = input.nextInt();
            Thread t1 = new Thread(machine);
            Waiter waiter = new Waiter(customerID, coffeMade);
            Thread t2 = new Thread(waiter);
            t2.join();
            t1.join();
            t1.start();
            t2.start();
        } catch (Exception e) {
            System.out.println("Input must be Integer");
        }

    }
}
