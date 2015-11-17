package com.registeredreviews.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

public class Util {
	public static final long DAY = 1000 * 60 * 60 * 24;
	private static String SyncFlag = "N";
	private static String _hostname = null;
	public static boolean DEBUG = false;
	private static String _ip = null;

	public static void setSyncFlag(String s) {
		synchronized (SyncFlag) {
			SyncFlag = s;
		}
	}

	public static String getSyncFlag() {
		return SyncFlag;
	}

	private static final String NO_DATA = "NO DATA";

	public static String filter(String str) {

		if (isNull(str)) {
			return NO_DATA;
		}
		return str;
	}

	public static boolean checkRequired(String fld, HttpServletRequest request) {
		if (!isNull(request.getParameter(fld))) {
			return true;
		}
		return false;
	}

	public static String flagRequired(boolean flagProcess, String fld,
			HttpServletRequest request) {
		if (flagProcess && isNull(request.getParameter(fld))) {
			return " class='formfieldRedBold' ";
		}
		return " class='formfield' ";
	}

	public static String flagSelection(boolean flagProcess, String fld,
			String value, HttpServletRequest request) {
		if (fld == null || value == null)
			return "";
		if (flagProcess && !isNull(request.getParameter(fld))) {
			return value.equals(request.getParameter(fld)) ? " selected " : "";
		}
		return "";
	}

	public static String flagRadio(boolean flagProcess, String fld,
			String value, HttpServletRequest request) {
		if (fld == null || value == null)
			return "";
		if (flagProcess && !isNull(request.getParameter(fld))) {
			return value.equals(request.getParameter(fld)) ? " checked " : "";
		}
		return "";
	}

	public static String printValue(boolean flagProcess, String fld,
			HttpServletRequest request) {
		if (flagProcess && !isNull(request.getParameter(fld))) {
			return sanitizeInput(request.getParameter(fld));
		}
		return "";
	}

	public static String printValue(Object value) {
		if (!isNull(value)) {
			return sanitizeInput(value.toString());
		}
		return "";
	}

	public static String sanitizeInput(String value) {
		if (!isNull(value)) {
			String s = replace(value, "\"", "&quot;");
			s = replace(s, "<", "&lt;");
			s = replace(s, "<", "&lt;");
			s = replace(s, "'", "&apos;");
			// s = replace(s,"&","&amp;");
			return s;
		}
		return "";
	}

	public static String encodeStr(String str) {
		if (str == null)
			return null;
		try {
			String s = new sun.misc.BASE64Encoder()
					.encode(str.getBytes("UTF8"));
			s = replace(s, "\n", "");
			s = replace(s, "\r", "");
			return s;
		} catch (Exception ex) {
		}
		return null;
	}

	public static String decodeStr(String str) {
		if (str == null)
			return null;
		try {
			String fs = replace(str, " ", "");
			return new String(new sun.misc.BASE64Decoder().decodeBuffer(fs),
					"UTF8");
		} catch (Exception ex) {

		}
		return null;
	}

