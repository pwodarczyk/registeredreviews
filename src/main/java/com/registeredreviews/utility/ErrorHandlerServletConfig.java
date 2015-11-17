package com.registeredreviews.utility;

public interface ErrorHandlerServletConfig {

	public static final String	SPRING_KEY	= "error.handler.servlet-configbean";
	
	/**
	 * @return the emailToAddress
	 */
	public String getEmailToAddress();

	
	/**
	 * @return the emailFromAddres
	 */
	public String getEmailFromAddres();
	
	
	/**
	 * @return the redirectPageDefault
	 */
	public String getRedirectPageDefault();
	
	
	/**
	 * @return the redirectPage404
	 */
	public String getRedirectPage404();
	
	
	/**
	 * @return the redirectPage500
	 */
	public String getRedirectPage500();
	
	
}
