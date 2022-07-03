package com.example.kivorkbackendselenium;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClientExample {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void main(String[] args) throws Exception {

        HttpClientExample obj = new HttpClientExample();

        try {
            System.out.println("Testing 1: Send Http GET request to https://freegeoip.app/json/");
            obj.sendGet();

        } finally {
            obj.close();
        }
    }

    private void close() throws IOException {
        httpClient.close();
    }

    private void sendGet() throws Exception {

        HttpGet request = new HttpGet("https://freegeoip.app/json/");

        request.addHeader("apikey", "D9Rcr0J03cAOQIasVXf80wJFGKq01FttiAQnTbMZ");

        try (CloseableHttpResponse response = httpClient.execute(request)) {

            System.out.println(response.getStatusLine().toString());

            HttpEntity entity = response.getEntity();
            Header headers = entity.getContentType();
            System.out.println(headers);

            if (entity != null) {
                String result = EntityUtils.toString(entity);
                System.out.println(result);
            }

        }

    }

}
