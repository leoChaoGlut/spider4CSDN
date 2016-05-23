package leo.util;

/**
 * @Description:
 * @Author leo
 * @Date 2016年5月19日 上午10:06:47
 * @Usage:
 */
public class Const {
	public static final String USER_AGENT = "Mozilla";

	public class Url {
		public static final String PREFIX = "http://blog.csdn.net/";
		public static final String ARTICLE_LIST = "/article/list/";
		public static final String ARTICLE_DETAILS = "/article/details/";

	}

	public class ViewMode {
		public static final String CONTENTS = "?viewmode=contents";
		public static final String LIST = "?viewmode=list";

	}

	public class Dom {
		public static final String ARTICLE_COUNT = "#papelist span";
		public static final String ARTICLE_LIST = "#article_list";
		public static final String ARTICLE_ITEM = ".article_item";
		public static final String ARTICLE_TITLE = ".article_title .link_title a";
		public static final String ARTICLE_DESCRIPTION = ".article_description";
		public static final String ARTICLE_POST_DATE = ".article_manage .link_postdate";
		public static final String ARTICLE_VIEW_COUNT = ".article_manage .link_view";
		public static final String ARTICLE_CONTENT = "#article_content";
		public static final String ARTICLE_CATEGORY = "#article_details .category .category_r label span";

		public static final String CATEGORY = "#panel_Category .panel_body li";
	}
}
