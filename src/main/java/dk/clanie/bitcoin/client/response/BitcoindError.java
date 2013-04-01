package dk.clanie.bitcoin.client.response;

import org.springframework.roo.addon.javabean.RooJavaBean;

import dk.clanie.core.BaseClass;

/**
 * Error code and -message.
 * 
 * @author Claus Nielsen
 */
@SuppressWarnings("serial")
@RooJavaBean(settersByDefault = false)
public class BitcoindError extends BaseClass {

	private Integer code;
	private String message;
	
}
