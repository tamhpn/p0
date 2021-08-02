package com.github.tamhpn.lib;

import java.io.File;
import java.util.Arrays;

public class SuperFile extends File {
    private SuperFile[] allSubfiles; // array of all files in a directory, including hidden files
    private SuperFile[] publicSubfiles; // array of only files visible by default (no hidden files)
    private SuperFile[] displayedFiles; // will point to either allSubfiles or publicSubfiles depending on boolean showHidden
    private boolean showHidden;

    public SuperFile(String pathname) {
        super(pathname);
    }

    // Default File compareTo only compares the two pathnames lexicographically.
    // New compareTo accounts for directories (directories before files),
    // disregards the leading "." in hidden files/directories, and ignores case.
    @Override
    public int compareTo(File pathname) {
        if (this.isDirectory() && !pathname.isDirectory()) {
            return -100;
        } else if (!this.isDirectory() && pathname.isDirectory()) {
            return 100;
        } else {
            String s1 = (this.isHidden() ? this.getName().substring(1) : this.getName()).toLowerCase();
            String s2 = (pathname.isHidden() ? pathname.getName().substring(1) : pathname.getName()).toLowerCase();
            return s1.compareTo(s2);
        }
    }

    public SuperFile[] listFiles() {
        File[] files = super.listFiles();
        return Arrays.stream(files).map(f -> new SuperFile(f.getAbsolutePath())).toArray(SuperFile[]::new);
    }

    public void refreshFileList() {
        this.allSubfiles = this.listFiles();
        Arrays.sort(this.allSubfiles);
        this.publicSubfiles = Arrays.stream(this.allSubfiles).filter(f -> !f.isHidden()).toArray(SuperFile[]::new);
        this.displayedFiles = this.showHidden ? this.allSubfiles : this.publicSubfiles;
    }

    public SuperFile[] getDisplayedFiles() {
        return this.displayedFiles;
    }

    public void toggleHidden() {
        this.showHidden = !this.showHidden;
    }

    public static void main(String[] args) {
    }
}
