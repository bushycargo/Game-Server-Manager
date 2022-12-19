package com.josephaines.gameservermanager.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

public abstract class Server {

    protected String serverId = UUID.randomUUID().toString();
    protected String serverName;
    protected ServerState serverState = ServerState.UNKNOWN;
    protected String path;
    protected String[] args;
    protected Process process;

    // Getters and Setters
    public String getServerId() {
        return serverId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setServerId(String serverId) {
        this.serverId = serverId;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public ServerState getServerState() {
        return serverState;
    }

    public void setServerState(ServerState serverState) throws IOException {
        switch (serverState){
            case ONLINE -> startServer();
            case OFFLINE -> stopServer();
            case UNKNOWN -> this.serverState = serverState;
        }
    }
    // End of getters and setters

    private void startServer() throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder(path);
        for (String arg : args){
            processBuilder.command(arg);
        }
        process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null){
            System.out.println(line);
        }

        serverState = ServerState.ONLINE;
    }

    private void stopServer(){
        process.destroy();
        serverState = ServerState.OFFLINE;
    }
}
