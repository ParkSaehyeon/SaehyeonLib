package me.saehyeon.saehyeonlib.event.bossbar;

import me.saehyeon.saehyeonlib.bossbar.BossBarTimer;

public class BossBarTimerPausedChangeEvent {
    BossBarTimer bossBarTimer;
    public BossBarTimerPausedChangeEvent(BossBarTimer bossBarTimer) {
        this.bossBarTimer = bossBarTimer;
    }

    public BossBarTimer getBossBarTimer() {
        return bossBarTimer;
    }
}
