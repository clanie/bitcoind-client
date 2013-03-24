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

import static dk.clanie.collections.CollectionFactory.newArrayList;

import java.util.Collections;
import java.util.List;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;

import dk.clanie.bitcoin.client.response.DumpPrivateKeyResponse;
import dk.clanie.bitcoin.client.response.GetAccountResponse;
import dk.clanie.bitcoin.client.response.GetInfoResponse;
import dk.clanie.bitcoin.client.response.VoidResponse;

/**
 * 
 * @author Claus Nielsen
 */
public class BitcoinClient {

	private static final String BITCOIND_HOST = "localhost";
	private static final int BITCOIND_PORT = 18332;
	private static final String BITCOIND_URL = "http://" + BITCOIND_HOST + ":" + BITCOIND_PORT;
	private static final String BITCOIND_USER_NAME = "bitcoinrpc";
	private static final String BITCOIND_PASSWORD = "3LUTo7SCiYmcYZuZyfkgFdLU4hSt9TDAdPQnJuvaGHoJ";

	private RestTemplate rest;

	/**
	 * Default constructor.
	 */
	public BitcoinClient() {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		// Set credentials
		CredentialsProvider credsProvider = new BasicCredentialsProvider();
		credsProvider.setCredentials(
				new AuthScope(BITCOIND_HOST, BITCOIND_PORT), 
				new UsernamePasswordCredentials(BITCOIND_USER_NAME, BITCOIND_PASSWORD));
		httpClient.setCredentialsProvider(credsProvider);
		rest = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
		rest.setErrorHandler(new BitcoinJsonRpcErrorHandler());
	}

	// TODO addmultisigaddress	 <nrequired> <'["key","key"]'> [account]	 Add a nrequired-to-sign multisignature address to the wallet. Each key is a bitcoin address or hex-encoded public key. If [account] is specified, assign address to [account].	 N
	// TODO addnode	remove|onetry>	version 0.8 Attempts add or remove <node> from the addnode list or try a connection to <node> once.	 N
	// TODO backupwallet	 <destination>	 Safely copies wallet.dat to destination, which can be a directory or a path with filename.	 N
	// TODO createrawtransaction	 [{"txid":txid,"vout":n},...] {address:amount,...}	version 0.7 Creates a raw transaction spending given inputs.	 N
	// TODO decoderawtransaction	 <hex string>	version 0.7 Produces a human-readable JSON object for a raw transaction.	 N

	/**
	 * Reveals the private key corresponding to the given bitcoin address.
	 * 
	 * Requires unlocked wallet.
	 * 
	 * @param bitcoinAddress
	 * 
	 * @return {@link DumpPrivateKeyResponse}
	 */
	public DumpPrivateKeyResponse dumpPrivateKey(String bitcoinAddress) {
		// TODO Make dumpPrivateKey work!
		List<String> params = newArrayList();
		params.add(bitcoinAddress);
		return jsonRpc("dumpprivkey", params, DumpPrivateKeyResponse.class);
	}

	// TODO encryptwallet	 <passphrase>	 Encrypts the wallet with <passphrase>.	 N

	/**
	 * Returns the account associated with the given address.
	 * 
	 * @param bitcoinAddress
	 * @return {@link GetAccountResponse}
	 */
	public GetAccountResponse getAccount(String bitcoinAddress) {
		List<String> params = newArrayList();
		params.add(bitcoinAddress);
		return jsonRpc("getaccount", params, GetAccountResponse.class);
	}


	// TODO getaccountaddress	 <account>	 Returns the current bitcoin address for receiving payments to this account.	 N
	// TODO getaddednodeinfo	 <dns> [node]	version 0.8 Returns information about the given added node, or all added nodes
	// TODO (note that onetry addnodes are not listed here) If dns is false, only a list of added nodes will be provided, otherwise connected information will also be available.
	// TODO getaddressesbyaccount	 <account>	 Returns the list of addresses for the given account.	 N
	// TODO getbalance	 [account] [minconf=1]	 If [account] is not specified, returns the server's total available balance.
	// TODO If [account] is specified, returns the balance in the account.	 N
	// TODO getblock	 <hash>	 Returns information about the given block hash.	 N
	// TODO getblockcount		 Returns the number of blocks in the longest block chain.	 N
	// TODO getblockhash	 <index>	 Returns hash of block in best-block-chain at <index>	 N
	// TODO getblocknumber		Deprecated. Removed in version 0.7. Use getblockcount.	 N
	// TODO getconnectioncount		 Returns the number of connections to other nodes.	 N
	// TODO getdifficulty		 Returns the proof-of-work difficulty as a multiple of the minimum difficulty.	 N
	// TODO getgenerate		 Returns true or false whether bitcoind is currently generating hashes	 N
	// TODO gethashespersec		 Returns a recent hashes per second performance measurement while generating.	 N

	/**
	 * Gets various state info.
	 * 
	 * @return {@link GetInfoResponse}
	 */
	public GetInfoResponse getInfo() {
		return jsonRpc("getinfo", Collections.EMPTY_LIST, GetInfoResponse.class);
	}