	public static String includeUrl(String uri) {
		if (uri == null)
			return "";
		java.net.URL url1 = null;
		java.net.URLConnection htmlConnection1 = null;
		BufferedReader dis = null;
		try {
			url1 = new java.net.URL(uri);
			htmlConnection1 = url1.openConnection();
			StringBuffer buf = new StringBuffer();
			String inputLine;
			dis = new java.io.BufferedReader(new java.io.InputStreamReader(
					htmlConnection1.getInputStream()));
			while ((inputLine = dis.readLine()) != null) {
				buf.append(inputLine + "\n");
				// System.out.println(inputLine);
			}
			return buf.toString();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		}finally {
            try {
                if (dis != null) {
                    dis.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
		return "";
	}

	public static boolean isNull(String str) {

		if (str == null || str.trim().equals("")
				|| str.trim().equalsIgnoreCase("null")) {
			return true;
		}
		return false;
	}

	public static boolean isNull(Object obj) {

		if (obj == null)
			return true;
		return isNull(obj.toString());
	}

	public static boolean isNull2(String str) {

		if (str == null || str.trim().equals("")
				|| str.trim().equalsIgnoreCase("null")
				|| str.trim().equalsIgnoreCase("na")
				|| str.trim().equalsIgnoreCase("n/a")) {
			return true;
		}
		return false;
	}

	public static boolean isNull2(Object obj) {

		if (obj == null)
			return true;
		return isNull2(obj.toString());
	}

	public static boolean isNullOrEmpty(Object obj) {
		return isNull(obj);
	}

	public static boolean isNullOrEmpty(String str) {
		return isNull(str);
	}

	public static boolean isNullOrEmpty2(Object obj) {
		return isNull2(obj);
	}

	public static boolean isNullOrEmpty2(String str) {
		return isNull2(str);
	}

	public static void debug(boolean debug, Object obj) {
		if (debug)
			System.out.println(obj);
	}

	public static String[] tokenToArray(String s, String delim) {
		if (s == null)
			return null;
		java.util.StringTokenizer st = new java.util.StringTokenizer(s, delim);
		Vector vector = new Vector();
		String t = null;
		while (st.hasMoreTokens()) {
			t = st.nextToken();
			vector.addElement(t);
			// LogUtil.log("elemt="+t);
		}
		String[] rt = new String[vector.size()];
		vector.copyInto(rt);
		return rt;
	}

	public static String replace(String string, String from, String to) {
		if (string == null || from == null || from.equals("") || to == null)
			return string;
		StringBuffer buf = new StringBuffer(2 * string.length());
		int previndex = 0;
		int index = 0;
		int flen = from.length();
		while (true) {
			index = string.indexOf(from, previndex);
			if (index == -1) {
				buf.append(string.substring(previndex));
				break;
			}
			buf.append(string.substring(previndex, index) + to);
			previndex = index + flen;
		}
		return buf.toString();
	}

	public static String includeFile(String uri) {
		if (uri == null)
			return "";
		java.io.FileInputStream fis = null;
		BufferedReader br = null;
		try {
			StringBuffer buf = new StringBuffer();
			fis = new java.io.FileInputStream(uri);
			String line = null;
			br = new BufferedReader(new InputStreamReader(fis));
			while ((line = br.readLine()) != null) {
				buf.append(line);
			}
			return buf.toString();
		} catch (Exception ex) {
			ex.printStackTrace(System.out);
		} finally {
			try {
				if (br != null)
					br.close();
				if (fis != null)
					fis.close();
			} catch (Exception ex) {
			}
		}
		return "";
	}

	public static String getSeqNum(long num) {
		long base = 1000000;
		if ((base + num) >= base * 2) {
			return num + "";
		} else {
			return "0" + ("" + (base + num)).substring(1);
		}
	}

	public static String genUId() {
		if (random == null) {
			try {
				random = new java.util.Random();// use classic one which is
												// fastter
			} catch (Exception ex) {
				System.out.println(ex.toString());
			}
		}
		int charLen = 10;
		byte charBytes[] = new byte[charLen];
		for (int i = 0; i < charLen; i++) {
			charBytes[i] = LETTER_AND_NUMBERS[(random.nextInt(128)) % 64];
		}
		return new String(charBytes);
	}
	
	public static String genUId(int  charLen) {
		if (random == null) {
			try {
				random = new java.util.Random();// use classic one which is
												// fastter
			} catch (Exception ex) {
				System.out.println(ex.toString());
			}
		}
		if(charLen <=0)
			charLen=32;
		byte charBytes[] = new byte[charLen];
		for (int i = 0; i < charLen; i++) {
			charBytes[i] = LETTER_AND_NUMBERS[(random.nextInt(128)) % 64];
		}
		return new String(charBytes);
	}

	public static String join(Object[] objs, String token) {
		if (objs == null)
			return null;
		String s = null;
		for (int i = 0; i < objs.length; i++) {
			if (i == 0)
				s = objs[i].toString();
			else
				s = s + "|" + objs[i].toString();
		}
		return s;
	}

	public static String formatPrice(double d) {
		return formatDecimal(d, "#,##0.00");
	}

	public static String formatDecimalObject(Object obj) {

		if (obj == null)
			return "";
		try {
			double d = Double.parseDouble(obj.toString());
			return formatDecimal(d, "#,##0.00");
		} catch (Exception ex) {
			//
		}
		return obj.toString();
	}

	public static String formatDecimal(double d) {

		return formatDecimal(d, "#,##0.00");
	}

	public static String formatDecimal(double d, String pattern) {
		java.text.DecimalFormat nf = new java.text.DecimalFormat(pattern);
		return nf.format(new Double(d));
	}

	public static String dateFormatOracle(java.util.Date date) {
		if (date == null) {
			return "";
		} else {
			SimpleDateFormat simpledateformat = new SimpleDateFormat(
					"dd-MMM-yyyy");
			return simpledateformat.format(date);
		}
	}

	public static String dateFormat(java.util.Date date, String pattern) {
		if (date == null) {
			return "";
		} else {
			SimpleDateFormat simpledateformat = new SimpleDateFormat(pattern);
			return simpledateformat.format(date);
		}
	}

	public static Date parseDate(String s, String fomat) {
		if (s == null) {
			return null;
		} else {
			SimpleDateFormat simpledateformat = new SimpleDateFormat(fomat);
			Date dt = null;
			try {
				dt = simpledateformat.parse(s);
			} catch (Exception ex) {
			}
			return dt;
		}
	}

	public static String xmlStrFilter(String inStr) {
		return org.apache.commons.lang.StringEscapeUtils.escapeXml(inStr);
		/*
		String rt = null;
		Character ch = null;
		if (inStr != null) {
			rt = replace(inStr, " & ", " &amp; ");
			rt = replace(rt, "<", "&lt;");
			rt = replace(rt, ">", "&gt;");
			rt = replace(rt, "\"", "&quot;");
			for (int i = 128; i <= 255; i++) {
				ch = new Character((char) i);// &egrave;
				rt = replace(rt, ch.toString(), "&#" + i + ";");
			}
		} else {
			rt = "";
		}
		return rt;
		*/
	}

	private static final byte LETTER_AND_NUMBERS[] = { 65, 66, 67, 68, 69, 70,
			71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87,
			88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107,
			108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120,
			121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 50, 49 };
	static java.util.Random random = null;

	public static String sanitize(String inStr) {
		if (inStr != null) {
			inStr = replace(inStr, "&", "&amp;");
			inStr = replace(inStr, "<", "&lt;");
			inStr = replace(inStr, ">", "&gt;");
			inStr = replace(inStr, "\"", "&quot;");
			inStr = replace(inStr, "#", "&#35;");
			inStr = replace(inStr, "(", "&#40;");
			inStr = replace(inStr, ")", "&#41;");
			inStr = replace(inStr, "\r", ""); // remove carriage return
			inStr = replace(inStr, "\n", ""); // remove line feed
			inStr =inStr.replaceAll("(?i)onblur=", "")
			.replaceAll("(?i)onchange=", "")
			.replaceAll("(?i)onclick=", "")
			.replaceAll("(?i)ondblclick=", "")
			.replaceAll("(?i)onfocus=", "")
			.replaceAll("(?i)onkeydown=", "")
			.replaceAll("(?i)onkeypress=", "")
			.replaceAll("(?i)onkeyup=", "")
			.replaceAll("(?i)onload=", "")
			.replaceAll("(?i)onmousedown=", "")
			.replaceAll("(?i)onmousemove=", "")
			.replaceAll("(?i)onmouseout=", "")
			.replaceAll("(?i)onmouseover=", "")
			.replaceAll("(?i)onmouseup=", "")
			.replaceAll("(?i)onreset=", "")
			.replaceAll("(?i)onselect=", "")
			.replaceAll("(?i)onunload=", "")
			.replaceAll("(?i)Location: http", "")
			.replaceAll("(?i)Document.write", "")
			.replaceAll("(?i)Set-Cookie: ", "");
			return inStr;
		}
		return "";
	}

	public static String getIdNumber(String file, String prefix) {
		if (file == null)
			return null;
		FileChannel fChan = null;
		FileLock lock = null;
		long count_seq = 0;
		String inputLine = null;
		java.io.PrintStream writer = null;
		java.io.FileOutputStream fo = null;
		FileInputStream fis = null;
		BufferedReader dis = null;
		try {
			File f = new File(file);
			if (f.exists()) {
				fis = new FileInputStream(f);
				dis = new BufferedReader(new InputStreamReader(
						fis));
				if ((inputLine = dis.readLine()) != null) {
					String str_seq = inputLine;
					if(inputLine.length() >1)
					    str_seq = inputLine.substring(prefix.length() + 1);
					String str = str_seq;
					while (str.startsWith("0")) {
						str = str.substring(1);
					}
					count_seq = Long.parseLong(str.trim()) + 1;
				}
			} else {
				count_seq = 1;
			}
			String seqnum = getSeqNum(count_seq);
			fo = new java.io.FileOutputStream(file);
			writer = new java.io.PrintStream(fo);
			writer.print(prefix + seqnum);
			return prefix + seqnum;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.flush();
					writer.close();
				}
				if (fo != null) {
					fo.flush();
					fo.close();
				}
				if (lock != null)
					lock.release();
				if (fChan != null)
					fChan.close();
				if (dis != null) {
                    dis.close();
                }
                if (fis != null) {
                    fis.close();
                }
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	public static boolean isNumeric(String num) {
		try {
			Double.parseDouble(num);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static String flagRequired(boolean flagProcess, String fld,
			HttpServletRequest request, boolean conditionedRequired) {
		if (conditionedRequired) {
			return flagRequired(flagProcess, fld, request);
		}
		return " class='formfield' ";
	}

	public static String flagSelection(boolean flagProcess, String fld,
			String value, HttpServletRequest request, String paramin) {
		if (fld == null || value == null)
			return "";
		if (flagProcess && !isNull(request.getParameter(fld))) {
			return value.equals(request.getParameter(fld)) ? " selected " : "";
		} else if (!flagProcess && !isNull(paramin)) {
			return value.equals(paramin) ? " selected " : "";
		}
		return "";
	}

	public static String getMonthName(int month) {
		switch (month) {
		case 1:
			return ("January");
		case 2:
			return ("February");
		case 3:
			return ("March");
		case 4:
			return ("April");
		case 5:
			return ("May");
		case 6:
			return ("June");
		case 7:
			return ("July");
		case 8:
			return ("August");
		case 9:
			return ("September");
		case 10:
			return ("October");
		case 11:
			return ("November");
		case 12:
			return ("December");
		default:
			return ("Illegal month");
		}
	} // method MonthName

	public static String formatIntTo2(int num) {
		// format 00:00:00
		if (num < 10)
			return "0" + num;
		return num + "";
	}

	public static String formatIntTo2(String num) {
		return formatIntTo2(Integer.parseInt(num));
	}

	public static int getSeconds(String s) {
		// format 00:00:00
		int hours = Integer.parseInt(s.substring(0, 2));
		int minutes = Integer.parseInt(s.substring(3, 5));
		int seconds = Integer.parseInt(s.substring(6, 8));
		return hours * 60 * 60 + minutes * 60 + seconds;
	}

	public static String formatSeconds(int secs) {
		// format 00:00:00
		return formatIntTo2((secs / 3600)) + ":" + formatIntTo2((secs / 60))
				+ ":" + formatIntTo2((secs % 60));
	}

	public static void debug(Object msg) {
		if (DEBUG)
			System.out.println(msg);
	}

	public static String getHostname() {
		if (_hostname == null)
			loadAddress();
		return _hostname;
	}

	public static String getIP() {
		if (_ip == null)
			loadAddress();
		return _ip;
	}

	private static void loadAddress() {
		if (_ip != null && _hostname != null)
			return;
		String hostName = null;
		int hostNameIndex = 0;
		try {
			InetAddress thisIp = InetAddress.getLocalHost();
			_ip = thisIp.getHostAddress();
			hostName = thisIp.getHostName();
			if (hostName == null)
				return;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		hostNameIndex = hostName.indexOf(".");
		if (hostNameIndex > 0)
			hostName = hostName.substring(0, hostNameIndex);
		hostName = hostName.toLowerCase();
		_hostname = hostName;
	}

	
	public static Map getMultipartData(HttpServletRequest request) {
		Map m = new HashMap();
		if(!ServletFileUpload.isMultipartContent(request))
			return m;
		try {
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// Set factory constraints
			// factory.setSizeThreshold(yourMaxMemorySize);
			// factory.setRepository(yourTempDirectory);
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("utf-8");

			// Set overall request size constraint
			// upload.setSizeMax(yourMaxRequestSize);

			// Parse the request
			List items = upload.parseRequest(request);

			// Process the uploaded items
			Iterator iter = items.iterator();
			while (iter.hasNext()) {
				Object obj = iter.next();
				FileItem item = (FileItem) obj;

				String name = item.getFieldName();
				if (item.isFormField()) {
					String value = item.getString();
					if (value != null) {
						try {
							value = new String(value.getBytes("iso-8859-1"),
									"utf-8");
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
					if (m.get(name) == null) {
						m.put(name, value);
					} else {
						m.put(name, m.get(name) + "|" + value);
					}
				} else {

					if (obj instanceof String) {
						String value = item.getString();
						if (value != null) {
							try {
								value = new String(
										value.getBytes("iso-8859-1"), "utf-8");
							} catch (Exception ex) {
								ex.printStackTrace();
							}
						}
						m.put(name, value);
					} else {
						m.put(name, item);
					}
					/*
					 * String fieldName = item.getFieldName(); String fileName =
					 * item.getName(); String contentType =
					 * item.getContentType(); boolean isInMemory =
					 * item.isInMemory(); long sizeInBytes = item.getSize();
					 * File fullFile = new File(item.getName());
					 * System.out.println
					 * ("-----------------------C:\\tmp\\"+fullFile.getName());
					 * File savedFile = new File("C:\\tmp\\",
					 * fullFile.getName()); item.write(savedFile);
					 */
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return m;
	}

	public static String getFriendlyName(String inStr) {
		return getFriendlyName(inStr, null);
	}

	public static String getFriendlyName(String inStr, String encode) {
		Character ch = null;
		if (encode != null) {
			try {
				inStr = new String(inStr.getBytes(), encode);
			} catch (Exception ex) {
				//
			}
		}
		if (inStr != null) {
			String regx = "[^a-zA-Z0-9-\\.]";
			inStr = inStr.replaceAll(regx, "");

			
			return inStr;
		}
		return "";
	}

	public static String getFriendlyAttributeName(String inStr) {
		Character ch = null;
		if (inStr != null) { // only leave a-zA-Z0-9
			String regx = "[^a-zA-Z0-9]";
			inStr = inStr.replaceAll(regx, "");
			return inStr;
		}
		return "";
	}

	public static String getAttributeDisplayValue(Object obj) {
		if(isNullOrEmpty2(obj))
            return "n/a";
		return obj.toString();
	}
	public static void writeToFile(String file, Object data){
		writeToFile(file,data,true);
	}
	public static void writeToFile(String file, Object data,boolean append){
		java.io.FileOutputStream fo = null;
		java.io.PrintStream writer = null;
		try{
		    fo = new java.io.FileOutputStream(file,append);
		    writer = new java.io.PrintStream(fo);
		    writer.println(data);
		}catch(Exception ex){
			ex.printStackTrace();
		}finally {
            try {
                if (writer != null) {
                	writer.flush();
                	writer.close();
                }
                if (fo != null) {
                    fo.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
	}
	public static String populateEmailMessage(String file, Map<String,String> data){
        java.io.FileInputStream fis = null;
        java.io.BufferedReader  br = null;
        String line = null;
        StringBuffer sb = new StringBuffer();

        try{
            fis = new java.io.FileInputStream(file);
            br = new java.io.BufferedReader(new java.io.InputStreamReader(fis));
            while ((line = br.readLine()) != null) {
                sb.append(line+"\n");
            }
            String s = sb.toString();
            for(java.util.Iterator<String> it = data.keySet().iterator(); it.hasNext();){
                String key = (String)it.next();
                String value = (String)data.get(key);
                s = s.replaceAll("\\%\\%"+key+"\\%\\%", value);
            }
            return s;
       }catch(Exception ex){
           ex.printStackTrace(System.out);
       }finally
       {
           try{
               if(fis != null)
                   fis.close();
               if(br != null)
                   br.close();
           }catch(Exception e){
              e.printStackTrace();
           }
       }
        return "";
    }
	
	public static String grabWebPage(String url, String method, String data) {
		if (url == null)
			return "";
		java.io.OutputStreamWriter osw  = null;
		BufferedReader dis = null;
		try {
			java.net.URL url1 = new java.net.URL(url);
			java.net.URLConnection conn = url1.openConnection();
			if("post".equalsIgnoreCase(method) && data != null){
				conn.setUseCaches(false);
		        conn.setDoOutput(true);
		        osw = new java.io.OutputStreamWriter(conn.getOutputStream());
		        osw.write(data);
		        osw.flush();
			}
			StringBuffer buf = new StringBuffer();
			String inputLine;
			dis = new java.io.BufferedReader(new java.io.InputStreamReader(
					conn.getInputStream()));
			while ((inputLine = dis.readLine()) != null) {
				buf.append(inputLine + "\n");
				// System.out.println(inputLine);
			}
			return buf.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally
	       {
	           try{
	        	   if(osw != null){
	   			    osw.close();
	        	   }
	               if(dis != null)
	            	   dis.close();
	           }catch(Exception e){
	              e.printStackTrace();
	           }
	       }
		return "";
	}
	
	public static String grabWebPage(String uri) {
		return grabWebPage(uri, null, null);
	}
	
	public static byte[] grabWebContent(String url, String method, String data) {
		if (url == null)
			return null;
		java.io.OutputStreamWriter osw  = null;
		DataInputStream in = null;
		InputStream stream = null;
		BufferedInputStream bis = null;
		try {
			java.net.URL url1 = new java.net.URL(url);
			java.net.URLConnection conn = url1.openConnection();
			if("post".equalsIgnoreCase(method) && data != null){
				conn.setUseCaches(false);
		        conn.setDoOutput(true);
		        osw = new java.io.OutputStreamWriter(conn.getOutputStream());
		        osw.write(data);
		        osw.flush();
			}
			int size =0;
			int bdata=0;
			stream = url1.openStream();
			bis = new BufferedInputStream(stream);
			while ((bdata = bis.read()) > -1){
				size++;
			}
			in = new DataInputStream(conn.getInputStream());
            byte[] buffer = new byte[size];
            in.readFully(buffer);
	        return buffer;
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally
	       {
	           try{
	        	   if(osw != null){
	   			    osw.close();
	        	   }
	               if(in != null)
	            	   in.close();
	               if(bis != null)
	            	   bis.close();
	               if(stream != null )
	            	   stream.close();
	           }catch(Exception e){
	              e.printStackTrace();
	           }
	       }
		return null;
	}
	
	public static byte[] grabWebContent(String uri) {
		return grabWebContent(uri, null, null);
	}
	
	public static String lowerCaseUnderlineToCamelCase(String name)
    {
      return StringUtils.lowerCaseUnderlineToCamelCase(name);
    }
  
  public static String camelCaseToLowerCaseUnderline(String name)
    {
	  return StringUtils.camelCaseToLowerCaseUnderline(name);
    }
  
  public static String upperCaseUnderlineToCamelCase(String name)
    {
      return lowerCaseUnderlineToCamelCase(name);
    }
  
  public static String camelCaseToUpperCaseUnderline(String name)
    {
	  return StringUtils.camelCaseToUpperCaseUnderline(name);
    }
  public static <T> T convertObject(Class<T> parameterClass, Object value)
  {
	  return StringUtils.convertObject(parameterClass,value);
  }
  
  public static String readFirstLine(String file){
      if (file == null) {
              return null;
      }
      String inputLine = null;
      FileInputStream fis = null;
      BufferedReader dis = null;
      try {
          File f = new File(file);
          if (f.exists()) {
              fis = new FileInputStream(f);
              dis = new BufferedReader(new InputStreamReader(
                      fis));
              if ((inputLine = dis.readLine()) != null) {
                  return inputLine;
              }
          }
      } catch (Exception ex) {
          ex.printStackTrace();
      } finally {
          try {
              if (dis != null) {
                  dis.close();
              }
              if (fis != null) {
                  fis.close();
              }
          } catch (Exception ex) {
              ex.printStackTrace();
          }
      }
      return null;
}
  
  public static boolean isWindowsOS() {
	  return (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0);
  }
  
  
}
