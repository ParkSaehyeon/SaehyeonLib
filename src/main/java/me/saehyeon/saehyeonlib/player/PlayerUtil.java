package me.saehyeon.saehyeonlib.player;

import me.saehyeon.saehyeonlib.gui.GUI;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

interface Filter {
    boolean run(Player player);
}
public class PlayerUtil {
    public static void sendActionBarAll(String message) {
        sendActionBarAll(new ArrayList<>(Bukkit.getOnlinePlayers()),message);
    }
    public static void sendActionBarAll(List<Player> players, String message) {
        players.forEach(p -> p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message)));
    }
    public static void sendTitleAll(String title, String subtitle, int fadein, int stay, int fadeout) {
        sendTitleAll(new ArrayList<>(Bukkit.getOnlinePlayers()),title,subtitle,fadein,stay,fadeout);
    }
    public static void sendTitleAll(String title, String subtitle) {
        sendTitleAll(new ArrayList<>(Bukkit.getOnlinePlayers()),title,subtitle,20,50,20);
    }
    public static void sendTitleAll(List<Player> players, String title, String subtitle,int fadein, int stay, int fadeout) {
        players.forEach(p -> p.sendTitle(title,subtitle, fadein,stay,fadeout));
    }

    public static void sendTitleAll(List<Player> players, String title, String subtitle) {
        sendTitleAll(players,title,subtitle);
    }

    public static List<Player> getOnlinePlayers() {
        return new ArrayList<>(Bukkit.getOnlinePlayers());
    }

    public static List<Player> getOnlinePlayers(Filter filter) {
        return getOnlinePlayers().stream().filter(filter::run).collect(Collectors.toList());
    }


    public static void spreadPlayers(List<Player> players, Location location, int radius) {
        for(Player p : players) {
            double x = location.getX()+(Math.random() * radius - radius);
            double z = location.getZ()+(Math.random() * radius - radius);
            Location loc = location.getWorld().getHighestBlockAt((int)x,(int)z).getLocation().clone().add(0,1,0);

            p.teleport(loc);
        }
    }


}
