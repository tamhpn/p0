package com.github.tamhpn;

import java.util.Scanner;
import java.util.Arrays;

public class FileMan {
    private FileEnhanced file;
    private FileEnhanced[] allSubFiles;
    private FileEnhanced[] visibleSubFiles;
    private boolean showHidden = false;

    public FileMan(String path) {
        this.changeDirectory(path);
        this.run();
    }

    private void run() {
        while (true) {
            this.displayFiles();
            this.getUserInput();
        }
    }

    private void changeDirectory(String path) {
        this.file = new FileEnhanced(path);
        this.allSubFiles = this.file.listFiles();
        Arrays.sort(this.allSubFiles);
        this.visibleSubFiles = Arrays.stream(this.allSubFiles).filter(f -> !f.isHidden()).toArray(FileEnhanced[]::new);
    }

    private void displayFiles() {
        System.out.print("\033[H\033[2J");
        int index = 0;
        FileEnhanced[] filesToDisplay = showHidden ? this.allSubFiles : this.visibleSubFiles;
        for (FileEnhanced file : filesToDisplay) {
            this.printFile(file, index++);
        }
    }

    private void printFile(FileEnhanced file, int index) {
        System.out.print(index + ". " + file.getName());
        if (file.isDirectory()) {
            System.out.print("/");
        }
        System.out.println();
    }

    private void getUserInput() {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        this.parseInput(input);
    }

    private void parseInput(String s) {
        if (isInteger(s)) {
            FileEnhanced newFile = showHidden ? this.allSubFiles[Integer.parseInt(s)] : this.visibleSubFiles[Integer.parseInt(s)];
            if (newFile.isDirectory()) {
                changeDirectory(newFile.toString());
            }
        } else {
            switch (s) {
            case ".":
                this.showHidden = !this.showHidden;
                break;
            case "~":
                this.changeDirectory(System.getProperty("user.home"));
                break;
            case "b":
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

    public static void main(String[] args) {
        FileMan fm = new FileMan(System.getProperty("user.home"));
    }
}