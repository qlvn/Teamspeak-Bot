package de.joel.teamspeakbot.listeners;

import com.github.theholywaffle.teamspeak3.api.ChannelProperty;
import com.github.theholywaffle.teamspeak3.api.event.*;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import de.joel.teamspeakbot.Bot;

import java.util.HashMap;
import java.util.Map;

public final class Events {

    public void loadEvents() {
        int WhoAmI = Bot.getInstance().getTs3Api().whoAmI().getId();
        Bot.getInstance().getTs3Api().addTS3Listeners(new TS3Listener() {

            @Override
            public void onTextMessage(TextMessageEvent textMessageEvent) {
                Client client = Bot.getInstance().getTs3Api().getClientByUId(textMessageEvent.getInvokerUniqueId());
                if (textMessageEvent.getInvokerId() != WhoAmI) {
                    if (textMessageEvent.getMessage().equalsIgnoreCase("!notify")) {
                        if (!client.isInServerGroup(45)) {
                            if (client.isInServerGroup(37) || (client.isInServerGroup(28)) || (client.isInServerGroup(17)) || (client.isInServerGroup(20)) || (client.isInServerGroup(16)) || (client.isInServerGroup(6))) {
                                Bot.getInstance().getTs3Api().addClientToServerGroup(45, client.getDatabaseId());
                                Bot.getInstance().getTs3Api().sendPrivateMessage(client.getId(), "Du erhälst nun [B]wieder[/B] Support Benachrichtigungen.");
                            }
                        } else {
                            Bot.getInstance().getTs3Api().removeClientFromServerGroup(45, client.getDatabaseId());
                            Bot.getInstance().getTs3Api().sendPrivateMessage(client.getId(), "Du erhälst nun [B]keine[/B] Support Benachrichtigungen mehr.");
                        }
                    }

                    if (textMessageEvent.getMessage().equalsIgnoreCase("!channel")) {
                        if (client.isInServerGroup(6)) {
                        final Map<ChannelProperty, String> properties = new HashMap<>();
                        properties.put(ChannelProperty.CHANNEL_FLAG_PERMANENT, "1");
                        properties.put(ChannelProperty.CHANNEL_DESCRIPTION, "");
                        properties.put(ChannelProperty.CHANNEL_ORDER, "68");

                        Bot.getInstance().getTs3Api().createChannel(client.getNickname() + "`s Channel", properties);
                        } else {
                            Bot.getInstance().getTs3Api().sendPrivateMessage(client.getId(), "Du benötigst mindestens den [B]VIP[/B] Rang.");
                        }
                    }
                }
            }

            @Override
            public void onClientJoin(ClientJoinEvent clientJoinEvent) {
                Client client = Bot.getInstance().getTs3Api().getClientInfo(clientJoinEvent.getClientId());

            }

            @Override
            public void onClientLeave(ClientLeaveEvent clientLeaveEvent) {

            }

            @Override
            public void onServerEdit(ServerEditedEvent serverEditedEvent) {

            }

            @Override
            public void onChannelEdit(ChannelEditedEvent channelEditedEvent) {

            }

            @Override
            public void onChannelDescriptionChanged(ChannelDescriptionEditedEvent channelDescriptionEditedEvent) {

            }

            @Override
            public void onClientMoved(ClientMovedEvent clientMovedEvent) {
                if (clientMovedEvent.getTargetChannelId() == 57) {
                    int i = 0;
                    for (Client client : Bot.getInstance().getTs3Api().getClients()) {
                        if (client.isInServerGroup(45)) {
                            i++;
                            Bot.getInstance().getTs3Api().pokeClient(client.getId(), "Jemand wartet im Support!");
                        }
                    }
                }
            }

            @Override
            public void onChannelCreate(ChannelCreateEvent channelCreateEvent) {

            }

            @Override
            public void onChannelDeleted(ChannelDeletedEvent channelDeletedEvent) {

            }

            @Override
            public void onChannelMoved(ChannelMovedEvent channelMovedEvent) {

            }

            @Override
            public void onChannelPasswordChanged(ChannelPasswordChangedEvent channelPasswordChangedEvent) {

            }

            @Override
            public void onPrivilegeKeyUsed(PrivilegeKeyUsedEvent privilegeKeyUsedEvent) {

            }
        });
    }
}
