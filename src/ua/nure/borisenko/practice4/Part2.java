package ua.nure.borisenko.practice4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;

public class Part2 {
    private static final int FOR_RANDOM = 50;
    private static final String ERROR_TEXT = "IOException: %s%n";
    private static final String PATH = "src/ua/nure/borisenko/practice4/";

    public static void main(String[] args) {
        String path = PATH + "part2.txt";
        File file = new File(path);
        if (!file.exists()) {
            createFile(path);
        }
        writeFile((sortText(readFile(path))));
    }

    private static void createFile(String outPath) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPath), "utf-8"))) {
            // создаем рандомом цифры для файла
            SecureRandom rand = new SecureRandom();
            StringBuilder stringBilder = new StringBuilder();
            for (int i = 0; i < 10; i++) {
                stringBilder.append(rand.nextInt(FOR_RANDOM) + " ");
            }
            // записываем его в файл
            writer.write(stringBilder.toString());
        } catch (IOException e) {
            System.err.format(ERROR_TEXT, e);
        }
    }

    private static String readFile(String in) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(in))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            System.err.format(ERROR_TEXT, e);
        }
        return sb.toString();
    }

    private static void writeFile(String text) {

        try (Writer writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream("part2_sorted.txt"), "utf-8"))) {
            writer.write(text);
        } catch (IOException e) {
            System.err.format(ERROR_TEXT, e);
        }
        System.out.println("input  ==> " + text);
        System.out.println("output  ==> " + sortText(text));
    }

    public static String sortText(String text) {
        StringBuilder sb = new StringBuilder();
        boolean isSorted = false;
        String buf;
        String[] mas = text.split("\\D+");

        while (!isSorted) {
            isSorted = true;
            for (int i = 0; i < mas.length - 1; i++) {
                if (Integer.parseInt(mas[i]) > Integer.parseInt(mas[i + 1])) {
                    isSorted = false;

                    buf = mas[i];
                    mas[i] = mas[i + 1];
                    mas[i + 1] = buf;
                }
            }
        }
        for (String s : mas) {
            sb.append(s + " ");
        }
        return sb.toString();
    }
}
