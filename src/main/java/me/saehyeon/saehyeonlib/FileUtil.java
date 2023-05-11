package me.saehyeon.saehyeonlib;

import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

public class FileUtil {
    public static void writeFile(Plugin plugin, String fileName, String... contents) throws IOException {
        FileWriter w = new FileWriter(new File(plugin.getDataFolder(), fileName));

        for(int i = 0 ; i < contents.length; i++) {
            w.write(contents[i]+((i+1 != contents.length) ? "\n" : ""));
        }

        w.close();
    }

    public static void writeFile(Plugin plugin, String fileName, String content) throws IOException {
        Files.write(new File(plugin.getDataFolder(), fileName).toPath(),content.getBytes());
    }
}
