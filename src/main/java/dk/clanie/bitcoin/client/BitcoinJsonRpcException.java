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

import dk.clanie.bitcoin.client.response.BitcoinJsonRpcErrorResponse;

@SuppressWarnings("serial")
public class BitcoinJsonRpcException extends RuntimeException {

	private BitcoinJsonRpcErrorResponse errorResponse;

	public BitcoinJsonRpcException(BitcoinJsonRpcErrorResponse errorResponse) {
		super(errorResponse.getError().getMessage());
		this.errorResponse = errorResponse;
	}

	/**
	 * Gets the whole response received from bitcoind.
	 * 
	 * @return {@link BitcoinJsonRpcErrorResponse}
	 */
	public BitcoinJsonRpcErrorResponse getErrorResponse() {
		return errorResponse;
	}

	/**
	 * Gets the error code received from bitcoind.
	 * 
	 * @return errorcode.
	 */
	public Integer getErrorCode() {
		return errorResponse.getError().getCode();
	}

}
