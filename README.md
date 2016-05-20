# spider4CSDN
可根据csdn用户名爬取他名下的所有博客信息
用法:
	
		String userId="lc0817";
		
		Spider spider = new Spider(userId);

		spider.begin();

		List<Article> articles = spider.result();

		for (Article article : articles) {
			System.out.println(article.toString());//文章信息,包括标题,摘要,文章详情链接等.
		}

		System.out.println(articles.size());//文章总数
	
