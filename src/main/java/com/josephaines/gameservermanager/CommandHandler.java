package com.josephaines.gameservermanager;

import com.josephaines.gameservermanager.Server.Server;
import com.josephaines.gameservermanager.Server.ServerList;

import java.util.List;
import java.util.Scanner;

public class CommandHandler {
    ServerList servers;

    public CommandHandler(ServerList servers) {
        this.servers = servers;
    }

    public void startCommandHandler(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            String line = scanner.nextLine();
            if (line.equals("exit")){
                break;
            }
        }
        scanner.close();
        System.exit(0);
    }
}
