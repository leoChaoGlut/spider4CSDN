Usage:
String csdnUserId="lc0817"
Spider s = new Spider(csdnUserId);
s.start();//开始爬取
List<Article> articleList = s.getArticleList();//获取文章信息
List<Category> categoryList = s.getCategoryList();//获取目录信息
