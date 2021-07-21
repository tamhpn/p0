import java.io.File;
import java.util.Scanner;

public class FileMan {
    private File file;
    private File[] subFiles;
    private boolean showHidden = false;

    public FileMan(String path) {
        this.file = new File(path);
        this.subFiles = this.file.listFiles();
    }

    private void printFiles() {
        assert this.subFiles != null;
        System.out.print("\033[H\033[2J");
        for (File file : this.subFiles) {
            if (file.isHidden() && showHidden) {
                System.out.println(file.getName());
            } else if (!file.isHidden()) {
                System.out.println(file.getName());
            }
        }
    }

    private void getInput() {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        switch(input) {
            case ".":
                this.showHidden = !this.showHidden;
                break;
            case "~":
                this.changeDirectory(System.getProperty("user.home"));
                break;
            case "h":
                if (file.getParent() != null) {
                    this.changeDirectory(file.getParent());
                }
                break;
            case "q":
                System.exit(0);
            default:
                break;
        }
    }

    private void changeDirectory(String path) {
        this.file = new File(path);
        this.subFiles = this.file.listFiles();
    }

    public void run() {
        this.printFiles();
        while (true) {
            this.getInput();
            this.printFiles();
        }
    }

    public static void main(String[] args) {
        if (args.length > 1) {
            System.out.println("Invalid input");
            System.exit(0);
        }

        FileMan fm;
        if (args.length == 0) {
            fm = new FileMan(System.getProperty("user.home"));
        } else {
            fm = new FileMan(args[0]);
        }
        fm.run();
    }
}
