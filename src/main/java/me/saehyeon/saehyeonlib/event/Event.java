package me.saehyeon.saehyeonlib.event;

import me.saehyeon.saehyeonlib.SaehyeonLib;
import me.saehyeon.saehyeonlib.bossbar.BossBarTimer;
import me.saehyeon.saehyeonlib.event.gui.GUIClickEvent;
import me.saehyeon.saehyeonlib.event.gui.GUICloseEvent;
import me.saehyeon.saehyeonlib.event.gui.GUIOpenEvent;
import me.saehyeon.saehyeonlib.gui.GUI;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;

public class Event implements Listener {
    @EventHandler
    void onInventoryOpen(InventoryOpenEvent e) {
        Player p = (Player) e.getPlayer();
        if(GUI.getInstance().inv.containsKey(p.getUniqueId()))
            S_EventManager.getInstance().trigger(new GUIOpenEvent(p, p.getOpenInventory().getTitle()));
    }

    @EventHandler
    void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(GUI.getInstance().inv.containsKey(p.getUniqueId())) {
            e.setCancelled(GUI.getInstance().cantClick.getOrDefault(p.getUniqueId(), false));
            S_EventManager.getInstance().trigger(new GUIClickEvent(p, e.getWhoClicked().getOpenInventory().getTitle(), e.getCurrentItem()));
        }
    }

    @EventHandler
    void onInventoryClose(InventoryCloseEvent e) {
        Player p = (Player) e.getPlayer();

        if(GUI.getInstance().inv.containsKey(p.getUniqueId())) {

            // 닫을 수 없는 상태 -> 1tick 후 다시 열기
            if(GUI.getInstance().cantClose.getOrDefault(p.getUniqueId(),false)) {
                Inventory openInv = GUI.getInstance().inv.get(p.getUniqueId());
                Bukkit.getScheduler().runTaskLater(SaehyeonLib.ins, () -> p.openInventory(openInv), 1);
                return;
            }

            S_EventManager.getInstance().trigger(new GUICloseEvent(p, e.getPlayer().getOpenInventory().getTitle()));
            GUI.getInstance().inv.remove(p.getUniqueId());
            GUI.getInstance().cantClick.remove(p.getUniqueId());
        }
    }

    @EventHandler
    void onJoin(PlayerJoinEvent e) {

        // ---- 포함되어 있는 보스바 타이머의 보스바 보이기
        BossBarTimer.bossBarTimers.forEach(timer -> {
            if(timer.getPlayers().contains(e.getPlayer())) {
                 BossBar bossBar = timer.getBossbar();

                 if(bossBar != null) {
                     bossBar.addPlayer(e.getPlayer());
                 }
            }
        });
    }
}
