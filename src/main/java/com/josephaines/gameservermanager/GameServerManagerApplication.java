package com.josephaines.gameservermanager;

import com.josephaines.gameservermanager.Server.Server;
import com.josephaines.gameservermanager.Server.ServerList;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class GameServerManagerApplication {

    public static void main(String[] args) {
        System.out.println("Game Server Manager - Initialising...");
        SpringApplication.run(GameServerManagerApplication.class, args);

        System.out.println("Game Server Manager - Initialising server list...");
        ServerList serverList = new ServerList();

        Thread serverLogging = new Thread(() -> {
            while (true){
                try {
                    for (Server server:
                         serverList.servers) {
                        try {
                            server.getBufferedReader().readLine();
                        } catch (IOException ignored) {
                        }
                    }
                } catch (NullPointerException ignored) {
                }
            }
        });
        serverLogging.start();
        System.out.println("Game Server Manager - Done!");

        System.out.println("Game Server Manager - Starting command handler...");
        CommandHandler commandHandler = new CommandHandler(serverList);
        Thread commandThread = new Thread(commandHandler::startCommandHandler);
        commandThread.start();
        System.out.println("Game Server Manager - Done!");

        System.out.println("Game Server Manager - Initialising Finished!");
    }

}
