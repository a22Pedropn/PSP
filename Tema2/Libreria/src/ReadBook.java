import java.util.Random;

public class ReadBook {
    public static boolean read(Book b1, Book b2,Student student) throws InterruptedException {
        Book bA,bB;
        boolean result=false;
        if (b1.getId()<b2.getId()){
            bA=b1;
            bB=b2;
        }else{
            bA=b2;
            bB=b1;
        }
        synchronized(bA){
            synchronized(bB){
                Random random=new Random();
                long tRead=random.nextInt(1000)+2000;
                System.out.println("Student "+student.getName()+" is going to read "+tRead+" minutes" );
                Thread.currentThread().sleep(tRead);
                result=true;
            }

        }
        return result;
    }
}