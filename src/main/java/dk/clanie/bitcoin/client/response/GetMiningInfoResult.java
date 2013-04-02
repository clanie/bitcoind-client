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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import dk.clanie.bitcoin.json.JsonExtra;

/**
 * Data returned by getMiningInfo.
 * 
 * @author Claus Nielsen
 */
@SuppressWarnings("serial")
@RooJavaBean(settersByDefault = false)
@JsonPropertyOrder({
	"blocks",
	"currentblocksize",
	"currentblocktx",
	"difficulty",
	"errors",
	"generate",
	"genproclimit",
	"hashespersec",
	"pooledtx",
	"testnet"
})
public class GetMiningInfoResult extends JsonExtra {

	private Long blocks;

	@JsonProperty("currentblocksize")
	private Integer currentBlockSize;

	@JsonProperty("currentblocktx")
	private Integer currentBlockTx;

	private Double difficulty;

	private String errors;

	private Boolean generate;

	@JsonProperty("genproclimit")
	private Integer genProcLlimit;

	@JsonProperty("hashespersec")
	private Integer hashespersec;

	@JsonProperty("pooledtx")
	private Integer pooledTx;

	private Boolean testnet;

}
