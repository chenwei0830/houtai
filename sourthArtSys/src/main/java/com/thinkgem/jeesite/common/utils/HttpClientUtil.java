package com.thinkgem.jeesite.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.modules.api.entity.WxResult;

/**
 * 基于 HTTPCLIENT 4.5版本的 HTTP工具类
 * 
 * @author LM
 */
public class HttpClientUtil {

	private static PoolingHttpClientConnectionManager connMgr;
	private static RequestConfig requestConfig;
	private static final int MAX_TIMEOUT = 7000;

	static {
		// 设置连接池
		connMgr = new PoolingHttpClientConnectionManager();
		// 设置连接池大小
		connMgr.setMaxTotal(100);
		connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

		RequestConfig.Builder configBuilder = RequestConfig.custom();
		// 设置连接超时
		configBuilder.setConnectTimeout(MAX_TIMEOUT);
		// 设置读取超时
		configBuilder.setSocketTimeout(MAX_TIMEOUT);
		// 设置从连接池获取连接实例的超时
		configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
		// 在提交请求之前 测试连接是否可用
		// configBuilder.setStaleConnectionCheckEnabled(true);
		requestConfig = configBuilder.build();
	}

	/**
	 * 发送GET请求(HTTP)，不带参数
	 * 
	 * @param url
	 * @return
	 */
	public static String doGet(String url) {
		return doGet(url, new HashMap<String, Object>());
	}

	/**
	 * 发送 GET 请求（HTTP），K-V形式
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doGet(String url, Map<String, Object> params) {

		String apiUrl = url;
		StringBuffer param = new StringBuffer();
		int i = 0;

		for (String key : params.keySet()) {
			if (i == 0) {
				param.append("?");
			} else {
				param.append("&");
			}

			param.append(key).append("=").append(params.get(key));
			i++;
		}
		apiUrl += param.toString();

		String result = null;
		HttpClient httpClient = new DefaultHttpClient();

		try {
			HttpGet httpPost = new HttpGet(apiUrl);
			HttpResponse response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();

			System.out.println("执行状态码 : " + statusCode);

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				result = IOUtils.toString(instream, "UTF-8");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	
	public static String doGet(String url, Map<String, Object> params,String charset) {

		String apiUrl = url;
		StringBuffer param = new StringBuffer();
		int i = 0;

		for (String key : params.keySet()) {
			if (i == 0) {
				param.append("?");
			} else {
				param.append("&");
			}

			param.append(key).append("=").append(params.get(key));
			i++;
		}
		apiUrl += param.toString();

		String result = null;
		HttpClient httpClient = new DefaultHttpClient();

		try {
			HttpGet httpPost = new HttpGet(apiUrl);
			HttpResponse response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();

			System.out.println("执行状态码 : " + statusCode);

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				result = IOUtils.toString(instream, charset);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 发送 POST 请求（HTTP），不带输入数据
	 * 
	 * @param apiUrl
	 * @return
	 */
	public static String doPost(String apiUrl) {
		return doPost(apiUrl, new HashMap<String, Object>());
	}

	/**
	 * 发送 POST 请求（HTTP），K-V形式
	 * 
	 * @param apiUrl
	 *            API接口URL
	 * @param params
	 *            参数map
	 * @return
	 */
	public static String doPost(String apiUrl, Map<String, Object> params) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String httpStr = null;
		HttpPost httpPost = new HttpPost(apiUrl);
		CloseableHttpResponse response = null;

		try {
			httpPost.setConfig(requestConfig);
			List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
				pairList.add(pair);
			}
			httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			httpStr = EntityUtils.toString(entity, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return httpStr;
	}

	/**
	 * 发送 POST 请求（HTTP），JSON形式
	 * 
	 * @param apiUrl
	 * @param json
	 *            json对象
	 * @return
	 */
	public static String doPost(String apiUrl, Object json) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String httpStr = null;
		HttpPost httpPost = new HttpPost(apiUrl);
		CloseableHttpResponse response = null;

