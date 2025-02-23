package com.github.tamhpn.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import com.github.tamhpn.lib.SuperFile;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SuperFiles {
    public static Scanner scan = new Scanner(System.in);
    private static final Logger logger = LogManager.getLogger(SuperFiles.class);

    public static SuperFile changeDirectory(SuperFile file, String path) {
        if (Files.isDirectory(Paths.get(path))) {
            logger.info("Changing directory to " + path + "/");
            SuperFile newFile = new SuperFile(path);
            newFile.refreshFileList();
            return newFile;
        }
        logger.error("Unable to change directory to " + path + " from " + file.getAbsolutePath());
        return file;
    }

    public static void createFile(SuperFile file) {
        try {
            System.out.print("Name of new file: ");
            String fileName = scan.nextLine();
            logger.info("Creating file '" + fileName + "' in " + file.getAbsolutePath() + "/");
            Files.createFile(Paths.get(file.getAbsolutePath() + "/" + fileName));
            file.refreshFileList();
        } catch (IOException e) {
            logger.error("Unable to create file; " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void createFolder(SuperFile file) {
        try {
            System.out.print("Name of new directory: ");
            String folderName = scan.nextLine();
            logger.info("Creating folder '" + folderName + "' in " + file.getAbsolutePath() + "/");
            Files.createDirectory(Paths.get(file.getAbsolutePath() + "/" + folderName));
            file.refreshFileList();
        } catch (IOException e) {
            logger.error("Unable to create directory; " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void rename(SuperFile file) {
        try {
            System.out.println("Select a file to rename [0 - " + (file.getDisplayedFiles().length - 1) + "], or input q to exit: ");
            String index = scan.nextLine();
            if (index.equals("q")) {
                return;
            }
            String currentName = file.getDisplayedFiles()[Integer.parseInt(index)].getName();

            System.out.println("File selected: " + currentName);
            System.out.println("Name of new file (or q to exit): ");
            String newName = scan.nextLine();
            if (newName.equals("q")) {
                return;
            }

            System.out.println("'" + currentName + "' will be renamed to " + "'" + newName + "'" + ". Press <Enter> to confirm.");
            String s = scan.nextLine();
            if (s.equals("")) {
                logger.info("Renaming " + currentName + " to " + newName);
                Files.move(Paths.get(file.getAbsolutePath() + "/" + currentName), Paths.get(file.getAbsolutePath() + "/" + newName));
                file.refreshFileList();
            }
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            logger.error("Unable to rename file; " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void move(SuperFile file) { // TODO: move to parent directory
        try {
            System.out.println("Select a file to move [0 - " + (file.getDisplayedFiles().length - 1) + "], or input q to exit: ");
            String index = scan.nextLine();
            if (index.equals("q")) {
                return;
            }
            String currentName = file.getDisplayedFiles()[Integer.parseInt(index)].getName();
            System.out.println("File selected: " + currentName);

            System.out.println("Select a folder to move to [0 - ...] (or q to exit): ");
            String directory = scan.nextLine();
            if (directory.equals("q")) {
                return;
            }
            directory = file.getDisplayedFiles()[Integer.parseInt(directory)].getName();

            System.out.println("'" + currentName + "' will be moved to " + "'" + directory + "/'. Press <Enter> to confirm.");
            String s = scan.nextLine();
            if (s.equals("")) {
                logger.info("Moving " + currentName + " to " + directory + "/");
                Files.move(Paths.get(file.getAbsolutePath() + "/" + currentName),
                Paths.get(file.getAbsolutePath() + "/" + directory + "/" + currentName));
                file.refreshFileList();
            }
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            logger.error("Unable to move file; " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void copy(SuperFile file) {
        try {
            System.out.println("Select a file to copy [0 - " + (file.getDisplayedFiles().length - 1) + "], or input q to exit: ");
            String index = scan.nextLine();
            if (index.equals("q")) {
                return;
            }
            String currentName = file.getDisplayedFiles()[Integer.parseInt(index)].getName();
            System.out.println("File selected: " + currentName);
            
            System.out.println("Select a folder to copy to [0 - ...] (or q to exit): "); // TODO: "." for current directory
            index = scan.nextLine();
            if (index.equals("q")) {
                return;
            }
            String directory = file.getDisplayedFiles()[Integer.parseInt(index)].getName();

            System.out.println("Enter a new file name (or input <Enter> key to skip, or q to exit): ");
            String newName = scan.nextLine();
            if (newName.equals("q")) {
                return;
            } else if (newName.equals("")) {
                newName = currentName;
            }

            System.out.println("'" + currentName + "' will be copied to " + "'" + directory + "/" + newName + "'. Press <Enter> to confirm.");
            String s = scan.nextLine();
            if (s.equals("")) {
                logger.info("Copying " + currentName + " to " + directory + "/" + newName);
                Files.copy(Paths.get(file.getAbsolutePath() + "/" + currentName), Paths.get(file.getAbsolutePath() + "/" + directory + "/" + newName));
                file.refreshFileList();
            }
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            logger.error("Unable to copy file; " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void delete(SuperFile file) {
        try {
            System.out.println("Select a file to delete [0 - " + (file.getDisplayedFiles().length - 1) + "], or input q to exit: ");
            String index = scan.nextLine();
            if (index.equals("q")) {
                return;
            }
            String currentName = file.getDisplayedFiles()[Integer.parseInt(index)].getName();

            System.out.println("'" + currentName + "' will be deleted. WARNING: This is a permanent operation. \nPlease input the filename to confirm.");
            String s = scan.nextLine();
            if (currentName.equals(s)) {
                logger.info("Deleting " + currentName);
                Files.deleteIfExists(Paths.get(file.getAbsolutePath() + "/" + currentName));
                file.refreshFileList();
            }
        } catch (IOException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            logger.error("Unable to delete file; " + e.getMessage());
            e.printStackTrace();
        }
    }
}
