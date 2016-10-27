package com.training.core.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.CharSetUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * 字符常用类的整理，包括几大常用功能:<br>
 * <ol>
 * <li>对字符的修复及验证，如：去除空格，验证字符串为空等</li>
 * <li>对字符编码的转换，如：GBK,ISO,UTF8等编码转换</li>
 * <li>字符串的正则表达式的相关操作</li>
 * <li>字符串的各种验证</li>
 * <li>org.apache.commones.lang专区</li>
 * <li>其他</li>
 * </ol>
 * 
 * @date 2011-4-20
 * @author MipatchTeam#czg,MipatchTeam#guob,MipatchTeam#chenc
 * 
 */
public class StringUtil {

	// --------------------- 1. --------------------------------
	/**
	 * 返回一个字符串去掉空格后的值，如果为null则返回空串
	 * 
	 * @param str
	 *            :待处理的字符串
	 * @return 去掉空格后的字符串或者空串
	 */
	public static String trim(String str) {
		if (str == null) {
			return "";
		}
		if (str.trim().length() <= 0)
			return "";
		str = str.trim();
		return str;
	}

	/**
	 * 假如传入的字符串是null则转成空串，否则返回原字符串
	 * 
	 * @param str
	 *            :待处理的字符串
	 * @return 如果字符串是null则转成空串，否则返回原字符串
	 */
	public static String nullToSpace(String Content) {
		if (Content == null) {
			Content = "";
		}
		return trim(Content);
	}

	/**
	 * 判断字符是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		if (s == null || s.trim().length() == 0)
			return true;
		return false;
	}

	/**
	 * 去除字符串后面的0
	 * 
	 * @param str
	 *            :待处理的字符串
	 * @return String 去掉0后的字符串
	 */
	public static String removeLastZero(String str) {
		if (str == null || str.trim().equals(""))
			return "";
		StringBuffer sb = new StringBuffer(str);
		sb = sb.reverse();
		for (int i = 0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			if (c != '0') {
				str = sb.substring(i, sb.length());
				break;
			}
		}
		return new StringBuffer(str).reverse().toString();
	}

	/**
	 * 将空值转为0
	 * 
	 * @param str
	 *            :待处理的字符串
	 * @return 如果传入参数是null返回空串"0",否则原字串去空格后返回
	 */
	public static String nullToZero(String s1) {
		if (s1 == null)
			return "0";
		s1 = s1.trim();
		if (s1.length() == 0)
			return "0";
		return s1;
	}

	/**
	 * 去除所有的空白，如：" abc d " --> "abcd"
	 * 
	 * @param str
	 * @return
	 */
	public static String deleteWhitespace(String str) {
		return StringUtils.deleteWhitespace(str);
	}

	// ---------------------------------------------------------

	// --------------------- 2. --------------------------------
	/**
	 * 去掉字符串两端的空白字符, 转化为指定的字符集转化为另一个的标准字符集合。
	 * 
	 * @param str
	 *            :待处理的字符串
	 * @param srcCharset
	 *            :源编码格式
	 * @param ObjCharset
	 *            :所要转换的编码格式
	 * @return 返回编码后的字符串
	 * @throws Exception
	 */
	public static String stringCodeChange(String str, String srcCharset,
			String ObjCharset) throws Exception {
		if (str != null) {
			str = str.trim();
			if (str != null) {
				str = new String(str.getBytes(srcCharset), ObjCharset);
			}
		}
		return str;
	}

	/**
	 * 将ISO8859_1字符串转成GBK编码
	 */
	public static String stringCodeISOToGBK(String strvalue) throws Exception {
		return stringCodeChange(strvalue, "ISO8859_1", "GBK");
	}

	/**
	 * 将GBK字符串转成ISO8859_1编码
	 */
	public String stringCodeGBKToISO(String strvalue) throws Exception {
		return stringCodeChange(strvalue, "GBK", "ISO8859_1");
	}

	/**
	 * 将ISO8859_1字符串转成UTF-8编码
	 */
	public static String stringCodeISOToUTF8(String strvalue) throws Exception {
		return stringCodeChange(strvalue, "ISO8859_1", "UTF-8");
	}

	// 

