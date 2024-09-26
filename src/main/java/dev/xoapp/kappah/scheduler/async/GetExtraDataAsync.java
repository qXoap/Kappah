package dev.xoapp.kappah.scheduler.async;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.scheduler.AsyncTask;
import cn.nukkit.utils.JSONUtils;
import dev.xoapp.kappah.Loader;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class GetExtraDataAsync extends AsyncTask {

    private final Player target;

    public GetExtraDataAsync(Player target) {
        this.target = target;
    }

    @Override
    public void onRun() {

        String address = target.getAddress();

        HttpClient client = HttpClient.newHttpClient();

        String _url = "http://ip-api.com/json/";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(_url + address))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                return;
            }

            Map<String, String> data = JSONUtils.from(response.body(), HashMap.class);

            Map<String, String> result = new HashMap<>();
            result.put("country", data.get("country"));
            result.put("region", data.get("regionName"));
            result.put("city", data.get("city"));
            result.put("connectionORG", data.get("org"));

            setResult(result);

        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onCompletion(Server server) {
        Object result = this.getResult();

        if (result == null) {
            return;
        }

        Loader.getPlayerData().setData(target.getName(), result);
    }
}