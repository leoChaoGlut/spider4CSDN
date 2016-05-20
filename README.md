Usage:
String csdnUserId="lc0817"<br>
Spider s = new Spider(csdnUserId);<br>
s.start();//开始爬取<br>
List<Article> articleList = s.getArticleList();//获取文章信息<br>
List<Category> categoryList = s.getCategoryList();//获取目录信息<br>
