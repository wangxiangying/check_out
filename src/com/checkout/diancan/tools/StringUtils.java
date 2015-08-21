package com.checkout.diancan.tools;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.apache.log4j.Logger;

/**
 * å­—ç¬¦ä¸²å¤„ç�†ç±»
 * 
 * @author mah
 *
 */
public class StringUtils {
//	private static Logger logger = Logger.getLogger(StringUtils.class);
	public static final String STRING_NULL = "null";



	/**
	 * æ ¡éªŒurlæ˜¯å�¦å�ˆæ³•
	 * 
	 * @param url
	 * @return
	 */
	public static boolean veryUrl(String url) {
		String regEx = "^(http|https|ftp)\\://([a-zA-Z0-9\\.\\-]+(\\:[a-zA-"
				+ "Z0-9\\.&%\\$\\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{"
				+ "2}|[1-9]{1}[0-9]{1}|[1-9])\\.(25[0-5]|2[0-4][0-9]|[0-1]{1}"
				+ "[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|"
				+ "[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\\.(25[0-5]|2[0-"
				+ "4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0"
				+ "-9\\-]+\\.)*[a-zA-Z0-9\\-]+\\.[a-zA-Z]{2,4})(\\:[0-9]+)?(/"
				+ "[^/][a-zA-Z0-9\\.\\,\\?\\'\\\\/\\+&%\\$\\=~_\\-@]*)*$";
		return url.matches(regEx);
	}

	/**
	 * åŽ»é™¤æ�¢è¡Œå’Œç©ºæ ¼
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str) {
		String dest = "";
		if (str != null) {
			Pattern p = Pattern.compile("\\s*|\t|\r|\n");
			Matcher m = p.matcher(str);
			dest = m.replaceAll("");
		}
		return dest;
	}

	/**
	 * replace value of param with value of repalceValue when param is null
	 * 
	 * @param param
	 *            String
	 * @param param
	 *            String
	 */
	public static String checkParam(String param, String repalceValue) {

		return param == null || STRING_NULL.equalsIgnoreCase(param)
				|| "".equals(param.trim()) ? (repalceValue == null
				|| STRING_NULL.equalsIgnoreCase(repalceValue) ? ""
				: repalceValue) : param.trim();
	}

	/**
	 * å¯¹ä¸€ç»„å­—ç¬¦ä¸²è¿›è¡Œé¦–å­—æ¯�ä»Žå°�åˆ°è¾¾æŽ’åº�(é™¤ç­¾å��å­—æ®µå’Œç©ºå€¼å­—æ®µå¤–)
	 * 
	 * @param sortString
	 * @return
	 * @throws
	 */
	public static String[] StringSort(String[] sortString) throws Exception {
		String temp = "";

		try {
			for (int i = 0; i < sortString.length-1; i++) {

				for (int j = i + 1; j < sortString.length; j++) {
					// å¦‚æžœç¬¬ä¸€ä¸ªå­—ç¬¦ä¸²é¦–å­—æ¯�ï¼ˆå­—æ¯�ç›¸å�Œæ¯”è¾ƒä¸‹ä¸€ä¸ªå­—æ¯�ï¼‰å¤§äºŽç¬¬äºŒä¸ªå­—ç¬¦ä¸²é¦–å­—æ¯�ï¼Œè¿›è¡Œæ�¢ä½�
					if (compare(sortString[i], sortString[j]) == false) {
						temp = sortString[i];
						sortString[i] = sortString[j];
						sortString[j] = temp;
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("æŠ¥æ–‡æŽ’åº�å¼‚å¸¸", e);
//			throw new Exception("æŠ¥æ–‡æŽ’åº�å¼‚å¸¸");
		}

		return sortString;
	}

	/**
	 * æ¯”è¾ƒå­—ç¬¦ä¸²å¤§å°�
	 * 
	 * @param s1
	 * @param s2
	 * @return
	 */
	public static boolean compare(String s1, String s2) {
		boolean result = true;
		for (int i = 0; i < s1.length() && i < s2.length(); i++) {
			if (s1.charAt(i) > s2.charAt(i)) {
				result = false;
				break;
			} else if (s1.charAt(i) < s2.charAt(i)) {
				result = true;
				break;
			} else {
				if (s1.length() < s2.length()) {
					result = true;
				} else {
					result = false;
				}
			}
		}
		return result;
	}

	

	/**
	 * æ•°ç»„è½¬ä¸ºå­—ç¬¦ä¸²
	 * 
	 * @param array
	 *            æ•°ç»„
	 * @param join
	 *            æ‹¼æŽ¥å­—ç¬¦
	 * @return
	 */
	public static String arrayToString(String[] array, String join) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			if ((array[i].split("=")).length <= 1
					|| array[i].startsWith("sign")
					|| "".equals((array[i].split("=")[1]))
					|| "null".equals((array[i].split("=")[1]))) {
				continue;
			}
			sb.append(array[i]).append(join);
		}
		sb.delete(sb.length() - 1, sb.length());
		return sb.toString();
	}

	/**
	 * åˆ†å‰²ä¼ è¿‡æ�¥çš„å�‚æ•°å€¼
	 * 
	 * @param tranDate
	 * @return HashMap<String,String>
	 * @throws Exception
	 */
	public static HashMap<String, String> resolveString(String tranData,
			String sp) throws Exception {
		try {
			HashMap<String, String> stringMap = new HashMap<String, String>();
			String[] array = tranData.split(sp);
			for (int i = 0; i < array.length; i++) {

				String[] keyValue = array[i].split("=");
				String key = keyValue[0];
				String value;
				if (keyValue.length >= 2) {
					value = array[i].substring(array[i].indexOf("=") + 1);
				} else {
					value = null;
				}
				stringMap.put(key, value);
			}
			return stringMap;
		} catch (Exception e) {
			e.printStackTrace();
//			logger.error("åˆ†å‰²å­—ç¬¦ä¸²é”™è¯¯:", e);
			throw e;
		}
	}

	/**
	 * éªŒè¯�å­—ç¬¦ä¸²æ˜¯å�¦ä¸ºæ•°å­—ï¼ˆ#.##ï¼‰ä¸”ä¸�ä¸º0.00,å¦‚æžœæ˜¯è¿”å›žtrueï¼Œå�¦åˆ™è¿”å›žfalseï¼›
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumNotZero(String str) {
		if (!isNum(str))
			return false;
		Pattern pattern = Pattern.compile("^[0][.][0]{2}$");
		Matcher match = pattern.matcher(str);
		return match.matches() ? false : true;
	}

	/**
	 * éªŒè¯�å­—ç¬¦ä¸²æ˜¯å�¦ä¸ºæ•°å­—ï¼ˆ#.##ï¼‰,å¦‚æžœæ˜¯è¿”å›žtrueï¼Œå�¦åˆ™è¿”å›žfalseï¼›
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNum(String str) {
		if (str == null)
			return false;
		Pattern pattern = Pattern
				.compile("^[1-9][\\d]*[.][\\d]{2}|[0][.][\\d]{2}$");
		Matcher match = pattern.matcher(str);
		return match.matches();
	}


	public static boolean isNullOrEmpty(String str) {
		boolean flag = true;
		if (str != null && !"".equals(str.trim())) {
			flag = false;
		}
		return flag;
	}

	public static boolean isNotNullOrEmpty(String str) {
		return !isNullOrEmpty(str);
	}
}
