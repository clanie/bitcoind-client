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

import org.springframework.roo.addon.javabean.RooJavaBean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import dk.clanie.bitcoin.json.JsonExtra;

/**
 * Arguments for BitcoindClient's getBlockTemplate method.
 * 
 * @see {@link https://en.bitcoin.it/wiki/BIP_0022}
 * 
 * @author Claus Nielsen
 */
@SuppressWarnings("serial")
@RooJavaBean(settersByDefault = false)
public class TemplateRequest extends JsonExtra {

	private String[] capabilities;

	@JsonInclude(Include.NON_EMPTY)
	private String mode;

	/**
	 * Constructor.
	 *
	 * @param capabilities - SHOULD contain a list of the following, to indicate client-side support:
	 * "longpoll", "coinbasetxn", "coinbasevalue", "proposal", "serverlist",
	 * "workid", and any of the mutations (see {@link https://en.bitcoin.it/wiki/BIP_0022}).
	 * @param mode - MUST be "template" or omitted (null).
	 */
	public TemplateRequest(String[] capabilities, String mode) {
		this.capabilities = capabilities;
		this.mode = mode;
	}

}
