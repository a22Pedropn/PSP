import java.util.Random;

public class Student implements Runnable{
    public static final int NUM_LIBROS=9;
    private Book[] books;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Student(Book[] books, String nameStudent){
        this.books=books;
        this.setName(nameStudent);
    }

    @Override
    public void run() {

        do {
            Random r = new Random();
            int numb1 = r.nextInt(NUM_LIBROS);
            System.out.println("The student " + this.getName() + " selected the book " + numb1);
            int numb2 = -1;
            do {
                numb2 = r.nextInt(NUM_LIBROS);
                System.out.println("The student " + this.getName() + " selected the book " + numb2);
            } while (numb1 == numb2);
            try {
                ReadBook.read(books[numb1], books[numb2],this);
                long tSleep = r.nextInt(1000)+1000;
                Thread.currentThread().sleep(tSleep);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }while(true);

    }
}