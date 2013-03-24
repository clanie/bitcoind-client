package dk.clanie.bitcoin.client.response;

import org.springframework.roo.addon.javabean.RooJavaBean;

@RooJavaBean(settersByDefault = false)
public class BitcoinJsonRpcError {

	private Integer code;
	private String message;
	
}
