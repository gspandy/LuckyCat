package com.jesse.util;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HttpUtil {
	
	private  static  final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
	
	/**
	 * 自定义doGet方法,用于发送doGet请求
	 * @author lizhie
	 * @param url
	 * @return
	 * @date 2017年5月18日
	 */
	public static String doGet(String url) {
		String body = null;
		try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
			logger.info("create httppost:" + url);
			HttpGet httpGet = new HttpGet(url);
			httpGet.addHeader("Accept-Charset", "utf-8");
			HttpResponse response = sendRequest(httpClient, httpGet);
			body = parseResponse(response);
		} catch (IOException e) {
			logger.error("send post request failed: {}", e.getMessage());
		}
		return body;
	}
	
	/**
	 * 发送请求，获取response
	 * @author lizhie
	 * @param httpclient
	 * @param httpost
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @date 2017年5月18日
	 */
	private static HttpResponse sendRequest(CloseableHttpClient httpclient,
			HttpUriRequest httpost) throws ClientProtocolException, IOException {
		HttpResponse response = null;
		response = httpclient.execute(httpost);
		return response;
	}

	/**
	 * 解析response
	 * @author lizhie
	 * @param response
	 * @return
	 * @date 2017年5月18日
	 */
	private static String parseResponse(HttpResponse response) {
		logger.info("get response from http server..");
		HttpEntity entity = response.getEntity();
		logger.info("response status: " + response.getStatusLine());
		String body = null;
		try {
			body = EntityUtils.toString(entity, "utf-8");
		} catch (IOException e) {
			logger.warn("{}: cannot parse the entity", e.getMessage());
		}
		return body;
	}
}
