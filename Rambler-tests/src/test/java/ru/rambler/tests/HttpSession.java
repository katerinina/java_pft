package ru.rambler.tests;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.remote.BrowserType.FIREFOX;

/**
 * Created by user on 27.08.2017.
 */
public class HttpSession {

    private CloseableHttpClient httpClient;



    public HttpSession() {

        httpClient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
    }


    public boolean login(String username, String password) throws IOException {
        HttpPost post = new HttpPost("https://mail.rambler.ru/");
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("login", username));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("secure_session", "on"));
        params.add(new BasicNameValuePair("return", "index.php"));
        post.setEntity(new UrlEncodedFormEntity(params));
        CloseableHttpResponse response = httpClient.execute(post);
        String body = getTextFrom(response);
        //return body.contains(String.format("<span class=\"user-info\">%s</span>", username));
        System.out.println(username);
        System.out.println(password);
        System.out.println();
        System.out.println(body);

        return body.contains("<span class=\"rambler-topline__user-name\">Ekaterina Samoshkina</span>");
    }

    private String getTextFrom(CloseableHttpResponse response) throws IOException {
        try{
            return EntityUtils.toString(response.getEntity());
        } finally {
            response.close();
        }
    }

    public boolean IsLoggedInAs(String username) throws IOException {
        HttpGet get = new HttpGet("https://mail.rambler.ru/");
        CloseableHttpResponse response = httpClient.execute(get);
        String body = getTextFrom(response);
        return body.contains("<span class=\"rambler-topline__user-name\">Ekaterina Samoshkina</span>");
    }
}
