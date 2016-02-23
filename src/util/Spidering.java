package util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bean.Article;
import util.Const.*;

import static util.Const.*;

public class Spidering implements Callable<List<Article>> {
	String userId;
	String pageIndex;

	public Spidering() {
		// TODO Auto-generated constructor stub
	}

	public Spidering(String userId, String pageIndex) {
		this.userId = userId;
		this.pageIndex = pageIndex;
	}

	@Override
	public List<Article> call() throws Exception {
		// TODO Auto-generated method stub
		String url = PREFIX + userId + PAGE + pageIndex + SUFFIX;
		Document doc = Jsoup.connect(url).header(Header.KEY, Header.VALUE).get();
		Elements articleDoms = doc.select(Dom.ARTICLE);
		int len = articleDoms.size();
		List<Article> articles = new ArrayList<>(len + 1);

		for (int articleIndex = 0; articleIndex < len; articleIndex++) {
			Element articleDom = articleDoms.get(articleIndex);
			Elements titleDom = articleDom.select(Dom.TITLE);

			String title = titleDom.text();
			String link = titleDom.attr("href");
			String description = articleDom.select(Dom.DESC).text();
			String postdate = articleDom.select(Dom.POST_DATE).text();
			String view = articleDom.select(Dom.VIEW).text();

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
