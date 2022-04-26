package edu.polytech.repo_ihm;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import java.net.MalformedURLException;

import edu.polytech.repo_ihm.api.Request;

public class RequestTest {
    @Test
    public void getUser() throws InterruptedException {
        Request request = new Request("user/2", Request.RequestType.GET);
        request.getRequestThread().join();
        Assert.assertEquals(request.getRequestMessage().getRequestCode(), 200);

        Request request2 = new Request("user/495456", Request.RequestType.GET);
        request2.getRequestThread().join();
        Assert.assertEquals(request2.getRequestMessage().getRequestCode(), 204);
    }

    @Test
    public void login() throws InterruptedException {
        Request request = new Request("user/login", Request.RequestType.POST, "token", "Y2VjaSBlc3QgdW5lIHBhdGF0ZSBkb3VjZQ==", "email", "thomas.farineau@icloud.com", "password", "test");
        request.getRequestThread().join();
        System.out.println(request.getRequestMessage());
    }
}
