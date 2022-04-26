package edu.polytech.repo_ihm.api;

import static edu.polytech.repo_ihm.StartActivity.API_HOST;

import androidx.annotation.NonNull;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Request {
    private final Thread requestThread;
    private RequestMessage requestMessage;

    public Request(String uri, RequestType type, Object... parameters) {
        if (parameters.length % 2 != 0)
            throw new IllegalArgumentException("Arguments size not valid");
        requestThread = type == RequestType.GET ? getThread(uri) : otherThread(uri, type, parameters);
    }

    String mapToJson(HashMap<String, Object> map) {
        StringBuilder toReturn = new StringBuilder("{");
        for (Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
            Map.Entry<String, Object> entry = iterator.next();
            if (entry.getValue() instanceof String)
                toReturn.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\"");
            else
                toReturn.append("\"").append(entry.getKey()).append("\":").append(entry.getValue());
            if (iterator.hasNext()) toReturn.append(',');
        }
        return toReturn + "}";
    }

    @NonNull
    private Thread otherThread(String uri, RequestType type, Object[] parameters) {
        final Thread requestThread;
        HashMap<String, Object> parametersMap = new HashMap<>();
        for (int i = 0; i < parameters.length; i += 2) {
            parametersMap.put((String) parameters[i], parameters[i + 1]);
        }
        requestThread = new Thread(() -> {
            try {
                URL url = new URL(API_HOST + uri);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod(type.name());
                urlConnection.setRequestProperty("Accept", "application/json");
                urlConnection.setRequestProperty("Content-Type", "application/json; utf-8");
                urlConnection.setDoOutput(true);
                OutputStream os = urlConnection.getOutputStream();
                byte[] input = mapToJson(parametersMap).getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);

                int statusCode = urlConnection.getResponseCode();
                if (statusCode == 200) {
                    InputStream it = new BufferedInputStream(urlConnection.getInputStream());
                    InputStreamReader read = new InputStreamReader(it);
                    BufferedReader buff = new BufferedReader(read);
                    StringBuilder dta = new StringBuilder();
                    String chunks;
                    while ((chunks = buff.readLine()) != null) {
                        dta.append(chunks);
                    }
                    requestMessage = new RequestMessage(statusCode, dta.toString());
                } else {
                    requestMessage = new RequestMessage(statusCode, "error");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        requestThread.start();
        return requestThread;
    }

    @NonNull
    private Thread getThread(String uri) {
        final Thread requestThread;
        requestThread = new Thread(() -> {
            try {
                URL url = new URL(API_HOST + uri);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                int statusCode = urlConnection.getResponseCode();
                if (statusCode == 200) {
                    InputStream it = new BufferedInputStream(urlConnection.getInputStream());
                    InputStreamReader read = new InputStreamReader(it);
                    BufferedReader buff = new BufferedReader(read);
                    StringBuilder dta = new StringBuilder();
                    String chunks;
                    while ((chunks = buff.readLine()) != null) {
                        dta.append(chunks);
                    }
                    requestMessage = new RequestMessage(statusCode, dta.toString());
                } else {
                    requestMessage = new RequestMessage(statusCode, "error");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        requestThread.start();
        return requestThread;
    }

    public Thread getRequestThread() {
        return requestThread;
    }

    public RequestMessage getRequestMessage() {
        return requestMessage;
    }

    public enum RequestType {
        GET, POST, DELETE, PUT
    }

}
