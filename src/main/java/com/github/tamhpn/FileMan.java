package com.github.tamhpn;

import com.github.tamhpn.application.Config;
import com.github.tamhpn.ui.Server;
import com.github.tamhpn.ui.Terminal;

public class FileMan {
    public static void main(String[] args) {
        Config config = new Config(args);
        if (config.isServer()) {
            Server server = new Server(config.getFile());
            server.start();
        } else {
            Terminal terminal = new Terminal(config.getFile());
            terminal.start();
        }
    }
}