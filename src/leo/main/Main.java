package leo.main;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.alibaba.fastjson.JSON;

import leo.bean.Article;
import leo.bean.Category;
import leo.bean.CategoryAndArticle;
import leo.mapper.IArticleMapper;
import leo.mapper.ICategoryAndArticleMapper;
import leo.mapper.ICategoryMapper;
import leo.spider.CSDNSpider;
import leo.util.SpiderUtil;

/**
 * @Description:
 * @Author leo
 * @Date 2016年5月20日 上午11:24:33
 * @Usage:
 */
public class Main {
	/**
	 * args[0]:properties配置文件路径<br>
	 * args[1]:CSDN userID
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String propertiesPath = "/leo/config/setting.properties";

		if (args.length > 0) {
			propertiesPath = args[0];
		}

		Properties ppt = SpiderUtil.getProperties(propertiesPath);
		String userId = ppt.getProperty("userId");

		CSDNSpider spider = new CSDNSpider(userId);

		try {
			spider.start();

			List<Article> articleList = spider.getArticleList();
			List<Category> categoryList = spider.getCategoryList();
			List<CategoryAndArticle> categoryAndArticleList = spider.getCategoryAndArticleList();

			String articleJsonPath = ppt.getProperty("articleJsonPath");
			String categoryJsonPath = ppt.getProperty("categoryJsonPath");

			SpiderUtil.write(articleJsonPath, JSON.toJSONString(articleList));
			SpiderUtil.write(categoryJsonPath, JSON.toJSONString(categoryList));

			String resource = "leo/config/mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(inputStream);
			SqlSession session = ssf.openSession(true);

			IArticleMapper articleMapper = session.getMapper(IArticleMapper.class);
			ICategoryMapper categoryMapper = session.getMapper(ICategoryMapper.class);
			ICategoryAndArticleMapper caaMapper = session.getMapper(ICategoryAndArticleMapper.class);

			int articleCount = articleMapper.insertList(articleList);
			int categoryCount = categoryMapper.insertList(categoryList);
			int caaCount = caaMapper.insertList(categoryAndArticleList);

			System.out.println(articleCount + "," + categoryCount + "," + caaCount);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
