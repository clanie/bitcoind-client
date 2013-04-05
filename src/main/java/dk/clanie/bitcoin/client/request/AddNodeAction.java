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
package dk.clanie.bitcoin.client.request;

/**
 * Action code telling the BitcoindClient addNode method what to do.
 * 
 * @author Claus Nielsen
 */
public enum AddNodeAction {

	/**
	 * Add the given node.
	 */
	ADD("add"),

	/**
	 * Remove the given node.
	 */
	REMOVE("remove"),

	/**
	 * Try connecting to the given node once.
	 */
	ONE_TRY("onetry");

	private String value;

	private AddNodeAction(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

}