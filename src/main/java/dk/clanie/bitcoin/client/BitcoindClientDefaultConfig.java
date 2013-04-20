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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * Default BitcoindClient configuration.
 * <p>
 * Loads properties from:
 * <ol>
 * <li>classpath:/bitcoind-client.properties</li>
 * <li>classpath:/bitcoind-client-test.properties</li>
 * <li>The resource specified in environment variable
 * <code>BITCOIND_CLIENT_PROPERTY_FILE</code></li>
 * </ol>
 * <p>
 * At least one of these should be present and define the following properties:
 * <bl>
 * <li>bitcoind.client.host - defaults to localhost.</li>
 * <li>bitcoind.client.port - default 18332, which is bitcond's default testnet
 * port. The default for the real bitcoin network is 8332.</li>
 * <li>bitcoind.client.user</li>
 * <li>bitcoind.client.passwor</li>
 * </bl>
 * 
 * If a property is defined in more than one property file, the settings loaded
 * <i>last</i> takes precedence.
 * 
 * @author Claus Nielsen
 */
@Configuration
@ImportResource("classpath:/META-INF/spring/bitcoind-client-context.xml")
public class BitcoindClientDefaultConfig {

	@Value("${bitcoind.client.host}")
	private String host = "localhost";

	@Value("${bitcoind.client.port}")
	private String port = "18332";

	@Value("${bitcoind.client.user}")
	private String user = null;

	@Value("${bitcoind.client.password}")
	private String password = null;


	@Bean
	public BitcoindClient bitcoindClient() {
		BitcoindClient bitcoindClient = new BitcoindClient();
		bitcoindClient.setUrl("http://" + host + ":" + port);
		return bitcoindClient;
	}


	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.setRequestFactory(requestFactory());
		restTemplate.setErrorHandler(errorHandler());
		return restTemplate;
	}


	private ResponseErrorHandler errorHandler() {
		return new BitcoindJsonRpcErrorHandler();
	}


	private ClientHttpRequestFactory requestFactory() {
		return new HttpComponentsClientHttpRequestFactory(httpClient());
	}


	private HttpClient httpClient() {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		httpClient.setCredentialsProvider(credentialsProvicer());
		return httpClient;
	}


	private CredentialsProvider credentialsProvicer() {
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(
				new AuthScope(host, Integer.valueOf(port)),
				new UsernamePasswordCredentials(user, password));
		return credsProvider;
	}


}
