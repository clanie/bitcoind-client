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
package dk.clanie.bitcoin.client.response;

import org.springframework.roo.addon.javabean.RooJavaBean;

import dk.clanie.bitcoin.json.JsonExtra;

/**
 * A bitcoind JSON RPC response.
 * 
 * @author Claus Nielsen
 *
 * @param <RT> type of the result field.
 * @param <ET> type of the error field.
 */
@SuppressWarnings("serial")
@RooJavaBean(settersByDefault = false)
public abstract class BitcoindJsonRpcResponse<RT, ET> extends JsonExtra {

	private RT result;
	private ET error;
	private String id;

}
