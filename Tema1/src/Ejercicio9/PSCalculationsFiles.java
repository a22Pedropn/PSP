package Ejercicio9;

import java.io.*;

public class PSCalculationsFiles {
    public static void main(String[] args) throws IOException, InterruptedException {
        File folder = new File(args[0]);
        File[] listOfFiles = folder.listFiles();
        File fileDest=new File(args[1]);

        for (File file : listOfFiles) {
            if (file.isFile()) {
                System.out.println(file.getName());

                ProcessBuilder ps=new ProcessBuilder();

                ps.command().add("java");
                ps.command().add("-cp");
                String classpath=System.getProperty("java.class.path");

                ps.command().add(classpath);
                ps.command().add("org.example.AddData");
                ps.command().add(args[0]);

                String fileData=file.getName();
                ps.command().add(file.getName());

                FileWriter fw = new FileWriter(fileDest, true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);

                pw.println(fileData);
                pw.println("====================");
                pw.flush();

                ps.redirectOutput(ProcessBuilder.Redirect.appendTo(fileDest));
                Process p=ps.start();
                p.waitFor();
                pw.println("====================");
                pw.println();
                pw.flush();
            }
        }
    }
}