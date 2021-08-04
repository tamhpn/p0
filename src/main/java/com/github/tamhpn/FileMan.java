package com.github.tamhpn;

import com.github.tamhpn.application.Config;
import com.github.tamhpn.ui.Server;
import com.github.tamhpn.ui.Terminal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileMan {
    private static final Logger logger = LogManager.getLogger(FileMan.class);
    public static void main(String[] args) {
        logger.info("Starting FileMan...");
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