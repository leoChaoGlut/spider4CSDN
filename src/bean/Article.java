package bean;

/**
 * 
 * @author leo
 * @Date 2016年2月24日
 * @Comments 存储文章信息
 */
public class Article {
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 摘要
	 */
	private String description;
	/**
	 * 发文日期
	 */
	private String postDate;
	/**
	 * 阅读量
	 */
	private String view;
	/**
	 * 文章详情链接
	 */
	private String articleCode;

	private String content;

	public Article() {
		// TODO Auto-generated constructor stub
	}

	public Article(String title, String description, String postDate, String view, String articleCode) {
		this.title = title;
		this.description = description;
		this.postDate = postDate;
		this.view = view;
		this.articleCode = articleCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getArticleCode() {
		return articleCode;
	}

	public void setArticleCode(String articleCode) {
		this.articleCode = articleCode;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Article [title=" + title + ", description=" + description + ", postDate=" + postDate + ", view=" + view
				+ ", articleCode=" + articleCode + ", content=" + content + "]";
	}

}