	/**
	 * 将UNICODE编码转换为汉字，头符号目前支持两种（\ u , %u）<br>
	 * 如：%3e%u641c%u72d0%u793e%u533a%3a --> 搜狐社
	 */
	public static String readHTMLUnicode(String unicode) {
		String[] unicodes = unicode.split("[\\\\|%]u");
		StringBuffer strs = new StringBuffer("");
		for (int i = 0; i < unicodes.length; i++) {
			try {
				BigInteger bi = new BigInteger(unicodes[i], 16);
				char c = (char) bi.intValue();
				strs.append(c);
			} catch (Exception e) {
				continue;
			}
		}
		return strs.toString();
	}

	// ---------------------------------------------------------

	// --------------------- 3. --------------------------------
	/**
	 * 正则匹配，只匹配一轮<br>
	 * 如：abcdabfie 匹配 (ab). 表达式最终结果为 {abc,ab}
	 * 
	 * @param s
	 *            待匹配字符串
	 * @param pattern
	 *            正则表达式
	 * @return
	 */
	public static String[] match(String s, String pattern) {
		Matcher m = Pattern.compile(pattern).matcher(s);

		while (m.find()) {
			int n = m.groupCount();
			String[] ss = new String[n + 1];
			for (int i = 0; i <= n; i++) {
				ss[i] = m.group(i);
			}
			return ss;
		}
		return null;
	}

	/**
	 * 正则匹配，匹配所有的正则<br>
	 * 如：abcdabfie 匹配 (ab). 表达式最终结果为 [{abc,ab},{abf,ab}]
	 * 
	 * @param s
	 * @param pattern
	 * @return
	 */
	public static List<String[]> matchAll(String s, String pattern) {
		Matcher m = Pattern.compile(pattern).matcher(s);
		List<String[]> result = new ArrayList<String[]>(10);

		while (m.find()) {
			int n = m.groupCount();
			String[] ss = new String[n + 1];
			for (int i = 0; i <= n; i++) {
				ss[i] = m.group(i);
			}
			result.add(ss);
		}
		return result;
	}

	/**
	 * 正则匹配，匹配所有的正则<br>
	 * 如：abcdabfie 匹配 (ab). startIndex为2 表达式最终结果为 {abf,ab}
	 * 
	 * @param s
	 * @param pattern
	 * @param startIndex
	 * @return
	 */
	public static String[] matchFromIndex(String s, String pattern,
			int startIndex) {
		Matcher m = Pattern.compile(pattern).matcher(s);

		if (m.find(startIndex)) {
			int n = m.groupCount();
			String[] ss = new String[n + 1];
			for (int i = 0; i <= n; i++) {
				ss[i] = m.group(i);
			}
			return ss;
		}
		return null;
	}

	/**
	 * 正则匹配，只匹配一轮，忽略大小写<br>
	 * 如：abcdabfie 匹配 (ab). 表达式最终结果为 {abc,ab}
	 * 
	 * @param s
	 *            待匹配字符串
	 * @param pattern
	 *            正则表达式
	 * @return
	 */
	public static String[] matchWeak(String s, String pattern) {
		Matcher m = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(
				s);

		while (m.find()) {
			int n = m.groupCount();
			String[] ss = new String[n + 1];
			for (int i = 0; i <= n; i++) {
				ss[i] = m.group(i);
			}
			return ss;
		}
		return null;
	}

	// ---------------------------------------------------------

	// --------------------- 4. --------------------------------
	/**
	 * 是否为e-mail，如：inetcop@xm-my.com
	 */
	public static boolean isEmail(String str) {
		return str
				.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	}

	/**
	 * 是否为url
	 */
	public static boolean isUrl(String str) {
		return str
				.matches("^[a-zA-z]+://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*(\\?\\S*)?$");
	}

	/**
	 * 是否为国内电话
	 */
	public static boolean isPhoneNum(String str) {
		return str.matches("(\\d{3}-|\\d{4}-)?(\\d{8}|\\d{7})?");
	}

	/**
	 * 是否为国内手机电话
	 */
	public static boolean isMobilePhoneNum(String str) {
		return str.matches("^1\\d{10}$");
	}

