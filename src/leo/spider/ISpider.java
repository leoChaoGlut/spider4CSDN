package leo.spider;

import org.jsoup.nodes.Document;

/**
 * @Description:
 * @Author leo
 * @Date 2016年5月20日 上午11:26:31
 * @Usage:
 */
public interface ISpider {
	Document getDocument(String url);

	void start() throws Exception;
}
