package com.github.tamhpn;

import java.io.File;
import java.util.Scanner;
import java.util.Arrays;

public class FileMan {
    private File file;
    private File[] allSubFiles;
    private File[] visibleSubFiles;
    private boolean showHidden = false;

    public FileMan(String path) {
        this.changeDirectory(path);
        this.run();
    }

    private void changeDirectory(String path) {
        this.file = new File(path);
        this.allSubFiles = this.file.listFiles();
        this.visibleSubFiles = Arrays.stream(this.allSubFiles).filter(f -> !f.isHidden()).toArray(File[]::new);
    }

    private void printFiles() {
        System.out.print("\033[H\033[2J");
        int counter = 0;
        if (showHidden) {
            for (File file : this.allSubFiles) {
                System.out.println(counter++ + ". " + file.getName());
            }
        } else {
            for (File file : this.visibleSubFiles) {
                System.out.println(counter++ + ". " + file.getName());
            }
        }
    }

    private void getInput() {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        if (isInteger(input)) {
            File f = showHidden ? this.allSubFiles[Integer.parseInt(input)] : this.visibleSubFiles[Integer.parseInt(input)];
            if (f.isDirectory()) {
                changeDirectory(f.toString());
            }
        } else {
            switch(input) {
                case ".":
                    this.showHidden = !this.showHidden;
                    break;
                case "~":
                    this.changeDirectory(System.getProperty("user.home"));
                    break;
                case "b":
                    if (this.file.getParent() != null) {
                        this.changeDirectory(this.file.getParent());
                    }
                    break;
                case "h":
                    if (this.file.getParent() != null) {
                        this.changeDirectory(this.file.getParent());
                    }
                    break;
                case "q":
                    System.exit(0);
                default:
                    break;
            }
        }
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException err) {
            return false;
        }
    }

    public void run() {
        while (true) {
            this.printFiles();
            this.getInput();
        }
    }

    public static void main(String[] args) {
       FileMan fm = new FileMan(System.getProperty("user.home"));
   }
}
