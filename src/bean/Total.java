package bean;

/**
 * 
 * @author leo
 * @Date 2016年2月24日
 * @Comments 存储文章总数和分页总数
 */
public class Total {
	/**
	 * 文章总数
	 */
	private int articleCount;
	/**
	 * 分页总数
	 */
	private int pageCount;

	public Total() {
		// TODO Auto-generated constructor stub
	}

	public Total(int articleCount, int pageCount) {
		this.articleCount = articleCount;
		this.pageCount = pageCount;
	}

	public int getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

}
