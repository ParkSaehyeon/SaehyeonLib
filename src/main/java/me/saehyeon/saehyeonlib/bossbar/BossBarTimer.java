package me.saehyeon.saehyeonlib.bossbar;

import me.saehyeon.saehyeonlib.SaehyeonLib;
import me.saehyeon.saehyeonlib.TimeUtil;
import me.saehyeon.saehyeonlib.event.S_EventManager;
import me.saehyeon.saehyeonlib.event.bossbar.BossBarTimerEndEvent;
import me.saehyeon.saehyeonlib.event.bossbar.BossBarTimerPausedChangeEvent;
import me.saehyeon.saehyeonlib.event.bossbar.BossBarTimerPriodEvent;
import me.saehyeon.saehyeonlib.event.bossbar.BossBarTimerStartEvent;
import org.bukkit.boss.BossBar;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BossBarTimer {
    public static List<BossBarTimer> bossBarTimers = new ArrayList<>();

    BossBar bossbar;
    long stepSeconds;
    int maxSeconds;
    int leftSeconds;
    boolean isPaused;
    BukkitTask task;
    List<Player> players = new ArrayList<>();

    public BossBarTimer(BossBar bossbar, float step, int maxSeconds) {
        this.bossbar = bossbar;
        this.stepSeconds = (long)(Math.min(0.05,step) * 20L);
        this.maxSeconds = maxSeconds;
        bossBarTimers.add(this);
    }

    public void start() {
        if(task != null) task.cancel();

        bossbar.setVisible(true);

        final double DECREMENT = 1D / maxSeconds;

        AtomicInteger leftTime = new AtomicInteger(maxSeconds);
        final String BOSSBAR_TITLE = bossbar.getTitle();

        S_EventManager.getInstance().trigger(new BossBarTimerStartEvent(this));

        task = Bukkit.getScheduler().runTaskTimer(SaehyeonLib.ins, () -> {
            leftTime.getAndDecrement();
            leftSeconds = leftTime.get();

            // 보스바 타이틀 수정
            long hour = TimeUtil.secondsToTime(leftSeconds)[0];
            long min = TimeUtil.secondsToTime(leftSeconds)[0];
            long sec = TimeUtil.secondsToTime(leftSeconds)[0];
            bossbar.setTitle(BOSSBAR_TITLE.replaceAll("\\{h}",hour+"").replaceAll("\\{m}",min+"").replaceAll("\\{s}",sec+""));

            // 보스바 progress 수정
            final double newProgress = bossbar.getProgress() - DECREMENT;

            // progress 변경 전, 타이머 종료 시도
            if(newProgress <= 0 || leftTime.get() <= 0) stop();

            bossbar.setProgress(newProgress);

            S_EventManager.getInstance().trigger(new BossBarTimerPriodEvent(this));

        },0,stepSeconds);

        // 보스바 메니저에 등록
        BossBarManager.getInstance().Register(bossbar);
    }

    public void stop() {
        if(task != null) {
            task.cancel();
            bossbar.removeAll();
            bossbar.setVisible(false);
            S_EventManager.getInstance().trigger(new BossBarTimerEndEvent(this));
        }
    }

    public void setPlayers(List<Player> players) {
        bossbar.removeAll();
        this.players = players;
        this.players.forEach(bossbar::addPlayer);
    }

    public List<Player> getPlayers() {
        return bossbar.getPlayers();
    }

    public void setPaused(boolean isPaused) {
        this.isPaused = isPaused;
        S_EventManager.getInstance().trigger(new BossBarTimerPausedChangeEvent(this));
    }

    public boolean getPaused() {
        return isPaused;
    }

    public int getLeftSeconds() {
        return leftSeconds;
    }

    public BossBar getBossbar() {
        return bossbar;
    }
}
