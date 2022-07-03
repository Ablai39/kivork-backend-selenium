package com.example.kivorkbackendselenium.FreeGeoIpApp;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class FreeGeoIpApp {

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public static void main(String[] args) throws Exception {

        /*
            2)  Call https://freegeoip.app/json/ API and perform the following actions:
                a) Assert the response code;
                b) Parse the response;
                c) Assert your latitude and longitude with a 0.01° tolerance (assume you know your actual lat and lon).
         */

        /*
            Подзабыл, что есть только 150 запросов,
                создавал React проект, который выводит данные по введенному IP,
                далее хотел пройтись уже Selenium'ом.
            Были проблемы с CORS, поэтому превысил лимит запросов на стороне фронта.
         */

        FreeGeoIpApp obj = new FreeGeoIpApp();

        try {
            System.out.println("Тест 1: Отправка GET запроса на https://freegeoip.app/json/");
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

        /*
            Добавляем в Header наш ключ сгенерированный на сайте https://app.ipbase.com/api-keys,
                пока тестировал, закончились 150 бесплатных запросов.
         */

        String apikey = "D9Rcr0J03cAOQIasVXf80wJFGKq01FttiAQnTbMZ";
        request.addHeader("apikey", apikey);

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
