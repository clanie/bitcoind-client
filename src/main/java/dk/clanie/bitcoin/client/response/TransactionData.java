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

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.roo.addon.javabean.RooJavaBean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import dk.clanie.bitcoin.json.BigDecimalPlainSerializer;
import dk.clanie.bitcoin.json.JsonExtra;

/**
 * Data about one transaction as returned by BitconidClient's listTransactions
 * and listSinceBlock methods.
 * 
 * @author Claus Nielsen
 */
@SuppressWarnings("serial")
@RooJavaBean(settersByDefault = false)
@JsonPropertyOrder({
	"account",
	"address",
	"category",
	"amount",
	"fee",
	"confirmations",
	"generated",
	"blockhash",
	"blockindex",
	"blocktime",
	"txid",
	"time",
	"timereceived",
	"comment",
	"to"
})
public class TransactionData extends JsonExtra {

	private String account;
	private String address;
	private String category;

	@JsonSerialize(using = BigDecimalPlainSerializer.class)
	private BigDecimal amount;

	@JsonInclude(Include.NON_NULL)
	@JsonSerialize(using = BigDecimalPlainSerializer.class)
	private BigDecimal fee;

	private Integer confirmations;
	
	@JsonInclude(Include.NON_NULL)
	private Boolean generated;

	@JsonProperty("blockhash")
	@JsonInclude(Include.NON_NULL)
	private String blockHash;

	@JsonProperty("blockindex")
	@JsonInclude(Include.NON_NULL)
	private Integer blockIndex;

	@JsonProperty("blocktime")
	@JsonInclude(Include.NON_NULL)
	private Date blockTime;

	@JsonProperty("txid")
	private String txId;

	private Date time;

	@JsonProperty("timereceived")
	private Date timeReceived;

	@JsonInclude(Include.NON_NULL)
	private String comment;

	@JsonInclude(Include.NON_NULL)
	private String to;

}
