package leo.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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
import leo.bean.CategoryAndArticle;
import leo.mapper.IArticleMapper;
import leo.mapper.ICategoryAndArticleMapper;
import leo.mapper.ICategoryMapper;
import leo.spider.CSDNSpider;
import leo.util.SpiderUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

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
		ICategoryAndArticleMapper caaMapper = session.getMapper(ICategoryAndArticleMapper.class);
		List<CategoryAndArticle> list = Arrays.asList(new CategoryAndArticle(1, "c1"), new CategoryAndArticle(1, "c2"),
				new CategoryAndArticle(1, "c1"));
		caaMapper.insertList(list);
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

	@Test
	public void test06() throws Exception {
		String resource = "leo/config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = ssf.openSession(true);

		IArticleMapper articleMapper = session.getMapper(IArticleMapper.class);
		ICategoryMapper categoryMapper = session.getMapper(ICategoryMapper.class);
	}

	@Test
	public void test07() throws Exception {
		String resource = "leo/config/mybatis-config.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		InputStreamReader isr = new InputStreamReader(is);
		isr.close();
		is.available();

		FileInputStream fis = null;
		ObjectInputStream ois = null;
		ois.close();

	}

	@Test
	public void test08() {
		CSDNSpider spider = new CSDNSpider("lc0817");
		String articleContent = spider.getArticleContent(51474157);
		// System.out.println(articleContent);
	}

	public static <T> List<T> reverse(List<T> list) {
		int size = list.size();
		List<T> newList = new ArrayList<>(size);
		for (int i = size - 1; i >= 0; i--) {
			newList.add(list.get(i));
		}
		return newList;
	}

	@Test
	public void test09() throws IOException {
		String resource = "leo/config/mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = ssf.openSession(true);

		String data = leo.util.FileReader.read("C://articleJSON.json");
		List<Article> list = JSON.parseArray(data, Article.class);

		IArticleMapper articleMapper = session.getMapper(IArticleMapper.class);
		int count = articleMapper.insertList(list);
		System.out.println(count);

	}

	@Test
	public void test10() throws IOException {
		JedisPoolConfig cfg = new JedisPoolConfig();
		JedisPool pool = new JedisPool(cfg, "127.0.0.1");
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.set("testkey01", "testvalue01");
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		pool.close();
	}
}
