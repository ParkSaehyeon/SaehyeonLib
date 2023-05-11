package me.saehyeon.saehyeonlib.event.gui;

import org.bukkit.entity.Player;

public class GUIOpenEvent {
    Player player;
    String title;
    public GUIOpenEvent(Player player, String title) {
        this.player = player;
        this.title = title;
    }

    public Player getPlayer() {
        return player;
    }

    public String getTitle() {
        return title;
    }
}
