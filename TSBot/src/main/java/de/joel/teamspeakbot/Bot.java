package de.joel.teamspeakbot;

import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import de.joel.teamspeakbot.file.Data;
import de.joel.teamspeakbot.file.DataFile;
import de.joel.teamspeakbot.listeners.Events;
import de.joel.teamspeakbot.utils.AFKMover;

public class Bot {

    private static Bot instance;
    private DataFile file;
    private TS3Api ts3Api;
    private TS3Query ts3Query;
    private TS3Config ts3Config;

    public static void main(String... args) {
        System.out.println(" ");
        System.out.println("\n" +
                "  _______                                        _    ____        _   \n" +
                " |__   __|                                      | |  |  _ \\      | |  \n" +
                "    | | ___  __ _ _ __ ___  ___ _ __   ___  __ _| | _| |_) | ___ | |_ \n" +
                "    | |/ _ \\/ _` | '_ ` _ \\/ __| '_ \\ / _ \\/ _` | |/ /  _ < / _ \\| __|\n" +
                "    | |  __/ (_| | | | | | \\__ \\ |_) |  __/ (_| |   <| |_) | (_) | |_ \n" +
                "    |_|\\___|\\__,_|_| |_| |_|___/ .__/ \\___|\\__,_|_|\\_\\____/ \\___/ \\__|\n" +
                "                               | |                                    \n" +
                "                               |_|                                    \n");


        System.out.println(Data.getprefix() + "Bot is starting...");

        instance = new Bot();
        instance.start();
    }

    public static Bot getInstance() {
        return instance;
    }

    private void start() {

        this.ts3Config = new TS3Config();
        this.ts3Query = new TS3Query(ts3Config);
        this.ts3Api = new TS3Api(ts3Query);

        ts3Config.setHost("127.0.0.1");
        ts3Config.setFloodRate(TS3Query.FloodRate.DEFAULT);
        ts3Query.connect();

        System.out.println(Data.getprefix() + "Bot is running!");
        System.out.println(" ");

        ts3Api.login("serveradmin", "ytRHDoSL");

        ts3Api.selectVirtualServerByPort(9987);
        ts3Api.setNickname("TS-Bot");

        file = new DataFile();
        file.load();

        AFKMover afkMover = new AFKMover();
        AFKMover.start();

        new Events().loadEvents();
        ts3Api.registerAllEvents();
    }

    public DataFile getFile() {
        return file;
    }

    public TS3Api getTs3Api() {
        return ts3Api;
    }

    public TS3Config getTs3Config() {
        return ts3Config;
    }

    public TS3Query getTs3Query() {
        return ts3Query;
    }
}
