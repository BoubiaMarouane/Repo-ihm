package edu.polytech.repo_ihm.account;

import edu.polytech.repo_ihm.api.RequestMessage;

public class AuthenticatorSingleton {
    private static AuthenticatorSingleton instancedSingleton = null;
    public Thread logInThread;
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
        this.logInThread = new Thread(() -> lastRM = new RequestMessage(200, "{\"session_token\": \"1234\"}"));
        this.logInThread.start();
    }

    public void setCurrentUser(String session_token) {
        this.setCurrentUserThread = new Thread(() -> currentUser = new User(session_token));
        this.setCurrentUserThread.start();
    }

    public void logOut(String session_token) {
        this.logOutThread = new Thread(() -> {
            currentUser = null;
            lastRM = new RequestMessage(200, "{\"message\": \"success\"}");
        });
        logOutThread.start();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isUserLogged() {
        return currentUser != null;
    }


}
