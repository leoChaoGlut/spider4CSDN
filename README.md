# spider4CSDN
可根据csdn用户名爬取他名下的所有博客信息

Example
  @Test
	public void test2() {
		Spider spider = new Spider("lc0817");

		spider.begin();

		List<Article> articles = spider.result();

		for (Article article : articles) {
			System.out.println(article.getLink());
		}

		System.out.println(articles.size());
	}
