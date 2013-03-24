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

import static dk.clanie.collections.CollectionFactory.newHashMap;

import java.util.Map;
import java.util.Set;

import org.springframework.roo.addon.javabean.RooJavaBean;

import com.fasterxml.jackson.annotation.JsonAnySetter;

/**
 * A bitcoind JSON RPC response.
 * 
 * @author Claus Nielsen
 *
 * @param <T> type of the result field.
 */
@RooJavaBean(settersByDefault = false)
public abstract class BitcoinJsonRpcResponse<T> {

	private String id;
	private T result;

	private Map<String, Object> otherFields = newHashMap();


	/**
	 * Default constructor.
	 */
	public BitcoinJsonRpcResponse() {
	}


	@JsonAnySetter
	public void set(String field, Object value)  {
		otherFields.put(field, value);
	}


	/**
	 * Gets the value of other fields available in the JSON response.
	 *  
	 * @param field
	 */
	public Object get(String field) {
		return otherFields.get(field);
	}


	/**
	 * Gets names of all other (unknown) JSON fields.
	 * 
	 * @return Names of other fields available. 
	 */
	public Set<String> getOtherFields() {
		return otherFields.keySet();
	}


}