		try {
			httpPost.setConfig(requestConfig);
			StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");// 解决中文乱码问题
			stringEntity.setContentEncoding("UTF-8");
			stringEntity.setContentType("application/json");
			httpPost.setEntity(stringEntity);
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			System.out.println(response.getStatusLine().getStatusCode());
			httpStr = EntityUtils.toString(entity, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return httpStr;
	}

	/**
	 * 发送 SSL POST 请求（HTTPS），K-V形式
	 * 
	 * @param apiUrl
	 *            API接口URL
	 * @param params
	 *            参数map
	 * @return
	 */
	public static String doPostSSL(String apiUrl, Map<String, Object> params) {
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
				.setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
		HttpPost httpPost = new HttpPost(apiUrl);
		CloseableHttpResponse response = null;
		String httpStr = null;

		try {
			httpPost.setConfig(requestConfig);
			List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
				pairList.add(pair);
			}
			httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("utf-8")));
			response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				return null;
			}
			httpStr = EntityUtils.toString(entity, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return httpStr;
	}

	/**
	 * 发送 SSL POST 请求（HTTPS），JSON形式
	 * 
	 * @param apiUrl
	 *            API接口URL
	 * @param json
	 *            JSON对象
	 * @return
	 */
	public static String doPostSSL(String apiUrl, Object json) {
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
				.setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
		HttpPost httpPost = new HttpPost(apiUrl);
		CloseableHttpResponse response = null;
		String httpStr = null;

		try {
			httpPost.setConfig(requestConfig);
			StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");// 解决中文乱码问题
			stringEntity.setContentEncoding("UTF-8");
			stringEntity.setContentType("application/json");
			httpPost.setEntity(stringEntity);
			response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				return null;
			}
			httpStr = EntityUtils.toString(entity, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return httpStr;
	}

	/**
	 * 创建SSL安全连接
	 * 
	 * @return
	 */
	private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
		SSLConnectionSocketFactory sslsf = null;
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

				@Override
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}

				@Override
				public void verify(String host, SSLSocket ssl) throws IOException {
				}

				@Override
				public void verify(String host, X509Certificate cert) throws SSLException {
				}

				@Override
				public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
				}
			});
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		return sslsf;
	}

	/**
	 * 发送 POST 请求（HTTP），K-V形式
	 * 
	 * @param apiUrl
	 *            API接口URL
	 * @param params
	 *            参数map
	 * @return
	 */
	public static String doPost(String apiUrl, Map<String, Object> params,String charset) {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String httpStr = null;
		HttpPost httpPost = new HttpPost(apiUrl);
		CloseableHttpResponse response = null;

		try {
			httpPost.setConfig(requestConfig);
			List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
				pairList.add(pair);
			}
			httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName(charset)));
			response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			httpStr = EntityUtils.toString(entity, charset);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (response != null) {
				try {
					EntityUtils.consume(response.getEntity());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return httpStr;
	}
	
	
	/**
	 * 测试方法
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		
		
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("title", "hahah");
		params.put("modelType", "0");
		params.put("artType", "1");
		params.put("openId", "xxxxxxxxxxxx");
		params.put("orgId", "2");
		params.put("textContent", "assadsadsdas");
		String apiUrl = "https://www.chenqimao.com/sourthArtSys/api/auth";
		String result = HttpClientUtil.doGet(apiUrl);
//		String result = HttpClientUtil.doPostSSL(apiUrl, JsonMapper.toJsonString(params));
		System.out.println(result);
//		String apiUrl = "http://www.artfacts.net/en/artists/top100.html";
//		
//		Map<String,Object> params = new HashMap<String,Object>();
//		params.put("year", "2017");
//		params.put("living", "0");
//		params.put("order", "0");
//		params.put("switch", "0");
//		params.put("x", "4");
//		params.put("y", "12");
//		String result = HttpClientUtil.doPost(apiUrl, params,"ISO-8859-1");
//		Document cDoc = Jsoup.parse(result);
//		Element eBody = cDoc.body();
//		Element eDiv = eBody.getElementById("afn_main");
//		Element eTables = eDiv.getElementsByTag("table").first();
//		Element eTab = eTables.getElementsByTag("table").last();
//		 
//		Elements eTrs = eTab.getElementsByTag("tr");
//		eTrs.remove(0);
//		eTrs.remove(0);
//		 
//		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
//		for(Element e : eTrs) {
//			Element td_1 = e.getElementsByTag("td").get(1);
//			Element td_3 = e.getElementsByTag("td").get(3);
//			Element td_4 = e.getElementsByTag("td").get(4);
//			Map<String,String> map = new HashMap<String,String>();
//			map.put("id", td_1.getElementsByTag("a").first().attr("href").replaceFirst("/index.php/pageType/artistInfo/artist/", ""));
//			map.put("url", "http://www.artfacts.net"+td_1.getElementsByTag("a").first().attr("href"));
//			map.put("name", td_1.getElementsByTag("a").first().text());
//			map.put("date", td_3.text());
//			map.put("Citizenship", td_4.text());
//			list.add(map);
//		}
//		for(int i=0;i<list.size();i++) {
//			Map<String,String> m = list.get(i);
//			String r = HttpClientUtil.doGet(m.get("url"), new HashMap<String,Object>() ,"ISO-8859-1");
//			Document cDoc_2 = Jsoup.parse(r);
//			Element eBody_2 = cDoc_2.body();
//			Element eDiv_2 = eBody_2.getElementById("contents");
//			Element eTable_2 = eDiv_2.getElementsByTag("table").first();
//			Element eTr_2 = eTable_2.getElementsByTag("tr").first();
//			Element eTd_2 = eTr_2.getElementsByTag("td").first();
//			//Artist's arttributes
//			
//			Elements tabs= eTd_2.getElementsByTag("table");
//			for(Element e : tabs) {
//				Elements trs = e.getElementsByTag("tbody").first().getElementsByTag("tr");
//				Element tr_first = trs.first();
//				Element h2 = tr_first.getElementsByTag("h2").first();
//				if(h2!=null) {
//					String text = h2.toString();
//					if(text.contains("Artist's arttributes")) {
//						if(trs.size()==2) {
//							m.put("Gender", trs.get(1).getElementsByTag("td").last().text());
//							m.put("Category", "-");
//							m.put("Movement", "-");
//						}else if(trs.size()==3) {
//							m.put("Gender", trs.get(1).getElementsByTag("td").last().text());
//							m.put("Category", trs.get(2).getElementsByTag("td").last().text());
//							m.put("Movement", "-");
//						}else if(trs.size()==4) {
//							m.put("Gender", trs.get(1).getElementsByTag("td").last().text());
//							m.put("Category", trs.get(2).getElementsByTag("td").last().text());
//							m.put("Movement", trs.get(3).getElementsByTag("td").last().text());
//						}else {
//							m.put("Gender", "-");
//							m.put("Category", "-");
//							m.put("Movement", "-");
//						}
//						break;
//					}
//					
//				}
//			}
//			System.out.println("第"+(i+1)+"位:"+JsonMapper.toJsonString(m));
//		}
//		System.out.println(JsonMapper.toJsonString(list));
	}
}
