public class Main {
    public static final int MAX_CLIENTS = 100;

    public static void main(String[] args) {

        System.out.println("Starting clerk and customer threads");

        TakeANumber numberGadget = new TakeANumber(MAX_CLIENTS);

        Thread clerkThread = new Thread(new Clerk(numberGadget));
        clerkThread.start();

        for (int i=0;i<MAX_CLIENTS;i++) {
            Thread customerThread =new Thread(new Customer(numberGadget));
            customerThread.start();
        }
    }
}