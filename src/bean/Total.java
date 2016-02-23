package bean;

public class Total {
	private int articleCount;
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
