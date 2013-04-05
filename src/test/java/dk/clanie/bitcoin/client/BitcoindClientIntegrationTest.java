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
import dk.clanie.bitcoin.client.request.AddNodeAction;
import dk.clanie.bitcoin.client.response.BooleanResponse;
import dk.clanie.bitcoin.client.response.DecodeRawTransactionResponse;
import dk.clanie.bitcoin.client.response.GetInfoResponse;
import dk.clanie.bitcoin.client.response.GetMiningInfoResponse;
import dk.clanie.bitcoin.client.response.GetTransactionResponse;
import dk.clanie.bitcoin.client.response.ListReceivedByAccountResponse;
import dk.clanie.bitcoin.client.response.ListReceivedByAddressResponse;
import dk.clanie.bitcoin.client.response.ListUnspentResponse;
import dk.clanie.bitcoin.client.response.SignRawTransactionResponse;
import dk.clanie.bitcoin.client.response.StringResponse;
import dk.clanie.bitcoin.client.response.ValidateAddressResponse;
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
	public void testCreateRawTransaction() throws Exception {
		AddressAndAmount aaa = new AddressAndAmount("mj3QxNUyp4Ry2pbbP19tznUAAPqFvDbRFq", BigDecimal.valueOf(100000000L, 8));
		List<TransactionOutputRef> txOuts = newArrayList();
		txOuts.add(new TransactionOutputRef("280acc1c3611fee83331465c715b0da2d10b65733a688ee2273fdcc7581f149b", 0));
		StringResponse createRawTransactionResponse = bc.createRawTransaction(txOuts, aaa, aaa);
		print(createRawTransactionResponse);

	}


	@Test
	public void testDecodeRawTransaction() throws Exception {
		DecodeRawTransactionResponse decodeRawTransactionResponse = bc.decodeRawTransaction("01000000019b141f58c7dc3f27e28e683a73650bd1a20d5b715c463133e8fe11361ccc0a280000000000ffffffff0100c2eb0b000000001976a91426ab1c83e2a8269b7007baf0244151cca4c5e3fd88ac00000000");
		print(decodeRawTransactionResponse);
	}


	@Test
	public void testDecodeRawTransaction_signedTransaction() throws Exception {
		DecodeRawTransactionResponse decodeRawTransactionResponse = bc.decodeRawTransaction("01000000019b141f58c7dc3f27e28e683a73650bd1a20d5b715c463133e8fe11361ccc0a28000000006a473044022011a55030de6225d16b0f0c8854a324cbbbf0f9ef92d1b0b18696b403d7c3ccbc0220331ad3f476ee016849185138e68ba33d29a684f0ec014cd7f05e3d406412b4c4012103b72d2e7dcf317a8d26e64172e80ac88754e31dad59ec25c2fbfdb082f0288aa6ffffffff0100c2eb0b000000001976a91426ab1c83e2a8269b7007baf0244151cca4c5e3fd88ac00000000");
		print(decodeRawTransactionResponse);
	}


	@Test
	public void testAddMultiSigAddress() throws Exception {
		List<String> keys = newArrayList();
		keys.add("mj3QxNUyp4Ry2pbbP19tznUAAPqFvDbRFq");
		StringResponse addMultiSigAddress = bc.addMultiSigAddress(1, keys, "ACCOUNT1");
		print(addMultiSigAddress);
	}


	@Test
	public void testAddNode() throws Exception {
		VoidResponse addNode = bc.addNode("faucet.bitcoin.st", AddNodeAction.ONE_TRY);
		print(addNode);
	}


	@Test
	public void testDumpPrivateKey() throws Exception {
		StringResponse dumpPrivateKeyResponse = bc.dumpPrivateKey("mxphxiG4Ggjb3bKFbeFnsuK2qde3541S93");
		//		StringResponse dumpPrivateKeyResponse = bc.dumpPrivateKey("mof5U4zusfjigWYwwjf6c88Qn77KEafStx");
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
		GetTransactionResponse transactionResponse = bc.getTransaction("9e8485aed75a0e0c8b1bbcda5f3e1426a7da914cb5732f73dd8bd6128344a608");
		print(transactionResponse);
	}


	@Test
	public void testHelp() throws Exception {
		StringResponse helpResponse = bc.help("verifymessage");
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
	public void testSendToAddress() throws Exception {
		StringResponse sendToAddress = bc.sendToAddress("mwswEtw6t2ziSjsfip62FPg84NXGsJ5H2o", BigDecimal.valueOf(0.01d), "Comment", "CommentTO");
		print(sendToAddress);
	}


	@Test
	public void testSetAccount() throws Exception {
		VoidResponse setAccount = bc.setAccount("mj3QxNUyp4Ry2pbbP19tznUAAPqFvDbRFq", "clanie");
		print(setAccount);
	}


	@Test
	public void testSetGenerate() throws Exception {
		VoidResponse setGenerate = bc.setGenerate(false, null);
		print(setGenerate);
	}


	@Test
	public void testSignMessage() throws Exception {
		StringResponse signMessage = bc.signMessage("mj3QxNUyp4Ry2pbbP19tznUAAPqFvDbRFq", "We love Bitcoin");
		print(signMessage);
	}


	@Test
	public void testSignRawTransaction() throws Exception {
		SignRawTransactionResponse signRawTransaction = bc.signRawTransaction("01000000019b141f58c7dc3f27e28e683a73650bd1a20d5b715c463133e8fe11361ccc0a280000000000ffffffff0100c2eb0b000000001976a91426ab1c83e2a8269b7007baf0244151cca4c5e3fd88ac00000000", null, null, null);
		print(signRawTransaction);
	}


	@Test
	public void testSetTxFee() throws Exception {
		BooleanResponse setTxFeeResponse = bc.setTxFee(BigDecimal.valueOf(0.00001d));
		print(setTxFeeResponse);
	}


	@Test
	public void testStop() throws Exception {
		VoidResponse stopResponse = bc.stop();
		print(stopResponse);
	}


	@Test
	public void testValidateAddress() throws Exception {
		ValidateAddressResponse validateAddressResponse = bc.validateAddress("mj3QxNUyp4Ry2pbbP19tznUAAPqFvDbRFq");
		print(validateAddressResponse);
	}


	@Test
	public void testValidateAddress_invalid() throws Exception {
		ValidateAddressResponse validateAddressResponse = bc.validateAddress("2j3QxNUyp4Ry2pbbP19tznUAAPqFvDbRFq");
		print(validateAddressResponse);
	}


	@Test
	public void testVerifyMessage() throws Exception {
		BooleanResponse verifyMessage = bc.verifyMessage("mj3QxNUyp4Ry2pbbP19tznUAAPqFvDbRFq", "IPmHnxzFa8bKD0Tt/0uT+3ak+8g+ToxEhivc49ciJgA3wuQWSMyc2OdTL/AooRXQ7qtCMkp4NXZ/dw0vBI6fPAs=", "We love Bitcoin");
		print(verifyMessage);
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
