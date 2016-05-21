package leo.bean;

/**
 * @Description:
 * @Author leo
 * @Date 2016年5月19日 上午9:49:02
 * @Usage:
 */
public class Category {
	private String id;
	private String name;
	private Integer articleCount;

	public Category() {
	}

	public Category(String id, String name, Integer articleCount) {
		this.id = id;
		this.name = name;
		this.articleCount = articleCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", articleCount=" + articleCount + "]";
	}

}
