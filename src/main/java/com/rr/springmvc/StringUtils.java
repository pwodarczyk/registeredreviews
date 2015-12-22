package com.rr.springmvc;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
public class StringUtils {
	  
  public static boolean isNullOrEmpty(String str) {
      
      if (str == null || str.trim().equals("") || str.trim().equals("null")) {
          return true;
      } 
      return false;
  }
   public static boolean isNullOrEmpty(Object obj) {
      
      if(obj == null) return true;
      return isNullOrEmpty(obj.toString());
  }
   
   public static String formatDecimal(double d,String pattern){
       java.text.DecimalFormat nf= new java.text.DecimalFormat(pattern);
       return nf.format(new Double(d));
   }
   
   public static String dateFormat(java.util.Date date, String pattern)
   {
       if(date == null)
       {
           return "";
       } else
       {
           SimpleDateFormat simpledateformat = new SimpleDateFormat(pattern);
           return simpledateformat.format(date);
       }
   }
   
   public static Date parseDate(String s, String fomat)
   {
       if(s == null)
       {
           return null;
       } else
       {
           SimpleDateFormat simpledateformat = new SimpleDateFormat(fomat);
           Date dt= null;
           try{
               dt = simpledateformat.parse(s);
           }catch(Exception ex){}
           return dt;
       }
   }
   
   public static String arrayToString(String[] a, String separator) {
	    
	    return join(a,separator);
	 }
   public static String join(String[] a, String separator) {
	   if (a == null || separator == null) {
	        return null;
	    }
	    StringBuffer result = new StringBuffer();
	    if (a.length > 0) {
	        result.append(a[0]);
	        for (int i=1; i < a.length; i++) {
	            result.append(separator);
	            result.append(a[i]);
	        }
	    }
	    return result.toString();
   }
  
  
private static java.util.Random random = null;
  
  public static String generatePassword(int charLen){
	  if(charLen <= 0)
		  charLen = 8; //default to 8
	  if(random==null){
			try{
				//Provided By sun, it will cause a lot garbage collection when startup
			    //random = java.security.SecureRandom.getInstance("SHA1PRNG");
				//random = new FastRandom();
				random = new java.util.Random();//use classic one which is fastter
			}catch(Exception ex){
			    System.out.println(ex.toString());
			}
	  }
	  byte charBytes[] = new byte[charLen];
      
      for(int i = 0; i < charLen; i++){
          charBytes[i] = LETTER_AND_NUMBERS[(random.nextInt(128)) % 64];
      }

      return new String(charBytes);
  }

  public static String generatePassword(){
	  return generatePassword(-1);
  }
  private static final byte LETTER_AND_NUMBERS[] = {
      65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 
      75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 
      85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 
      101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 
      111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 
      121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 
      56, 57, 50, 49
  };
  
  
		  
    public static boolean isValidPhone(String phone) {
		if(phone!=null)
			return phone.matches("^\\(?(\\d{3})\\)?[- \\.]?(\\d{3})[- \\.]?(\\d{4})$");
		return false;
	}
    /*
     *removes -_(). and spaces from given string
     * 
     */
    public static String stripToPhoneNumber(String phone) {
		if(phone!=null){
			phone = phone.replace("-", "");
			phone = phone.replace("_", "");
			phone = phone.replace("(", "");
			phone = phone.replace(")", "");
			phone = phone.replace(".", "");
			phone = phone.replace(" ", "");
		}
		return phone;
	}
    /*
     * returns XXX-XXX-XXXX with given XXXXXXXXXX format(10 digits)
     * call stripToPhoneNumber to get this format.
     * 
     */
    public static String getFormattedPhoneNumber(String phoneNumber){
	    if(phoneNumber!=null&& phoneNumber.length()==10){
			char[] digs= phoneNumber.toCharArray();
			// quick fix for the fact that it was doing ascii decimal value addition on the first 3 digits
			phoneNumber=""+digs[0]+digs[1]+digs[2]+"-"+digs[3]+digs[4]+digs[5]+"-"+digs[6]+digs[7]+digs[8]+digs[9];
		}
	    return phoneNumber;
    }
    
	public static boolean isValidEmail(String email) {
		if(email!=null)
			return email.matches("^[\\w\\.-]+@([\\w\\-]+\\.)+[a-zA-Z]{2,4}$");
		return false;
	}
	
	
	public static String truncate(String input,int maxLength){
		if(input == null)
			return null;
		if(maxLength >0)
			input = input.length() > maxLength ? input.substring(0,maxLength) : input;
		return input;
	}
	
	public static String md5(String message)
	  {
	    String digest = "";
	    MessageDigest md5 = null;
	    try
	    {
	      md5 = MessageDigest.getInstance("MD5");
	    }
	    catch (Exception ex)
	    {
	      ex.printStackTrace();
	      
	    }
	    byte[] dig = md5.digest((byte[]) message.getBytes());
	    StringBuffer code = new StringBuffer();
	    for (int i = 0; i < dig.length; ++i)
	    {
	      code.append(Integer.toHexString(0x0100 + (dig[i] & 0x00FF)).substring(1));
	    }
	    return code.toString();

	    //System.out.println("Message: " + message);
	    //System.out.println("Digest: " + digest);

	  }
	
}