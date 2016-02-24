package util;

import static util.Const.PREFIX;
import static util.Const.SUFFIX;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import bean.Article;
import bean.Total;
import util.Const.Header;

/**
 * 
 * @author leo
 * @Date 2016年2月24日
 * @Comments 小蜘蛛
 * @usage 实例化该类,然后调用begin(),最后调用result()获取数据.
 */
public class Spider {
	/**
	 * CSDN用户id
	 */
	private String userId;
	/**
	 * 所有的文章会存储在该List里
	 */
	private List<Article> articles;
	/**
	 * 由于爬到到网页后,要对网页进行解析处理,所以CPU压力不大.<br>
	 * 因此线程池的数量可适当调大,或设为缓存线程池,提高CPU利用率.
	 */
	private ExecutorService threadPool;

	public Spider() {
	}

	public Spider(String userId) {
		this.userId = userId;
		threadPool = Executors.newCachedThreadPool();
	}

	/**
	 * 调用该方法,才会开始爬取数据(该方法在没抓取完成前或未出异常前会阻塞)
	 */
	public void begin() {
		Total total = getTotal();
		try {
			if (total == null) {
				throw new Exception("获取文章总数和分页数失败");
			}
			initTask(total);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 初始化多线程爬取
	 * 
	 * @param total
	 * @return
	 * @throws Exception
	 */
	private void initTask(Total total) throws Exception {
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

		sortArticles(articleCount, futureTasks);

	}

	/**
	 * 将获取到的数据写入到 articles
	 * 
	 * @param articleCount
	 * @param futureTasks
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	private void sortArticles(int articleCount, List<FutureTask<List<Article>>> futureTasks)
			throws InterruptedException, ExecutionException {
		articles = new ArrayList<>(articleCount);

		for (FutureTask<List<Article>> futureTask : futureTasks) {
			articles.addAll(futureTask.get());
		}
	}

	/**
	 * 获取文章总数和分页总数
	 * 
	 * @return {@link Total}
	 */
	private Total getTotal() {
		String url = PREFIX + userId + SUFFIX;
		Document doc = null;
		Total total = null;
		try {
			doc = Jsoup.connect(url).header(Header.KEY, Header.VALUE).get();
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

	/**
	 * 小蜘蛛爬完所有数据后调用该方法可返回所有文章信息
	 * 
	 * @return List<{@link Article}>
	 */
	public List<Article> result() {
		return articles;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

}
