package com.github.tamhpn.application;

import com.github.tamhpn.lib.SuperFile;

public class Config {
    private String[] args;
    private boolean server = false;
    private SuperFile file;

    public Config(String[] args) {
        this.args = args;
        this.file = new SuperFile(System.getProperty("user.home"));
        this.file.refreshFileList();

        for (String arg : this.args) {
            switch (arg) {
                case "-s":
                case "--server":
                    this.server = true;
                    break;
                case "-h":
                case "--help":
                    this.printHelpMenu();
                    System.exit(0);
            }
        }
    }

    public SuperFile getFile() {
        return this.file;
    }

    public boolean isServer() {
        return this.server;
    }

    private void printHelpMenu() {
        System.out.println("Usage:");
        System.out.println("  mvn exec:java");
        System.out.println("  mvn exec:java -Dexec.args=[option]");
        System.out.println();
        System.out.println("Options");
        System.out.println("  -h, --help            Print this help message");
        System.out.println("  -s, --s               Start a Javalin server instance, viewable at ");
        System.out.println("                        http://localhost:8080/");
    }
}
