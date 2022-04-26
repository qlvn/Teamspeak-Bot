package de.joel.teamspeakbot.utils;

import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.joel.teamspeakbot.Bot;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class AFKMover {

    private static final int Away = 1200000;
    private static HashMap<String, Long> AFK = new HashMap<>();
    private static HashMap<String, Boolean> Moved = new HashMap<>();
    private static HashMap<String, Integer> Channel = new HashMap<>();
    private static int moved = 0;

    public static void start() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (Client client : Bot.getInstance().getTs3Api().getClients()) {
                    if (!client.isServerQueryClient()) {
                        if (!client.isInServerGroup(221) || (client.isInServerGroup(140)) || (client.isInServerGroup(169)) || (client.isInServerGroup(162)) || (client.isInServerGroup(125)) || (client.isInServerGroup(124)) || (client.isInServerGroup(128)) || (client.isInServerGroup(163)) || (client.isInServerGroup(127)) || (client.isInServerGroup(167)) || (client.isInServerGroup(182))) {
                            if (client.isAway() || client.isInputMuted() || client.isOutputMuted()) {
                                if (!(AFK.containsKey(client.getUniqueIdentifier()))) {
                                    if (!(AFK.containsKey(client.getUniqueIdentifier()))) {
                                        AFK.put(client.getUniqueIdentifier(), System.currentTimeMillis());
                                    } else {
                                        long current = AFK.get(client.getUniqueIdentifier());
                                        if ((System.currentTimeMillis() - current) >= Away) {
                                            Moved.put(client.getUniqueIdentifier(), true);
                                            moved++;
                                            Channel.put(client.getUniqueIdentifier(), client.getChannelId());
                                            AFK.remove(client.getUniqueIdentifier());
                                            Bot.getInstance().getTs3Api().moveClient(client.getId(), 635);
                                            Bot.getInstance().getTs3Api().sendPrivateMessage(client.getId(), "Du wurdest in den AFK-Channel gemoved, da du mehr als 20 Minuten inaktiv warst!");
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (client.isRecording()) {
                        Bot.getInstance().getTs3Api().kickClientFromServer("Kein Aufnehmen!", client.getId());
                    }
                }
            }
        }, 1000, 5000);
    }
}
