package test;

import java.util.List;

import org.junit.Test;

import bean.Article;
import util.Spider;

public class MyTest {

	@Test
	public void test2() {
		Spider spider = new Spider("lc0817");

		spider.begin();

		List<Article> articles = spider.result();

		for (Article article : articles) {
			System.out.println(article.toString());
		}

		System.out.println(articles.size());
	}

}