	// TODO getmemorypool	 [data]	 If [data] is not specified, returns data needed to construct a block to work on:
	// TODO "version" : block version
	// TODO "previousblockhash" : hash of current highest block
	// TODO "transactions" : contents of non-coinbase transactions that should be included in the next block
	// TODO "coinbasevalue" : maximum allowable input to coinbase transaction, including the generation award and transaction fees
	// TODO "time" : timestamp appropriate for next block
	// TODO "bits" : compressed target of next block
	// TODO If [data] is specified, tries to solve the block and returns true if it was successful.
	// TODO N
	// TODO getmininginfo		 Returns an object containing mining-related information:
	// TODO blocks
	// TODO currentblocksize
	// TODO currentblocktx
	// TODO difficulty
	// TODO errors
	// TODO generate
	// TODO genproclimit
	// TODO hashespersec
	// TODO pooledtx
	// TODO testnet
	// TODO N
	// TODO getnewaddress	 [account]	 Returns a new bitcoin address for receiving payments. If [account] is specified (recommended), it is added to the address book so payments received with the address will be credited to [account].	 N
	// TODO getpeerinfo		version 0.7 Returns data about each connected node.	 N
	// TODO getrawmempool		version 0.7 Returns all transaction ids in memory pool	 N
	// TODO getrawtransaction	 <txid> [verbose=0]	version 0.7 Returns raw transaction representation for given transaction id.	 N
	// TODO getreceivedbyaccount	 [account] [minconf=1]	 Returns the total amount received by addresses with [account] in transactions with at least [minconf] confirmations. If [account] not provided return will include all transactions to all accounts. (version 0.3.24)	 N
	// TODO getreceivedbyaddress	 <bitcoinaddress> [minconf=1]	 Returns the total amount received by <bitcoinaddress> in transactions with at least [minconf] confirmations. While some might consider this obvious, value reported by this only considers *receiving* transactions. It does not check payments that have been made *from* this address. In other words, this is not "getaddressbalance". Works only for addresses in the local wallet, external addresses will always show 0.	 N
	// TODO gettransaction	 <txid>	 Returns an object about the given transaction containing:
	// TODO "amount" : total amount of the transaction
	// TODO "confirmations" : number of confirmations of the transaction
	// TODO "txid" : the transaction ID
	// TODO "time" : time the transaction occurred
	// TODO "details" - An array of objects containing:
	// TODO "account"
	// TODO "address"
	// TODO "category"
	// TODO "amount"
	// TODO "fee"
	// TODO N
	// TODO getwork	 [data]	 If [data] is not specified, returns formatted hash data to work on:
	// TODO "midstate" : precomputed hash state after hashing the first half of the data
	// TODO "data" : block data
	// TODO "hash1" : formatted hash buffer for second hash
	// TODO "target" : little endian hash target
	// TODO If [data] is specified, tries to solve the block and returns true if it was successful.
	// TODO N
	// TODO help	 [command]	 List commands, or get help for a command.	 N
	// TODO importprivkey	 <bitcoinprivkey> [label] [rescan=true]	 Adds a private key (as returned by dumpprivkey) to your wallet. This may take a while, as a rescan is done, looking for existing transactions. Optional [rescan] parameter added in 0.8.0.	 Y
	// TODO keypoolrefill		 Fills the keypool, requires wallet passphrase to be set.	 Y
	// TODO listaccounts	 [minconf=1]	 Returns Object that has account names as keys, account balances as values.	 N
	// TODO listaddressgroupings		version 0.7 Returns all addresses in the wallet and info used for coincontrol.	 N
	// TODO listreceivedbyaccount	 [minconf=1] [includeempty=false]	 Returns an array of objects containing:
	// TODO "account" : the account of the receiving addresses
	// TODO "amount" : total amount received by addresses with this account
	// TODO "confirmations" : number of confirmations of the most recent transaction included
	// TODO N
	// TODO listreceivedbyaddress	 [minconf=1] [includeempty=false]	 Returns an array of objects containing:
	// TODO "address" : receiving address
	// TODO "account" : the account of the receiving address
	// TODO "amount" : total amount received by the address
	// TODO "confirmations" : number of confirmations of the most recent transaction included
	// TODO To get a list of accounts on the system, execute bitcoind listreceivedbyaddress 0 true
	// TODO N
	// TODO listsinceblock	 [blockhash] [target-confirmations]	 Get all transactions in blocks since block [blockhash], or all transactions if omitted.	 N
	// TODO listtransactions	 [account] [count=10] [from=0]	 Returns up to [count] most recent transactions skipping the first [from] transactions for account [account]. If [account] not provided will return recent transaction from all accounts.	 N
	// TODO listunspent	 [minconf=1] [maxconf=999999]	version 0.7 Returns array of unspent transaction inputs in the wallet.	 N
	// TODO listlockunspent		version 0.8 Returns list of temporarily unspendable outputs
	// TODO lockunspent	 <unlock?> [array-of-objects]	version 0.8 Updates list of temporarily unspendable outputs
	// TODO move	 <fromaccount> <toaccount> <amount> [minconf=1] [comment]	 Move from one account in your wallet to another	 N
	// TODO sendfrom	 <fromaccount> <tobitcoinaddress> <amount> [minconf=1] [comment] [comment-to]	 <amount> is a real and is rounded to 8 decimal places. Will send the given amount to the given address, ensuring the account has a valid balance using [minconf] confirmations. Returns the transaction ID if successful (not in JSON object).	 Y
	// TODO sendmany	 <fromaccount> {address:amount,...} [minconf=1] [comment]	 amounts are double-precision floating point numbers	 Y
	// TODO sendrawtransaction	 <hexstring>	version 0.7 Submits raw transaction (serialized, hex-encoded) to local node and network.	 N
	// TODO sendtoaddress	 <bitcoinaddress> <amount> [comment] [comment-to]	 <amount> is a real and is rounded to 8 decimal places. Returns the transaction ID <txid> if successful.	 Y
	// TODO setaccount	 <bitcoinaddress> <account>	 Sets the account associated with the given address. Assigning address that is already assigned to the same account will create a new address associated with that account.	 N
	// TODO setgenerate	 <generate> [genproclimit]	 <generate> is true or false to turn generation on or off.
	// TODO Generation is limited to [genproclimit] processors, -1 is unlimited.	 N
	// TODO signmessage	 <bitcoinaddress> <message>	 Sign a message with the private key of an address.	 Y
	// TODO signrawtransaction	 <hexstring> [{"txid":txid,"vout":n,"scriptPubKey":hex},...] [<privatekey1>,...]	version 0.7 Adds signatures to a raw transaction and returns the resulting raw transaction.	 Y/N
	// TODO settxfee	 <amount>	 <amount> is a real and is rounded to the nearest 0.00000001	 N
	// TODO stop		 Stop bitcoin server.	 N
	// TODO validateaddress	 <bitcoinaddress>	 Return information about <bitcoinaddress>.	 N
	// TODO verifymessage	 <bitcoinaddress> <signature> <message>	 Verify a signed message.	 N
	// TODO walletlock		 Removes the wallet encryption key from memory, locking the wallet. After calling this method, you will need to call walletpassphrase again before being able to call any methods which require the wallet to be unlocked.	 N
	// TODO walletpassphrase	 <passphrase> <timeout>	 Stores the wallet decryption key in memory for <timeout> seconds.	 N


