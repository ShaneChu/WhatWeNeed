package shane.biz.impl;

import java.util.List;

import shane.biz.IArticleBiz;
import shane.dao.IArticleDAO;
import shane.dao.mysql.impl.ArticleDAO;
import shane.vo.article;

public class ArticleBiz implements IArticleBiz {

	public List<article> getArticleByID(int sid) {
		IArticleDAO articledao = new ArticleDAO();
		List<article> articleList = articledao.getArticleByID(sid);
		return articleList;
	}

	public String getUserName(int uid) {
		IArticleDAO articledao = new ArticleDAO();
		String userName = articledao.getUserName(uid);
		return userName;
	}

	public article getArticle(int aid) {
		IArticleDAO articledao = new ArticleDAO();
		article art = articledao.getArticle(aid);
		return art;
	}

	public boolean donew(String headline, String content, int uid, int sid) {
		IArticleDAO articledao = new ArticleDAO();
		boolean flag = articledao.donew(headline, content, uid, sid);
		return flag;
	}

	public boolean updateRevNum(int aid) {
		IArticleDAO articledao = new ArticleDAO();
		boolean flag = articledao.updateRevNum(aid);
		return flag;
	}

	public int getArtNum(int sid) {
		IArticleDAO articledao = new ArticleDAO();
		int num  = articledao.getArtNum(sid);
		return num;
	}

	public boolean doUpdate(int aid, String headline, String content, int isTop, int isOpen, int sid) {
		IArticleDAO articledao = new ArticleDAO();
		boolean flag  = articledao.doUpdate(aid, headline, content, isTop, isOpen, sid);
		return flag;
	}
}
