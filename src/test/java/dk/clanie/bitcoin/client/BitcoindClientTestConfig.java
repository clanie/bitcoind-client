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

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * BitcoindClient configuration for isolated unit testing.
 * 
 * @author Claus Nielsen
 */
@Configuration
public class BitcoindClientTestConfig {

	private static final String BITCOIND_HOST = "localhost";
	private static final int BITCOIND_PORT = 18332;

	@Bean
	public BitcoindClient getBitcoindClient() {
		BitcoindClient bitcoindClient = new BitcoindClient();
		bitcoindClient.setUrl("http://" + BITCOIND_HOST + ":" + BITCOIND_PORT);
		return bitcoindClient;
	}

	@Bean
	public RestTemplate getRestTemplate() {
		return Mockito.mock(RestTemplate.class);
	}

}
