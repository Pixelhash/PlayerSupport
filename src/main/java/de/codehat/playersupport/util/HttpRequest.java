/*
 * Copyright (c) 2016 CodeHat.
 * This file is part of 'PlayerSupport' and is licensed under GPLv3.
 */

package de.codehat.playersupport.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * HttpRequest
 * @author CodeHat
 */

public class HttpRequest {

	private final static String USER_AGENT = "Updater by CodeHat";

	/**
	 * Sends a GET request to the given URL.
	 * @param url The URL to send the request.
	 * @return The result of the GET request.
	 * @throws Exception Something went wrong.
	 */
	public static String sendGet(String url) throws Exception {
		URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        SSLSocketFactory sslSocketFactory = createSslSocketFactory();
        con.setSSLSocketFactory(sslSocketFactory);
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setReadTimeout(2000);
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();
	}
	
	private static SSLSocketFactory createSslSocketFactory() throws Exception {
        TrustManager[] byPassTrustManagers = new TrustManager[] { new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }
        } };
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, byPassTrustManagers, new SecureRandom());
        return sslContext.getSocketFactory();
    }
}