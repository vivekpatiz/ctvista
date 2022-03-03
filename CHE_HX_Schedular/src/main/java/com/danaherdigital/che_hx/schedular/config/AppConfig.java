package com.danaherdigital.che_hx.schedular.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

@Configuration
public class AppConfig {

	 @Bean
	 public RestTemplate okhttp3Template() {
	     RestTemplate restTemplateOK = new RestTemplate();

	     // create the okhttp client builder
	     OkHttpClient.Builder builder = new OkHttpClient.Builder();
	     ConnectionPool okHttpConnectionPool = new ConnectionPool(50, 3600, TimeUnit.SECONDS);
	     builder.connectionPool(okHttpConnectionPool);
	     builder.connectTimeout(3600, TimeUnit.SECONDS);
	     builder.readTimeout(3600, TimeUnit.SECONDS);
	     builder.writeTimeout(3600, TimeUnit.SECONDS);
	     builder.callTimeout(3600, TimeUnit.SECONDS);
	     builder.retryOnConnectionFailure(false);

	     // embed the created okhttp client to a spring rest template
	     restTemplateOK.setRequestFactory(new OkHttp3ClientHttpRequestFactory(builder.build()));

	     return restTemplateOK;
	 }

}