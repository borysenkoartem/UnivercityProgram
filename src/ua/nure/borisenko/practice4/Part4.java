package ua.nure.borisenko.practice4;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 implements Iterable<String> {


    private static final String REGEX = "([^.!?]+\\.)";
    private static final String PATH = "src/ua/nure/borisenko/practice4/";
    private String text = readText(PATH + "part4.txt");

    @Override
    public Iterator<String> iterator() {
        return new IteratorImpl(text);
    }

    class IteratorImpl implements Iterator<String> {
        private Pattern p = Pattern.compile(REGEX);
        private Matcher m = p.matcher(text);

        public IteratorImpl(String text) {
        }

        public boolean hasNext() {
            return m.find();
        }

        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return m.group().trim();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private static String readText(String textPath) {

        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(textPath), Charset.forName("windows-1251"))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);

            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Part4 p4 = new Part4();
        Iterator iterator = p4.iterator();
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());

    }
}
