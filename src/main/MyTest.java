package main;

import static util.Const.PREFIX;
import static util.Const.SUFFIX;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import bean.Article;
import bean.Total;
import util.Const.Header;
import util.Spidering;

public class MyTest {

	String userId = "lc0817";

	ExecutorService threadPool = Executors.newCachedThreadPool();

	List<Article> articles = new ArrayList<>();

	@Test
	public void test() throws Exception {

		Total total = getPageCount();

		List<Article> articles = spidering(total);

		for (Article article : articles) {
			System.out.println(article.toString());
		}

	}

	private List<Article> spidering(Total total) throws Exception {
		// TODO Auto-generated method stub
		int pageCount = total.getPageCount();
		int articleCount = total.getArticleCount();
		List<FutureTask<List<Article>>> futureTasks = new ArrayList<>(pageCount);
		for (int pageIndex = 1; pageIndex <= pageCount; pageIndex++) {
			Spidering spidering = new Spidering(userId, pageIndex + "");
			FutureTask<List<Article>> futureTask = new FutureTask<>(spidering);
			futureTasks.add(futureTask);
			threadPool.submit(futureTask);
		}

		threadPool.shutdown();
		while (!threadPool.isTerminated())
			;
		List<Article> articles = new ArrayList<>(articleCount);
		for (FutureTask<List<Article>> futureTask : futureTasks) {
			articles.addAll(futureTask.get());
		}

		return articles;
	}

	private Total getPageCount() {
		String url = PREFIX + userId + SUFFIX;
		WeakReference<Document> weakDoc = null;
		Document doc = null;
		Total total = null;
		try {
			weakDoc = new WeakReference<Document>(Jsoup.connect(url).header(Header.KEY, Header.VALUE).get());
			doc = weakDoc.get();
			String text = doc.select("#papelist").text();
			StringBuffer sb = new StringBuffer(text);
			int articleCount = Integer.valueOf(sb.substring(0, sb.indexOf("条")));
			int pageCount = Integer.valueOf(sb.substring(sb.indexOf("共") + 1, sb.indexOf("页")));
			total = new Total(articleCount, pageCount);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return total;
	}

}
