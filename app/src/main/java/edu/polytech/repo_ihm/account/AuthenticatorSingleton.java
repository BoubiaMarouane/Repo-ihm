package edu.polytech.repo_ihm.account;

import android.util.Log;

import edu.polytech.repo_ihm.api.RequestMessage;

public class AuthenticatorSingleton {
    private static AuthenticatorSingleton instancedSingleton = null;
    public Thread loginThread;
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

    public void logIn(String email, String password) {
        this.loginThread = new Thread(() -> lastRM = new RequestMessage(200, "{\"session_token\": \"1234\"}"));
        this.loginThread.start();
    }

    public void setCurrentUser(String session_token) {
        this.setCurrentUserThread = new Thread(() -> currentUser = new User(session_token));
        this.setCurrentUserThread.start();
    }

    public boolean isUserLogged() {
        return currentUser != null;
    }


}
