package ua.nure.borisenko.practice6.part6;

public class Part6 {

    public static void main(String[] args) {

        if (args.length > 4) {
            throw new IllegalArgumentException();
        }
        String task;
        String path;
        if ("-i".equals(args[0]) || "--input".equals(args[0])) {
            path = args[1];
            task = args[3];
        } else if ("-t".equals(args[0]) || "--task".equals(args[0])) {
            path = args[3];
            task = args[1];
        } else {
            throw new IllegalArgumentException();
        }
        String text = new ReadFile(path).readFile();
        switch (task) {
            case "frequency":
                new Part61(text).find();
                break;
            case "length":
                new Part62(text).find();
                break;
            case "duplicates":
                new Part63(text).find();
                break;
            default:
                throw new IllegalArgumentException();
        }
    }
}
