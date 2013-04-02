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

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.ObjectMapper;

import dk.clanie.bitcoin.AddressAndAmount;
import dk.clanie.bitcoin.TransactionOutputRef;
import dk.clanie.bitcoin.client.response.DecodeRawTransactionResponse;
import dk.clanie.bitcoin.client.response.GetInfoResponse;
import dk.clanie.bitcoin.client.response.GetMiningInfoResponse;
import dk.clanie.bitcoin.client.response.GetTransactionResponse;
import dk.clanie.bitcoin.client.response.ListReceivedByAccountResponse;
import dk.clanie.bitcoin.client.response.ListReceivedByAddressResponse;
import dk.clanie.bitcoin.client.response.ListUnspentResponse;
import dk.clanie.bitcoin.client.response.StringResponse;
import dk.clanie.bitcoin.client.response.VoidResponse;


/**
 * Test against running bitcoind.
 * <p>
 * The tests in this class are meant to be tweaked to match a running
 * bitcoind and the addresses in it, and to be executed manually.
 * <p>
 * They are <i>not</i> executed as part of the build or other
 * automated test.
 * 
 * @author Claus Nielsen
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BitcoindClientDefaultConfig.class})
public class BitcoindClientIntegrationTest {

	@Autowired
	private BitcoindClient bc;

	private ObjectMapper om = new ObjectMapper();


	@Test
	public void testBackupWallet() throws Exception {
		VoidResponse backupWallet = bc.backupWallet("C:\\wallet.backup");
		print(backupWallet);
	}


	@Test
	public void testCreateRawTransaction_and_decodeRawTransaction() throws Exception {
		AddressAndAmount aaa = new AddressAndAmount("mj3QxNUyp4Ry2pbbP19tznUAAPqFvDbRFq", BigDecimal.valueOf(100000000L, 8));
		List<TransactionOutputRef> txOuts = newArrayList();
		txOuts.add(new TransactionOutputRef("280acc1c3611fee83331465c715b0da2d10b65733a688ee2273fdcc7581f149b", 0));
		StringResponse createRawTransactionResponse = bc.createRawTransaction(txOuts, aaa, aaa);

		DecodeRawTransactionResponse decodeRawTransactionResponse = bc.decodeRawTransaction(createRawTransactionResponse.getResult());
		print(decodeRawTransactionResponse);
		System.out.println(decodeRawTransactionResponse);
	}


	@Test
	public void testAddMultiSigAddress() throws Exception {
		List<String> keys = newArrayList();
		keys.add("mj3QxNUyp4Ry2pbbP19tznUAAPqFvDbRFq");
		StringResponse addMultiSigAddress = bc.addMultiSigAddress(1, keys, "ACCOUNT1");
		print(addMultiSigAddress);
	}


	@Test
	public void testAddNode() {
		// TODO
	}


	@Test
	public void testDumpPrivateKey() throws Exception {
//		StringResponse dumpPrivateKeyResponse = bc.dumpPrivateKey("mj3QxNUyp4Ry2pbbP19tznUAAPqFvDbRFq");
		StringResponse dumpPrivateKeyResponse = bc.dumpPrivateKey("mof5U4zusfjigWYwwjf6c88Qn77KEafStx");
		print(dumpPrivateKeyResponse);
	}


	@Test
	public void testEncryptWallet() throws Exception {
		VoidResponse encryptWalletResponse = bc.encryptWallet("popidop");
		print(encryptWalletResponse);
	}


	@Test
	public void testGetAccount() throws Exception {
//		StringResponse getAccountResponse = bc.getAccount("mj3QxNUyp4Ry2pbbP19tznUAAPqFvDbRFq");
		StringResponse getAccountResponse = bc.getAccount("mof5U4zusfjigWYwwjf6c88Qn77KEafStx");
		print(getAccountResponse);
	}


	@Test
	public void testGetAccountAddress() throws Exception {
		StringResponse accountAddressResponse = bc.getAccountAddress("cocoo");
		print(accountAddressResponse);
	}


	@Test
	public void testGetAddedNodeInfo() throws Exception {
		// TODO
		// When calling with dns=false an oject is returned; when calling ith dns=true an array is returned.
		StringResponse addedNodeInfoResponse = bc.getAddedNodeInfo(false, null);
		print(addedNodeInfoResponse);
	}


	@Test
	public void testGetInfo() throws Exception {
		GetInfoResponse info = bc.getInfo();
		print(info);
	}


	@Test
	public void testGetMiningInfo() throws Exception {
		GetMiningInfoResponse info = bc.getMiningInfo();
		print(info);
	}


	@Test
	public void testGetTransaction() throws Exception {
		GetTransactionResponse transactionResponse = bc.getTransaction("280acc1c3611fee83331465c715b0da2d10b65733a688ee2273fdcc7581f149b");
		print(transactionResponse);
	}


	@Test
	public void testHelp() throws Exception {
		StringResponse helpResponse = bc.help("getaccountaddress");
		print(helpResponse);
	}


	@Test
	public void testImportPrivateKey() throws Exception {
		VoidResponse importPrivateKeyResponse = bc.importPrivateKey("cV48j141Jf5nAdEftRxRbGGXGpzDixw94aDjJBYniidUAPbAQZfB", null, true);
		print(importPrivateKeyResponse);
	}


	@Test
	public void testListReceivedByAccount() throws Exception {
		ListReceivedByAccountResponse listReceivedByAccountResponse = bc.listReceivedByAccount(0, true);
		print(listReceivedByAccountResponse);
	}


	@Test
	public void testListReceivedByAddress() throws Exception {
		ListReceivedByAddressResponse listReceivedByAddressResponse = bc.listReceivedByAddress(0, true);
		print(listReceivedByAddressResponse);
	}


	@Test
	public void testListUnspent() throws Exception {
		ListUnspentResponse listUnspentResponse = bc.listUnspent(0, 999999, "mj3QxNUyp4Ry2pbbP19tznUAAPqFvDbRFq", "mprSidR7coMDYzfnTXdq6taxDZyEb3fopo");
		print(listUnspentResponse);
	}


	@Test
	public void testWalletPassPhrase() throws Exception {
		VoidResponse walletPassPhraseResponse = bc.walletPassPhrase("popidop", 99999999);
		print(walletPassPhraseResponse);
	}


	@Test
	public void testWalletLock() throws Exception {
		VoidResponse walletLockResponse = bc.walletLock();
		print(walletLockResponse);
	}


	@Test
	public void testWalletPassPhraseChange() throws Exception {
		VoidResponse walletPassPhraseChangeResponse = bc.walletPassPhraseChange("tiotop", "popidop");
		print(walletPassPhraseChangeResponse);
	}


	/**
	 * Prints the given object as json.
	 * 
	 * @param o
	 *            - object to print.
	 * @throws Exception
	 */
	private void print(Object o) throws Exception {
		System.out.println(om.writeValueAsString(o));
	}


}
