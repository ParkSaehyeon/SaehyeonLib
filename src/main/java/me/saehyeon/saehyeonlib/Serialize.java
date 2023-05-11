package me.saehyeon.saehyeonlib;

import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.*;
import java.nio.file.Files;
import java.util.Base64;

public class Serialize {
    public static String serialize(Object obj) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        BukkitObjectOutputStream out = new BukkitObjectOutputStream(bos);
        out.writeObject(obj);
        out.flush();
        out.close();
        bos.close();

        return Base64.getEncoder().encodeToString(bos.toByteArray());
    }

    public static Object deSerialize(String serializedStr) throws IOException, ClassNotFoundException {
        byte[] data = Base64.getDecoder().decode(serializedStr);
        BukkitObjectInputStream in = new BukkitObjectInputStream(new ByteArrayInputStream(data));
        Object obj = in.readObject();
        in.close();

        return obj;
    }

}
