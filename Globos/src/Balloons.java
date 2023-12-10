public class Balloons {
    private int[] balloons;
    private int maxSize;
    private int numCurrentBalloon;
    private int numBlowingNow;
    private int maxBlowing;
    private int maxVolume;
    private int numBalloonsWarehouse;

    synchronized public int getNumBalloonsWarehouse() {
        return numBalloonsWarehouse;
    }

    synchronized public void setNumBalloonsWarehouse(int numBalloonsWarehouse) {
        this.numBalloonsWarehouse = numBalloonsWarehouse;
    }

    synchronized public int getNumCurrentBalloon() {
        return numCurrentBalloon;
    }

    synchronized public void setNumCurrentBalloon(int numCurrentBalloon) {
        this.numCurrentBalloon = numCurrentBalloon;
    }



    synchronized public int getNumBlowingNow() {
        return numBlowingNow;
    }

    synchronized public void setNumBlowingNow(int numBlowingNow) {
        this.numBlowingNow = numBlowingNow;
    }



    synchronized public int[] getBalloons() {
        return balloons;
    }

    synchronized public void setBalloons(int[] balloons) {
        this.balloons = balloons;
    }

    public Balloons(int maxBalloons, int maxBlowing, int maxVolume) {
        this.maxSize=maxBalloons;
        balloons =new int[maxBalloons];
        for (int i=0;i<maxSize;i++){
            balloons[i]=0;
        }
        this.numCurrentBalloon =1;
        this.numBlowingNow =0;
        this.maxBlowing = maxBlowing;
        this.numBalloonsWarehouse =maxSize;
        this.maxVolume=maxVolume;
    }

    synchronized public int getMaxSize() {
        return maxSize;
    }

    synchronized public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
    synchronized public int getBalloons(String name){
        int res=-1;
        while (numBlowingNow == maxBlowing) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (numCurrentBalloon >= maxSize + 1) {
            System.out.println("There are no more balloons to deliver");
        }else {
            System.out.println("Balloon " + numCurrentBalloon + " delivered to " + name);
            res = numCurrentBalloon;
            this.setNumBlowingNow(this.getNumBlowingNow()+1);
            //System.out.println("Blowing balloons right now:"+this.getNumBlowingNow());
            numCurrentBalloon++;
            notifyAll();
        }
        return res;
    }
    synchronized public boolean blowBalloon(String name, int numBalloon) {
        boolean blowing = false;
        if (numBalloon <= maxSize + 1 && balloons[numBalloon - 1] <= maxVolume) {
            balloons[numBalloon - 1]++;
            blowing = true;
        }
        if (balloons[numBalloon - 1] > maxVolume && blowing) {
            System.out.println("Balloon " + numBalloon + " bursts");
            if (this.getNumBlowingNow() > 0) {
                this.setNumBlowingNow(this.getNumBlowingNow() - 1);
            }
            if (this.getNumBalloonsWarehouse() > 0) {
                this.setNumBalloonsWarehouse(this.getNumBalloonsWarehouse() - 1);
            }
            notifyAll();
            return true;
        }
        if (blowing){
            System.out.println("Balloon "+numBalloon+" gets volume "+ balloons[numBalloon-1]);
            return false;
        }else{
            return true;
        }
    }

    synchronized public boolean prickBalloon(String name) {
        while (this.getNumBlowingNow()==0){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        boolean pinchado=false;
        int i=0;
        while (!pinchado && i<maxSize){
            if (balloons[i]>0 && balloons[i]<= maxVolume){
                System.out.println("Balloon "+(i+1)+" pricked by "+name);
                if (this.getNumBalloonsWarehouse()>0) {
                    this.setNumBalloonsWarehouse(this.getNumBalloonsWarehouse() - 1);
                }
                balloons[i]= maxVolume +2;
                this.setNumBlowingNow(this.getNumBlowingNow()-1);
                pinchado=true;
                notifyAll();
            }else {
                i++;
            }
        }
        return this.getNumBlowingNow()!=0;
    }
}