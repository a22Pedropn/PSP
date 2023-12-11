public class Main {

    public static void main(String[] args) {

        System.out.println("Start the simulation");

        Book[] books=new Book[9];

        for (int i=0;i<9;i++){
            Book b=new Book(i);
            books[i]=b;
        }
        Thread[] students=new Thread[4];
        for (int i=0;i<4;i++) {
            students[i] = new Thread(new Student(books,"Student"+i));
            students[i].start();
        }
    }
}