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

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dk.clanie.bitcoin.client.response.ListUnspentResponse;
import dk.clanie.bitcoin.exception.BitcoinException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BitcoindClientDefaultConfig.class})
public class BitcoindClientIntegrationTest {

	@Autowired
	private BitcoindClient bc;

	@Test
	public void test() {
		try {

			//			VoidResponse backupWallet = backupWallet("C:\\wallet.backup");
			//			print(backupWallet);

			//			AddressAndAmount aaa = new AddressAndAmount("mj3QxNUyp4Ry2pbbP19tznUAAPqFvDbRFq", BigDecimal.valueOf(100000000L, 8));
			//			List<TransactionOutputRef> txOuts = newArrayList();
			//			txOuts.add(new TransactionOutputRef("280acc1c3611fee83331465c715b0da2d10b65733a688ee2273fdcc7581f149b", 0));
			//			CreateRawTransactionResponse createRawTransactionResponse = bc.createRawTransaction(txOuts, aaa, aaa);
			//			print(bc.decodeRawTransaction(createRawTransactionResponse.getResult()));

			//			DecodeRawTransactionResponse decodeRawTransactionResponse = decodeRawTransaction(createRawTransactionResponse.getResult());
			//			print(decodeRawTransactionResponse);
			//			System.out.println(decodeRawTransactionResponse);

			//			List<String> keys = newArrayList();
			//			keys.add("mj3QxNUyp4Ry2pbbP19tznUAAPqFvDbRFq");
			//			AddMultiSigAddressResponse addMultiSigAddress = addMultiSigAddress(1, keys, "ACCOUNT1");
			//			print(addMultiSigAddress);

			//			DumpPrivateKeyResponse dumpPrivateKeyResponse = dumpPrivateKey("mj3QxNUyp4Ry2pbbP19tznUAAPqFvDbRFq");
			//			print(dumpPrivateKeyResponse);

			//			StringResponse encryptWalletResponse = bc.encryptWallet("popidop");
			//			print(encryptWalletResponse);

			//			GetAccountResponse getAccountResponse = getAccount("mj3QxNUyp4Ry2pbbP19tznUAAPqFvDbRFq");
			//			print(getAccountResponse);

			//			GetInfoResponse info = getInfo();
			//			print(info);

			//			StringResponse helpResponse = bc.help("listunspent");
			//			print(helpResponse);

			ListUnspentResponse listUnspentResponse = bc.listUnspent(0, 999999, "mj3QxNUyp4Ry2pbbP19tznUAAPqFvDbRFq", "mprSidR7coMDYzfnTXdq6taxDZyEb3fopo");
			print(listUnspentResponse);

			//			VoidResponse walletPassPhraseResponse = bc.walletPassPhrase("popidop", 99999999);
			//			print(walletPassPhraseResponse);

			//			VoidResponse walletLockResponse = bc.walletLock();
			//			print(walletLockResponse);

			//			VoidResponse walletPassPhraseChangeResponse = bc.walletPassPhraseChange("boo", "popidop");
			//			print(walletPassPhraseChangeResponse);

		} catch (BitcoinException e) {
			System.out.println("Error response:");
			print(e.getErrorResponse());
			e.printStackTrace();
		} catch (Exception e) {
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
