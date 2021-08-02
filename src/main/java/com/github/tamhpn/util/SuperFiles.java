package com.github.tamhpn.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import com.github.tamhpn.lib.SuperFile;

public class SuperFiles {
    public static Scanner scan = new Scanner(System.in);

    public static SuperFile changeDirectory(SuperFile currentDirectory, String path) {
        if (Files.isDirectory(Paths.get(path))) {
            SuperFile file = new SuperFile(path);
            file.refreshFileList();
            return file;
        }
        return currentDirectory;
    }

    public static void createFile(SuperFile file) {
        try {
            System.out.print("Name of new file: ");
            String fileName = scan.nextLine();
            Files.createFile(Paths.get(file.getAbsolutePath() + "/" + fileName));
            file.refreshFileList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createFolder(SuperFile file) {
        try {
            System.out.print("Name of new directory: ");
            String folderName = scan.nextLine();
            Files.createDirectory(Paths.get(file.getAbsolutePath() + "/" + folderName));
            file.refreshFileList();
        } catch (IOException e) {
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
                Files.move(Paths.get(file.getAbsolutePath() + "/" + currentName), Paths.get(file.getAbsolutePath() + "/" + newName));
                file.refreshFileList();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
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
                Files.move(Paths.get(file.getAbsolutePath() + "/" + currentName),
                Paths.get(file.getAbsolutePath() + "/" + directory + "/" + currentName));
                file.refreshFileList();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
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
                Files.copy(Paths.get(file.getAbsolutePath() + "/" + currentName), Paths.get(file.getAbsolutePath() + "/" + directory + "/" + newName));
                file.refreshFileList();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
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
                Files.deleteIfExists(Paths.get(file.getAbsolutePath() + "/" + currentName));
                file.refreshFileList();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
