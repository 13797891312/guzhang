package com.caiyun.guzhang.util;

import java.util.regex.Pattern;

public class Patterns {
	public static final Pattern WEB_URL = Pattern.compile(""); // and finally, a word boundary or end of
	                        // input.  This is to stop foo.sure from
	                        // matching as foo.su
	public static final String BIAOQING="\\[_[0-1][0-9]{2}\\]";
}
