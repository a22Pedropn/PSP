import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        ProcessBuilder pb = new ProcessBuilder(new String[]{"cmd", "/C", "tracert", "iessanclemente.net"});
        System.out.printf("Command in execution: %s\n", Arrays.toString(args));

        pb.redirectOutput(new File("outputTracert.txt"));

        Process p = pb.start();

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            String line;
            System.out.println("Output of the process:");
            while ((line = bf.readLine()) != null) {
                System.out.println(line); // Imprimir la salida del proceso en la consola
            }
        }
        int exitCode = p.waitFor(); // Esperar a que el proceso termine
        System.out.println("Process exited with code: " + exitCode);


    }
}