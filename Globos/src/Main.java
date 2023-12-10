public class Main {
    public static void main(String[] args) {
        public static final int MAX_BALLOONS =10;
        public static final int MAX_I=5;
        public static final int MAX_BLOWING =3;
        public static final int MAX_VOLUME=5;

        Balloons balloons =new Balloons(MAX_BALLOONS, MAX_BLOWING,MAX_VOLUME);
        Thread[] hg=new Thread[MAX_I];
        Thread[] pg=new Thread[MAX_I];
        for (int i=0;i<MAX_I;i++) {
            hg[i] = new Thread(new BalloonBlower("BB" + i, balloons));
            pg[i] = new Thread(new BalloonPricker("BP" + i, balloons));
        }
        for (int i=0;i<MAX_I;i++){
            hg[i].start();
            pg[i].start();
        }
        for (int i=0;i<MAX_I;i++){
            hg[i].join();
            pg[i].join();
        }
    }
}