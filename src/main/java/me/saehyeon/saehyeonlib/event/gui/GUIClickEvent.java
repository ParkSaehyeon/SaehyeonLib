package me.saehyeon.saehyeonlib.event.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GUIClickEvent {
    Player player;
    String title;
    ItemStack clickedItem;
    public GUIClickEvent(Player player, String title, ItemStack clickedItem) {
        this.player = player;
        this.title = title;
        this.clickedItem = clickedItem;
    }

    public Player getPlayer() {
        return player;
    }

    public String getTitle() {
        return title;
    }

    public ItemStack getClickedItem() {
        return clickedItem;
    }
}
