package com.devadas.poc.misc;

import java.io.IOException;
import java.util.Scanner;

//import org.apache.hc.client5.http.ClientProtocolException;
//import org.apache.hc.client5.http.classic.HttpClient;
//import org.apache.hc.client5.http.classic.methods.HttpGet;
//import org.apache.hc.client5.http.classic.methods.HttpPost;
//import org.apache.hc.client5.http.impl.DefaultRedirectStrategy;
//import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
//import org.apache.hc.client5.http.impl.classic.HttpClientBuilder;
//import org.apache.hc.client5.http.impl.classic.HttpClients;
//import org.apache.hc.core5.http.ClassicHttpResponse;
//import org.apache.hc.core5.http.HttpException;
//import org.apache.hc.core5.http.HttpResponse;
//import org.apache.hc.core5.http.io.HttpClientResponseHandler;

public class HttpClient5Tester {
//	public static void main(String[] args) throws ClientProtocolException, IOException {
//		new HttpClient5Tester().test();
//	}
//
//	private void test() throws IOException {
////	   HttpClientBuilder clientBuilder = HttpClients.custom()
////                   .setRedirectStrategy(new DefaultRedirectStrategy());
//		
//		System.out.println("propoerty = " + System.getProperty("http.proxyPassword"));
//	   HttpClientBuilder clientBuilder = HttpClients.custom();
//	   HttpClient instance = clientBuilder.build();
//	   
//	   
//	   
////		HttpClient instance = HttpClientBuilder.create().build();
//		System.out.println("About to exec");
//		CloseableHttpResponse response = (CloseableHttpResponse) instance
//				.execute(new HttpGet("http://localhost:8080/products/redirect"), new CustomHttpClientResponseHandler());
//		System.out.println("Got response " + response);
//		System.out.println(response.getCode());
//	}
//
//	class CustomHttpClientResponseHandler implements HttpClientResponseHandler {
//
//		@Override
//		public Object handleResponse(ClassicHttpResponse response) throws HttpException, IOException {
//			Scanner s = new Scanner(response.getEntity().getContent()).useDelimiter("\\A");
//			String result = s.hasNext() ? s.next() : "";
//			System.out.println("Received response in handler " + result);
//			return response;
//		}
//
//	}
}
