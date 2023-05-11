package me.saehyeon.saehyeonlib;

import me.saehyeon.saehyeonlib.bossbar.BossBarManager;
import me.saehyeon.saehyeonlib.event.Event;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class SaehyeonLib extends JavaPlugin {
    public static SaehyeonLib ins;

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new Event(), this);
        ins = this;
    }

    @Override
    public void onDisable() {
        BossBarManager.getInstance().ServerDisable();
    }
}
