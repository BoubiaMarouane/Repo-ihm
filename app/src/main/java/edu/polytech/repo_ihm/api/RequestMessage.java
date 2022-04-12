package edu.polytech.repo_ihm.api;

import org.json.JSONException;
import org.json.JSONObject;

public class RequestMessage {
    int requestCode;
    String requestMessage;

    public RequestMessage(int requestCode, String requestMessage) {
        this.requestCode = requestCode;
        this.requestMessage = requestMessage;
    }

    @Override
    public String toString() {
        return "RequestMessage{" +
                "requestCode=" + requestCode +
                ", requestMessage='" + requestMessage + '\'' +
                '}';
    }

    public JSONObject getRequestMessage() {
        try {
            return new JSONObject(requestMessage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
