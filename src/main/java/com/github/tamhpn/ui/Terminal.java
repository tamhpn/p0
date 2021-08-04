package com.github.tamhpn.ui;

import java.util.Scanner;

import com.github.tamhpn.lib.SuperFile;
import com.github.tamhpn.util.SuperFiles;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Terminal implements Startable {
    private Scanner scan;
    private SuperFile file;
    private static final Logger logger = LogManager.getLogger(Terminal.class);

    public Terminal(SuperFile file) {
        this.file = file;
        this.scan = new Scanner(System.in);
    }

    public void start() {
        logger.info("Starting FileMan in terminal interface...");
        while (true) {
            this.displayFiles();
            this.getUserInput();
        }
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
                logger.info("User selected " + newFile.getName());
                this.file = SuperFiles.changeDirectory(this.file, newFile.toString());
            } catch (ArrayIndexOutOfBoundsException e) {
                logger.error("User selected non-existing file; " + e.getMessage());
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
            case "b": // move to parent directory
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
                this.printHelpMenu();
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
            logger.info("Exiting FileMan...");
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

    private void printHelpMenu() {
        System.out.println("  `.` will toggle hidden files");
        System.out.println("  `~` will move to the home directory");
        System.out.println("  `b` or `p` will move to the parent directory");
        System.out.println("  `c` will copy a file to another directory");
        System.out.println("  `d` will create a new directory");
        System.out.println("  `f` will create a new file");
        System.out.println("  `h` will print this message");
        System.out.println("  `m` will move a file to another directory");
        System.out.println("  `q` will end the program");
        System.out.println("  `r` will rename a file");
        // System.out.println("  `u` will unzip a zip archive");
        System.out.println("  `x` will WARNING: PERMANENTLY delete a file");
        // System.out.println("  `z` will zip files to a zip archive");
        System.out.println("  `1` will move to the subdirectory at index 1 given it is a directory");
        System.out.println("Press any key to continue...");
        scan.nextLine();
    }
}
