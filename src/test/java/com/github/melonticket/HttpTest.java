package com.github.melonticket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpTest {
    public static void main(String[] args) {
        // 配置代理服务器
        InetSocketAddress proxyAddress = InetSocketAddress.createUnresolved("127.0.0.1", 7890);
        ProxySelector proxySelector = ProxySelector.of(proxyAddress);

        // 创建 HttpClient 实例并配置代理
        HttpClient client = HttpClient.newBuilder()
                .proxy(proxySelector)
                .build();

        // 创建 HttpRequest 实例并设置 User-Agent
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://tkglobal.melon.com/main/index.htm?langCd=EN"))
                .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/135.0.0.0 Safari/537.36")
                .build();

        try {
            // 发送请求并获取响应
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // 打印响应状态码
            System.out.println("Status Code: " + response.statusCode());

            // 打印响应体
            System.out.println("Response Body: " + response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
