package dk.clanie.bitcoin.client.response;

import org.springframework.roo.addon.javabean.RooJavaBean;

import dk.clanie.core.BaseClass;

@SuppressWarnings("serial")
@RooJavaBean(settersByDefault = false)
public class BitcoindJsonRpcError extends BaseClass {

	private Integer code;
	private String message;
	
}
