package leo.bean;

/**
 * @Description:
 * @Author leo
 * @Date 2016年5月19日 上午9:49:02
 * @Usage:
 */
public class Category {
	private String name;
	private Integer articleCount;

	public Category() {
	}

	public Category(String name, Integer articleCount) {
		this.name = name;
		this.articleCount = articleCount;
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
		return "Category [name=" + name + ", articleCount=" + articleCount + "]";
	}

}
