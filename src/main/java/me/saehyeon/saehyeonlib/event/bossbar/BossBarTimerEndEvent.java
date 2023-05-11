package me.saehyeon.saehyeonlib.event.bossbar;

import me.saehyeon.saehyeonlib.bossbar.BossBarTimer;

public class BossBarTimerEndEvent {
    BossBarTimer bossBarTimer;
    public BossBarTimerEndEvent(BossBarTimer bossBarTimer) {
        this.bossBarTimer = bossBarTimer;
    }

    public BossBarTimer getBossBarTimer() {
        return bossBarTimer;
    }
}
