package com.github.tamhpn;

import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import io.javalin.Javalin;

public class FileMan {
    private FileEnhanced file;
    private FileEnhanced[] allSubFiles;
    private FileEnhanced[] visibleSubFiles;
    private FileEnhanced[] filesToDisplay;
    private boolean showHidden = false;
    private Javalin javalin = Javalin.create();
    private Scanner scan;

    public FileMan(String path) {
        this.scan = new Scanner(System.in);
        this.changeDirectory(path);
        this.filesToDisplay = showHidden ? this.allSubFiles : this.visibleSubFiles;
        // this.startServer();
        this.runInTerminal();
    }

    private void runInTerminal() {
        while (true) {
            this.displayFiles();
            this.getUserInput();
        }
    }

    private void startServer() {
        this.javalin.start(8080);
        javalin.get("/*", ctx -> {
            String path = "/" + ctx.splat(0);
            if (path.equals("/~")) {
                path = System.getProperty("user.home");
            }
            this.changeDirectory(path);
            ctx.html(this.htmlContext());
        });
    }

    private String htmlContext() {
        StringBuilder sb = new StringBuilder();
        String path = this.file.getAbsolutePath();
        sb.append("Current directory: " + path);
        sb.append("<br>");
        if (!path.equals("/")) {
            sb.append("<a href='" + this.file.getParent() + "'>" + "<button>..</button>" + "</a>");
            sb.append("<br>");
        }
        sb.append("<br>");
        for (FileEnhanced file : this.filesToDisplay) {
            sb.append("<a href='" + file.getAbsolutePath() + "'>" + file.getName());
            if (file.isDirectory()) {
                sb.append("/");
            }
            sb.append("</a>");
            sb.append("<br>");
        }
        return sb.toString();
    }

    private void changeDirectory(String path) {
        if (Files.isDirectory(Paths.get(path))) {
            this.file = new FileEnhanced(path);
            this.refreshSubfilesList();
        }
    }

    private void refreshSubfilesList() {
            this.allSubFiles = this.file.listFiles();
            Arrays.sort(this.allSubFiles);
            this.visibleSubFiles = Arrays.stream(this.allSubFiles).filter(f -> !f.isHidden()).toArray(FileEnhanced[]::new);
    }

    private void displayFiles() {
        System.out.print("\033[H\033[2J");
        int index = 0;
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
        String input = scan.nextLine();
        this.parseInput(input);
    }

    private void parseInput(String s) {
        if (isInteger(s)) {
            FileEnhanced newFile = this.filesToDisplay[Integer.parseInt(s)];
            if (newFile.isDirectory()) {
                changeDirectory(newFile.toString());
            }
        } else {
            switch (s) {
            case ".": // toggle whether hidden files are displayed
                this.toggleHidden();
                break;
            case "~": // move to home directory
                this.changeDirectory(System.getProperty("user.home"));
                break;
            case "b": // move up to parent directory
            case "p":
                if (this.file.getParent() != null) {
                    this.changeDirectory(this.file.getParent());
                }
                break;
            case "c": // copy file to new directory
                break;
            case "d": // create new directory
                this.createFolder();
                break;
            case "f": // create new file
                this.createFile();
                break;
            case "h": // display help
                break;
            case "m": // move file to new directory
                break;
            case "r": // rename file
                this.renameFile();
                break;
            case "q": // quit
                System.exit(0);
            case "u": // unzip a zip archive
                break;
            case "x": // delete file WARNING DELETION IS PERMANENT
                break;
            case "z": // zip a file to an archive
                break;
            default:
                break;
            }
        }
    }

    private void toggleHidden() {
        this.showHidden = !this.showHidden;
        this.filesToDisplay = showHidden ? this.allSubFiles : this.visibleSubFiles;
    }

    private boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException err) {
            return false;
        }
    }

    private void createFile() {
        try {
            System.out.print("Name of new file: ");
            String fileName = this.scan.nextLine();
            Files.createFile(Paths.get(this.file.getAbsolutePath() + "/" + fileName));
            this.refreshSubfilesList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFolder() {
        try {
            System.out.print("Name of new directory: ");
            String folderName = this.scan.nextLine();
            Files.createDirectory(Paths.get(this.file.getAbsolutePath() + "/" + folderName));
            this.refreshSubfilesList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void renameFile() {
        try {
            System.out.println("Select a file to rename [0 - " + (this.filesToDisplay.length - 1) + "]: ");
            String index = this.scan.nextLine();
            String currentName = this.filesToDisplay[Integer.parseInt(index)].getName();
            System.out.println("File selected: " + currentName);
            System.out.println("Enter the new file name: ");
            String newName = this.scan.nextLine();
            Files.move(Paths.get(this.file.getAbsolutePath() + "/" + currentName), Paths.get(this.file.getAbsolutePath() + "/" + newName));
            this.refreshSubfilesList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileMan fm = new FileMan(System.getProperty("user.home"));
    }
}