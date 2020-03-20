/*
 * %W% %E% mytel_mbccs_notification - ThoND
 *
 * Copyright (c) 2017-2018 SMAC VNIT, jsc. All Rights Reserved. 
 *
 * This software is the confidential and proprietary information of SMAC VNIT, jsc. 
 * ("Confidential Information"). You shall not disclose such Confidential Information 
 * and shall use it only in accordance with the terms of the license agreement you entered 
 * into with SMAC.
 *
 * SMAC MAKES NO REPRESENTATIONS OR WARRANTIES ABOUT THE SUITABILITY OF 
 * THE SOFTWARE, EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED 
 * TO THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. SMAC SHALL NOT BE LIABLE FOR 
 * ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR 
 * DISTRIBUTING THIS SOFTWARE OR ITS DERIVATIVES.
*/
package com.smac.news.common.utils;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Class description goes here.
 * 
 * @since 11:21:05 AM
 * @author ThoND
 */
public class DataUtil {

	@SuppressWarnings("rawtypes")
	public static boolean isNullObject(Object obj1) {
		if (obj1 == null) {
			return true;
		}
		if (obj1 instanceof String) {
			return isNullOrEmpty(obj1.toString());
		}
		if (obj1 instanceof Long) {
			if (obj1.equals(0L)) {
				return true;
			}
		}
		if (obj1 instanceof List) {
			return isNullOrEmpty((Collection) obj1);
		}
		return false;
	}

	public static boolean isNullOrEmpty(CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(cs.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNullOrEmpty(final Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}

	public static boolean isNullOrEmpty(final Object[] collection) {
		return collection == null || collection.length == 0;
	}

	public static boolean isNullOrEmpty(final Map<?, ?> map) {
		return map == null || map.isEmpty();
	}

	public static Long safeToLongForPrimaryKey(Object obj) {
		try {
			if (obj != null) {
				Long id = Long.parseLong(obj.toString());
				if (id.equals(0L)) {
					return null;
				}
				return id;
			}
		} catch (NumberFormatException e) {

		}
		return null;
	}

	public static Long safeToLong(Object obj) {
		try {
			if (obj != null) {
				return Long.parseLong(obj.toString());
			}
		} catch (NumberFormatException e) {

		}
		return null;
	}

	public static Double safeToDouble(Object obj) {
		try {
			if (obj != null) {
				return Double.parseDouble(obj.toString());
			}
		} catch (NumberFormatException e) {

		}
		return null;
	}

	public static BigDecimal safeToBigDecimal(Object obj) {
		try {
			if (obj != null) {
				return (BigDecimal) obj;
			}
		} catch (NumberFormatException e) {

		}
		return null;
	}

	public static boolean safeEqual(String obj1, String obj2) {
		if (null != obj1 && obj1.equals(obj2)) {
			return true;
		}
		return ((obj1 != null) && (obj2 != null) && obj1.equals(obj2));
	}

	public static boolean safeEqual(Long obj1, Long obj2) {
		if (Objects.equals(obj1, obj2)) {
			return true;
		}
		return ((obj1 != null) && (obj2 != null) && (obj1.compareTo(obj2) == 0));
	}

	public static boolean isNullOrZero(Long value) {
		return (value == null || value.equals(0L));
	}

	public static String safeToString(Object obj1, String defaultValue) {
		if (obj1 == null) {
			return defaultValue;
		}

		return obj1.toString();
	}

	public static String safeToString(Object obj1) {
		return safeToString(obj1, "");
	}

	/**
	 * 
	 * created by: Son Dinh
	 * 
	 * @param format
	 * @return
	 */
	public static String getTimeNow(String format) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
		LocalDateTime now = LocalDateTime.now();
		String currentTime = dtf.format(now);
		return currentTime;
	}
	
	/**
	 * 
	 * created by: Son Dinh
	 * @param key
	 * @return
	 */
	public static String MD5(String key) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] hashInBytes = md.digest(key.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : hashInBytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
	}
}
