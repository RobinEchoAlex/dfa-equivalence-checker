package main.java;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2 || args.length!=3) {
            System.out.println("Invalid parameters");
        }
        Main main = new Main();

        if (args.length == 3) {
            main.readDfa(args[1]);
            switch (args[0]) {
                case "-complementation":
                    break;
                case "-intersection":
                    break;
                case "-sd":
                    break;
                case "-emptyness":
                    break;
                case "-equivalence":
                    break;
            }
        } else {
            main.readDfa(args[0]);
            main.readDfa(args[1]);
        }

    }

    public void readDfa(String filePath) {

    }
}
