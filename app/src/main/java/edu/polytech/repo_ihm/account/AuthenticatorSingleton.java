package edu.polytech.repo_ihm.account;

import static edu.polytech.repo_ihm.api.Request.RequestType.POST;

import org.json.JSONException;
import org.json.JSONObject;

import edu.polytech.repo_ihm.StartActivity;
import edu.polytech.repo_ihm.api.Request;
import edu.polytech.repo_ihm.api.RequestMessage;

public class AuthenticatorSingleton {
    private static AuthenticatorSingleton instancedSingleton = null;
    public Thread logInThread;
    public Thread registerThread;
    public Thread logOutThread;
    public Thread setCurrentUserThread;
    volatile public RequestMessage lastRM;


    private User currentUser = null;

    public AuthenticatorSingleton() {
    }

    public static AuthenticatorSingleton getInstance() {
        if (instancedSingleton == null)
            instancedSingleton = new AuthenticatorSingleton();
        return instancedSingleton;
    }

    public void resetInstance() {
        instancedSingleton = new AuthenticatorSingleton();
    }

    public void logIn(String email, String password) {
        this.logInThread = new Thread(() -> {
            Request request = new Request("user/login", POST, "token", StartActivity.API_KEY, "email", email, "password", password);
            try {
                request.getRequestThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lastRM = request.getRequestMessage();
        });
        this.logInThread.start();
    }

    public void register(String firstname, String name, String email, String password, String confirmPassword) {
        this.registerThread = new Thread(() -> {
            Request request = new Request("user/register", POST, "token", StartActivity.API_KEY, "email", email, "firstname", firstname, "name", name, "password", password, "password_confirm", confirmPassword);
            try {
                request.getRequestThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lastRM = request.getRequestMessage();
        });
        this.registerThread.start();
    }

    public void logOut(String session_token) {
        this.logOutThread = new Thread(() -> {
            Request request = new Request("user/logout", POST, "token", StartActivity.API_KEY, "session_token", session_token);
            try {
                request.getRequestThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            currentUser = null;
            lastRM = request.getRequestMessage();
        });
        logOutThread.start();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(String session_token) {
        this.setCurrentUserThread = new Thread(() -> {
            Request request = new Request("user", POST, "token", StartActivity.API_KEY, "session_token", session_token);
            try {
                request.getRequestThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            JSONObject response = request.getRequestMessage().getRequestMessage();
            if (response != null) {
                try {
                    currentUser = new User(response.getString("firstname"),
                            response.getString("name"),
                            response.getString("email"),
                            response.optString("address"),
                            response.optString("additional_address"),
                            response.optString("city"),
                            response.optInt("postal"),
                            response.optString("dob"));

                    currentUser.setSessionToken(session_token);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        this.setCurrentUserThread.start();
    }

    public boolean isUserLogged() {
        return currentUser != null;
    }


}