	/**
	 * 是否为空行
	 */
	public static boolean isBlankLine(String str) {
		return str.matches("\\n[\\s| ]*\\r");
	}

	/**
	 * 是否为空行
	 */
	public static boolean isIPAddress(String str) {
		return str.matches("[0-255]\\.[0-255]\\.[0-255]\\.[0-255]");
	}

	/**
	 * 是否为QQ号码
	 */
	public static boolean isQQNum(String str) {
		return str.matches("^[1-9]*[1-9][0-9]*$");
	}

	/**
	 * 是否匹配中文字符
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isChineseChar(String str) {
		return str.matches("[\u4e00-\u9fa5]");
	}

	/**
	 * 是否只包含字母和汉字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isAlpha(String str) {
		return StringUtils.isAlpha(str);
	}

	// ---------------------------------------------------------

	// --------------------- 5. --------------------------------
	/**
	 * 比较传入的两个字符串大小，按顺序<br>
	 * 规则：若长度不同 则长度大的排在后面。 若长度相同，则按每个字符的char值排序。
	 * 
	 * @param o1
	 * @param o2
	 * @return 0相等 1前者大 -1后者大
	 */
	public static int compare(String o1, String o2) {
		// 若长度不同 则长度大的排在后面
		if (o1.trim().length() > o2.trim().length())
			return 1;
		else if (o1.trim().length() < o2.trim().length())
			return -1;

		// 若长度相同，则按每个字符的char值排序
		for (int i = 0; i < o1.length(); i++) {
			if (o1.charAt(i) > o2.charAt(i))
				return 1;
			else if (o1.charAt(i) < o2.charAt(i))
				return -1;
		}

		return 0;
	}

	/**
	 * 替换sql中的 ' 转为 ''
	 * 
	 * @param sPassword
	 */
	public static String toSql(String str) {
		return (str == null)?"":str.replace("'", "''");
	}

	/**
	 * Collection链表转换成字符串
	 * 
	 * @param element
	 *            要被处理的字符数组
	 * @param separator分割符号
	 * @return String 转换后的字符串
	 */
	public static String listToStr(Collection<String> element, String separator) {

		StringBuffer returnstr = new StringBuffer("");

		if (element == null)
			return "";
		if (separator == null)
			separator = "";

		Iterator<String> it = element.iterator();

		while (it.hasNext()) {
			returnstr.append(String.valueOf(it.next()));
			if (it.hasNext())
				returnstr.append(separator);
		}

		return returnstr.toString();
	}

	/**
	 * 将字符串反转
	 * 
	 * @param str
	 * @return
	 */
	public static String reverse(String str) {
		return StringUtils.reverse(str);
	}

	/**
	 * 若干对象全部反转
	 * 
	 * @param str
	 * @return
	 */
	public static Object[] reverse(Object... str) {
		StringBuilder sb = new StringBuilder();
		for (Object o : str)
			sb.append(o + ",");
		return sb.reverse().toString().substring(1).split(",");
	}

	// ---------------------------------------------------------

	// --------------------- 1. --------------------------------
	/**
	 * 将字符串以指定填充字符填充至指定长度，保证给定字符串位于中间位置<br>
	 * 如：center("a",4,"=") --> 结果为：=a==
	 * 
	 * @param str
	 *            给定字符串
	 * @param size
	 *            指定长度
	 * @param padStr
	 *            填充字符串
	 * @return
	 */
	public static String center(String str, int size, String padStr) {
		return StringUtils.center(str, size, padStr);
	}

	/**
	 * 是否包含目标字符，如： contains("abcd","d") --> true ; contains("abcd","e") -->
	 * false
	 * 
	 * @param str
	 * @param searchChar
	 * @return
	 */
	public static boolean contains(String str, String searchChar) {
		return StringUtils.contains(str, searchChar);
	}

	/**
	 * 是否包含目标字符并忽略大小写，如： contains("abcd","A") --> false ;
	 * containsIgnoreCase("abcd","A") --> true
	 * 
	 * @param str
	 * @param searchChar
	 * @return
	 */
	public static boolean containsIgnoreCase(String str, String searchChar) {
		return StringUtils.containsIgnoreCase(str, searchChar);
	}

