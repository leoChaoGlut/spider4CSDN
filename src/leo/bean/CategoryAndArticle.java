package leo.bean;

/**
 * @Description:
 * @Author leo
 * @Date 2016年5月23日 上午9:58:51
 * @Usage:
 */
public class CategoryAndArticle {
	private Integer articleId;
	private Integer categoryId;
	private String caregoryName;

	public CategoryAndArticle() {
	}

	public CategoryAndArticle(Integer articleId, String caregoryName) {
		super();
		this.articleId = articleId;
		this.caregoryName = caregoryName;
	}

	public Integer getArticleId() {
		return articleId;
	}

	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCaregoryName() {
		return caregoryName;
	}

	public void setCaregoryName(String caregoryName) {
		this.caregoryName = caregoryName;
	}

	@Override
	public String toString() {
		return "CategoryAndArticle [articleId=" + articleId + ", categoryId=" + categoryId + ", caregoryName="
				+ caregoryName + "]";
	}

}
