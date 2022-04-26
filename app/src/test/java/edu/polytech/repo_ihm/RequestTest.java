package edu.polytech.repo_ihm;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static edu.polytech.repo_ihm.api.Request.RequestType.POST;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.polytech.repo_ihm.api.Request;

public class RequestTest {

    @BeforeEach
    public void setup() {
        StartActivity.API_HOST = "http://d8d8-109-210-108-136.eu.ngrok.io/";
        StartActivity.API_KEY = "Y2VjaSBlc3QgdW5lIHBhdGF0ZSBkb3VjZQ==";
    }


    @Test
    public void getUser() throws InterruptedException {
        Request request = new Request("user/2", Request.RequestType.GET);
        request.getRequestThread().join();
        assertEquals(request.getRequestMessage().getRequestCode(), 200);

        Request request2 = new Request("user/495456", Request.RequestType.GET);
        request2.getRequestThread().join();
        assertEquals(request2.getRequestMessage().getRequestCode(), 204);
    }

    @Test
    public void login() throws InterruptedException {
        Request request = new Request("user/login", Request.RequestType.POST, "token", StartActivity.API_KEY, "email", "thomas.farineau@icloud.com", "password", "test");
        request.getRequestThread().join();
        try {
            assertTrue(request.getRequestMessage().getRequestMessage().has("session_token"));
            System.out.println(request.getRequestMessage().getRequestMessage().getString("session_token"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getAll() throws InterruptedException {
        Request request = new Request("user", Request.RequestType.POST, "token", StartActivity.API_KEY, "session_token", "$2y$10$c9zMy1WNv.PhGY7kOo/8HuUTjauliZV7pVii6cc0LMjAZ5Fn2YFEe");
        request.getRequestThread().join();
        try {
            System.out.println(request.getRequestMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void register() throws InterruptedException {
        Request request = new Request("user/register", POST, "token", StartActivity.API_KEY, "email", "oui@oui.fr", "firstname", "oui", "name", "oui", "password", "aaa", "password_confirm", "aaa");
        request.getRequestThread().join();
        try {
            System.out.println(request.getRequestMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
