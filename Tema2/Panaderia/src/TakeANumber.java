public class TakeANumber {
    private int next = 0;
    private int serving = 0;
    private int maxClients=0;
    public TakeANumber(int maxClients) {
        this.maxClients=maxClients;
    }
    public int getMaxClients() {
        return maxClients;
    }
    public synchronized int getServing() {

        return serving;
    }
    public synchronized int nextNumber(int custId) throws InterruptedException {  //Da Tiket al cliente
        next = next + 1;
        System.out.println("Customer " + custId + " takes ticket " + next);
        notifyAll();
        return next;
    }
    public synchronized int nextCustomer() { // Espera a que el siguiente cliente sea atendido
        try {
            while (next <= serving) {
                System.out.println("  Clerk waiting ");
                wait();   // espera a el clerk tenga un cliente
            }
        } catch (InterruptedException e) {
            System.out.println("Exception " + e.getMessage());
        } finally {
            ++serving;
            System.out.println("  Clerk serving ticket " + serving);
            notifyAll();
            return serving;
        }
    }
}
