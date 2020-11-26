package com.tao.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HTTPClientDemo {
    private static CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//    private static CloseableHttpClient httpClient = HttpClients.createDefault();


    static RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(10000)
            .setConnectTimeout(10000)
            .setConnectionRequestTimeout(10000)
            .build();

    public static void sendPostRequest(){
        try {
            HttpPost httpPost = new HttpPost("https://www.thepaper.cn/www/commentPraise.msp");
            httpPost.addHeader(HTTP.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.66 Safari/537.36");
            httpPost.setConfig(requestConfig);
            httpPost.setHeader(HTTP.CONN_DIRECTIVE, HTTP.CONN_KEEP_ALIVE);
            List<NameValuePair> params = Lists.newArrayList();
            params.add(new BasicNameValuePair("commentId", "18718372"));
            httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

            HttpResponse response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (200 == statusCode) {
                String res = EntityUtils.toString(response.getEntity(), HTTP.UTF_8);
                System.out.println(res);
            }else {
                httpPost.abort();
                throw new RuntimeException("HttpClient,error status code :" + statusCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
