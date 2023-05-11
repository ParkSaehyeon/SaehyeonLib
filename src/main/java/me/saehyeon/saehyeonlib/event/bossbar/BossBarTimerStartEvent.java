package me.saehyeon.saehyeonlib.event.bossbar;

import me.saehyeon.saehyeonlib.bossbar.BossBarTimer;
import org.bukkit.boss.BossBar;

public class BossBarTimerStartEvent {
    BossBarTimer bossBarTimer;
    public BossBarTimerStartEvent(BossBarTimer bossBarTimer) {
        this.bossBarTimer = bossBarTimer;
    }

    public BossBarTimer getBossBarTimer() {
        return bossBarTimer;
    }
}
