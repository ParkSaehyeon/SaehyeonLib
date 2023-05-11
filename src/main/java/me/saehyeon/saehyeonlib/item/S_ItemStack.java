package me.saehyeon.saehyeonlib.item;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class S_ItemStack {
    ItemStack itemstack;
    public S_ItemStack(Material material, int amount) {
        itemstack = new ItemStack(material, amount);
    }

    public S_ItemStack(Material material, int amount,String displayName) {
        itemstack = new ItemStack(material, amount);
        ItemMeta meta = itemstack.getItemMeta();
        meta.setDisplayName(displayName);
        itemstack.setItemMeta(meta);
    }

    public S_ItemStack(Material material, int amount,String displayName, List<String> lores) {
        itemstack = new ItemStack(material, amount);
        ItemMeta meta = itemstack.getItemMeta();
        meta.setDisplayName(displayName);
        meta.setLore(lores);
        itemstack.setItemMeta(meta);
    }

    public S_ItemStack(ItemStack itemstack) {
        this.itemstack = itemstack;
    }

    public List<String> getLore() {
        if(itemstack.getItemMeta() != null && itemstack.getItemMeta().getLore() != null)
            return itemstack.getItemMeta().getLore();

        return Arrays.asList();
    }

    public void setLore(String... lores) {
        ItemMeta meta = itemstack.getItemMeta();
        meta.setLore(Arrays.asList(lores));
        itemstack.setItemMeta(meta);
    }

    public void setDisplayName(String displayName) {
        ItemMeta meta = itemstack.getItemMeta();
        meta.setDisplayName(displayName);
        itemstack.setItemMeta(meta);
    }

    public String getDisplayName() {
        if(itemstack.getItemMeta() != null && itemstack.getItemMeta().getDisplayName() != null)
            return itemstack.getItemMeta().getDisplayName();

        return "";
    }

    public void setHeadOwner(OfflinePlayer player) {
        if(itemstack.getType() != Material.PLAYER_HEAD)
            itemstack.setType(Material.PLAYER_HEAD);

        SkullMeta meta = (SkullMeta) itemstack.getItemMeta();
        meta.setOwningPlayer(player);
        itemstack.setItemMeta(meta);
    }

    public OfflinePlayer getHeadOwner() {
        if(itemstack.getType() == Material.PLAYER_HEAD) {
            SkullMeta meta = (SkullMeta) itemstack.getItemMeta();
            return meta.getOwningPlayer();
        }

        return null;
    }

    public ItemStack toItemStack() {
        return itemstack;
    }
}
