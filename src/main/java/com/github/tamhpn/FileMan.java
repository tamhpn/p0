package com.github.tamhpn;

import java.util.Scanner;

import com.github.tamhpn.lib.SuperFile;
import com.github.tamhpn.util.SuperFiles;

import io.javalin.Javalin;

public class FileMan {
    private SuperFile file;
    private Scanner scan;
    private Javalin javalin = Javalin.create();

    public FileMan(String path) {
        this.file = SuperFiles.changeDirectory(this.file, path);
        this.scan = new Scanner(System.in);
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
            this.file = SuperFiles.changeDirectory(this.file, path);
            this.file.refreshFileList();
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
        for (SuperFile file : this.file.getDisplayedFiles()) {
            sb.append("<a href='" + file.getAbsolutePath() + "'>" + file.getName());
            if (file.isDirectory()) {
                sb.append("/");
            }
            sb.append("</a>");
            sb.append("<br>");
        }
        return sb.toString();
    }

    private void displayFiles() {
        System.out.print("\033[H\033[2J");
        int index = 0;
        for (SuperFile file : this.file.getDisplayedFiles()) {
            this.printFile(file, index++);
        }
    }

    private void printFile(SuperFile file, int index) {
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
            try {
                SuperFile newFile = this.file.getDisplayedFiles()[Integer.parseInt(s)];
                this.file = SuperFiles.changeDirectory(this.file, newFile.toString());
                this.file.refreshFileList();
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        } else {
            switch (s) {
            case ".": // toggle whether hidden files are displayed
            case "hidden":
                this.file.toggleHidden();
                this.file.refreshFileList();
                break;
            case "~": // move to home directory
            case "home":
                this.file = SuperFiles.changeDirectory(this.file, System.getProperty("user.home"));
                break;
            case "b": // move up to parent directory
            case "back":
            case "p":
            case "parent":
                if (this.file.getParent() != null) {
                    this.file = SuperFiles.changeDirectory(this.file, this.file.getParent());
                }
                break;
            case "c": // copy file to new directory
            case "copy":
                SuperFiles.copy(this.file);
                break;
            case "d": // create new directory
            case "directory":
                SuperFiles.createFolder(this.file);
                break;
            case "f": // create new file
            case "file":
                SuperFiles.createFile(this.file);
                break;
            case "h": // display help
            case "help":
                break;
            case "m": // move file to new directory
            case "move":
                SuperFiles.move(file);
                break;
            case "r": // rename file
            case "rename":
                SuperFiles.rename(this.file);
                break;
            case "q": // quit
            case "quit":
                System.exit(0);
            case "u": // unzip a zip archive
            case "unzip":
                break;
            case "x": // delete file WARNING DELETION IS PERMANENT
            case "delete":
                SuperFiles.delete(this.file);
                break;
            case "z": // zip a file to an archive
            case "zip":
                break;
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