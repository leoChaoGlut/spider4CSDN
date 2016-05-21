package leo.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import leo.bean.Article;
import leo.bean.Category;
import leo.mapper.IArticleMapper;
import leo.mapper.ICategoryMapper;
import leo.spider.CSDNSpider;
import leo.util.SpiderUtil;

/**
 * @Description:
 * @Author leo
 * @Date 2016年5月19日 上午10:11:35
 * @Usage:
 */
public class MyTest {

	@Test
	public void test01() {
		Properties ppt = SpiderUtil.getProperties("/leo/config/setting.properties");
		String articleJsonPath = ppt.getProperty("articleJsonPath");
		String categoryJsonPath = ppt.getProperty("categoryJsonPath");
		System.out.println(articleJsonPath + "========" + categoryJsonPath);
	}

	@Test
	public void test02() throws IOException {
		CSDNSpider s = new CSDNSpider("lc0817");
		try {
			long begin = System.nanoTime();
			System.out.println("begin");
			s.start();
			System.out.println("end");
			long end = System.nanoTime();
			System.out.println(BigDecimal.valueOf(end - begin, 9));
			List<Category> categoryList = s.getCategoryList();
			List<Article> articleList = s.getArticleList();
			String categoryJSON = JSON.toJSONString(categoryList);
			String articleJSON = JSON.toJSONString(articleList);
			FileWriter fw1 = new FileWriter("C://categoryJSON");
			FileWriter fw2 = new FileWriter("C://articleJSON");
			BufferedWriter bw1 = new BufferedWriter(fw1);
			BufferedWriter bw2 = new BufferedWriter(fw2);
			bw1.write(categoryJSON);
			bw1.flush();

			bw2.write(articleJSON);
			bw2.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void test03() throws Exception {
		FileReader fr = new FileReader("c://articleJSON");
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		List<Article> articles = JSON.parseArray(sb.toString(), Article.class);
		int size = articles.size();
		System.out.println(size);
	}

	@Test
	public void test04() throws Exception {
		String resource = "leo/config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = ssf.openSession(true);
		IArticleMapper articleMapper = session.getMapper(IArticleMapper.class);
		FileReader fr = new FileReader("C://articleJSON.json");
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		List<Article> list = JSON.parseArray(sb.toString(), Article.class);
		int count = articleMapper.insertList(list);
		System.out.println(count);
	}

	@Test
	public void test05() throws Exception {
		String resource = "leo/config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = ssf.openSession(true);
		ICategoryMapper caregoryMapper = session.getMapper(ICategoryMapper.class);
		FileReader fr = new FileReader("C://categoryJSON.json");
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		StringBuilder sb = new StringBuilder();
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		System.out.println(sb.toString());
		List<Category> list = JSON.parseArray(sb.toString(), Category.class);
		System.out.println(list);
		int count = caregoryMapper.insertList(list);
		System.out.println(count);
	}
}
