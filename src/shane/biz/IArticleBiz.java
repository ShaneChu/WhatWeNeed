package shane.biz;

import java.util.List;

import shane.vo.article;

public interface IArticleBiz {

	public List<article> getArticleByID(int sid);
	public String getUserName(int uid) ;
	public article getArticle(int aid);
	public boolean donew(String headline, String content, int uid, int sid);
	public boolean updateRevNum(int aid);
	public int getArtNum(int sid);
	public boolean doUpdate(int aid, String headline, String content, int isTop, int isOpen, int sid);
	
}
