package leo.bean;

/**
 * @Description:
 * @Author leo
 * @Date 2016年5月19日 上午9:47:58
 * @Usage:
 */
public class Article {
	private Integer id;
	private String title;
	private String description;
	private String postDate;
	private Integer viewCount;
	private String content;

	public Article() {
	}

	public Article(Integer id, String title, String description, String postDate, Integer viewCount, String content) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.postDate = postDate;
		this.viewCount = viewCount;
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getViewCount() {
		return viewCount;
	}

	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", description=" + description + ", postDate=" + postDate
				+ ", viewCount=" + viewCount + ", content=" + content + "]";
	}

}
