public class BalloonBlower implements Runnable{
    private Balloons balloons;
    private String name;
    public BalloonBlower(String name, Balloons balloons) {
        System.out.println(name+" created");
        this.name=name;
        this.balloons = balloons;
    }

    @Override
    public void run() {
        boolean end=false;
        boolean damagedBalloon=false;
        while(!end) {
            int numGlobo=this.balloons.getBalloons(this.name);
            if (numGlobo==-1){
                end=true;
            }else {
                do {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    damagedBalloon = this.balloons.blowBalloon(this.name, numGlobo);
                } while (!damagedBalloon);
            }
        }
        System.out.println("The user "+this.name+" finishes their task");
    }
}