package edu.polytech.repo_ihm.account;

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
            Request request = new Request("user/login", Request.RequestType.POST, "token", "Y2VjaSBlc3QgdW5lIHBhdGF0ZSBkb3VjZQ==", "email", email, "password", password);
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
        this.registerThread = new Thread(() -> lastRM = new RequestMessage(200, "{\"session_token\": \"1234\"}"));
        this.registerThread.start();
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

    public void setCurrentUser(String session_token) {
        this.setCurrentUserThread = new Thread(() -> currentUser = new User(session_token));
        this.setCurrentUserThread.start();
    }

    public boolean isUserLogged() {
        return currentUser != null;
    }


}
