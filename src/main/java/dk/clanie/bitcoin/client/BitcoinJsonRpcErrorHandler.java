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

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.Series;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.UnknownHttpStatusCodeException;

import com.fasterxml.jackson.databind.ObjectMapper;

import dk.clanie.bitcoin.client.response.BitcoinJsonRpcErrorResponse;

/**
 * Handles error responses from bitcoind.
 * <p>
 * When bitcoind returns an server error response (an HTTP 5xx response)
 * this handler will throw an BitcoinJsonRpcException including the
 * response body as an {@link BitcoinJsonRpcErrorResponse}.
 * <p>
 * This error handler i based on Springs {@link DefaultResponseErrorHandler}
 * and all other errors are handled as the DefaultResponseErrorHandler would
 * handle them.
 * 
 * @author Claus Nielsen
 */
public class BitcoinJsonRpcErrorHandler extends DefaultResponseErrorHandler {

	public void handleError(ClientHttpResponse response) throws IOException {
		HttpStatus statusCode = getHttpStatusCode(response);
		if (statusCode.series() == Series.SERVER_ERROR) {
			String body = new String(getResponseBody(response), getCharset(response));
			ObjectMapper objectMapper = new ObjectMapper();
			BitcoinJsonRpcErrorResponse errorResponse = objectMapper.readValue(body, BitcoinJsonRpcErrorResponse.class);
			switch (errorResponse.getError().getCode()) {
				case -4: // Private key for address <bitcoinaddress> is not known
					throw new UnknownAddressException(errorResponse);
				case -5: // Invalid Bitcoin address
					throw new InvalidAddressException(errorResponse);
				case -15: // Error: running with an unencrypted wallet, but walletpassphrasechange was called.
					throw new WalletNotEncryptedException(errorResponse);
				default:
					throw new BitcoinJsonRpcException(errorResponse);
			}
		} else {
			super.handleError(response);
		}
	}


	private HttpStatus getHttpStatusCode(ClientHttpResponse response) throws IOException {
		HttpStatus statusCode;
		try {
			statusCode = response.getStatusCode();
		}
		catch (IllegalArgumentException ex) {
			throw new UnknownHttpStatusCodeException(response.getRawStatusCode(),
					response.getStatusText(), response.getHeaders(), getResponseBody(response), getCharset(response));
		}
		return statusCode;
	}


	private byte[] getResponseBody(ClientHttpResponse response) {
		try {
			InputStream responseBody = response.getBody();
			if (responseBody != null) {
				return FileCopyUtils.copyToByteArray(responseBody);
			}
		}
		catch (IOException ex) {
			// ignore
		}
		return new byte[0];
	}


	private Charset getCharset(ClientHttpResponse response) {
		HttpHeaders headers = response.getHeaders();
		MediaType contentType = headers.getContentType();
		Charset charset = null;
		if (contentType != null) charset = contentType.getCharSet();
		if (charset == null) charset = Charset.forName("ISO-8859-1");
		return charset;
	}


}
