package leo.spider;

import static leo.util.Const.USER_AGENT;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import leo.bean.Article;
import leo.bean.Category;
import leo.util.Const.Dom;
import leo.util.Const.Url;
import leo.util.Const.ViewMode;

/**
 * @Description:
 * @Author leo
 * @Date 2016年5月19日 上午9:47:12
 * @Usage: Spider s = new Spider("lc0817");<br>
 *         s.start();<br>
 *         List<Article> articleList = s.getArticleList();<br>
 *         List<Category> categoryList = s.getCategoryList();
 * 
 * 
 */
public class CSDNSpider implements ISpider {
	private String userId;
	private int articleCount;
	private int pageCount;
	private List<Category> categoryList;
	private List<Article> articleList;

	private final int TIME_OUT = 8000;

	private CSDNSpider() {
	}

	public CSDNSpider(String userId) {
		this.userId = userId;
	}

	/**
	 * 需要主动调用该方法
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public void start() throws Exception {
		Document doc = getDocument(Url.PREFIX + userId + ViewMode.LIST);
		Elements articleCountElem = doc.select(Dom.ARTICLE_COUNT);
		String articleCountTxt = articleCountElem.html();
		this.articleCount = getArticleCount(articleCountTxt);
		this.pageCount = getPageCount(articleCountTxt);

		categoryList = getAllCategories(doc.select(Dom.CATEGORY));
		articleList = getAllArticles();
	}

	private List<Category> getAllCategories(Elements categoryElems) {
		int categoryCount = categoryElems.size();
		List<Category> categoryList = new ArrayList<>(categoryCount);
		for (int i = 0; i < categoryCount; i++) {
			Element categoryElem = categoryElems.get(i);
			String categoryId = categoryElem.select("a").attr("href")
					.substring(categoryElem.select("a").attr("href").lastIndexOf("/") + 1);
			String categoryName = categoryElem.select("a").text();
			int articleCount = Integer.valueOf(
					categoryElem.select("span").text().substring(1, categoryElem.select("span").text().length() - 1));
			Category category = new Category(categoryId, categoryName, articleCount);
			categoryList.add(category);
		}
		return categoryList;
	}

	private List<Article> getAllArticles() throws Exception {
		List<Article> articleList = new ArrayList<>(articleCount);
		for (int page = 1; page <= pageCount; page++) {
			Document doc = getDocument(Url.PREFIX + userId + Url.ARTICLE_LIST + page + ViewMode.LIST);
			Elements articlesElem = doc.select(Dom.ARTICLE_ITEM);
			int articleCountOfPage = articlesElem.size();
			for (int i = 0; i < articleCountOfPage; i++) {
				Element articleElem = articlesElem.get(i);
				Article article = buildArticle(articleElem);
				articleList.add(article);
			}
		}
		if (articleList.size() != articleCount) {
			throw new Exception("文章数目不一致");
		}
		return articleList;
	}

	private Article buildArticle(Element articleElem) {
		Elements titleElem = articleElem.select(Dom.ARTICLE_TITLE);
		Elements descriptionElem = articleElem.select(Dom.ARTICLE_DESCRIPTION);
		Elements postDateElem = articleElem.select(Dom.ARTICLE_POST_DATE);
		Elements viewCountElem = articleElem.select(Dom.ARTICLE_VIEW_COUNT);

		int articleId = Integer.valueOf(titleElem.attr("href").substring(titleElem.attr("href").lastIndexOf("/") + 1));
		int viewCount = Integer.valueOf(viewCountElem.text().substring(3, viewCountElem.text().length() - 1));

		String title = titleElem.text();
		String description = descriptionElem.text();
		String postDate = postDateElem.text();
		String content = getArticleContent(articleId);

		Article article = new Article(articleId, title, description, postDate, viewCount, content);

		return article;
	}

	private String getArticleContent(int articleId) {
		Document doc = getDocument(Url.PREFIX + userId + Url.ARTICLE_DETAILS + articleId);
		Elements articleContentElem = doc.select(Dom.ARTICLE_CONTENT);
		String content = articleContentElem.html();
		return content;
	}

	private int getArticleCount(String txt) {
		return Integer.valueOf(txt.substring(0, txt.indexOf("条")));
	}

	private int getPageCount(String txt) {
		return Integer.valueOf(txt.substring(txt.lastIndexOf("共") + 1, txt.lastIndexOf("页")));
	}

	@Override
	public Document getDocument(String url) {
		Document doc = null;
		try {
			doc = Jsoup.connect(url).userAgent(USER_AGENT).timeout(TIME_OUT).get();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return doc;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public List<Article> getArticleList() {
		return articleList;
	}

	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}

}
