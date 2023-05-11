package me.saehyeon.saehyeonlib.gui;

import it.unimi.dsi.fastutil.Hash;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;
import java.util.UUID;

public class GUI {
    static GUI instance;

    public static GUI getInstance() {
        if(instance == null) instance = new GUI();
        return instance;
    }

    public HashMap<UUID, Inventory> inv = new HashMap<>();
    public HashMap<UUID, Boolean> cantClick = new HashMap<>();
    public HashMap<UUID, Boolean> cantClose = new HashMap<>();

    public static void open(Player player, int rows, String title) {
        open(player,rows,title,false,false);
    }
    public static void open(Player player, int rows, String title, boolean cantClick) {
        open(player,rows,title,cantClick,false);
    }
    public static void open(Player player, int rows, String title, boolean cantClick,boolean cantClose) {
        GUI.getInstance().inv.put(player.getUniqueId(), Bukkit.createInventory(null, rows, title));
        GUI.getInstance().cantClick.put(player.getUniqueId(),cantClick);
        GUI.getInstance().cantClose.put(player.getUniqueId(),cantClose);
        player.openInventory(GUI.getInstance().inv.get(player.getUniqueId()));
    }

    public static void setCantClose(Player player, boolean bool) {
        GUI.getInstance().cantClose.put(player.getUniqueId(), bool);
    }

    public static void setCantClick(Player player, boolean bool) {
        GUI.getInstance().cantClick.put(player.getUniqueId(), bool);
    }
}
