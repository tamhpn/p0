package com.github.tamhpn;

import com.github.tamhpn.lib.SuperFile;
import com.github.tamhpn.ui.Server;
import com.github.tamhpn.ui.Terminal;

public class FileMan {
    private SuperFile file;

    public FileMan(String path) {
        this.file = new SuperFile(path);
        this.file.refreshFileList();
    }

    public void start() {
        Server server = new Server(this.file);
        server.start();
        // Terminal terminal = new Terminal(this.file);
        // terminal.start();
    }

    public static void main(String[] args) {
        FileMan fm = new FileMan(System.getProperty("user.home"));
        fm.start();
    }
}