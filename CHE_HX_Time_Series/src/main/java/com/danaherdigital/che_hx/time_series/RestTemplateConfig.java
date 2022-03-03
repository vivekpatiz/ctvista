package com.danaherdigital.che_hx.time_series;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

@Configuration
public class RestTemplateConfig {

	 @Bean
	 public RestTemplate okhttp3Template() {
	     RestTemplate restTemplateOK = new RestTemplate();

	     // create the okhttp client builder
	     OkHttpClient.Builder builder = new OkHttpClient.Builder();
	     ConnectionPool okHttpConnectionPool = new ConnectionPool(50, 120, TimeUnit.SECONDS);
	     builder.connectionPool(okHttpConnectionPool);
	     builder.connectTimeout(120, TimeUnit.SECONDS);
	     builder.readTimeout(15000, TimeUnit.SECONDS);
	     builder.writeTimeout(120, TimeUnit.SECONDS);
	     builder.callTimeout(120, TimeUnit.SECONDS);
	     builder.retryOnConnectionFailure(false);

	     // embed the created okhttp client to a spring rest template
	     restTemplateOK.setRequestFactory(new OkHttp3ClientHttpRequestFactory(builder.build()));

	     return restTemplateOK;
	 }

}