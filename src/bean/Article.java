package bean;

public class Article {
	private String title;
	private String description;
	private String postDate;
	private String view;
	private String link;

	public Article(String title, String description, String postDate, String view, String link) {
		this.title = title;
		this.description = description;
		this.postDate = postDate;
		this.view = view;
		this.link = link;
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

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@Override
	public String toString() {
		return "Article [title=" + title + ", description=" + description + ", postDate=" + postDate + ", view=" + view
				+ ", link=" + link + "]";
	}

}
