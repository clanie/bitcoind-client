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
package dk.clanie.bitcoin.exception;

import org.apache.commons.lang.builder.ToStringBuilder;

import dk.clanie.bitcoin.client.response.BitcoindErrorResponse;

/**
 * Superclass for all exceptions thrown when a call to bitcoind fails.
 * 
 * @author Claus Nielsen
 */
@SuppressWarnings("serial")
public class BitcoinException extends RuntimeException {

	private BitcoindErrorResponse errorResponse = null;


	protected BitcoinException(BitcoindErrorResponse errorResponse) {
		super(errorResponse.getError().getMessage());
		this.errorResponse = errorResponse;
	}


	public BitcoinException(Exception cause) {
		super(cause);
	}
	

	public BitcoinException(String message, Exception cause) {
		super(message, cause);
	}
	

	/**
	 * Gets the whole response received from bitcoind.
	 * 
	 * @return {@link BitcoindErrorResponse} - may be null.
	 */
	public BitcoindErrorResponse getErrorResponse() {
		return errorResponse;
	}


	/**
	 * Gets the error code received from bitcoind.
	 * 
	 * @return errorcode or null if no error code was received.
	 */
	public Integer getErrorCode() {
		if (errorResponse == null || errorResponse.getError() == null) return null;
		return errorResponse.getError().getCode();
	}


	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}


}
