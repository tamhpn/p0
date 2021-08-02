package com.github.tamhpn.ui;

import io.javalin.Javalin;
import com.github.tamhpn.lib.SuperFile;
import com.github.tamhpn.util.SuperFiles;

public class Server implements Startable {
    private Javalin javalin;
    private SuperFile file;

    public Server(SuperFile file) {
        this.javalin = Javalin.create();
        this.file = file;
    }

    public void start() {
        this.javalin.start(8080);
        javalin.get("/*", ctx -> {
            String path = "/" + ctx.splat(0);
            if (path.equals("/~")) {
                path = System.getProperty("user.home");
            }
            this.file = SuperFiles.changeDirectory(this.file, path);
            ctx.html(this.getHtmlContext());
        });
    }

    private String getHtmlContext() {
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
}