	/**
	 * Changes the wallet passphrase from <code>oldpassphrase</code> to <code>newpassphrase</code>.
	 * 
	 * @param oldPassPhrase
	 * @param newPassPhrase
	 * @return 
	 */
	public VoidResponse walletPassPhraseChange(String oldPassPhrase, String newPassPhrase) {
		List<String> params = newArrayList();
		params.add(oldPassPhrase);
		params.add(newPassPhrase);
		return jsonRpc("walletpassphrasechange", params, VoidResponse.class);
	}


	/**
	 * Performs a JSON-RPC call specifying the given mathod and parameters and returning a response of the given type.
	 * 
	 * @param method
	 * @param params
	 * @param responseType
	 * @return
	 */
	private <T> T jsonRpc(String method, List<?> params, Class<T> responseType) {
		String request = (new BasicDBObject())
				.append("jsonrpc", "2.0")
				.append("method", method)
				.append("params", params)
				.toString();

		//		
		//		ObjectMapper om = new ObjectMapper();
		//		try {
		//			ObjectNode root = ((ObjectNode) om.readTree("{}")).put("jsonrpc", "2.0");
		//			root.put("method", method);
		//			ArrayNode paramsArrayNode = root.putArray("params");
		//			for (Object param : params) {
		//				paramsArrayNode.add((String)param);
		//			}
		//			request = om.writeValueAsString(root);
		//		} catch (IOException e) {
		//			throw new RuntimeException(e);
		//		}

		T response= null;
		try  {
			response = rest.postForObject(BITCOIND_URL, request, responseType);
		} catch (HttpStatusCodeException e) {
			System.out.println(e.getResponseBodyAsString());
			e.printStackTrace();
		}
		return response;
	}


	public static void main(String[] args) {
		new BitcoinClient().run();
	}

	private void run() {
		try {
//				DumpPrivateKeyResponse dumpPrivateKeyResponse = dumpPrivateKey("mj3QxNUyp4Ry2pbbP19tznUAAPqFvDbRFq");
//				print(dumpPrivateKeyResponse);
		//		GetAccountResponse getAccountResponse = getAccount("mj3QxNUyp4Ry2pbbP19tznUAAPqFvDbRFq");
		//		print(getAccountResponse);
		//			GetInfoResponse info = getInfo();
		//			print(info);

//		VoidResponse walletPassPhraseChangeResponse = walletPassPhraseChange("", "boo");
//		print(walletPassPhraseChangeResponse);
		} catch (BitcoinJsonRpcException e) {
			print( e.getErrorResponse());
			e.printStackTrace();
		}
	}

	private void print(Object o) {
		ObjectMapper om = new ObjectMapper();
		try {
			System.out.println(om.writeValueAsString(o));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