	/**
	 * 字符串是否为纯数字，如：“123”-- true ； “12 3”-- false
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isPureNumber(String str) {
		return StringUtils.isNumeric(str);
	}

	/**
	 * 字符串是否为正确的数字，如：“0x45” "132L" -- true ; “a” -- false
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isCorrectNumber(String str) {
		return NumberUtils.isNumber(str);
	}

	/**
	 * 计算给定字符串中后者（单个字符）出现的次数，如：StringUtil.countRepStr("Tihe quick", "aeiou") -->
	 * 4
	 * 
	 * @param src
	 * @param rep
	 * @return
	 */
	public static int countRepStr(String src, String rep) {
		return CharSetUtils.count(src, rep);
	}

	/**
	 * 删除给定字符串（单个字符），如：StringUtil.deleteRepStr("The quick", "aeiou") --> Th qck
	 * 
	 * @param src
	 * @param rep
	 * @return
	 */
	public static String deleteRepStr(String src, String rep) {
		return CharSetUtils.delete(src, rep);
	}

	/**
	 * 保留指定字符串（单个字符），如：StringUtil.keepRepStr("Thie quick", "aeiou") --> ieui
	 * 
	 * @param src
	 * @param rep
	 * @return
	 */
	public static String keepRepStr(String src, String rep) {
		return CharSetUtils.keep(src, rep);
	}

	/**
	 * 合并重复指定字符串（单个字符），如：StringUtil.squeezeRepStr("Thiie quuu uuick", "aeiou")
	 * --> Thie qu uick
	 * 
	 * @param src
	 * @param rep
	 * @return
	 */
	public static String squeezeRepStr(String src, String rep) {
		return CharSetUtils.squeeze(src, rep);
	}

	/**
	 * 将指定字符串重复指定次数，如：repeatStr("abc ", 3) --> abc abc abc
	 * 
	 * @param rep
	 * @param repcout
	 * @return
	 */
	public static String repeatStr(String rep, int repcout) {
		return StringUtils.repeat(rep, repcout);
	}

	/**
	 * 将字符转换为HTML支持的格式
	 * 
	 * For example: </p>
	 * <p>
	 * <code>"bread" & "butter"</code>
	 * </p>
	 * becomes:
	 * <p>
	 * <code>&amp;quot;bread&amp;quot; &amp;amp; &amp;quot;butter&amp;quot;</code>
	 * .
	 * </p>
	 * 
	 * @param str
	 * @return
	 */
	public static String escapeHtml(String str) {
		return StringEscapeUtils.escapeHtml(str);
	}

	/**
	 * 将字符转换为JAVA支持的格式
	 * 
	 * <p>
	 * Example:
	 * 
	 * <pre>
	 * input string: He didn't say, "Stop!"
	 * output string: He didn't say, \"Stop!\"
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 * @return
	 */
	public static String escapeJava(String str) {
		return StringEscapeUtils.escapeJava(str);
	}

	/**
	 * 将字符转换为JAVASCRIPT支持的格式
	 * 
	 * <p>
	 * Example:
	 * 
	 * <pre>
	 * input string: He didn't say, "Stop!"
	 * output string: He didn\'t say, \"Stop!\"
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 * @return
	 */
	public static String escapeJavaScript(String str) {
		return StringEscapeUtils.escapeJavaScript(str);
	}

	/**
	 * 将字符转换为SQL支持的格式
	 * 
	 * <p>
	 * Example:
	 * 
	 * <pre>
	 * (<code>"McHale's Navy"</code> => <code>"McHale''s Navy"</code>)
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @param str
	 * @return
	 */
	public static String escapeSql(String str) {
		return StringEscapeUtils.escapeSql(str);
	}

	/**
	 * 将字符转换为XML支持的格式
	 * 
	 * <p>
	 * For example: <tt>"bread" & "butter"</tt> =>
	 * <tt>&amp;quot;bread&amp;quot; &amp;amp; &amp;quot;butter&amp;quot;</tt>.
	 * </p>
	 * 
	 * @param str
	 * @return
	 */
	public static String escapeXml(String str) {
		return StringEscapeUtils.escapeXml(str);
	}

	// ---------------------------------------------------------

}
