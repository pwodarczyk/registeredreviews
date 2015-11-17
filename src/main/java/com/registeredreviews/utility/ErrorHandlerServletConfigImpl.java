package com.registeredreviews.utility;

public class ErrorHandlerServletConfigImpl implements ErrorHandlerServletConfig {

	private String emailToAddress;
	private String emailFromAddres;
	
	private String redirectPageDefault;
	private String redirectPage404;
	private String redirectPage500;
	
	
	/**
	 * @return the emailToAddress
	 */
	public String getEmailToAddress() {
		return emailToAddress;
	}
	
	/**
	 * @param emailToAddress the emailToAddress to set
	 */
	public void setEmailToAddress( String emailToAddress ) {
		this.emailToAddress = emailToAddress;
	}
	
	/**
	 * @return the emailFromAddres
	 */
	public String getEmailFromAddres() {
		return emailFromAddres;
	}
	
	/**
	 * @param emailFromAddres the emailFromAddres to set
	 */
	public void setEmailFromAddres( String emailFromAddres ) {
		this.emailFromAddres = emailFromAddres;
	}
	
	/**
	 * @return the redirectPageDefault
	 */
	public String getRedirectPageDefault() {
		return redirectPageDefault;
	}
	
	/**
	 * @param redirectPageDefault the redirectPageDefault to set
	 */
	public void setRedirectPageDefault( String redirectPageDefault ) {
		this.redirectPageDefault = redirectPageDefault;
	}
	
	/**
	 * @return the redirectPage404
	 */
	public String getRedirectPage404() {
		return redirectPage404;
	}
	
	/**
	 * @param redirectPage404 the redirectPage404 to set
	 */
	public void setRedirectPage404( String redirectPage404 ) {
		this.redirectPage404 = redirectPage404;
	}
	
	/**
	 * @return the redirectPage500
	 */
	public String getRedirectPage500() {
		return redirectPage500;
	}
	
	/**
	 * @param redirectPage500 the redirectPage500 to set
	 */
	public void setRedirectPage500( String redirectPage500 ) {
		this.redirectPage500 = redirectPage500;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailFromAddres == null) ? 0 : emailFromAddres.hashCode());
		result = prime * result + ((emailToAddress == null) ? 0 : emailToAddress.hashCode());
		result = prime * result + ((redirectPage404 == null) ? 0 : redirectPage404.hashCode());
		result = prime * result + ((redirectPage500 == null) ? 0 : redirectPage500.hashCode());
		result = prime * result + ((redirectPageDefault == null) ? 0 : redirectPageDefault.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj ) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ErrorHandlerServletConfigImpl other = (ErrorHandlerServletConfigImpl) obj;
		if (emailFromAddres == null) {
			if (other.emailFromAddres != null) {
				return false;
			}
		} else if (!emailFromAddres.equals( other.emailFromAddres )) {
			return false;
		}
		if (emailToAddress == null) {
			if (other.emailToAddress != null) {
				return false;
			}
		} else if (!emailToAddress.equals( other.emailToAddress )) {
			return false;
		}
		if (redirectPage404 == null) {
			if (other.redirectPage404 != null) {
				return false;
			}
		} else if (!redirectPage404.equals( other.redirectPage404 )) {
			return false;
		}
		if (redirectPage500 == null) {
			if (other.redirectPage500 != null) {
				return false;
			}
		} else if (!redirectPage500.equals( other.redirectPage500 )) {
			return false;
		}
		if (redirectPageDefault == null) {
			if (other.redirectPageDefault != null) {
				return false;
			}
		} else if (!redirectPageDefault.equals( other.redirectPageDefault )) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "ErrorHandlerServletConfigImpl [emailToAddress=" );
		builder.append( emailToAddress );
		builder.append( ", emailFromAddres=" );
		builder.append( emailFromAddres );
		builder.append( ", redirectPageDefault=" );
		builder.append( redirectPageDefault );
		builder.append( ", redirectPage404=" );
		builder.append( redirectPage404 );
		builder.append( ", redirectPage500=" );
		builder.append( redirectPage500 );
		builder.append( "]" );
		return builder.toString();
	}
	
	
	
			
}
