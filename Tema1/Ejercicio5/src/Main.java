import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/** Create a process to launch in the command line a tracert to iessanclemente.net
 *  Redirect the process output to outputTracert.txt using the redirectOutput method of ProcessBuilder
 *  Wait for 500 miliseconds and destroy the process after that time throwing an InterruptedException
 */
public class Main {
    public static int TIME_MAX = 500;

    public static void main(String[] args) throws IOException,InterruptedException{

        ProcessBuilder pb = new ProcessBuilder(new String[]{"cmd", "/C", "tracert", "iessanclemente.net"});
        System.out.printf("Command in execution: %s\n", Arrays.toString(args));

        pb.redirectOutput(new File("outputTracert.txt"));

        Process p = pb.start();

        if (!p.waitFor(TIME_MAX, TimeUnit.MILLISECONDS)) {
            p.destroy();
            System.out.printf("NOTICE: Not finished in %d ms\n",
                    TIME_MAX);
            throw new InterruptedException(
                    "Process has been interrupted because of timeout (" + TIME_MAX + "ms). ");
        }
        if (p.isAlive()){
            System.out.println("The process is still alive");
        }
    }
}