package util;

import static util.Const.PAGE;
import static util.Const.PREFIX;
import static util.Const.SUFFIX;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bean.Article;
import util.Const.Dom;
import util.Const.Header;

/**
 * 
 * @author leo
 * @Date 2016年2月24日
 * @Comments 实现了callable接口,适用多线程爬取数据.
 */
public class ArticleSpider implements Callable<List<Article>> {
	/**
	 * 用户id
	 */
	String userId;
	/**
	 * 分页
	 */
	String pageIndex;

	public ArticleSpider() {
		// TODO Auto-generated constructor stub
	}

	public ArticleSpider(String userId, String pageIndex) {
		this.userId = userId;
		this.pageIndex = pageIndex;
	}

	/**
	 * 具体处理逻辑
	 */
	@Override
	public List<Article> call() throws Exception {
		// TODO Auto-generated method stub
		String url = PREFIX + userId + PAGE + pageIndex + SUFFIX;
		Document doc = Jsoup.connect(url).header(Header.KEY, Header.VALUE).get();
		Elements articleDoms = doc.select(Dom.ARTICLE);
		int len = articleDoms.size();
		List<Article> articles = new ArrayList<>(len);

		for (int articleIndex = 0; articleIndex < len; articleIndex++) {
			Element articleDom = articleDoms.get(articleIndex);
			Elements titleDom = articleDom.select(Dom.TITLE);

			String title = titleDom.text();
			String link = titleDom.attr("href");
			String description = articleDom.select(Dom.DESC).text();
			String postdate = articleDom.select(Dom.POST_DATE).text();
			String view = articleDom.select(Dom.VIEW).text();

			link = link.substring(link.lastIndexOf("/") + 1);
			view = view.substring(view.indexOf("(") + 1, view.indexOf(")"));

			Article article = new Article(title, description, postdate, view, link);

			articles.add(article);
		}
		return articles;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

}
