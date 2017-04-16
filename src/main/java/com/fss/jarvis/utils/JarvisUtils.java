/**
 * 
 */
package com.fss.jarvis.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author admin
 *
 */
@Service("jarvisUtils")
public class JarvisUtils {
 
	public static Logger LOGGER = LoggerFactory.getLogger(JarvisUtils.class);
	
	public String validateNullData(String value) {
		
		if (value == null) {
			value = "-";
		}
		LOGGER.info("value :: {}", value);
		return value;
	}
}
