import java.io.File;

public class FileManager {
    private File file;

    public FileManager(String directory) {
        this.file = new File(directory);
    }

    public void printFiles() {
        File[] files = this.file.listFiles();
        assert files != null;
        for (File file : files) {
            System.out.println(file);
        }
    }

    public static void main(String... args) {
        if (args.length > 1) {
            System.out.println("Invalid input");
            System.exit(0);
        }

        FileManager fm;
        if (args.length == 0) {
            fm = new FileManager(System.getProperty("user.home"));
        } else {
            fm = new FileManager(args[0]);
        }
        fm.printFiles();
    }
}
