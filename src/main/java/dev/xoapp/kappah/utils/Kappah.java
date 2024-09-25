package dev.xoapp.kappah.utils;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.utils.TextFormat;
import com.google.gson.Gson;
import dev.xoapp.kappah.Loader;

import java.util.HashMap;
import java.util.Map;

public class Kappah {

    private static Map<String, String> deserializePlayerData(Player player) {
        String data = Loader.getPlayerData().getData(player.getName()).toString();

        data = data.substring(1, data.length() - 1);

        Map<String, String> map = new HashMap<>();

        String[] pairs = data.split(", ");

        for (String pair : pairs) {
            String[] keyValue = pair.split("=", 2);
            map.put(keyValue[0], keyValue[1]);
        }

        return map;
    }

    public static void sendInformation(Player player, Player i_player) {

        String[] messages = new String[] {
                "&l&a    PLAYER INFORMATION&r   ",
                " ",
                " &fName: &e" + i_player.getPlayerInfo().getUsername(),
                " &fXuid: &e" + i_player.getLoginChainData().getXUID(),
                " &fUnique ID: &e" + i_player.getPlayerInfo().getUniqueId(),
                " &fAddress: &e" + i_player.getAddress(),
                " ",
                " &fCountry: &e%s",
                " &fCity: &e%s",
                " &fRegion: &e%s",
                " &fInternet ORG: &e%s",
                " "
        };

        Map<String, String> playerData = deserializePlayerData(i_player);

        String formattedMessage = String.format(
                String.join("\n", messages),
                playerData.get("country"),
                playerData.get("city"),
                playerData.get("region"),
                playerData.get("connectionORG")
        );
        player.sendMessage(TextFormat.colorize(formattedMessage));
    }
}
