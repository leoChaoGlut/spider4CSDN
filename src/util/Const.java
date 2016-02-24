package util;

/**
 * 
 * @author leo
 * @Date 2016年2月24日
 * @Comments 常量
 */
public class Const {
	public static final String PREFIX = "http://blog.csdn.net/";
	public static final String SUFFIX = "?viewmode=list";
	public static final String PAGE = "/article/list/";

	public class Header {
		public static final String KEY = "User-Agent";
		public static final String VALUE = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";
	}

	public class Dom {
		public static final String ARTICLE = ".article_item";
		public static final String TITLE = ".link_title a";
		public static final String DESC = ".article_description";
		public static final String POST_DATE = ".article_manage .link_postdate";
		public static final String VIEW = ".article_manage .link_view";
		public static final String COMMENT = ".article_manage .link_comments";
		public static final String PAGE_LIST = "#papelist";
	}
}
