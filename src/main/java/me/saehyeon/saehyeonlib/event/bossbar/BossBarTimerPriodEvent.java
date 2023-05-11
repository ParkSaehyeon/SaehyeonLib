package me.saehyeon.saehyeonlib.event.bossbar;

import me.saehyeon.saehyeonlib.bossbar.BossBarTimer;

public class BossBarTimerPriodEvent {
    BossBarTimer bossBarTimer;
    public BossBarTimerPriodEvent(BossBarTimer bossBarTimer) {
        this.bossBarTimer = bossBarTimer;
    }

    public BossBarTimer getBossBarTimer() {
        return bossBarTimer;
    }
}
