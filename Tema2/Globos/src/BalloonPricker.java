import java.util.Random;
public class BalloonPricker implements Runnable{
    private Balloons balloons;
    private String name;
    public BalloonPricker(String name, Balloons balloons) {
        System.out.println(name+" created");
        this.balloons = balloons;
        this.name=name;
    }

    @Override
    public void run() {
        boolean possible;
        Random r=new Random();
        while(this.balloons.getNumBalloonsWarehouse()>0) {
            int tAleatorio = r.nextInt(9000) + 1000;
            try {
                Thread.sleep(tAleatorio);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if (this.balloons.getNumBalloonsWarehouse()>0) {
                possible = balloons.prickBalloon(name);
            }
        }
        System.out.println("Usuario "+name+" acabo su tarea");
    }
}