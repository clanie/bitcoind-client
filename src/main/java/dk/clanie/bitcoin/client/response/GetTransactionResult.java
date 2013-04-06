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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import dk.clanie.bitcoin.json.JsonExtra;

/**
 * Data about a transaction as returned by BitcoindClient's getTransaction method.
 * 
 * @author Claus Nielsen
 */
@SuppressWarnings("serial")
@RooJavaBean(settersByDefault = false)
@JsonPropertyOrder({
	"amount",
	"confirmations",
	"blockhash",
	"blockindex",
	"blocktime",
	"txid",
	"time",
	"timereceived",
	"details"
})
public class GetTransactionResult extends JsonExtra {

	/**
	 * Total amount of the transaction.
	 */
	private BigDecimal amount;
	
	/**
	 * Number of confirmations of the transaction.
	 */
	private Integer confirmations;

	@JsonProperty("blockhash")
	private String blockHash;

	@JsonProperty("blockindex")
	private Integer blockIndex;

	@JsonProperty("blocktime")
	private Date blockTime;

	/**
	 * Transaction ID
	 */
	@JsonProperty("txid")
	private String txId;

	/**
	 * Time the transaction occurred.
	 */
	private Date time;

	@JsonProperty("timereceived")
	private Date timeReceived;

	private TransactionDetail[] details;
	
}
