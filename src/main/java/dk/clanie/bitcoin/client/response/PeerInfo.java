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

import java.util.Date;

import org.springframework.roo.addon.javabean.RooJavaBean;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import dk.clanie.bitcoin.json.JsonExtra;

/**
 * Data returned by BitcoindClient's getPeerInfo method.
 * 
 * @author Claus Nielsen
 */
@SuppressWarnings("serial")
@RooJavaBean(settersByDefault = false)
@JsonPropertyOrder({
	"addr",
	"services",
	"lastsend",
	"lastrecv",
	"conntime",
	"version",
	"subver",
	"inbound",
	"releasetime",
	"startingheight",
	"banscore"
})
public class PeerInfo extends JsonExtra {

	/**
	 * Network address.
	 */
	@JsonProperty("addr")
	private String address;

	private String services;
	
	@JsonProperty("lastsend")
	private Date lastSend;
	
	@JsonProperty("lastrecv")
	private Date lastRecv;
	
	@JsonProperty("conntime")
	private Date connTime;
	
	private Integer version;
	
	@JsonProperty("subver")
	private String subVersion;

	private Boolean inbound;
	
	@JsonProperty("releasetime")
	private Date releaseTime;
	
	@JsonProperty("startingheight")
	private Long startingHeight;
	
	@JsonProperty("banscore")
	private Integer banScore;

}
