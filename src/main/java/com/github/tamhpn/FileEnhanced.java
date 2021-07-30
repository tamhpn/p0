package com.github.tamhpn;

import java.io.File;
import java.util.Arrays;

public class FileEnhanced extends File {
    public FileEnhanced(String pathname) {
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

    public FileEnhanced[] listFiles() {
        File[] oldMethod = super.listFiles();
        return Arrays.stream(oldMethod).map(f -> new FileEnhanced(f.getAbsolutePath())).toArray(FileEnhanced[]::new);
    }
}
