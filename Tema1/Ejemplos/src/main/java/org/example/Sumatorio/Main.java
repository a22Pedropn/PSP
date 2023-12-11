package org.example.Sumatorio;

import java.io.File;

public class Main {
    final static String ERROR_FILE = "C:\\Users\\pedro\\Desktop\\FP\\2º Segundo\\PSP\\Tema1\\Ejemplos\\errores\\errors.txt";
    final static String CLASS_PATH = System.getProperty("java.class.path");

    public static void main(String[] args) {

        String[] command = { "java", "-cp", CLASS_PATH, "org.example.Sumatorio.Suma", "10", "120" };
        String[] commandError = { "java", "-cp", CLASS_PATH, "org.example.Sumatorio.Suma"};

        ProcessBuilder pb = new ProcessBuilder();
        ProcessBuilder pbError = new ProcessBuilder().command(commandError);

        pb.command(command);
        pb.redirectError(new File(ERROR_FILE));

        try {

            Process process = pb.start();
            process.waitFor();

            Process processError = pbError.start();
            processError.waitFor();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}