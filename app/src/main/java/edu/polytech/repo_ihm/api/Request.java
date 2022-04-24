package edu.polytech.repo_ihm.api;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Request {
    public static String API_HOST = "http://82e2-109-210-108-136.eu.ngrok.io/";
    private RequestMessage requestMessage;


    public Request(String URL, String type, String... parameters) {
        OkHttpClient client = new OkHttpClient();

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(API_HOST + URL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }
                    System.out.println(responseBody.string());
                }
            }
        });

        try {
            Response response = client.newCall(request).execute();
            requestMessage = new RequestMessage(response.code(), response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public RequestMessage getRequestMessage() {
        return requestMessage;
    }

}
