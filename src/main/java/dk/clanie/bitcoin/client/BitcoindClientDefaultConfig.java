/**
 * Copyright (C) 2013, Claus Nielsen, cn@cn-consult.dk
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package dk.clanie.bitcoin.client;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * Default BitcoindClient configuration.
 * 
 * @author Claus Nielsen
 */
@Configuration
public class BitcoindClientDefaultConfig {

	// TODO Make default config properties configurable
	private static final String BITCOIND_HOST = "localhost";
	private static final int BITCOIND_PORT = 18332;
	private static final String BITCOIND_USER_NAME = "bitcoinrpc";
	private static final String BITCOIND_PASSWORD = "3LUTo7SCiYmcYZuZyfkgFdLU4hSt9TDAdPQnJuvaGHoJ";

	@Bean
	public BitcoindClient getBitcoindClient() {
		BitcoindClient bitcoindClient = new BitcoindClient();
		bitcoindClient.setUrl("http://" + BITCOIND_HOST + ":" + BITCOIND_PORT);
		return bitcoindClient;
	}

	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(getRequestFactory());
		restTemplate.setErrorHandler(getErrorHandler());
		return restTemplate;
	}

	private ResponseErrorHandler getErrorHandler() {
		return new BitcoindJsonRpcErrorHandler();
	}

	private ClientHttpRequestFactory getRequestFactory() {
		return new HttpComponentsClientHttpRequestFactory(getHttpClient());
	}

	private HttpClient getHttpClient() {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient.setCredentialsProvider(getCredentialsProvicer());
		return httpClient;
	}

	private CredentialsProvider getCredentialsProvicer() {
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(
				new AuthScope(BITCOIND_HOST, BITCOIND_PORT),
				new UsernamePasswordCredentials(BITCOIND_USER_NAME, BITCOIND_PASSWORD));
		return credsProvider;
	}

}
