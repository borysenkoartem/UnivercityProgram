package ua.nure.borisenko.practice6;

import java.io.IOException;

public class Demo {

    private static final String PATH = "src/ua/nure/borisenko/practice6/part6.txt";

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("~~~~~~~~~~~~Part1");
        ua.nure.borisenko.practice6.part1.Part1.main(args);
        System.out.println("~~~~~~~~~~~~Part2");
        ua.nure.borisenko.practice6.part2.Part2.main(args);
        System.out.println("~~~~~~~~~~~~Part3");
        ua.nure.borisenko.practice6.part3.Part3.main(args);
        System.out.println("~~~~~~~~~~~~Part4");
        ua.nure.borisenko.practice6.part4.Part4.main(args);
        System.out.println("~~~~~~~~~~~~Part5");
        ua.nure.borisenko.practice6.part5.Part5.main(args);
        System.out.println("~~~~~~~~~~~~Part6");
        ua.nure.borisenko.practice6.part6.Part6.main(new String[]{"--input", PATH, "--task", "frequency"});
        ua.nure.borisenko.practice6.part6.Part6.main(new String[]{"--input", PATH, "--task", "length"});
        ua.nure.borisenko.practice6.part6.Part6.main(new String[]{"--input", PATH, "--task", "duplicates"});
    }
}
