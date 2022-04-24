package edu.polytech.repo_ihm;

import org.junit.Test;

import edu.polytech.repo_ihm.api.Request;

public class RequestTest {
    @Test
    public void getUser() throws InterruptedException {
        Request request = new Request("user/2", "GET", "");
    }
}
