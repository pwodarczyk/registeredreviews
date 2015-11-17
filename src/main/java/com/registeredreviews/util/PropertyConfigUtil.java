
package com.registeredreviews.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;


public class PropertyConfigUtil extends PropertyPlaceholderConfigurer {

	private String defaultFileName;
	private ResourceLoader resourceLoader;

	public PropertyConfigUtil(ResourceLoader resourceLoader, String defaultName) {

		if ((defaultName == null) || (defaultName.length() < 1)) {
			throw new IllegalArgumentException( "Error! PropertyConfigUtil's Spring Config requires the default name be specified." );
		}

		if (resourceLoader == null) {
			throw new IllegalArgumentException( "Error! PropertyConfigUtil's Spring Config requires the resource-loader name be specified." );
		}

		this.defaultFileName = defaultName;
		this.resourceLoader = resourceLoader;

	}
	
	public Map<String, String> getResolvedProps() {
		Properties props = null;
		try {
			props = this.mergeProperties();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, String> result = new HashMap<String, String>();
		for (final String name: props.stringPropertyNames())
		    result.put(name, props.getProperty(name));
	      return Collections.unmodifiableMap(result);
	  }

	/**
	 * Bean Setter method, which has been greatly enhanced to manage looking up the property file override order and then configure the super classes
	 * resolver locations.
	 * 
	 * @param baseLocation
	 */
	public void setDefaultLocation( String baseLocation ) {

		try {

			List<Resource> resourceList = new ArrayList<Resource>();

			// Add Level 1: The base, default file (filename defined in beanconfig's constructor)

			String defaultLocation = baseLocation + defaultFileName + ".properties";
			Resource defaultResource = resourceLoader.getResource( defaultLocation );
			String defaultPropFileName = defaultResource.getFile().getPath();
			resourceList.add( defaultResource );
			System.out.println( "-> Spring's PropertyFile Resolver Level " + resourceList.size() + " set to: " + defaultLocation + " (" + defaultPropFileName + ")" );


			// Add Level 2: The hostname override.

			String serverName = Util.getHostname();
			String hostnameLocation = baseLocation + serverName + ".properties";
			Resource hostConfig = resourceLoader.getResource( hostnameLocation );

			if (hostConfig.exists()) {

				String localPropFileName = hostConfig.getFile().getPath();
				resourceList.add( hostConfig );

				System.out.println( "-> Spring's PropertyFile Resolver Level " + resourceList.size() + " set to: " + hostnameLocation + " (" + localPropFileName + ")" );
			}


			// Add Level 3: The env-variable override.
			String envVar = System.getenv( "ENV" );
			if (envVar != null) {

				String envLocation = baseLocation + envVar + ".properties";
				Resource envConfig = resourceLoader.getResource( envLocation );

				if (envConfig.exists()) {

					resourceList.add( envConfig );

					System.out.println( "-> Spring's PropertyFile Resolver Level " + resourceList.size() + " set to: " + envLocation + " (" + envConfig.getFile().getAbsolutePath() + ")" );
				} else {
					System.out.println( "-> Spring's PropertyFile Resolver Level " + (resourceList.size() + 1) + " was declared with an environment variable (" + envVar + ") but the file was not found: " + envConfig );
				}
			}


			// Set parent classes resolver.
			super.setLocations( (Resource[]) resourceList.toArray( new Resource[resourceList.size()] ) );

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

/*
	private String defaultFileName;
	private ResourceLoader resourceLoader;
	private String serverName;
	private static String defaultPropFileName;
	private static String localPropFileName;
	private static boolean isLocalExist;

	private static Map keys_props;
	private static Map file_attributes;
	
	public static void loadData() {

		System.out.println( "--> Load data from: " + defaultPropFileName );
		try {
			Properties props = (Properties) keys_props.get( defaultPropFileName.toUpperCase() );
			if (props == null) {
				props = new Properties();
			}
			FileInputStream input = new FileInputStream( defaultPropFileName );
			File file = new File( defaultPropFileName );
			props.clear();
			props.load( input );
			parsePropertiesDefault( props );
			if (keys_props == null) {
				keys_props = new HashMap();
			}
			keys_props.put( defaultPropFileName.toUpperCase(), props );
			input.close();
			if (file_attributes == null) {
				file_attributes = new HashMap();
			}
			file_attributes.put( defaultPropFileName.toUpperCase() + "_lastLoaded", file.lastModified() + "" );
			if (isLocalExist) {
				System.out.println( " --> Loading data from: " + localPropFileName );
				Properties props2 = (Properties) keys_props.get( localPropFileName.toUpperCase() );
				if (props2 == null) {
					props2 = new Properties();
				}
				FileInputStream input2 = new FileInputStream( localPropFileName );
				File file2 = new File( localPropFileName );
				props2.clear();
				props2.load( input2 );
				parsePropertiesDefault( props2 );
				input.close();
				if (file_attributes == null) {
					file_attributes = new HashMap();
				}
				file_attributes.put( localPropFileName.toUpperCase() + "_lastLoaded", file2.lastModified() + "" );
				Enumeration keys = props2.keys();
				while (keys.hasMoreElements()) {
					String key = (String) keys.nextElement();
					props.setProperty( key, props2.getProperty( key ) );
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static Properties Current() {

		checkData();
		return (Properties) keys_props.get( defaultPropFileName.toUpperCase() );
	}


	private static void checkData() {

		if (isFileChanged( defaultPropFileName ) || (isLocalExist && isFileChanged( localPropFileName ))) {
			loadData();
		}
	}


	public static boolean isFileChanged( String fileName ) {

		if (fileName == null) {
			fileName = defaultPropFileName;
		}
		boolean isChanged = false;
		if (keys_props == null) {
			keys_props = new HashMap();
		}
		Properties props = (Properties) keys_props.get( defaultPropFileName.toUpperCase() );
		if (props == null) {

			loadData();
			isChanged = false;
		}
		if (file_attributes == null) {
			file_attributes = new HashMap();
		}

		long lastLoaded = 0;
		if (file_attributes.get( fileName.toUpperCase() + "_lastLoaded" ) == null) {
			isChanged = true;
		} else {
			lastLoaded = Long.parseLong( (String) file_attributes.get( fileName.toUpperCase() + "_lastLoaded" ) );
		}

		File file = null;
		if (!isChanged) {
			try {
				file = new File( fileName );
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (file.lastModified() > lastLoaded) {
				isChanged = true;
				file_attributes.put( fileName.toUpperCase() + "_lastLoaded", file.lastModified() + "" );
			}
		}
		return isChanged;
	} // end checkFile


	public static void parsePropertiesDefault( Properties props ) {
		parsePropertiesDefault( null, props );
	}


	public static void parsePropertiesDefault( String keyprefix, Properties props ) {

		if (keyprefix == null) {
			keyprefix = "";
		}

		try {
			Map old_refs = new HashMap();

			Enumeration prp_keys = props.propertyNames();
			String key = null;
			String val = null;
			String key_o = null;
			String old_s = null;
			String new_s = null;

			while (prp_keys.hasMoreElements()) {
				key = (String) prp_keys.nextElement();
				val = props.getProperty( key );
				if (!"".equals( keyprefix ) && (key.indexOf( keyprefix ) == 0)) {
					String prodkey = key.substring( keyprefix.length() );
					props.remove( prodkey );
					props.remove( key );
					props.setProperty( prodkey, val );
				}
			}

			prp_keys = props.propertyNames();
			old_refs.putAll( props );

			while (prp_keys.hasMoreElements()) {
				key = (String) prp_keys.nextElement();
				val = props.getProperty( key );
				if (val != null) {
					for (Iterator it = old_refs.keySet().iterator(); it.hasNext();) {
						try {
							key_o = (String) it.next();
							new_s = (String) old_refs.get( key_o );
							old_s = "${" + key_o + "}";
							if (val.indexOf( old_s ) >= 0) {
								val = Util.replace( val, old_s, new_s );
								props.setProperty( key, val );
							}
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
*/
}