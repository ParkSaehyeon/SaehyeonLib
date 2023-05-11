package me.saehyeon.saehyeonlib.bossbar;

import org.bukkit.boss.BossBar;

import java.util.ArrayList;

public class BossBarManager {
    static BossBarManager instance;

    public static BossBarManager getInstance() {
        if(instance == null) instance = new BossBarManager();
        return instance;
    }

    ArrayList<BossBar> bossBars = new ArrayList<>();

    public void ServerDisable() {
        bossBars.forEach(BossBar::removeAll);
    }

    public void Register(BossBar bossbar) {
        if(!bossBars.contains(bossbar))
            bossBars.add(bossbar);
    }

    public void unRegister(BossBar bossBar) {
        bossBars.remove(bossBar);
    }
}
