package leo.main;

import java.util.List;
import java.util.Properties;

import com.alibaba.fastjson.JSON;

import leo.bean.Article;
import leo.bean.Category;
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

			String articleJsonPath = ppt.getProperty("articleJsonPath");
			String categoryJsonPath = ppt.getProperty("categoryJsonPath");

			SpiderUtil.write(articleJsonPath, JSON.toJSONString(articleList));
			SpiderUtil.write(categoryJsonPath, JSON.toJSONString(categoryList));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
