package edu.polytech.repo_ihm.api;

import android.os.AsyncTask;

import org.json.JSONObject;

public class AsyncRequest extends AsyncTask<String, Void, JSONObject> {

        public AsyncRequest() {
    }


    @Override
    protected JSONObject doInBackground(String... parameters) {
        JSONObject response = null;
        try {
            response = Requester.query(parameters[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
    }

}
