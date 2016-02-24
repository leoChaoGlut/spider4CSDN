package test;

import static util.Const.PREFIX;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import bean.Article;
import util.Const.Header;
import util.Spider;

public class MyTest {

	@Test
	public void test2() throws IOException {
		Spider spider = new Spider("lc0817");

		spider.begin();

		List<Article> articles = spider.result();

		int maxTitleLength = 0;
		int maxDescLength = 0;
		for (Article article : articles) {
			if (article.getTitle().length() > maxTitleLength) {
				maxTitleLength = article.getTitle().length();
			}
			if (article.getDescription().length() > maxDescLength) {
				maxDescLength = article.getDescription().length();
			}
		}
		
		System.out.println(maxTitleLength);
		System.out.println(maxDescLength);

		String jsonString = JSON.toJSONString(articles);

		BufferedWriter bw = new BufferedWriter(new FileWriter("c://1.txt"));
		bw.write(jsonString);
		bw.flush();
		bw.close();
		System.out.println(articles.size());
	}

	@Test
	public void test() throws Exception {
		String articleLink = "/lc0817/article/details/50442715";
		String url = PREFIX + articleLink;
		Document doc = Jsoup.connect(url).header(Header.KEY, Header.VALUE).get();
		String string = doc.select("#article_content").toString();
		BufferedWriter bw = new BufferedWriter(new FileWriter("c://1.txt"));
		bw.write(string);
		bw.flush();
		bw.close();
	}

}
